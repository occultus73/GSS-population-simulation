package framework

import business.American
import framework.DataColumns.AGE
import framework.DataColumns.IQ
import framework.DataColumns.YEAR_BORN

class SterileDataLoader(traitToStudy: Int) : DataLoader(traitToStudy) {

    override fun verifyMaleAmericanCondition(tokens: List<String>) = verifyAmericanCondition(tokens)

    override fun verifyFemaleAmericanCondition(tokens: List<String>) = verifyAmericanCondition(tokens)

    override fun getMaleAmerican(tokens: List<String>) = getAmerican(tokens)

    override fun getFemaleAmerican(tokens: List<String>) = getAmerican(tokens)

    private fun getAmerican(tokens: List<String>): American {
        return American(
            yearBorn = tokens[YEAR_BORN].toInt(),
            ageAtSurvey = tokens[AGE].toInt(),
            iq = tokens[IQ].toFloat(),
            race = getRace(tokens),
            sex = getSex(tokens),
            numberOfChildren = 0f,
            ageAtFirstChild = 0,
            trait = tokens[traitToStudy].toDouble()
        )
    }

    private fun verifyAmericanCondition(tokens: List<String>): Boolean {
        return tokens[AGE].toIntOrNull() != null
                && tokens[IQ].toFloatOrNull() != null
                && tokens[YEAR_BORN].toIntOrNull() != null
                && tokens[traitToStudy].toDoubleOrNull() != null
    }

}