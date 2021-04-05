package com.erikaosgue.recipesapp.data

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erikaosgue.recipesapp.R
import com.erikaosgue.recipesapp.ShowLinkActivity
import com.erikaosgue.recipesapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context, private val listRecipes: ArrayList<Recipe>):
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    // Class viewHolder, that
    inner class RecipeViewHolder(var view: View): RecyclerView.ViewHolder(view) {

        // Instantiating the views from the view (row_list)
        // inflated that will represent our viewHolder
        private val titleRecipe = view.findViewById<TextView>(R.id.titleRecipeId)
        private val imageRecipe = view.findViewById<ImageView>(R.id.imageRecipeId)
        private val ingredientsRecipe = view.findViewById<TextView>(R.id.ingredientsRecipeId)
        private val moreInfoButton = view.findViewById<Button>(R.id.moreInfoButton)




        // bindData receives an Object of type Recipe
        fun bindData(recipe: Recipe) {

            titleRecipe.text = recipe.title
            ingredientsRecipe.text = recipe.ingredients

            //Adding the image using the Picasso Library
            if (recipe.image.isNotEmpty()) {
                Log.d("1 **Image is Empty: ==>", "${recipe.image.isEmpty()}")
                Picasso.get()
                    .load(recipe.image)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .resize(600, 500)
                    .into(imageRecipe)
            } else {
                Log.d("2 ==Image is Empty: ==>", "${recipe.image.isEmpty()}")
                Picasso.get()
                    .load(android.R.drawable.ic_menu_report_image)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .resize(600, 500)
                    .into(imageRecipe)

            }


            //Using Glide to load the Picture
            /*
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            // myViewLayout id the context of the view that will
            // contain the image
            Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(recipe.image)
                .into(imageRecipe)

             */



            moreInfoButton.setOnClickListener {
                val intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.link)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        //Inflates the viewHolder (the hole row_list.xml file) in one Object
        val viewInflated = LayoutInflater.from(parent.context).inflate(R.layout.row_list , parent, false)
        return RecipeViewHolder(viewInflated)
    }

    override fun onBindViewHolder(recipeViewHolder: RecipeViewHolder, position: Int) {
        recipeViewHolder.bindData(listRecipes[position])
    }

    override fun getItemCount(): Int {
        return listRecipes.size
    }

}

