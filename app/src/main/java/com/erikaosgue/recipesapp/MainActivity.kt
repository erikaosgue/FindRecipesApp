package com.erikaosgue.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erikaosgue.recipesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	lateinit var  activityMainBinding: ActivityMainBinding
 	override fun onCreate(savedInstanceState: Bundle?) {
 		super.onCreate(savedInstanceState)
 		activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
 		setContentView(activityMainBinding.root)

		val button = activityMainBinding.buttonGoToListRecipesActivityId

		button.setOnClickListener {
			val ingredients = activityMainBinding.ingredientsTextId.text.toString()
			val searchTerm = activityMainBinding.searchTermId.text.toString()
			val parameters = checkParameters(ingredients, searchTerm)
			if (parameters != "invalid") {

				val intent = Intent(this, RecipeList::class.java).apply {
					putExtra("parameters", parameters)
				}
				startActivity(intent)
			}
		}
 	}
	private fun checkParameters(ingredients: String, searchTerm: String): String {

		val myRegex = "^[a-zA-Z, ]*$"
		var parameters = ""

		if (ingredients.isNotEmpty() && searchTerm.isNotEmpty()) {
			if (searchTerm.matches(myRegex.toRegex()) && ingredients.matches("^[a-zA-Z]*$".toRegex()))
					parameters = "?i=$ingredients&q=$searchTerm"
			else {
				Toast.makeText(this, "Enter only Alpha chars", Toast.LENGTH_LONG).show()
				parameters = "invalid"
			}
		} else if (searchTerm.isEmpty() && ingredients.isNotEmpty()) {
			if (ingredients.matches(myRegex.toRegex()))
				parameters = "?i=$ingredients"
			else {
				Toast.makeText(this, "Enter only Alpha chars", Toast.LENGTH_LONG).show()
				parameters = "invalid"
			}
		} else if (searchTerm.isNotEmpty() && ingredients.isEmpty()) {
				if (searchTerm.matches(myRegex.toRegex()))
					parameters = "?q=$searchTerm"
				else {
					Toast.makeText(this, "Enter only Alpha chars", Toast.LENGTH_LONG).show()
					parameters = "invalid"
				}
		}
        return parameters
	}


}