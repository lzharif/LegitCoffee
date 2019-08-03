package com.radicalgoodsyndicate.legitcoffee.Activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.radicalgoodsyndicate.legitcoffee.R

class AboutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_github_lz -> {
                val browserIntent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/lzharif"));
                startActivity(browserIntent);
            }
            R.id.btn_linkedin_lz -> {
                val browserIntent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/lzharif/"));
                startActivity(browserIntent);
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val toolbar : Toolbar = findViewById(R.id.tool_bar)
        val btnGithub : Button = findViewById(R.id.btn_github_lz)
        val btnLinkedIn : Button = findViewById(R.id.btn_linkedin_lz)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Kreator Legit Coffee")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnGithub.setOnClickListener(this)
        btnLinkedIn.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }
}
