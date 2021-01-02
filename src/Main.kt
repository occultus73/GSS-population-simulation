import Main.run
import business.American
import business.AnalyticMethods.percentageHasTraitAsync
import business.AnalyticMethods.traitAverageAsync
import business.HeritabilitySimulator
import business.SterileSimulator
import framework.DataColumns.CONSERVATIVE
import framework.HeritabilityDataLoader
import framework.IdealizedDataLoader
import framework.SterileDataLoader
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import java.io.FileWriter

fun main() = run()

object Main {

    // See DataColumns for list of traits you can study - note: this only works out-of-the-box on the numerical columns.
    private const val TRAIT_TO_STUDY = CONSERVATIVE
    private const val CURRENT_YEAR = 1945
    private const val DOOMSDAY = 2080

    private val resultsFileWriter = FileWriter("$TRAIT_TO_STUDY.csv")
    private val populationSizeFileWriter = FileWriter("$TRAIT_TO_STUDY-population_size.csv")
    private val averageYearBornFileWriter = FileWriter("$TRAIT_TO_STUDY-average_year_born.csv")
    private val averageAgeAtSurveyFileWriter = FileWriter("$TRAIT_TO_STUDY-average_age_at_survey.csv")
    private val averageNumberOfChildrenFileWriter = FileWriter("$TRAIT_TO_STUDY-average_num_of_children.csv")
    private val averageAgeAtFirstChildFileWriter = FileWriter("$TRAIT_TO_STUDY-average_age_at_first_child.csv")
    private val averageIQFileWriter = FileWriter("$TRAIT_TO_STUDY-average_IQ.csv")
    private val proportionWhiteFileWriter = FileWriter("$TRAIT_TO_STUDY-proportion_of_white.csv")
    private val proportionMenFileWriter = FileWriter("$TRAIT_TO_STUDY-proportion_of_men.csv")

    private val traitHeritability: Array<MutableMap<Int, Deferred<Double>?>> = arrayOf(mutableMapOf(),mutableMapOf(),mutableMapOf(),mutableMapOf())
    private val traitIdealized: Array<MutableMap<Int, Deferred<Double>?>> = arrayOf(mutableMapOf(),mutableMapOf(),mutableMapOf(),mutableMapOf())
    private val traitSterile: Array<MutableMap<Int, Deferred<Double>?>> = arrayOf(mutableMapOf(),mutableMapOf(),mutableMapOf(),mutableMapOf())

