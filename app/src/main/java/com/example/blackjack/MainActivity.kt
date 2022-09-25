package com.example.blackjack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.text = "clicked"
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)

    }
}