package com.example.taco.web

import com.example.taco.Ingredient
import com.example.taco.Taco
import com.example.taco.TacoOrder
import com.example.taco.data.IngredientRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
abstract class DesignTacoController {
    private var ingredientRepo: IngredientRepository

    @Autowired
    constructor(
        ingredientRepo: IngredientRepository
    ) {
        this.ingredientRepo = ingredientRepo
    }
    @ModelAttribute
    fun addIngredientsToModel(model: Model){

//        var ingredients:List<Ingredient> = Arrays.asList(
//            Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
//            Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP),
//            Ingredient("GRBF","Ground Beef", Ingredient.Type.PROTEIN),
//            Ingredient("CARN","Carnitas", Ingredient.Type.PROTEIN),
//            Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES),
//            Ingredient("LETC","Lettuce", Ingredient.Type.VEGGIES),
//            Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE),
//            Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE),
//            Ingredient("SLSA","Salsa", Ingredient.Type.SAUCE),
//            Ingredient("SRCR","Sour Cream", Ingredient.Type.SAUCE),
//        )

        var ingredients : Iterable<Ingredient> = ingredientRepo.findAll()

        var types =  Ingredient.Type.values();
        for( type in types)
        {
            model.addAttribute(type.toString().lowercase(), filterByType(ingredients as List<Ingredient>, type))
        }
    }

    @ModelAttribute(name="tacoOrder")
    fun order():TacoOrder
    {
        return TacoOrder()
    }
    @ModelAttribute(name="taco")
    fun taco(): Taco
    {
        return Taco()
    }

    @GetMapping
    fun showDesignForm():String
    {
        return "design"
    }

    @PostMapping
    fun processTaco(taco: Taco?, @ModelAttribute tacoOrder: TacoOrder
    ): String? {
        tacoOrder.addTaco(taco!!)
        return "redirect:/orders/current"
    }

    fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type):Iterable<Ingredient>
    {
        return ingredients.stream()
            .filter { x->x.type.equals(type)}
            .collect(Collectors.toList())
    }
}