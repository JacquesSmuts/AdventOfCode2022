import kotlin.math.abs

/**
 * https://adventofcode.com/2022/day/12
 */

fun day12Task1(input: String): Int {

    val map = MapData.parse(input)

    val shortestPath = calculateShortestPath(map)!!
    val width = map.width
    println()
    map.heightMap.forEach {
        print(if (it in shortestPath) Char(it.z+97) else ".")
        if ((it.x+1) % width == 0) {
            println()
        }
    }
    println()
    return shortestPath.size -1
}

fun day12Task2(input: String): Int {

    val initialMap = MapData.parse(input)

    val allPossibleMaps = initialMap.heightMap.filter { it.z == 0 }.map {
        MapData(
            startingCoordinates = it,
            goalCoordinates = initialMap.goalCoordinates,
            heightMap = initialMap.heightMap
        )
    }

    return allPossibleMaps.minOf {
        calculateShortestPath(it)?.size ?: Int.MAX_VALUE
    } - 1
}

/**
 * Largely stolen from https://gist.github.com/EdwarDDay/2df9134bcc2fc09c842adc4bf6b74050
 */
tailrec fun calculateShortestPath(
    mapData: MapData,
    paths: List<AStarPath> = listOf(mapData.aStarElement(listOf(mapData.startingCoordinates), 0)),
    walkedPath: Set<HeightCoordinates> = emptySet()
): List<HeightCoordinates>? {
    if (paths.isEmpty()) {
        return null
    }
    val aStarElement = paths[0]
    val tail = paths.drop(1)
    return when (aStarElement.pathEnd) {
        mapData.goalCoordinates -> aStarElement.path
        in walkedPath -> calculateShortestPath(
            mapData = mapData,
            paths = tail,
            walkedPath = walkedPath
        )
        else -> calculateShortestPath(
            mapData = mapData,
            paths = (tail + mapData.walkableNeighbours(aStarElement)),
            walkedPath = walkedPath + aStarElement.pathEnd
        )
    }
}

data class HeightCoordinates(val x: Int, val y: Int, val z: Int) {

}

class MapData(
    val startingCoordinates: HeightCoordinates,
    val goalCoordinates: HeightCoordinates,
    val heightMap: List<HeightCoordinates>
) {

    val width get()= heightMap.filter { it.y == 0 }.size

    fun aStarElement(path: List<HeightCoordinates>, realCost: Int) =
        AStarPath(path, realCost, approximateShortestDistance(path.last()))

    private fun approximateShortestDistance(point: HeightCoordinates): Int {
        val heightToGo = abs(goalCoordinates.y - point.y)
        val widthToGo = abs(goalCoordinates.x - point.x)
        val minToGo = minOf(heightToGo, widthToGo)
        val maxToGo = maxOf(heightToGo, widthToGo)
        return minToGo + (maxToGo - minToGo)
    }

    fun walkableNeighbours(aStarPath: AStarPath): List<AStarPath> {
        val heightCoordinates = aStarPath.pathEnd
        val x = heightCoordinates.x
        val y = heightCoordinates.y
        val z = heightCoordinates.z
        val listOfPoints = listOf(
            (x-1 to y),
            (x+1 to y),
            (x to y-1),
            (x to y +1)
        )

        val validNeighbours = heightMap.filter { coord ->
            listOfPoints.any { pair ->
                // find neighbors on x/y-axis, but also filter out those that are too high
                pair.first == coord.x && pair.second == coord.y && (coord.z - z <= 1)
            }
        }

        return validNeighbours.map { point ->
            aStarElement(aStarPath.path + point, aStarPath.realCost + 1)
        }
    }

    companion object {
        fun parse(input: String): MapData {
            val heightMap = mutableListOf<HeightCoordinates>()
            var startingCoordinates: HeightCoordinates? = null
            var goalCoordinates: HeightCoordinates? = null
            input
                .splitByLine()
                .forEachIndexed { y, line ->
                    line.forEachIndexed { x, char ->
                        // In Ascii code: a = 97
                        val z = when (char) {
                            'S' -> {
                                startingCoordinates = HeightCoordinates(x,y,0)
                                0
                            }
                            'E' -> {
                                goalCoordinates = HeightCoordinates(x,y,25)
                                25
                            }
                            else -> char.code - 97
                        }
                        heightMap.add(HeightCoordinates(x, y, z))
                    }
                }

            return MapData(
                startingCoordinates!!,
                goalCoordinates!!,
                heightMap,
            )
        }
    }
}

data class AStarPath(
    val path: List<HeightCoordinates>,
    val realCost: Int,
    private val approximateCost: Int
): Comparable<AStarPath> {
    val pathEnd: HeightCoordinates get() = path.last()

    override fun compareTo(other: AStarPath): Int = cost - other.cost

    private val cost get() = realCost + approximateCost
}
