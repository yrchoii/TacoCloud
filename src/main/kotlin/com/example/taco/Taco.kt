package com.example.taco

import lombok.Data
import java.util.*


@Data
class Taco (

    )
{
    var id: Long = 0
    var name:String =""
    var ingredients: List<IngredientRef> = listOfNotNull()
    var createdAt:Date = Date()

    fun addIngredient(taco: Ingredient) {
        ingredients += IngredientRef(taco.id)
    }
}