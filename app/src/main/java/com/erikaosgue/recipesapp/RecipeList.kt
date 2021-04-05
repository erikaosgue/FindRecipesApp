package com.erikaosgue.recipesapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.erikaosgue.recipesapp.data.RecipeAdapter
import com.erikaosgue.recipesapp.databinding.ActivityRecipeListBinding
import com.erikaosgue.recipesapp.model.Recipe
import org.json.JSONArray
import org.json.JSONObject

class RecipeList : AppCompatActivity() {


  var requestQueue: RequestQueue? = null

  private var recyclerView: RecyclerView? = null
  var adapter: RecipeAdapter? = null
  var recipeList: ArrayList<Recipe>? = null

    lateinit var activityRecipeListBinding: ActivityRecipeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecipeListBinding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(activityRecipeListBinding.root)


        recyclerView = activityRecipeListBinding.recyclerViewId
        recipeList = ArrayList()


        //make the request and receives a List
        requestQueue = Volley.newRequestQueue(this)

        //Receive the information from main Activity
        val extras = intent.extras
        val parameters = extras?.get("parameters")

        val url = "http://www.recipepuppy.com/api/$parameters"


        getRecipeList(url)
    }

    //Make the request
    private fun getRecipeList(url: String){


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response: JSONObject ->

                //resultArray is a List of Objects
                val resultsArray = response["results"] as JSONArray

                // Loop through a list of recipes?
                for (i in 0 until resultsArray.length()) {
                    val resultOBJ = resultsArray[i] as JSONObject

                    val recipe = Recipe()
                    recipe.title = resultOBJ["title"] as String
                    recipe.ingredients = resultOBJ["ingredients"] as String
                    recipe.image = resultOBJ["thumbnail"] as String
                    recipe.link = resultOBJ["href"] as String
                    recipeList?.add(recipe)

                    //adapter = RecipeAdapter(this, recipeList!!)
                    //recyclerView?.layoutManager = LinearLayoutManager(this)
                    //recyclerView?.adapter = adapter
                }
                //adapter?.notifyDataSetChanged()

                if (recipeList!!.size > 0) {
                    adapter = RecipeAdapter(this, recipeList!!)
                    recyclerView?.layoutManager = LinearLayoutManager(this)
                    recyclerView?.adapter = adapter

                }
                else {
                    Toast.makeText(this, "We can't find recipes con that information\nAdd different data!", Toast.LENGTH_LONG).show()
                    super.onBackPressed()
                }
            },

            { error: VolleyError ->
                Log.d("Error", "Can't make a request $error")
            }
        )
        requestQueue?.add(jsonObjectRequest)
    }
}