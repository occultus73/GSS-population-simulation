import java.io.BufferedReader
import java.io.FileReader

internal object DataLoader {

    const val SURVEY_YEAR = 0  // ranges from 1972 to 2018
    const val YEAR_BORN = 1  // ranges from 1883 to 1977 for 41+
    const val MALE = 2
    const val FEMALE = 3
    const val FEMALE_OVER_41 = 4
    const val MALE_OVER_44 = 5
    const val CHILDREN_OF_FEMALE_OVER_41 = 6
    const val CHILDREN_OF_MALE_OVER_44 = 7
    const val BORN_IN_US = 8
    const val WHITE = 9
    const val BLACK = 10
    const val SEX = 11
    const val AGE = 12
    const val IQ = 13
    const val YEARS_IN_SCHOOL = 14
    const val HIGHEST_DEGREE = 15
    const val EDUCATION = 16
    const val LOG_INCOME = 17
    const val CONSERVATIVE = 18
    const val REPUBLICAN = 19
    const val RELIGIOUS_ATTENDANCE = 20
    const val STRENGTH_OF_RELIGIOUS_COMMITMENT = 21
    const val TOLERANCE_ATHEIST = 22
    const val TOLERANCE_RACIST = 23
    const val TOLERANCE_COMMUNIST = 24
    const val TOLERANCE_MILITARIST = 25
    const val TOLERANCE_HOMOSEXUAL = 26
    const val CAPITAL_PUNISHMENT = 27
    const val COURTS_AND_CRIME = 28
    const val FUNDAMENTALIST = 29
    const val EXPENDITURE_FOR_SPACE_EXPLORATION = 30
    const val EXPENDITURE_FOR_ENVIRONMENT = 31
    const val EXPENDITURE_FOR_HEALTH = 32
    const val EXPENDITURE_FOR_CITY = 33
    const val EXPENDITURE_FOR_CRIME = 34
    const val EXPENDITURE_FOR_DRUGS = 35
    const val EXPENDITURE_FOR_EDUCATION = 36
    const val EXPENDITURE_FOR_BLACKS = 37
    const val EXPENDITURE_FOR_MILITARY = 38
    const val EXPENDITURE_FOR_FOREIGN_AID = 39
    const val EXPENDITURE_FOR_WELFARE = 40
    const val EXPENDITURE_FOR_HIGHWAYS = 41
    const val EXPENDITURE_FOR_SOCIAL_SECURITY = 42
    const val EXPENDITURE_FOR_TRANSPORTATION = 43
    const val EXPENDITURE_FOR_PARKS = 44
    const val GOVERNMENT_SHOULD_IMPROVE_STANDARD_OF_LIVING = 45
    const val GOVERNMENT_SHOULD_DO_MORE = 46
    const val GOVERNMENT_SHOULD_HELP_PAY_FOR_MEDICAL_CARE = 47
    const val GOVERNMENT_SHOULD_AID_BLACKS = 48
    const val ABORTION_OK = 49  // ranges from 6 to 12
    const val CAUSE_OF_RACE_DIFFERENCES_DISCRIMINATION = 50
    const val CAUSE_OF_RACE_DIFFERENCES_INBORN = 51
    const val CAUSE_OF_RACE_DIFFERENCES_EDUCATION = 52
    const val CAUSE_OF_RACE_DIFFERENCES_WILL = 53
    const val WOMEN_TAKE_CARE_OF_COUNTRY_NOT_HOME = 54
    const val WOMEN_SHOULD_WORK = 55
    const val VOTE_FOR_WOMAN_PRESIDENT = 56
    const val WOMEN_SUITABLE_FOR_POLITICS = 57
    const val MOTHER_WORKING_DOES_NOT_HURT_CHILDREN = 58
    const val WIVES_SHOULD_NOT_HELP_HUSBANDS_CAREER_FIRST = 59
    const val PRESCHOOL_KIDS_DO_NOT_SUFFER_WHEN_MOTHER_WORKS = 60
    const val NOT_BETTER_FOR_WOMEN_TO_DO_HOME_MAKING = 61
    const val TRUST_IN_BANKS = 62
    const val TRUST_IN_BIG_COMPANIES = 63
    const val TRUST_IN_CLERGY = 64
    const val TRUST_IN_EDUCATION = 65
    const val TRUST_IN_EXECUTIVE_GOVERNMENT = 66
    const val TRUST_IN_ORGANIZED_LABOR = 67
    const val TRUST_IN_PRESS = 68
    const val TRUST_IN_MEDICINE = 69
    const val TRUST_IN_TV = 70
    const val TRUST_IN_SUPREME_COURT = 71
    const val TRUST_IN_SCIENCE = 72
    const val TRUST_IN_CONGRESS = 73
    const val TRUST_IN_MILITARY = 74
    const val TRUST_IN_PEOPLE = 75
    const val TAX_TOO_LOW = 76
    const val GOVERNMENT_SHOULD_REDUCE_INCOME_DIFFERENCES = 77
    const val LEGALIZE_POT = 78
    const val PRIVATE_ENTERPRISE_SOLVES_PROBLEMS = 79
    const val GOVERNMENT_CREATE_JOBS = 80
    const val GOVERNMENT_REGULATE_LESS = 81
    const val GOVERNMENT_SUPPORT_NEW_PRODUCTS = 82
    const val GOVERNMENT_SUPPORT_DECLINING_INDUSTRIES = 83
    const val GAY_MARRIAGE_OK = 84
    const val PREMARITAL_SEX_OK = 85
    const val EXTRAMARITAL_SEX_OK = 86
    const val HOMOSEXUAL_SEX_OK = 87
    const val FAVOUR_GUN_PERMITS = 88
    const val FAVOUR_WIRETAPPING = 89
    const val EUTHANASIA_OK = 90
    const val SUICIDE_DISEASE_OK = 91
    const val SUICIDE_OTHER_OK = 92
    const val MISCEGENATION_OK = 93
    const val VOTE_FOR_BLACK_PRESIDENT = 94
    const val RAISE_IMMIGRATION = 95
    const val BLACKS_SHOULD_NOT_PUSH = 96
    const val FAVOUR_BLACK_HIRING_PREFERENCE = 97
    const val HAPPINESS = 98
    const val LOT_OF_AVERAGE_MAN_GETTING_WORSE = 99
    const val NOT_FAIR_MAKING_CHILD = 100
    const val OFFICIALS_NOT_INTERESTED_IN_COMMON_MAN = 101
    const val JOB_HIGH_INCOME = 102
    const val JOB_SECURE = 103
    const val JOB_SHORT_HOURS = 104
    const val JOB_ADVANCEMENT = 105
    const val JOB_IMPORTANT = 106
    const val GET_AHEAD_WITH_HARD_WORK = 107
    const val BETTER_LIVING_STANDARDS_NOW_THAN_PARENTS = 108
    const val BETTER_LIVING_STANDARDS_FOR_CHILDREN_THAN_NOW = 109
    const val IDEAL_NUMBER_OF_CHILDREN = 110
    const val FAVOUR_SEX_EDUCATION = 111
    const val MAKE_DIVORCE_EASIER = 112
    const val LEGALIZE_PORN = 113
    const val FAVOUR_SPANKING = 114
    const val AFRAID_TO_WALK_IN_THE_DARK = 115
    const val NUMBER_OF_CLUB_MEMBERSHIPS = 116
    const val WHITE_BLACK_DIFFERENCE_RICH = 117
    const val WHITE_BLACK_DIFFERENCE_HARD_WORKING = 118
    const val WHITE_BLACK_DIFFERENCE_INTELLIGENCE = 119
    const val REDUCTION_IN_LENGTH_OF_WORK_WEEK = 120
    const val GOVERNMENT_SPENDING_ON_ENVIRONMENT = 121
    const val GOVERNMENT_SPENDING_ON_HEALTH = 122
    const val GOVERNMENT_SPENDING_ON_LAW_ENFORCEMENT = 123
    const val GOVERNMENT_SPENDING_ON_EDUCATION = 124
    const val GOVERNMENT_SPENDING_ON_MILITARY = 125
    const val GOVERNMENT_SPENDING_ON_RETIREMENT_BENEFITS = 126
    const val GOVERNMENT_SPENDING_ON_UNEMPLOYMENT_BENEFITS = 127
    const val GOVERNMENT_SPENDING_ON_CULTURE_AND_ARTS = 128
    const val GOVERNMENT_RESPONSIBILITY_PROVIDE_JOBS_FOR_ALL = 129
    const val GOVERNMENT_RESPONSIBILITY_KEEP_PRICES_UNDER_CONTROL = 130
    const val GOVERNMENT_RESPONSIBILITY_PROVIDE_HEALTH_CARE_FOR_THE_SICK = 131
    const val GOVERNMENT_RESPONSIBILITY_PROVIDE_FOR_ELDERLY = 132
    const val GOVERNMENT_RESPONSIBILITY_ASSIST_INDUSTRIAL_GROWTH = 133
    const val GOVERNMENT_RESPONSIBILITY_PROVIDE_FOR_THE_UNEMPLOYED = 134
    const val GOVERNMENT_RESPONSIBILITY_REDUCE_INCOME_DIFFERENCES = 135
    const val GOVERNMENT_HELP_COLLEGE_STUDENTS = 136
    const val GOVERNMENT_HOUSING_POOR = 137
    const val POLITICAL_INTEREST = 138
    const val DO_NOT_HAVE_ANY_SAY = 139
    const val LIVING_STANDARDS_WILL_IMPROVE = 140
    const val INCOME_GAPS_TOO_BIG = 141
    const val GOVERNMENT_REDUCE_INCOME_GAPS = 142
    const val MEN_OVERWORKING_BAD_FOR_FAMILY = 143
    const val TAKE_ACTIVE_PART_IN_WORLD_AFFAIRS = 144
    const val MARRIAGE_CONDITION = 145
    const val MARRIAGE_TYPE = 146
    const val AGE_AT_FIRST_MARRIAGE = 147
    const val DIVORCED = 148
    const val WIDOWED = 149
    const val AGE_AT_FIRST_CHILD = 150

