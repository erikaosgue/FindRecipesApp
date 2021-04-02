package com.erikaosgue.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.erikaosgue.recipesapp.databinding.ActivityShowLinkBinding

class ShowLinkActivity : AppCompatActivity() {
  lateinit var  activityShowLinkBinding: ActivityShowLinkBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityShowLinkBinding = ActivityShowLinkBinding.inflate(layoutInflater)
    setContentView(activityShowLinkBinding.root)



    val extras = intent.extras

    if (extras != null) {
      val link = extras.get("link")
      Log.d("link", "$link")

      activityShowLinkBinding.webViewId.loadUrl(link.toString())

    }
  }
}