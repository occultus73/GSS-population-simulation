package business

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

object AnalyticMethods {

    fun List<American>.traitAverageAsync(): Deferred<Double> {
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
        return GlobalScope.async(Dispatchers.Default) { count { american -> trait.any { it == american.trait } }.toDouble() / size }
    }

    fun List<American>.averageIQforTrait(vararg trait: Double): Deferred<Double> {
        return GlobalScope.async(Dispatchers.Default) {
            var average = 0.0
            val size = filter { american -> trait.any { it == american.trait } }.onEach {
                average += it.iq
            }.count()
            average /= size
            average
        }
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