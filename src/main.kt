import java.io.BufferedReader
import java.io.FileReader

private const val SURVEY_YEAR = 0  // ranges from 1972 to 2018
private const val YEAR_BORN = 1  // ranges from 1883 to 1977
private const val MALE = 2
private const val FEMALE = 3
private const val FEMALE_OVER_41 = 4
private const val MALE_OVER_44 = 5
private const val CHILDREN_OF_FEMALE_OVER_41 = 6
private const val CHILDREN_OF_MALE_OVER_44 = 7
private const val BORN_IN_US = 8
private const val WHITE = 9
private const val BLACK = 10
private const val SEX = 11
private const val AGE = 12
private const val IQ = 13
private const val YEARS_IN_SCHOOL = 14
private const val HIGHEST_DEGREE = 15
private const val EDUCATION = 16
private const val LOG_INCOME = 17
private const val CONSERVATIVE = 18
private const val REPUBLICAN = 19
private const val RELIGIOUS_ATTENDANCE = 20
private const val STRENGTH_OF_RELIGIOUS_COMMITMENT = 21
private const val TOLERANCE_ATHEIST = 22
private const val TOLERANCE_RACIST = 23
private const val TOLERANCE_COMMUNIST = 24
private const val TOLERANCE_MILITARIST = 25
private const val TOLERANCE_HOMOSEXUAL = 26
private const val CAPITAL_PUNISHMENT = 27
private const val COURTS_AND_CRIME = 28
private const val FUNDAMENTALIST = 29
private const val EXPENDITURE_FOR_SPACE_EXPLORATION = 30
private const val EXPENDITURE_FOR_ENVIRONMENT = 31
private const val EXPENDITURE_FOR_HEALTH = 32
private const val EXPENDITURE_FOR_CITY = 33
private const val EXPENDITURE_FOR_CRIME = 34
private const val EXPENDITURE_FOR_DRUGS = 35
private const val EXPENDITURE_FOR_EDUCATION = 36
private const val EXPENDITURE_FOR_BLACKS = 37
private const val EXPENDITURE_FOR_MILITARY = 38
private const val EXPENDITURE_FOR_FOREIGN_AID = 39
private const val EXPENDITURE_FOR_WELFARE = 40
private const val EXPENDITURE_FOR_HIGHWAYS = 41
private const val EXPENDITURE_FOR_SOCIAL_SECURITY = 42
private const val EXPENDITURE_FOR_TRANSPORTATION = 43
private const val EXPENDITURE_FOR_PARKS = 44
private const val GOVERNMENT_SHOULD_IMPROVE_STANDARD_OF_LIVING = 45
private const val GOVERNMENT_SHOULD_DO_MORE = 46
private const val GOVERNMENT_SHOULD_HELP_PAY_FOR_MEDICAL_CARE = 47
private const val GOVERNMENT_SHOULD_AID_BLACKS = 48
private const val ABORTION_OK = 49  // ranges from 6 to 12
private const val CAUSE_OF_RACE_DIFFERENCES_DISCRIMINATION = 50
private const val CAUSE_OF_RACE_DIFFERENCES_INBORN = 51
private const val CAUSE_OF_RACE_DIFFERENCES_EDUCATION = 52
private const val CAUSE_OF_RACE_DIFFERENCES_WILL = 53
private const val WOMEN_TAKE_CARE_OF_COUNTRY_NOT_HOME = 54
private const val WOMEN_SHOULD_WORK = 55
private const val VOTE_FOR_WOMAN_PRESIDENT = 56
private const val WOMEN_SUITABLE_FOR_POLITICS = 57
private const val MOTHER_WORKING_DOES_NOT_HURT_CHILDREN = 58
private const val WIVES_SHOULD_NOT_HELP_HUSBANDS_CAREER_FIRST = 59
private const val PRESCHOOL_KIDS_DO_NOT_SUFFER_WHEN_MOTHER_WORKS = 60
private const val NOT_BETTER_FOR_WOMEN_TO_DO_HOME_MAKING = 61
private const val TRUST_IN_BANKS = 62
private const val TRUST_IN_BIG_COMPANIES = 63
private const val TRUST_IN_CLERGY = 64
private const val TRUST_IN_EDUCATION = 65
private const val TRUST_IN_EXECUTIVE_GOVERNMENT = 66
private const val TRUST_IN_ORGANIZED_LABOR = 67
private const val TRUST_IN_PRESS = 68
private const val TRUST_IN_MEDICINE = 69
private const val TRUST_IN_TV = 70
private const val TRUST_IN_SUPREME_COURT = 71
private const val TRUST_IN_SCIENCE = 72
private const val TRUST_IN_CONGRESS = 73
private const val TRUST_IN_MILITARY = 74
private const val TRUST_IN_PEOPLE = 75
private const val TAX_TOO_LOW = 76
private const val GOVERNMENT_SHOULD_REDUCE_INCOME_DIFFERENCES = 77
private const val LEGALIZE_POT = 78
private const val PRIVATE_ENTERPRISE_SOLVES_PROBLEMS = 79
private const val GOVERNMENT_CREATE_JOBS = 80
private const val GOVERNMENT_REGULATE_LESS = 81
private const val GOVERNMENT_SUPPORT_NEW_PRODUCTS = 82
private const val GOVERNMENT_SUPPORT_DECLINING_INDUSTRIES = 83
private const val GAY_MARRIAGE_OK = 84
private const val PREMARITAL_SEX_OK = 85
private const val EXTRAMARITAL_SEX_OK = 86
private const val HOMOSEXUAL_SEX_OK = 87
private const val FAVOUR_GUN_PERMITS = 88
private const val FAVOUR_WIRETAPPING = 89
private const val EUTHANASIA_OK = 90
private const val SUICIDE_DISEASE_OK = 91
private const val SUICIDE_OTHER_OK = 92
private const val MISCEGENATION_OK = 93
private const val VOTE_FOR_BLACK_PRESIDENT = 94
private const val RAISE_IMMIGRATION = 95
private const val BLACKS_SHOULD_NOT_PUSH = 96
private const val FAVOUR_BLACK_HIRING_PREFERENCE = 97
private const val HAPPINESS = 98
private const val LOT_OF_AVERAGE_MAN_GETTING_WORSE = 99
private const val NOT_FAIR_MAKING_CHILD = 100
private const val OFFICIALS_NOT_INTERESTED_IN_COMMON_MAN = 101
private const val JOB_HIGH_INCOME = 102
private const val JOB_SECURE = 103
private const val JOB_SHORT_HOURS = 104
private const val JOB_ADVANCEMENT = 105
private const val JOB_IMPORTANT = 106
private const val GET_AHEAD_WITH_HARD_WORK = 107
private const val BETTER_LIVING_STANDARDS_NOW_THAN_PARENTS = 108
private const val BETTER_LIVING_STANDARDS_FOR_CHILDREN_THAN_NOW = 109
private const val IDEAL_NUMBER_OF_CHILDREN = 110
private const val FAVOUR_SEX_EDUCATION = 111
private const val MAKE_DIVORCE_EASIER = 112
private const val LEGALIZE_PORN = 113
private const val FAVOUR_SPANKING = 114
private const val AFRAID_TO_WALK_IN_THE_DARK = 115
private const val NUMBER_OF_CLUB_MEMBERSHIPS = 116
private const val WHITE_BLACK_DIFFERENCE_RICH = 117
private const val WHITE_BLACK_DIFFERENCE_HARD_WORKING = 118
private const val WHITE_BLACK_DIFFERENCE_INTELLIGENCE = 119
private const val REDUCTION_IN_LENGTH_OF_WORK_WEEK = 120
private const val GOVERNMENT_SPENDING_ON_ENVIRONMENT = 121
private const val GOVERNMENT_SPENDING_ON_HEALTH = 122
private const val GOVERNMENT_SPENDING_ON_LAW_ENFORCEMENT = 123
private const val GOVERNMENT_SPENDING_ON_EDUCATION = 124
private const val GOVERNMENT_SPENDING_ON_MILITARY = 125
private const val GOVERNMENT_SPENDING_ON_RETIREMENT_BENEFITS = 126
private const val GOVERNMENT_SPENDING_ON_UNEMPLOYMENT_BENEFITS = 127
private const val GOVERNMENT_SPENDING_ON_CULTURE_AND_ARTS = 128
private const val GOVERNMENT_RESPONSIBILITY_PROVIDE_JOBS_FOR_ALL = 129
private const val GOVERNMENT_RESPONSIBILITY_KEEP_PRICES_UNDER_CONTROL = 130
private const val GOVERNMENT_RESPONSIBILITY_PROVIDE_HEALTH_CARE_FOR_THE_SICK = 131
private const val GOVERNMENT_RESPONSIBILITY_PROVIDE_FOR_ELDERLY = 132
private const val GOVERNMENT_RESPONSIBILITY_ASSIST_INDUSTRIAL_GROWTH = 133
private const val GOVERNMENT_RESPONSIBILITY_PROVIDE_FOR_THE_UNEMPLOYED = 134
private const val GOVERNMENT_RESPONSIBILITY_REDUCE_INCOME_DIFFERENCES = 135
private const val GOVERNMENT_HELP_COLLEGE_STUDENTS = 136
private const val GOVERNMENT_HOUSING_POOR = 137
private const val POLITICAL_INTEREST = 138
private const val DO_NOT_HAVE_ANY_SAY = 139
private const val LIVING_STANDARDS_WILL_IMPROVE = 140
private const val INCOME_GAPS_TOO_BIG = 141
private const val GOVERNMENT_REDUCE_INCOME_GAPS = 142
private const val MEN_OVERWORKING_BAD_FOR_FAMILY = 143
private const val TAKE_ACTIVE_PART_IN_WORLD_AFFAIRS = 144
private const val MARRIAGE_CONDITION = 145
private const val MARRIAGE_TYPE = 146
private const val AGE_AT_FIRST_MARRIAGE = 147
private const val DIVORCED = 148
private const val WIDOWED = 149
private const val AGE_OF_FIRST_CHILD = 150


