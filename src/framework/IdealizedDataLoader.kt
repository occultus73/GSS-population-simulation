package framework

import business.American
import framework.DataColumns.AGE
import framework.DataColumns.AGE_AT_FIRST_CHILD
import framework.DataColumns.IDEAL_NUMBER_OF_CHILDREN
import framework.DataColumns.IQ
import framework.DataColumns.YEAR_BORN

class IdealizedDataLoader(traitToStudy: Int) : DataLoader(traitToStudy) {

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
            numberOfChildren = tokens[IDEAL_NUMBER_OF_CHILDREN].toFloat() / 2,
            ageAtFirstChild = tokens[AGE_AT_FIRST_CHILD].toIntOrNull() ?: 0,
            trait = tokens[traitToStudy].toDouble()
        )
    }

    private fun verifyAmericanCondition(tokens: List<String>): Boolean {
        return tokens[AGE].toIntOrNull() != null
                && tokens[IQ].toFloatOrNull() != null
                && tokens[YEAR_BORN].toIntOrNull() != null
                && tokens[IDEAL_NUMBER_OF_CHILDREN].toIntOrNull() != null
                && (tokens[AGE_AT_FIRST_CHILD].toIntOrNull() != null || tokens[IDEAL_NUMBER_OF_CHILDREN].toInt() == 0)
                && tokens[traitToStudy].toDoubleOrNull() != null
    }

}