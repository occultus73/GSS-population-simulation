import AnalyticMethods.hasTrait
import DataLoader.ABORTION_OK
import DataLoader.EXTRAMARITAL_SEX_OK
import DataLoader.FAVOUR_SEX_EDUCATION
import DataLoader.FAVOUR_SPANKING
import DataLoader.FUNDAMENTALIST
import DataLoader.GAY_MARRIAGE_OK
import DataLoader.HOMOSEXUAL_SEX_OK
import DataLoader.IDEAL_NUMBER_OF_CHILDREN
import DataLoader.IQ
import DataLoader.LEGALIZE_PORN
import DataLoader.MAKE_DIVORCE_EASIER
import DataLoader.MISCEGENATION_OK
import DataLoader.MOTHER_WORKING_DOES_NOT_HURT_CHILDREN
import DataLoader.PREMARITAL_SEX_OK
import DataLoader.RAISE_IMMIGRATION
import DataLoader.TOLERANCE_HOMOSEXUAL
import DataLoader.WOMEN_SHOULD_WORK
import DataLoader.YEARS_IN_SCHOOL
import DataLoader.loadSample
import java.io.FileWriter

const val CURRENT_YEAR = 1947
const val DOOMSDAY = 2100

fun main() {
    // See DataLoader.kt for list of traits you can study - note: only works on the number columns.
    val sample = loadSample(traitToStudy = FUNDAMENTALIST)
    val simulator = Simulator(CURRENT_YEAR, sample)
    val fileWriter = FileWriter("FUNDAMENTALIST.csv")
    for (year in CURRENT_YEAR until DOOMSDAY){
        if(!simulator.hasNext()) break
        val result = simulator.next()
        val trait1 = result.hasTrait(1.0).format(2)
        val trait2 = result.hasTrait(2.0).format(2)
        val trait3 = result.hasTrait(3.0).format(2)
        fileWriter.write("$year,$trait1,$trait2,$trait3\n")
        fileWriter.flush()
        println("Written: $year, adult population size: ${result.size}")
    }
    println("Maximum trait in sample is: ${sample.maxByOrNull { it.trait }}")
    println("Minimum trait in sample is: ${sample.minByOrNull { it.trait }}")
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
