package com.example.taco

import lombok.Data
import java.util.*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Data
@Entity
class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null
    var createdAt = Date()

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    var ingredients: MutableList<Ingredient> = ArrayList()
    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }
}