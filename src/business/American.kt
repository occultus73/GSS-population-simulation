package business

import kotlin.random.Random

class American(
    val yearBorn: Int,
    val ageAtSurvey: Int,
    val iq: Float,
    val race: Int,
    val sex: Int,
    val numberOfChildren: Float,
    val ageAtFirstChild: Int,
    val trait: Double
) {

    val children: List<American> by lazy {
        mutableListOf<American>().apply {
            for (birthOrder in 0 until roundNumberOfChildren()) {
                add(
                    American(
                        yearBorn = yearBorn + ageAtFirstChild + birthOrder * US_AVERAGE_AGE_GAP_NEXT_SIBLING,
                        ageAtSurvey = ageAtSurvey,
                        iq = iq,
                        race = race,
                        sex = sex,
                        numberOfChildren = numberOfChildren,
                        ageAtFirstChild = ageAtFirstChild,
                        trait = trait
                    )
                )
            }
        }
    }

    fun hasBorn(currentYear: Int): Boolean = age(currentYear) >= 0

    fun hasDied(currentYear: Int): Boolean = age(currentYear) >= US_LIFE_EXPECTANCY

    fun isAdult(currentYear: Int): Boolean = age(currentYear) >= US_VOTING_AGE

    private fun age(currentYear: Int) = currentYear - yearBorn

    private fun roundNumberOfChildren(): Int {
        var wholeNumberOfChildren = numberOfChildren.toInt()
        val remainderNumberOfChildren = numberOfChildren - wholeNumberOfChildren
        if (Random.nextInt(100) < remainderNumberOfChildren * 100) wholeNumberOfChildren++
        return wholeNumberOfChildren
    }

    private companion object Statistics {
        const val US_LIFE_EXPECTANCY = 79
        const val US_AVERAGE_AGE_GAP_NEXT_SIBLING = 2
        const val US_VOTING_AGE = 18
    }

}