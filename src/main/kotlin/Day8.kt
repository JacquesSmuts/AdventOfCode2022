import kotlin.math.max

/**
 * https://adventofcode.com/2022/day/8
 *
 */
fun day8Task1(input: String): Int {

    val treeMap = buildTreeMap(input)

    var visibleTrees = 0
    val maxX = treeMap.maxBy { map -> map.key.first }.key.first
    val maxY = treeMap.maxBy { map -> map.key.second }.key.second

    treeMap.forEach { tree ->
        val (x, y) = tree.key
        val height = tree.value

        if (x <= 0 || x >= maxX || y <= 0 || y >= maxY){
            visibleTrees += 1
            return@forEach
        }

        val smallerTreesToLeft = treeMap.filter {
            it.key.first < x && it.key.second == y && it.value < height
        }.size
        val smallerTreesToRight = treeMap.filter {
            it.key.first > x && it.key.second == y && it.value < height
        }.size
        val smallerTreesAbove = treeMap.filter {
            it.key.first == x && it.key.second < y && it.value < height
        }.size
        val smallerTreesBelow = treeMap.filter {
            it.key.first == x && it.key.second > y && it.value < height
        }.size

        if (smallerTreesToLeft >= x ||
            smallerTreesToRight >= (maxX - x) ||
            smallerTreesAbove >= y ||
            smallerTreesBelow >= (maxY - y)
            ) {
            visibleTrees += 1
        }
    }

    return visibleTrees
}

fun day8Task2(input: String): Int {

    val treeMap = buildTreeMap(input)

    val maxX = treeMap.maxBy { map -> map.key.first }.key.first
    val maxY = treeMap.maxBy { map -> map.key.second }.key.second

    var bestScenicScore = 0

    treeMap.forEach { tree ->
        val (x, y) = tree.key
        val height = tree.value

        val indexOfFirstVisibleTreeOnLeft = ((x-1) downTo 0).firstOrNull { walkingX ->
            (treeMap[walkingX to y] ?: 0) >= height
        } ?: 0
        val visibleTreesToLeft = x - indexOfFirstVisibleTreeOnLeft

        val indexOfFirstVisibleTreeOnRight = ((x+1)..maxX).firstOrNull { walkingX ->
            (treeMap[walkingX to y] ?: 0) >= height
        } ?: maxX
        val visibleTreesToRight = indexOfFirstVisibleTreeOnRight - x

        val indexOfFirstVisibleTreeAbove = ((y-1) downTo 0).firstOrNull { walkingY ->
            (treeMap[x to walkingY] ?: 0) >= height
        } ?: 0
        val visibleTreesAbove = y - indexOfFirstVisibleTreeAbove

        val indexOfFirstVisibleTreeBelow = ((y+1)..maxY).firstOrNull { walkingY ->
            (treeMap[x to walkingY] ?: 0) >= height
        } ?: maxY
        val visibleTreesBelow = indexOfFirstVisibleTreeBelow - y

        bestScenicScore = max(bestScenicScore, visibleTreesToLeft*visibleTreesToRight*visibleTreesAbove*visibleTreesBelow)
    }

    return bestScenicScore

}

fun buildTreeMap(input: String): Map<Pair<Int, Int>, Int> {

    val treeMap = mutableMapOf<Pair<Int,Int>, Int>()

    input.splitByLine().forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            treeMap[x to y] = char.digitToInt()
        }
    }
    return treeMap
}