package com.example.taco.data

import com.example.taco.Ingredient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


@Repository
class JdbcIngredientRepository: IngredientRepository {

    private var _jdbcTemplate: JdbcTemplate
    constructor(jdbcTemplate: JdbcTemplate)
    {
        this._jdbcTemplate = jdbcTemplate
    }
    override fun findAll(): Iterable<Ingredient> {
        return _jdbcTemplate.query(
            "select id, name, type from Ingredient",
            this::mapRowToIngredient
        )
    }

    override fun findById(id: String): Ingredient {
        return _jdbcTemplate.queryForObject(
            "select id, name, type from Ingredient where id=?",
            RowMapper { rs, rowNum ->
                Ingredient(
                    rs.getString("id"),
                    rs.getString("name"),
                    Ingredient.Type.valueOf(rs.getString("type"))
                )
            }, id
        )!!
    }

    override fun save(ingredient: Ingredient): Ingredient {
        _jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?,?,?)",
            ingredient.id,
            ingredient.name,
            ingredient.type.toString()
        )
        return ingredient
    }

    @Throws(SQLException::class)
    private fun mapRowToIngredient(row: ResultSet, rowNum: Int): Ingredient? {
        return Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type"))
        )
    }
}