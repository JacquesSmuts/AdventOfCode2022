import kotlin.math.abs

/**
 * https://adventofcode.com/2022/day/9
 *
 */
fun day9Task1(input: String): Int {
    return trackTailHistory(input)
}

fun day9Task2(input: String): Int {
    return trackTailHistory(input, 10)
}

fun trackTailHistory(input: String, ropeLength: Int = 2): Int {

    val ropePosList = generateSequence { 0 to 0 }.take(ropeLength).toMutableList()
    val tPosHistory = mutableSetOf<Pair<Int, Int>>()

    input.splitByLine().forEach { line ->

        val move = Move.parse(line)

        for (i in 0 until move.distance) {
            ropePosList.forEachIndexed { index, pos ->
                if (index == 0) {
                    ropePosList[index] = moveHead(pos, move)
                } else {
                    ropePosList[index] = moveTail(ropePosList[index - 1], pos)
                }
            }
            tPosHistory.add(ropePosList.last())
        }
    }

    return tPosHistory.size
}

fun moveHead(pos: Pair<Int,Int>, move: Move): Pair<Int, Int> {
    val (x, y) = pos
    return when (move) {
        is Move.Down -> x to y-1
        is Move.Left -> x-1 to y
        is Move.Right -> x+1 to y
        is Move.Up -> x to y+1
    }
}

fun moveTail(leader: Pair<Int, Int>, follower: Pair<Int, Int>): Pair<Int,Int> {
    val (hx, hy) = leader
    val (tx, ty) = follower

    return when {
        // Touching. Do nothing.
        abs(hx-tx) <=1 && abs(hy-ty) <= 1 -> follower
        // 2 units to the side, in the same line. Move 1 in that direction
        hy==ty && (hx - tx) >= 2 -> tx+1 to ty
        hy==ty && (tx - hx) >= 2 -> tx-1 to ty
        hx==tx && (hy - ty) >= 2 -> tx to ty+1
        hx==tx && (ty - hy) >= 2 -> tx to ty-1
        // diagonal move, move 1 in both x and y towards leader
        else -> {
            val xOp = if (hx > tx) 1 else -1
            val yOp = if (hy > ty) 1 else -1
            tx+xOp to ty + yOp
        }
    }
}

sealed class Move(val distance: Int) {
    class Up(distance: Int): Move(distance)
    class Down(distance: Int): Move(distance)
    class Left(distance: Int): Move(distance)
    class Right(distance: Int): Move(distance)

    companion object {

        fun parse(input: String): Move {
            val (direction, distanceS) = input.split(" ")
            val distance = distanceS.toInt()

            return when (direction) {
                "U" -> Up(distance)
                "R" -> Right(distance)
                "D" -> Down(distance)
                else -> Left(distance)
            }
        }
    }
}