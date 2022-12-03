import java.lang.IllegalArgumentException

/**
 * https://adventofcode.com/2022/day/3
 */
fun day3Task1(input: String): Int {

    return input.replace("\r", "")
        .split("\n")
        .sumOf {  line ->
            priorityValueOfLine(line)
        }

}

fun priorityValueOfLine(line: String): Int {
    return priorityValueOfChar(
        matchingChar(
            left = line.take(line.length/2),
            right = line.takeLast(line.length/2)
        )
    )
}

/**
 * @return priority value of char where a=1, z=26,A=27,Z=52
 */
fun priorityValueOfChar(matchingChar: Char): Int {

    // Turn into Ascii value
    val code = matchingChar.code
    // In Ascii: A = 65, a = 97

    return if (code > 96) {
        code - 96
    } else {
        code - 38
    }
}

/**
 * Find char that matches left and right string.
 *
 * @throws IllegalArgumentException
 */
fun matchingChar(left: String, right: String): Char {

    left.forEach { leftChar ->
        if (right.contains(leftChar)) {
            return leftChar
        }
    }
    throw IllegalArgumentException("No Matches found")
}

/**
 * https://adventofcode.com/2022/day/3#part2
 */
fun day3Task2(input: String): Int {

    return input.replace("\r", "")
        .split("\n")
        .chunked(3)
        .sumOf { groupOfThree ->
            priorityValueOfGroupOfThree(groupOfThree)
        }
}


/**
 * Find char that is in all 3 lines, and return that priority
 *
 * @throws IllegalArgumentException
 */
fun priorityValueOfGroupOfThree(groupOfThree: List<String>): Int {

    groupOfThree[0].forEach { char ->
        if (groupOfThree[1].contains(char) && groupOfThree[2].contains(char)) {
            return priorityValueOfChar(char)
        }
    }
    throw IllegalArgumentException("No Matches found")
}