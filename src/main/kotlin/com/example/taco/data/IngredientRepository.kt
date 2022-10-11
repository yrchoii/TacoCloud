package com.example.taco.data

import com.example.taco.Ingredient
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import java.util.*


interface IngredientRepository : CrudRepository<Ingredient, String> {
//    fun findAll(): Iterable<Ingredient?>?
//    fun findById(id: String?): Optional<Ingredient?>?
//    fun save(ingredient: Ingredient?): Ingredient?
}