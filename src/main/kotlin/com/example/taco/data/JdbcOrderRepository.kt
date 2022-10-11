package com.example.taco.data

import com.example.taco.Ingredient
import com.example.taco.IngredientRef
import com.example.taco.Taco
import com.example.taco.TacoOrder
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Types
import java.util.*


@Repository
class JdbcOrderRepository: OrderRepository {
    private val jdbcOperations: JdbcOperations
    constructor(jdbcOperations: JdbcOperations)
    {
        this.jdbcOperations = jdbcOperations
    }

    @Transactional
    override fun save(order: TacoOrder): TacoOrder {
        val pscf = PreparedStatementCreatorFactory("insert into Taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) "
        +"values (?,?,?,?,?,?,?,?,?)",
        Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
        Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
        Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP)

        pscf.setReturnGeneratedKeys(true)

        order.placedAt= Date()
        val psc: PreparedStatementCreator = pscf.newPreparedStatementCreator(
            Arrays.asList(
                order.deliveryName,
                order.deliveryStreet,
                order.deliveryCity,
                order.deliveryState,
                order.deliveryZip,
                order.ccNumber,
                order.ccExpiration,
                order.ccCVV,
                order.placedAt))

        val keyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)
        val orderId = keyHolder.key!!.toLong()
        order.id = orderId
        val tacos: List<Taco> = order.tacos
        var i = 0
        for (taco in tacos) {
            saveTaco(orderId, i++, taco)
        }
        return order
    }


    fun saveTaco(orderId:Long, orderKey:Int, taco:Taco) : Long
    {
        val pscf = PreparedStatementCreatorFactory(
            "insert into Taco (name, created_at, taco_order, taco_order_key) values (?,?,?,?)",
            Types.VARCHAR,Types.TIMESTAMP, Types.LONGVARCHAR, Types.LONGVARCHAR
        )

        pscf.setReturnGeneratedKeys(true)
        val psc = pscf.newPreparedStatementCreator(
            Arrays.asList(
                taco.name,
                taco.createdAt,
                orderId,
                orderKey
            )
        )

        val keyHolder:GeneratedKeyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)
        var tacoId = keyHolder.key
        taco.id = tacoId as Long

        saveIngredientRefs(tacoId, taco.ingredients)
        return tacoId as Long
    }

    fun saveIngredientRefs(tacoId: Long, ingredientRefs: List<IngredientRef>)
    {
        var key:Int = 0
        for (ingredientRef in ingredientRefs)
        {
            jdbcOperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key) values (?,?,?)", ingredientRef.ingredient, tacoId, key++)
        }
    }
}