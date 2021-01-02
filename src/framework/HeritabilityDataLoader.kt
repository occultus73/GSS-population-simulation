package framework

import business.American
import framework.DataColumns.AGE
import framework.DataColumns.AGE_AT_FIRST_CHILD
import framework.DataColumns.CHILDREN_OF_FEMALE_OVER_41
import framework.DataColumns.CHILDREN_OF_MALE_OVER_44
import framework.DataColumns.IQ
import framework.DataColumns.YEAR_BORN

class HeritabilityDataLoader(traitToStudy: Int) : DataLoader(traitToStudy) {

    override fun verifyMaleAmericanCondition(tokens: List<String>): Boolean {
        return tokens[CHILDREN_OF_MALE_OVER_44].toIntOrNull() != null
                && tokens[AGE].toIntOrNull() != null
                && tokens[IQ].toFloatOrNull() != null
                && (tokens[AGE_AT_FIRST_CHILD].toIntOrNull() != null || tokens[CHILDREN_OF_MALE_OVER_44].toInt() == 0)
                && tokens[traitToStudy].toDoubleOrNull() != null
    }

    override fun verifyFemaleAmericanCondition(tokens: List<String>): Boolean {
        return tokens[CHILDREN_OF_FEMALE_OVER_41].toIntOrNull() != null
                && tokens[AGE].toIntOrNull() != null
                && tokens[IQ].toFloatOrNull() != null
                && (tokens[AGE_AT_FIRST_CHILD].toIntOrNull() != null || tokens[CHILDREN_OF_FEMALE_OVER_41].toInt() == 0)
                && tokens[traitToStudy].toDoubleOrNull() != null
    }

    override fun getMaleAmerican(tokens: List<String>): American {
        return American(
            yearBorn = tokens[YEAR_BORN].toInt(),
            ageAtSurvey = tokens[AGE].toInt(),
            iq = tokens[IQ].toFloat(),
            race = getRace(tokens),
            sex = getSex(tokens),
            numberOfChildren = tokens[CHILDREN_OF_MALE_OVER_44].toFloat() / 2,
            ageAtFirstChild = tokens[AGE_AT_FIRST_CHILD].toIntOrNull() ?: 0,
            trait = tokens[traitToStudy].toDouble()
        )
    }

    override fun getFemaleAmerican(tokens: List<String>): American {
        return American(
            yearBorn = tokens[YEAR_BORN].toInt(),
            ageAtSurvey = tokens[AGE].toInt(),
            iq = tokens[IQ].toFloat(),
            race = getRace(tokens),
            sex = getSex(tokens),
            numberOfChildren = tokens[CHILDREN_OF_FEMALE_OVER_41].toFloat() / 2,
            ageAtFirstChild = tokens[AGE_AT_FIRST_CHILD].toIntOrNull() ?: 0,
            trait = tokens[traitToStudy].toDouble()
        )
    }

}