package com.example.blackjack

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), View.OnClickListener  {
    private var balance: Int = 2000
    private var bet: Int = 100
    private var enemy1: Card = Card(1)
    private var enemy2: Card = Card(1)
    private var my1: Card = Card(1)
    private var my2: Card = Card(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val buttonUp = findViewById<Button>(R.id.buttonUp)
        buttonUp.setOnClickListener(this)
        val buttonDown = findViewById<Button>(R.id.buttonDown)
        buttonDown.setOnClickListener(this)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        buttonOpen.setOnClickListener(this)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        buttonNext.setOnClickListener(this)

        nextGame()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonUp -> onClickButtonUp()
            R.id.buttonDown -> onClickButtonDown()
            R.id.buttonOpen -> onClickButtonOpen()
            R.id.buttonNext -> onClickButtonNext()
        }
    }
    private fun onClickButtonNext() {
        nextGame()
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
        if (bet < 100) {
            bet = 100
        }
        updateText(R.id.textViewBet, bet.toString())
    }

    private fun onClickButtonOpen() {
        updateText(R.id.textViewEnemy2, enemy2.getCardName())

        var enemyTotal :Int = calcDifference(enemy1, enemy2)
        var myTotal :Int = calcDifference(my1, my2)

        if (enemyTotal < 0) {
            enemyTotal = 21
        }
        if (myTotal < 0) {
            myTotal = 21
        }
        if (myTotal > enemyTotal) {
            updateText(R.id.textViewResult, "Lose")
            balance -= bet
            updateText(R.id.textViewBalance, balance.toString())
        } else if (myTotal < enemyTotal) {
            updateText(R.id.textViewResult, "Win")
            balance += bet
            updateText(R.id.textViewBalance, balance.toString())
        } else {
            updateText(R.id.textViewResult, "draw")
        }
        updateVisible(R.id.buttonNext, View.VISIBLE)
        updateVisible(R.id.buttonOpen, View.INVISIBLE)
    }



    private fun updateText(id: Int, newText: String) {
        val view = findViewById<TextView>(id)
        view.text = newText
    }
    private fun nextGame() {
        do {
            enemy1 = Card()
            enemy2 = Card()
            my1 = Card()
            my2 = Card()
        }while(enemy1.isSame(enemy2) || enemy1.isSame(my1) || enemy1.isSame(my2)
            || enemy2.isSame(my1) || enemy2.isSame(my2) || my1.isSame(my2))
        updateText(R.id.textViewEnemy1, enemy1.getCardName())
        updateText(R.id.textViewEnemy2, "❓❓")
        updateText(R.id.textViewMy1, my1.getCardName())
        updateText(R.id.textViewMy2, my2.getCardName())

        updateVisible(R.id.buttonNext, View.INVISIBLE)
        updateVisible(R.id.buttonOpen, View.VISIBLE)

    }

    private fun updateVisible(id: Int, visible: Int) {
        val view = findViewById<View>(id)
        view.visibility = visible
    }

    private fun calcDifference (card1: Card, card2: Card): Int {
        var diff = 21 - (card1.getBlackJackNumber() + card2.getBlackJackNumber())

        // 1st A
        if (diff < 0 && (card1.isA() || card2.isA())) {
            diff += 10
        }

        // 2nd A
        if (diff < 0 && (card1.isA() && card2.isA())) {
            diff += 10
        }

        return diff
    }
}


