internal object AnalyticMethods{

    fun List<Person>.traitAverage(): Double {
        var average = 0.0
        forEach {
            average += it.trait
        }
        average /= size
        return average
    }

    fun List<Person>.hasTrait(trait: Double): Double {
        return count { it.trait == trait }.toDouble() / size
    }

}