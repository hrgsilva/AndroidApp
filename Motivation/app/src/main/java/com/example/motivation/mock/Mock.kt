package com.example.motivation.mock

import com.example.motivation.utils.MotivationConstants
import kotlin.random.Random

class Phrase(val descripition: String, val category: Int)

fun Int.random(): Int{
    return Random.nextInt(this)
}

class Mock {
    private val ALL = MotivationConstants.PHRASE_FILTER.ALL
    private val SUN = MotivationConstants.PHRASE_FILTER.SUN
    private val HAPPY = MotivationConstants.PHRASE_FILTER.HAPPY

    private val mListPhrases: List<Phrase> = listOf(
        Phrase("Bom dia 1", SUN),
        Phrase("Bom dia 2", SUN),
        Phrase("Bom dia 3", SUN),
        Phrase("Bom dia 4", SUN),
        Phrase("Bom dia 5", SUN),
        Phrase("Bom dia 6", SUN),
        Phrase("Bom dia 7", SUN),

        Phrase("Feliz 1", HAPPY),
        Phrase("Feliz 2", HAPPY),
        Phrase("Feliz 3", HAPPY),
        Phrase("Feliz 4", HAPPY),
        Phrase("Feliz 5", HAPPY),
        Phrase("Feliz 6", HAPPY),
        Phrase("Feliz 7", HAPPY)

    )

    fun getPrhase(value:Int): String {

        val filtered = mListPhrases.filter { it -> (it.category == value || value == ALL) }

        val rand = (filtered.size).random()

        return filtered[rand].descripition
    }
}