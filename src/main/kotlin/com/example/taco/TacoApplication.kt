package com.example.taco

import com.example.taco.data.IngredientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class TacoCloudApplication{

	@Bean
	fun dataLoader(repo: IngredientRepository): CommandLineRunner {
		return CommandLineRunner {
			repo.deleteAll() // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
			repo.save(Ingredient("FLTO", "Flour Tortilla", Type.WRAP))
			repo.save(Ingredient("COTO", "Corn Tortilla", Type.WRAP))
			repo.save(Ingredient("GRBF", "Ground Beef", Type.PROTEIN))
			repo.save(Ingredient("CARN", "Carnitas", Type.PROTEIN))
			repo.save(Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES))
			repo.save(Ingredient("LETC", "Lettuce", Type.VEGGIES))
			repo.save(Ingredient("CHED", "Cheddar", Type.CHEESE))
			repo.save(Ingredient("JACK", "Monterrey Jack", Type.CHEESE))
			repo.save(Ingredient("SLSA", "Salsa", Type.SAUCE))
			repo.save(Ingredient("SRCR", "Sour Cream", Type.SAUCE))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<TacoCloudApplication>(*args)
	//SpringApplication.run(TacoCloudApplication::class.java, *args)
}
