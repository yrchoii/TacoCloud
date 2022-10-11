package com.example.taco.data

import com.example.taco.Ingredient
import java.util.*

interface IngredientRepository  {
    fun findAll(): Iterable<Ingredient>
    fun findById(id: String): Ingredient
    fun save(ingredient: Ingredient): Ingredient
}