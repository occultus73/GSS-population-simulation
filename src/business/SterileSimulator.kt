package business

class SterileSimulator(startingYear: Int, startingPopulation: List<American>): Simulator(startingYear, startingPopulation) {

    override fun applyBirths() = Unit

}