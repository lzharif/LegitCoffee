package com.radicalgoodsyndicate.legitcoffee.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.radicalgoodsyndicate.legitcoffee.R

class SplashActivity : AppCompatActivity() {

    var loadingTime : Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            kotlin.run {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, loadingTime.toLong())
    }
}