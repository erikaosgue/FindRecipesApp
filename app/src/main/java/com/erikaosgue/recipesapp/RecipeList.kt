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

  var recipeList: ArrayList<Recipe>? = null
  var adapter: RecipeAdapter? = null

  var requestQueue: RequestQueue? = null
    var mContext: Context? = null
    private var recyclerView: RecyclerView? = null

    lateinit var activityRecipeListBinding: ActivityRecipeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecipeListBinding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(activityRecipeListBinding.root)


        recipeList = ArrayList<Recipe>()
        recyclerView = activityRecipeListBinding.recyclerViewId


      //make the request and receives a List
        requestQueue = Volley.newRequestQueue(this)

        //Receive the information from main Activity
        val extras = intent.extras
        val parameters = extras?.get("parameters")

        val url = "http://www.recipepuppy.com/api/$parameters"
        Log.d("Url==>", "$url")

        getRecipeList(url)
//        Toast.makeText(this, "List : $recipesList", Toast.LENGTH_LONG).show()
//        if (recipesList != null) {
//            val adapter = RecipeAdapter(this, recipesList)
//            recyclerView = activityRecipeListBinding.recyclerViewId
//            recyclerView?.layoutManager = LinearLayoutManager(this)
//            recyclerView?.adapter = adapter
//
//        }else {
//            Toast.makeText(this, "Can't do the recyclerView", Toast.LENGTH_LONG).show()
//        }


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

//                    adapter = RecipeAdapter(this, recipeList!!)
//                    recyclerView?.layoutManager = LinearLayoutManager(this)
//                    recyclerView?.adapter = adapter
                }
//                adapter?.notifyDataSetChanged()

                Log.d("Recipe List==>", "$recipeList")
                adapter = RecipeAdapter(this, recipeList!!)
                recyclerView?.layoutManager = LinearLayoutManager(this)
                recyclerView?.adapter = adapter
            },

            { error: VolleyError ->
                Log.d("Error", "Can't make a request $error")
            }
        )
        requestQueue?.add(jsonObjectRequest)
    }
}