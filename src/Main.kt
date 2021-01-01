import business.AnalyticMethods.averageIQforTrait
import business.AnalyticMethods.percentageHasTraitAsync
import business.HeritabilitySimulator
import business.SterileSimulator
import framework.DataColumns.CONSERVATIVE
import framework.DataColumns.RELIGIOUS_ATTENDANCE
import framework.HeritabilityDataLoader
import framework.IdealizedDataLoader
import framework.SterileDataLoader
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import java.io.FileWriter

// See framework.DataColumns object for all options.
const val TRAIT_TO_STUDY = CONSERVATIVE
const val CURRENT_YEAR = 1945
const val DOOMSDAY = 2080

fun main() {
    // See framework.DataLoader.kt for list of traits you can study - note: only works on the number columns.
    val heritabilitySample = HeritabilityDataLoader(TRAIT_TO_STUDY).loadSample()
    val idealizedSample = IdealizedDataLoader(TRAIT_TO_STUDY).loadSample()
    val sterileSample = SterileDataLoader(TRAIT_TO_STUDY).loadSample()
    val heritabilitySimulator = HeritabilitySimulator(CURRENT_YEAR, heritabilitySample)
    val idealizedSimulator = HeritabilitySimulator(CURRENT_YEAR, idealizedSample)
    val sterileSimulator = SterileSimulator(CURRENT_YEAR, sterileSample)
//    println("Maximum trait in sample is: ${sterileSample.maxBy { it.trait }?.trait}")
//    println("Minimum trait in sample is: ${sterileSample.minBy { it.trait }?.trait}")
//    return
    val fileWriter = FileWriter("$TRAIT_TO_STUDY.csv")
    val trait1Heritability: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait2Heritability: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait3Heritability: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait4Heritability: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait1Idealized: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait2Idealized: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait3Idealized: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait4Idealized: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait1Sterile: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait2Sterile: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait3Sterile: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    val trait4Sterile: MutableMap<Int, Deferred<Double>?> = mutableMapOf()
    for (year in CURRENT_YEAR until DOOMSDAY) {
        val heritabilityResult = heritabilitySimulator.next()
        val idealizedResult = idealizedSimulator.next()
        val sterileResult = if (sterileSimulator.hasNext()) {
            sterileSimulator.next()
        } else null
        trait1Heritability[year] = heritabilityResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
        trait2Heritability[year] = heritabilityResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
        trait3Heritability[year] = heritabilityResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
        trait4Heritability[year] = heritabilityResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
        trait1Idealized[year] = idealizedResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
        trait2Idealized[year] = idealizedResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
        trait3Idealized[year] = idealizedResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
        trait4Idealized[year] = idealizedResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
        trait1Sterile[year] = sterileResult?.filter { it.sex == 1 && it.race == 1 }?.percentageHasTraitAsync(1.0, 2.0)
        trait2Sterile[year] = sterileResult?.filter { it.sex == 2 && it.race == 1 }?.percentageHasTraitAsync(1.0, 2.0)
        trait3Sterile[year] = sterileResult?.filter { it.sex == 1 && it.race == 1 }?.percentageHasTraitAsync(7.0, 6.0)
        trait4Sterile[year] = sterileResult?.filter { it.sex == 2 && it.race == 1 }?.percentageHasTraitAsync(7.0, 6.0)
        println("Loaded: $year, heritability population size: ${heritabilityResult.size}, idealized population size: ${idealizedResult.size}, sterile population size: ${sterileResult?.size}")
    }
    for (year in CURRENT_YEAR until DOOMSDAY) {
        runBlocking {
            fileWriter.write("$year")
            fileWriter.write(",${trait1Heritability[year]?.await()}")
            fileWriter.write(",${trait2Heritability[year]?.await()}")
            fileWriter.write(",${trait3Heritability[year]?.await()}")
            fileWriter.write(",${trait4Heritability[year]?.await()}")
            fileWriter.write(",${trait1Idealized[year]?.await()}")
            fileWriter.write(",${trait2Idealized[year]?.await()}")
            fileWriter.write(",${trait3Idealized[year]?.await()}")
            fileWriter.write(",${trait4Idealized[year]?.await()}")
            fileWriter.write(",${trait1Sterile[year]?.await()}")
            fileWriter.write(",${trait2Sterile[year]?.await()}")
            fileWriter.write(",${trait3Sterile[year]?.await()}")
            fileWriter.write(",${trait4Sterile[year]?.await()}")
            fileWriter.write("\n")
            fileWriter.flush()
        }
    }
}