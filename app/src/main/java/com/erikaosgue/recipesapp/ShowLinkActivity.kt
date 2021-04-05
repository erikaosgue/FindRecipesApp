package com.erikaosgue.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import com.erikaosgue.recipesapp.databinding.ActivityShowLinkBinding

class ShowLinkActivity : AppCompatActivity() {

  var webView: WebView? = null


  lateinit var activityShowLinkBinding: ActivityShowLinkBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityShowLinkBinding = ActivityShowLinkBinding.inflate(layoutInflater)
    setContentView(activityShowLinkBinding.root)

    Toast.makeText(this, "*onCreate", Toast.LENGTH_LONG).show()

    // To do:
    // How to check when the press back button is from the url and make it go back again
    // Read all the images http without hardcoding them in security file

    val extras = intent.extras
    if (extras != null) {

      val link = extras.get("link")
      Log.d("link", "$link")

      webView = activityShowLinkBinding.webViewId
      webView?.loadUrl(link.toString())

    } else {
      super.onBackPressed()
      Toast.makeText(this, "this is comming back from website", Toast.LENGTH_LONG).show()

    }
  }

  override fun onStart() {

    Toast.makeText(this, "*onStart", Toast.LENGTH_LONG).show()
    super.onBackPressed()
    super.onStart()
  }

  override fun onResume() {
    Toast.makeText(this, "*onResume", Toast.LENGTH_LONG).show()
    super.onResume()
  }

  override fun onPause() {
    Toast.makeText(this, "*onPause", Toast.LENGTH_LONG).show()
    super.onPause()
  }
}
