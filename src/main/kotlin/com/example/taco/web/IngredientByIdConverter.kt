package com.example.taco.web

import com.example.taco.Ingredient
import com.example.taco.data.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


@Component
class IngredientByIdConverter @Autowired constructor(private val ingredientRepo: IngredientRepository) :
    Converter<String, Ingredient> {
    override fun convert(id: String): Ingredient {
        return ingredientRepo.findById(id).orElse(null)
    }
}