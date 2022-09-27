package com.example.blackjack

class Card {
    private val id: Int

    constructor() {
        this.id = (1..52).random();
    }

    constructor(id: Int) {
        this.id = id;
    }

    public fun getCardName(): String {
        var number :Int = id % 13
        if(number == 0) {
            number = 13
        }

        var mark :String = when ((id - 1)/13) {
            1 -> "♥️"
            2 -> "♦️"
            3 -> "♠️"
            0 -> "♣️"
            else -> "-"
        }

        if (number == 1) {
            return mark + "A";
        }

        if (number >= 11) {
            var numberMark :String = when (number) {
                11 -> "J"
                12 -> "Q"
                13 -> "K"
                else -> "-"
            }
            return mark + numberMark
        }

        return mark + number.toString()
    }

    public fun getBlackJackNumber(): Int {
        if (isA()) {
            return 11;
        }

        if (isPictureCard()) {
            return 10;
        }

        return id % 13
    }

    public fun isA(): Boolean {
        return id % 13 == 1
    }

    public fun isPictureCard(): Boolean {
        return id % 13 > 10 || id % 13 == 0
    }

    public fun isSame(anotherCard: Card): Boolean {
        return id == anotherCard.id
    }
}