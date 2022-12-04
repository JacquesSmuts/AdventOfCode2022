/**
 * https://adventofcode.com/2022/day/3
 */
fun day3Task1(input: String): Int {

    return input.splitByLine()
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
 */
fun matchingChar(left: String, right: String): Char {
    return left.toCharArray().intersect(right.toCharArray().toSet()).first()
}

/**
 * https://adventofcode.com/2022/day/3#part2
 */
fun day3Task2(input: String): Int {

    return input.splitByLine()
        .chunked(3)
        .sumOf { groupOfThree ->
            priorityValueOfGroupOfThree(groupOfThree)
        }
}


/**
 * Find char that is in all 3 lines, and return that priority
 */
fun priorityValueOfGroupOfThree(groupOfThree: List<String>): Int {

    val char = groupOfThree[0].toCharArray()
        .intersect(groupOfThree[1].toCharArray().toSet())
        .intersect(groupOfThree[2].toCharArray().toSet()).first()

    return priorityValueOfChar(char)
}