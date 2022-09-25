package com.example.blackjack

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), View.OnClickListener  {
    var balance : Int = 2000
    var bet : Int = 0
    var enemy1 :Int = 0
    var enemy2 :Int = 0
    var my1 :Int = 0
    var my2 :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val buttonUp = findViewById<Button>(R.id.buttonUp)
        buttonUp.setOnClickListener(this)
        val buttonDown = findViewById<Button>(R.id.buttonDown)
        buttonDown.setOnClickListener(this)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        buttonOpen.setOnClickListener(this)

        val range = (1..52)
        enemy1 = range.random()
        enemy2 = range.random()
        my1 = range.random()
        my2 = range.random()
        updateText(R.id.textViewEnemy1, getCardName(enemy1))
        updateText(R.id.textViewEnemy2, getCardName(enemy2))
        updateText(R.id.textViewMy1, getCardName(my1))
        updateText(R.id.textViewMy2, getCardName(my2))


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonUp -> onClickButtonUp()
            R.id.buttonDown -> onClickButtonDown()
            R.id.buttonOpen -> onClickButtonOpen()
        }
    }

    private fun onClickButtonUp() {
        bet += 100
        if (bet > balance) {
            bet = balance
        }

        updateText(R.id.textViewBet, bet.toString())
    }

    private fun onClickButtonDown() {
        bet -= 100
        if (bet < 0) {
            bet = 0
        }
        updateText(R.id.textViewBet, bet.toString())
    }

    private fun onClickButtonOpen() {

    }

    private fun getCardName(id: Int): String {

        var number :Int = id % 13
        var mark :String = when ( id/13 ) {
            1 -> "♥️"
            2 -> "♦️"
            3 -> "♠️"
            0 -> "♣️"
            else -> "-"
        }
        return mark + number.toString()

    }

    private fun updateText(id: Int, newText: String) {
        val view = findViewById<TextView>(id)
        view.text = newText
    }
}
