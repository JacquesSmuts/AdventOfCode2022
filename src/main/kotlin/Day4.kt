/**
 * https://adventofcode.com/2022/day/4
 */
fun day4Task1(input: String): Int {

    return input.splitByLine()
        .map {
            it.split(",")
        }.filter {
            oneRangeContainsOther(it)
        }.size
}

/**
 * input: 2-4, 3-4 == true
 * input: 2-4, 3-6 == false
 * input: 2-4, 5-9 == false
 */
fun oneRangeContainsOther(ranges: List<String>): Boolean {

    val range1 = ranges[0].split("-").map { it.toInt() }
    val range2 = ranges[1].split("-").map { it.toInt() }

    return (range1[0] <= range2[0] && range1[1] >= range2[1]) ||
            (range1[0] >= range2[0] && range1[1] <= range2[1])
}

/**
 * https://adventofcode.com/2022/day/4
 */
fun day4Task2(input: String): Int {

    return input.splitByLine()
        .map {
            it.split(",")
        }.filter {
            rangesOverlap(it)
        }.size
}

/**
 * input: 2-4, 3-4 == true
 * input: 2-4, 3-6 == true
 * input: 2-4, 5-9 == false
 */
fun rangesOverlap(ranges: List<String>): Boolean {

    val range1 = ranges[0].split("-").map { it.toInt() }
    val range2 = ranges[1].split("-").map { it.toInt() }

    return (range2[0] >= range1[0] && range2[0] <= range1[1]) ||
            (range2[1] >= range1[0] && range2[1] <= range1[1]) ||
            (range1[0] >= range2[0] && range1[0] <= range2[1]) ||
            (range1[1] >= range2[0] && range1[1] <= range2[1])
}
