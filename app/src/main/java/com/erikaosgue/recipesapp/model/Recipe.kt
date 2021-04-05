package com.erikaosgue.recipesapp.model

// Diferrent types of creating the class Recipe

//class Recipe(var title: String, var description: String, var image: String)

//data class Recipe(var title: String, var description: String, var image: String)

//class Recipe {
//    var title: String? = null
//    var description: String? = null
//    var image: String? = null
//}

class Recipe {
    lateinit var title: String
    lateinit var ingredients: String
    lateinit var image: String
    lateinit var link: String

    override fun toString(): String {
        return "Recipe(title=$title, ingredients=$ingredients, image=$image, link=$link"
    }
}