fun main() {
    val femalesOverForty = loadFemalesOverForty(PREMARITAL_SEX_OK)
    println("\nTrait averages range from: ${femalesOverForty.minBy { it.trait }?.trait} TO ${femalesOverForty.maxBy { it.trait }?.trait}\n\n")
    for (year in 1972 until 2019) {
        val sample = femalesOverForty.filter { it.surveyYear == year }
        if (sample.isEmpty()) continue
        analyzeSample(year, sample)
    }
}

private fun analyzeSample(year: Int, sample: List<FemaleOverForty>) {
    println("SURVEY $year average for trait: " + "%.2f".format(sample.traitAverage()))
    val simulator = Simulator(sample)
    for (generation in 1 until 3) {
        if (!simulator.hasNext()) break
        val nextGeneration = simulator.next()
        println("${year + 41 * generation} average for trait: " + "%.2f".format(nextGeneration.traitAverage()))
    }
    println("###################################\n")
}

private fun loadFemalesOverForty(trait: Int): List<FemaleOverForty> = mutableListOf<FemaleOverForty>().also { list ->
    val fileReader = BufferedReader(FileReader("GSS_fertility_data.csv"))
    var line: String? = fileReader.readLine()
    var count = 0
    while (line != null) {
        count++
        val tokens = line.split(",")
        if (tokens[FEMALE_OVER_41].toIntOrNull() == 1
            && tokens[CHILDREN_OF_FEMALE_OVER_41].isNotEmpty()
            && tokens[trait].isNotEmpty()
        ) {
            try {
                list.add(
                    FemaleOverForty(
                        surveyYear = tokens[SURVEY_YEAR].toInt(),
                        yearBorn = tokens[YEAR_BORN].toInt(),
                        daughters = tokens[CHILDREN_OF_FEMALE_OVER_41].toFloat() / 2,
                        trait = tokens[trait].toFloat()
                    )
                )
            } catch (t: Throwable) {
                println("Caught exception reading line: $count")
                t.printStackTrace()
            }

        }
        line = fileReader.readLine()
        println(tokens.size)
        println(tokens)
    }

    fileReader.close()
}

private fun List<FemaleOverForty>.traitAverage(): Double {
    var average = 0.0
    forEach {
        average += it.trait
    }
    average /= size
    return average
}