    fun run() {
        val heritabilitySample = HeritabilityDataLoader(TRAIT_TO_STUDY).loadSample()
        val idealizedSample = IdealizedDataLoader(TRAIT_TO_STUDY).loadSample()
        val sterileSample = SterileDataLoader(TRAIT_TO_STUDY).loadSample()
        val heritabilitySimulator = HeritabilitySimulator(CURRENT_YEAR, heritabilitySample)
        val idealizedSimulator = HeritabilitySimulator(CURRENT_YEAR, idealizedSample)
        val sterileSimulator = SterileSimulator(CURRENT_YEAR, sterileSample)

        for (year in CURRENT_YEAR until DOOMSDAY) {
            val heritabilityResult = heritabilitySimulator.next()
            val idealizedResult = idealizedSimulator.next()
            val sterileResult = if (sterileSimulator.hasNext()) {
                sterileSimulator.next()
            } else null

            traitHeritability[0][year] = heritabilityResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
            traitHeritability[1][year] = heritabilityResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
            traitHeritability[2][year] = heritabilityResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
            traitHeritability[3][year] = heritabilityResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)

            traitIdealized[0][year] = idealizedResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
            traitIdealized[1][year] = idealizedResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(1.0, 2.0)
            traitIdealized[2][year] = idealizedResult.filter { it.sex == 1 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)
            traitIdealized[3][year] = idealizedResult.filter { it.sex == 2 && it.race == 1 }.percentageHasTraitAsync(7.0, 6.0)

            traitSterile[0][year] = sterileResult?.filter { it.sex == 1 && it.race == 1 }?.percentageHasTraitAsync(1.0, 2.0)
            traitSterile[1][year] = sterileResult?.filter { it.sex == 2 && it.race == 1 }?.percentageHasTraitAsync(1.0, 2.0)
            traitSterile[2][year] = sterileResult?.filter { it.sex == 1 && it.race == 1 }?.percentageHasTraitAsync(7.0, 6.0)
            traitSterile[3][year] = sterileResult?.filter { it.sex == 2 && it.race == 1 }?.percentageHasTraitAsync(7.0, 6.0)

            logPopulationInfo(year, heritabilityResult, idealizedResult, sterileResult)
        }
        for (year in CURRENT_YEAR until DOOMSDAY) {
            runBlocking {
                resultsFileWriter.run {
                    write("$year")
                    traitHeritability.forEach { write(",${it[year]?.await()}") }
                    traitIdealized.forEach { write(",${it[year]?.await()}") }
                    traitSterile.forEach { write(",${it[year]?.await()}") }
                    write("\n")
                    flush()
                }
            }
        }
    }

    private fun logPopulationInfo(
        year: Int,
        heritabilityResult: List<American>,
        idealizedResult: List<American>,
        sterileResult: List<American>?
    ) {
        println("Loaded: $year, heritability population size: ${heritabilityResult.size}, idealized population size: ${idealizedResult.size}, sterile population size: ${sterileResult?.size}")
        populationSizeFileWriter.run {
            write("$year")
            write(",${heritabilityResult.size}")
            write(",${idealizedResult.size}")
            write(",${sterileResult?.size}")
            write("\n")
            flush()
        }
        averageYearBornFileWriter.run {
            write("$year")
            write(",${heritabilityResult.map { it.yearBorn }.average()}")
            write(",${idealizedResult.map { it.yearBorn }.average()}")
            write(",${sterileResult?.map { it.yearBorn }?.average()}")
            write("\n")
            flush()
        }
        averageAgeAtSurveyFileWriter.run {
            write("$year")
            write(",${heritabilityResult.map { it.ageAtSurvey }.average()}")
            write(",${idealizedResult.map { it.ageAtSurvey }.average()}")
            write(",${sterileResult?.map { it.ageAtSurvey }?.average()}")
            write("\n")
            flush()
        }
        averageNumberOfChildrenFileWriter.run {
            write("$year")
            write(",${heritabilityResult.map { it.numberOfChildren }.average()}")
            write(",${idealizedResult.map { it.numberOfChildren }.average()}")
            write(",${sterileResult?.map { it.numberOfChildren }?.average()}")
            write("\n")
            flush()
        }
        averageAgeAtFirstChildFileWriter.run {
            write("$year")
            write(",${heritabilityResult.filter{ it.ageAtFirstChild != 0 }.map { it.ageAtFirstChild }.average()}")
            write(",${idealizedResult.filter{ it.ageAtFirstChild != 0 }.map { it.ageAtFirstChild }.average()}")
            write(",${sterileResult?.filter{ it.ageAtFirstChild != 0 }?.map { it.ageAtFirstChild }?.average()}")
            write("\n")
            flush()
        }
        averageIQFileWriter.run {
            write("$year")
            write(",${heritabilityResult.map { it.iq }.average()}")
            write(",${idealizedResult.map { it.iq }.average()}")
            write(",${sterileResult?.map { it.iq }?.average()}")
            write("\n")
            flush()
        }
        proportionWhiteFileWriter.run {
            write("$year")
            write(",${heritabilityResult.run { count { it.race == 1 }.toDouble() / size }}")
            write(",${idealizedResult.run { count { it.race == 1 }.toDouble() / size }}")
            write(",${sterileResult?.run { count { it.race == 1 }.toDouble() / size }}")
            write("\n")
            flush()
        }
        proportionMenFileWriter.run {
            write("$year")
            write(",${heritabilityResult.run { count { it.sex == 1 }.toDouble() / size }}")
            write(",${idealizedResult.run { count { it.sex == 1 }.toDouble() / size }}")
            write(",${sterileResult?.run { count { it.sex == 1 }.toDouble() / size }}")
            write("\n")
            flush()
        }
    }

}