package framework

import business.American
import framework.DataColumns.BLACK
import framework.DataColumns.FEMALE
import framework.DataColumns.MALE
import framework.DataColumns.WHITE
import java.io.BufferedReader
import java.io.FileReader

abstract class DataLoader(val traitToStudy: Int) {

    protected abstract fun verifyMaleAmericanCondition(tokens: List<String>): Boolean

    protected abstract fun verifyFemaleAmericanCondition(tokens: List<String>): Boolean

    protected abstract fun getMaleAmerican(tokens: List<String>): American

    protected abstract fun getFemaleAmerican(tokens: List<String>): American

    fun loadSample(): List<American> = mutableListOf<American>().also { list ->
        println("Loading Sample For Trait: $traitToStudy")
        val fileReader = BufferedReader(FileReader("GSS_fertility_data.csv"))
        var line: String? = fileReader.readLine()
        var lineNumber = 0
        while (line != null) {
            lineNumber++
            val tokens = line.split(",")
            when {
                verifyFemaleAmericanCondition(tokens) -> try {
                    list.add(getFemaleAmerican(tokens))
                } catch (t: Throwable) {
                    println("Caught exception adding female at line: $lineNumber")
                    t.printStackTrace()
                }
                verifyMaleAmericanCondition(tokens) -> try {
                    list.add(getMaleAmerican(tokens))
                } catch (t: Throwable) {
                    println("Caught exception adding male at line: $lineNumber")
                    t.printStackTrace()
                }
            }
            line = fileReader.readLine()
        }
        fileReader.close()
    }

    protected fun getRace(tokens: List<String>): Int {
        return when {
            tokens[WHITE].isNotEmpty() -> 1
            tokens[BLACK].isNotEmpty() -> 2
            else -> 0
        }
    }

    protected fun getSex(tokens: List<String>): Int {
        return when {
            tokens[MALE].isNotEmpty() -> 1
            tokens[FEMALE].isNotEmpty() -> 2
            else -> 0
        }
    }

}