    fun loadSample(trait: Int): List<Person> = mutableListOf<Person>().also { list ->
        val fileReader = BufferedReader(FileReader("GSS_fertility_data.csv"))
        var line: String? = fileReader.readLine()
        var count = 0
        while (line != null) {
            count++
            val tokens = line.split(",")
            if (tokens[CHILDREN_OF_FEMALE_OVER_41].isNotEmpty()
                && tokens[AGE_AT_FIRST_CHILD] != "NA"
                && tokens[AGE_AT_FIRST_CHILD] != "DK"
                && !(tokens[AGE_AT_FIRST_CHILD].toIntOrNull() == null && tokens[CHILDREN_OF_FEMALE_OVER_41].toInt() > 0)
                && tokens[trait].isNotEmpty()
            ) {
                try {
                    list.add(
                        Person(
                            yearBorn = tokens[YEAR_BORN].toInt(),
                            numberOfChildren = tokens[CHILDREN_OF_FEMALE_OVER_41].toFloat() / 2,
                            ageAtFirstChild = tokens[AGE_AT_FIRST_CHILD].toIntOrNull() ?: 0,
                            trait = tokens[trait].toDouble()
                        )
                    )
                } catch (t: Throwable) {
                    println("Caught exception reading line: $count")
                    t.printStackTrace()
                }
            }
            if (tokens[CHILDREN_OF_MALE_OVER_44].isNotEmpty()
                && tokens[AGE_AT_FIRST_CHILD] != "NA"
                && tokens[AGE_AT_FIRST_CHILD] != "DK"
                && !(tokens[AGE_AT_FIRST_CHILD].toIntOrNull() == null && tokens[CHILDREN_OF_MALE_OVER_44].toInt() > 0)
                && tokens[trait].isNotEmpty()
            ) {
                try {
                    list.add(
                        Person(
                            yearBorn = tokens[YEAR_BORN].toInt(),
                            numberOfChildren = tokens[CHILDREN_OF_MALE_OVER_44].toFloat() / 2,
                            ageAtFirstChild = tokens[AGE_AT_FIRST_CHILD].toIntOrNull() ?: 0,
                            trait = tokens[trait].toDouble()
                        )
                    )
                } catch (t: Throwable) {
                    println("Caught exception reading line: $count")
                    t.printStackTrace()
                }
            }
            line = fileReader.readLine()
        }
        fileReader.close()
    }

}