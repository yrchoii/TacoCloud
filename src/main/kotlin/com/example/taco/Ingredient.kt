package com.example.taco

import lombok.Data

//@Data
//class Ingredient {
//    private lateinit var _id:String
//    private lateinit var _name:String
//    private lateinit var _type:Type
//
//    constructor(id:String, name:String, type: Type)
//    {
//        _id = id
//        _name = name
//        _type = type
//    }
//
//    val id : String
//        get() {
//            return _id
//        }
//    val name : String
//        get() {
//            return _name
//        }
//    val type : Type
//        get() {
//            return _type
//        }
//
//    enum class Type{
//        WRAP,
//        PROTEIN,
//        VEGGIES,
//        CHEESE,
//        SAUCE
//    }
//}
data class Ingredient(val id:String, val name:String, val type:Type)
{
    enum class Type{
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}


