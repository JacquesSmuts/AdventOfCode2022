/**
 * Goal is to split the string input into groups, and find the highest group sum amongst the groups
 * https://adventofcode.com/2022/day/1
 */
fun day1Task1 (input: String): Int {

    return input.split("\n\n").maxOf {
        sumGroup(it)
    }
}

/**
 * Goal is to split the string input into groups, and find the sum of the 3 highest group sums amongst the groups
 * https://adventofcode.com/2022/day/1
 */
fun day1Task2 (input: String): Int {

    return input.split("\n\n").map {
        sumGroup(it)
    }.sortedDescending().take(3).sum()
}

fun sumGroup(input: String): Int {
    return input.split("\n").sumOf { it.toInt() }
}
