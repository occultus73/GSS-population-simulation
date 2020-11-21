import AnalyticMethods.hasTrait
import AnalyticMethods.traitAverage
import DataLoader.ABORTION_OK
import DataLoader.AGE_AT_FIRST_CHILD
import DataLoader.AGE_AT_FIRST_MARRIAGE
import DataLoader.CHILDREN_OF_MALE_OVER_44
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
import DataLoader.STRENGTH_OF_RELIGIOUS_COMMITMENT
import DataLoader.TOLERANCE_HOMOSEXUAL
import DataLoader.WOMEN_SHOULD_WORK
import DataLoader.YEARS_IN_SCHOOL
import DataLoader.loadSample
import java.io.FileWriter

const val CURRENT_YEAR = 1945
const val DOOMSDAY = 2100

fun main() {
    // See DataLoader.kt for list of traits you can study - note: only works on the number columns.
    val sample = loadSample(traitToStudy = GAY_MARRIAGE_OK)
    val simulator = Simulator(CURRENT_YEAR, sample)
//    println("Maximum trait in sample is: ${sample.maxByOrNull { it.trait }}")
//    println("Minimum trait in sample is: ${sample.minByOrNull { it.trait }}")
//    return
    val fileWriter = FileWriter("GAY_MARRIAGE_OK.csv")
    for (year in CURRENT_YEAR until DOOMSDAY){
        if(!simulator.hasNext()) break
        val result = simulator.next()
        val trait1 = result.traitAverage()
        fileWriter.write("$year,$trait1\n")
        fileWriter.flush()
        println("Written: $year, adult population size: ${result.size}")
    }
    println("Maximum trait in sample is: ${sample.maxByOrNull { it.trait }}")
    println("Minimum trait in sample is: ${sample.minByOrNull { it.trait }}")
}