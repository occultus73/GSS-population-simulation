import AnalyticMethods.percentageHasTraitAsync
import AnalyticMethods.traitAverage
import DataLoader.ABORTION_OK
import DataLoader.HOMOSEXUAL_SEX_OK
import DataLoader.IQ
import DataLoader.loadSample
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import java.io.FileWriter

const val CURRENT_YEAR = 1945
const val DOOMSDAY = 2090

fun main() {
    // See DataLoader.kt for list of traits you can study - note: only works on the number columns.
    val sample = loadSample(traitToStudy = IQ)
    val fullSample = loadSample(traitToStudy = IQ, loadAll = true)
    val simulator = Simulator(CURRENT_YEAR, sample)
    val noChildrenSimulator = Simulator(CURRENT_YEAR, fullSample, false)
//    println("Maximum trait in sample is: ${sample.maxBy { it.trait }?.trait}")
//    println("Minimum trait in sample is: ${sample.minBy { it.trait }?.trait}")
//    return
    val fileWriter = FileWriter("IQ.csv")
    val trait1: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait2: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait1Control: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait2Control: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    for (year in CURRENT_YEAR until DOOMSDAY) {
        if (!simulator.hasNext()) break
        val result = simulator.next()
        val controlResult = if (noChildrenSimulator.hasNext()) {
            noChildrenSimulator.next()
        } else null
        trait1[year] = result.traitAverage()
        trait2[year] = null
        trait1Control[year] = controlResult?.traitAverage()
        trait2Control[year] = null
        println("Loaded: $year, adult population size: ${result.size}, control size: ${controlResult?.size}")
    }
    for (year in CURRENT_YEAR until DOOMSDAY) {
        runBlocking {
            fileWriter.write("$year,${trait1[year]?.await()},${trait2[year]?.await()},${trait1Control[year]?.await()},${trait2Control[year]?.await()}\n")
            fileWriter.flush()
        }
    }
}