/**
 * Score: Shape + 0/3/6 for w/d/l
 *
 * https://adventofcode.com/2022/day/2
 */
fun day2Task1(input: String): Int {

    return input.replace("\r", "")
        .split("\n")
        .sumOf {
            scoreOfMatch(it)
        }

}

/**
 * @return Victory score + the value of the input
 */
fun scoreOfMatch(input: String): Int {

    val letters = input.split(" ").map {
        moveLetterToMoveNumber(it)
    }

    return scoreOfVictory(letters[0], letters[1]) + letters[1]
}

/**
 * 1/1 = 2/2 = 3/3 = 3
 *
 * 1/2 = 2/3 = 3/1 = 6
 *
 * 2/1 = 3/2 = 1/3 = 0
 */
fun scoreOfVictory(them: Int, you: Int): Int {

    return when {
        them == you -> 3
        (them == 1 && you == 2) || (them == 2 && you == 3) || (them == 3 && you == 1) -> 6
        else -> 0
    }
}

/**
 *  A/X/1 == Rock
 *  B/Y/2 == Paper
 *  C/Z/3 == Scissors
 */
fun moveLetterToMoveNumber(it: String): Int {
    return when (it) {
        "A", "X" -> 1
        "B", "Y" -> 2
        "C", "Z" -> 3
        else -> 0
    }
}

/**
 * ABC = RockPaperScissor
 * XYZ = LoseDrawWin
 */
fun day2Task2(input: String): Int {

    return input.replace("\r", "")
        .split("\n")
        .sumOf {
            scoreOfMatchTask2(it)
        }

}
/**
 * @return Victory score + the value of the input
 */
fun scoreOfMatchTask2(input: String): Int {

    val letters = input.split(" ")
    val them = moveLetterToMoveNumber(letters[0])
    val you = determineMoveNumberFromTactic(them, letters[1])

    return scoreOfVictory(them, you) + you
}

/**
 * X = lose
 * Y = draw
 * Z = win 1/2 = 2/3 = 3/1 = 6
 */
fun determineMoveNumberFromTactic(them: Int, tactic: String): Int {
    return when (tactic) {
        "Z" -> when (them) {
            1 -> 2
            2 -> 3
            else -> 1
        }
        "Y" -> them
        else -> when (them) {
            1 -> 3
            2 -> 1
            else -> 2
        }
    }
}
