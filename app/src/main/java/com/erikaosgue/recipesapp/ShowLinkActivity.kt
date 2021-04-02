package com.erikaosgue.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import com.erikaosgue.recipesapp.databinding.ActivityShowLinkBinding

class ShowLinkActivity : AppCompatActivity() {
  var oneTime: Int? = null
  var webView: WebView?= null
  lateinit var  activityShowLinkBinding: ActivityShowLinkBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityShowLinkBinding = ActivityShowLinkBinding.inflate(layoutInflater)
    setContentView(activityShowLinkBinding.root)

    Toast.makeText(this, "*onCreate", Toast.LENGTH_LONG).show()

    oneTime = 0

    // To do:
    // How to check when the press back button is from the url an makeit go back again
    // Read the images

    val extras = intent.extras
    if (extras != null) {

      val link = extras.get("link")
      Log.d("link", "$link")

      webView =  activityShowLinkBinding.webViewId
      webView?.loadUrl(link.toString())
      oneTime = oneTime?.plus(1)

    }
    else {
      super.onBackPressed()
      Toast.makeText(this, "this is comming back from website", Toast.LENGTH_LONG).show()

    }
  }

  override fun onStart() {
    Toast.makeText(this, "*onStart: count $oneTime", Toast.LENGTH_LONG).show()

    if (oneTime!! == 1) {
      super.onBackPressed()
      Toast.makeText(this, "*onStart", Toast.LENGTH_LONG).show()
    }
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

  var variable: Int = 0
  override fun onBackPressed() {
    if(webView!!.isFocused && webView!!.canGoBack()) {
      Toast.makeText(this, "Herer =>>> comming back from website", Toast.LENGTH_LONG).show()

      webView!!.goBack();// if there is previous page open it
//    }

//    if (variable == 1) {
//      Toast.makeText(this, "this is comming back from website", Toast.LENGTH_LONG).show()
//      super.onBackPressed()
    }else {
      Toast.makeText(this, "Press Back again if you Want to leave", Toast.LENGTH_LONG).show()
//      variable = 1
      super.onBackPressed()
      finish();

    }
  }
}