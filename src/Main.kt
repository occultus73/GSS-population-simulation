import DataLoader.ABORTION_OK
import DataLoader.loadSample

const val CURRENT_YEAR = 2020
const val DOOMSDAY = 2100

fun main() {
    // See DataLoader.kt for list of traits you can study - note: only works on the number columns.
    val sample = loadSample(traitToStudy = ABORTION_OK)
    val simulator = Simulator(CURRENT_YEAR, sample)
    println("Maximum trait in sample is: ${sample.maxByOrNull { it.trait }}")
    println("Minimum trait in sample is: ${sample.minByOrNull { it.trait }}")
    for (year in CURRENT_YEAR until DOOMSDAY){
        if(!simulator.hasNext()) break
        println("Adult trait average is: ${simulator.next().format(2)} in $year for adult population size ${simulator.numberOfAdults()}")
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
