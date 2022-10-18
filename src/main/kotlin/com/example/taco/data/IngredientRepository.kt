package com.example.taco.data

import com.example.taco.Ingredient
import org.springframework.data.repository.CrudRepository


interface IngredientRepository : CrudRepository<Ingredient, String>