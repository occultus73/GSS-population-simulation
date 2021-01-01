package business

class HeritabilitySimulator(startingYear: Int, startingPopulation: List<American>): Simulator(startingYear, startingPopulation) {

    override fun applyBirths() {
        currentPopulation.toList().forEach { addChildrenToPopulation(it) }
    }

    private fun addChildrenToPopulation(parent: American) {
        parent.children.filter { it.hasBorn(currentYear) }.forEach { child ->
            currentPopulation.add(child)
            addChildrenToPopulation(child)
        }
    }

}