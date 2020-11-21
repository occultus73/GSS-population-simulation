internal class Simulator(startingYear: Int, startingPopulation: List<Person>) : Iterator<List<Person>> {

    private var currentPopulation = startingPopulation.toMutableList()

    private var currentYear = startingYear - 1

    override fun next(): List<Person> {
        currentYear++
        currentPopulation.toList().forEach { addChildrenToPopulation(it) }
        currentPopulation.removeIf { it.hasDied(currentYear) }
        return currentPopulation.filter{ it.isAdult(currentYear) }
    }

    override fun hasNext() = currentPopulation.isNotEmpty()

    private fun addChildrenToPopulation(mother: Person) {
        mother.children.filter { it.hasBorn(currentYear) }.forEach { child ->
            if (!currentPopulation.contains(child)) currentPopulation.add(child)
            addChildrenToPopulation(child)
        }
    }

}