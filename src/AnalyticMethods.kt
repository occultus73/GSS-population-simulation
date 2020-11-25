import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

internal object AnalyticMethods {

    fun List<American>.traitAverage(): Deferred<Double> {
        return GlobalScope.async(Dispatchers.Default) {
            var average = 0.0
            forEach {
                average += it.trait
            }
            average /= size
            average
        }
    }

    fun List<American>.percentageHasTraitAsync(vararg trait: Double): Deferred<Double> {
        return GlobalScope.async(Dispatchers.Default) {  count { person -> trait.any { it == person.trait } }.toDouble() / size }
    }

    fun List<American>.mostCommonPerson() {
        val result = groupingBy { it.toKey() }.eachCount().filter { it.value > 1 }.toList()
            .sortedBy { (_, value) -> value }
            .toMap()
        println()
    }

    private data class Key(val numberOfChildren: Float, val ageAtFirstChild: Int, val trait: Double)
    private fun American.toKey() = Key(numberOfChildren, ageAtFirstChild, trait)

}