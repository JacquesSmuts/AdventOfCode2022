/**
 * https://adventofcode.com/2022/day/5
 */
fun day5Task1(input: String): String {

    val inputSplit = input.replace("\r", "").split("\n\n")

    val mutableBoxesMap = parseBoxes(inputSplit[0])

    inputSplit[1].splitByLine().forEach {
        parseMove(mutableBoxesMap, it)
    }

    return (0 until mutableBoxesMap.size).map {
        mutableBoxesMap[it]!!.last()
    }.joinToString("")

}

/**
 *             [J]             [B] [W]
 *             [T]     [W] [F] [R] [Z]
 *         [Q] [M]     [J] [R] [W] [H]
 *     [F] [L] [P]     [R] [N] [Z] [G]
 * [F] [M] [S] [Q]     [M] [P] [S] [C]
 * [L] [V] [R] [V] [W] [P] [C] [P] [J]
 * [M] [Z] [V] [S] [S] [V] [Q] [H] [M]
 * [W] [B] [H] [F] [L] [F] [J] [V] [B]
 *  1   2   3   4   5   6   7   8   9
 */
fun parseBoxes(boxes: String): MutableMap<Int, List<Char>> {

    val boxesInLinesReversed = boxes.splitByLine().reversed().drop(0)

    return (0..boxesInLinesReversed[0].length/4).associateWith { i ->
        boxesInLinesReversed.map { it[i*4+1] }.filter { it != ' ' }
    }.toMutableMap()
}


/**
 * move 2 from 8 to 9
 */
fun parseMove(mutableBoxesMap: MutableMap<Int, List<Char>>, move: String) {
    val instructionsList = Regex("/?(\\d+)").findAll(move).map { it.value.toInt() }.toList()
    val amount = instructionsList[0]
    val moveFrom = instructionsList[1]-1 // Because we're using 0-index and they're not
    val moveTo = instructionsList[2]-1 // Because we're using 0-index and they're not
    val charsToRemove = mutableBoxesMap[moveFrom]!!.takeLast(amount)
    mutableBoxesMap[moveFrom] = mutableBoxesMap[moveFrom]!!.dropLast(amount)
    mutableBoxesMap[moveTo] = mutableBoxesMap[moveTo]!!.plus(charsToRemove.reversed())
}


/**
 * move 2 from 8 to 9
 */
fun parseMove2(mutableBoxesMap: MutableMap<Int, List<Char>>, move: String) {
    val instructionsList = Regex("/?(\\d+)").findAll(move).map { it.value.toInt() }.toList()
    val amount = instructionsList[0]
    val moveFrom = instructionsList[1]-1 // Because we're using 0-index and they're not
    val moveTo = instructionsList[2]-1 // Because we're using 0-index and they're not
    val charsToRemove = mutableBoxesMap[moveFrom]!!.takeLast(amount)
    mutableBoxesMap[moveFrom] = mutableBoxesMap[moveFrom]!!.dropLast(amount)
    mutableBoxesMap[moveTo] = mutableBoxesMap[moveTo]!!.plus(charsToRemove)
}


/**
 * https://adventofcode.com/2022/day/5#part2
 */
fun day5Task2(input: String): String {

    val inputSplit = input.replace("\r", "").split("\n\n")

    val mutableBoxesMap = parseBoxes(inputSplit[0])

    inputSplit[1].splitByLine().forEach {
        parseMove2(mutableBoxesMap, it)
    }

    return (0 until mutableBoxesMap.size).map {
        mutableBoxesMap[it]!!.last()
    }.joinToString("")
}