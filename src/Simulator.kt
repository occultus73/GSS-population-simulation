internal class Simulator(startingYear: Int, startingPopulation: List<Person>) : Iterator<Double> {

    private var currentPopulation = startingPopulation.toMutableList()

    private var currentYear = startingYear - 1

    override fun next(): Double {
        currentYear++
        currentPopulation.toList().forEach { addChildrenToPopulation(it) }
        currentPopulation.removeIf { it.hasDied(currentYear) }
        return currentPopulation.traitAverage()
    }

    override fun hasNext() = currentPopulation.isNotEmpty()

    fun numberOfAdults() = currentPopulation.count { it.isAdult(currentYear) }

    private fun List<Person>.traitAverage(): Double {
        var average = 0.0
        filter { it.isAdult(currentYear) }.forEach {
            average += it.trait
        }
        average /= numberOfAdults()
        return average
    }

    private fun addChildrenToPopulation(mother: Person) {
        mother.daughters.filter { it.hasBorn(currentYear) }.forEach { daughter ->
            if (!currentPopulation.contains(daughter)) currentPopulation.add(daughter)
            addChildrenToPopulation(daughter)
        }
    }

}