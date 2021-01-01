package business

abstract class Simulator(
    startingYear: Int,
    startingPopulation: List<American>,
) : Iterator<List<American>> {

    protected var currentPopulation = startingPopulation.toMutableSet()

    protected var currentYear = startingYear - 1

    override fun next(): List<American> {
        currentYear++
        applyBirths()
        applyDeaths()
        return getAdultPopulation()
    }

    override fun hasNext() = currentPopulation.filterNot { it.hasDied(currentYear + 1) }.isNotEmpty()

    protected abstract fun applyBirths()

    private fun applyDeaths() = currentPopulation.removeIf { it.hasDied(currentYear) }

    private fun getAdultPopulation() = currentPopulation.filter { it.isAdult(currentYear) }

}