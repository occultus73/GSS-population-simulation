internal class Simulator(startingYear: Int, startingPopulation: List<American>, private val addChildren: Boolean = true) :
    Iterator<List<American>> {

    private var currentPopulation = startingPopulation.toMutableSet()

    private var currentYear = startingYear - 1

    override fun next(): List<American> {
        currentYear++
        if(addChildren) currentPopulation.toList().forEach { addChildrenToPopulation(it) }
        currentPopulation.removeIf { it.hasDied(currentYear) }
        return currentPopulation.filter { it.isAdult(currentYear) }
    }

    override fun hasNext() = currentPopulation.filterNot { it.hasDied(currentYear+1) }.isNotEmpty()

    private fun addChildrenToPopulation(parent: American) {
        parent.children.filter { it.hasBorn(currentYear) }.forEach { child ->
            currentPopulation.add(child)
            addChildrenToPopulation(child)
        }
    }

}