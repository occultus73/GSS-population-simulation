import kotlin.math.floor
import kotlin.random.Random

class Simulator(private var population: List<FemaleOverForty>) : Iterator<List<FemaleOverForty>> {

    override fun hasNext() = population.isNotEmpty()

    override fun next(): List<FemaleOverForty> {
        population = mutableListOf<FemaleOverForty>().apply {
            population.forEach {
                addAll(getDaughters(it))
            }
        }
        return population
    }

    private fun getDaughters(mother: FemaleOverForty): List<FemaleOverForty> = mutableListOf<FemaleOverForty>().apply {
        for (i in 1..roundProbabilistically(mother.daughters)) {
            val daughterYearOfBirth = possibleYearOfBirth(mother.yearBorn)
            if (mother.surveyYear - daughterYearOfBirth > 41) {
                addAll(
                    getDaughters(
                        FemaleOverForty(
                            surveyYear = mother.surveyYear,
                            yearBorn = daughterYearOfBirth,
                            daughters = mother.daughters,
                            trait = mother.trait
                        )
                    )
                )
            } else add(
                FemaleOverForty(
                    surveyYear = mother.surveyYear + 41,
                    yearBorn = daughterYearOfBirth,
                    daughters = mother.daughters,
                    trait = mother.trait
                )
            )

        }
    }

    private fun roundProbabilistically(numberOfDaughters: Float): Int {
        var wholeNumberOfDaughters = numberOfDaughters.toInt()
        val remainderNumberOfDaughters = numberOfDaughters - wholeNumberOfDaughters
        if (Random.nextInt(100) < remainderNumberOfDaughters * 100) wholeNumberOfDaughters++
        return wholeNumberOfDaughters
    }

    private fun possibleYearOfBirth(parentYearOfBirth: Int) = parentYearOfBirth + Random.nextInt(16, 41)

}