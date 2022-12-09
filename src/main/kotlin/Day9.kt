import kotlin.math.abs
import kotlin.math.sign

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

    val ropePosList = MutableList(ropeLength) { 0 to 0 }
    val tPosHistory = mutableSetOf<Pair<Int, Int>>()

    input.splitByLine().forEach { line ->

        val move = Move.parse(line)

        for (i in 0 until move.distance) {
            ropePosList.forEachIndexed { index, pos ->
                if (index == 0) {
                    ropePosList[index] = moveHead(pos, move)
                } else {
                    ropePosList[index] = followLeader(ropePosList[index - 1], pos)
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

fun followLeader(leader: Pair<Int, Int>, follower: Pair<Int, Int>): Pair<Int,Int> {

    val (lx, ly) = leader
    val (fx, fy) = follower

    return if (abs(lx-fx) <=1 && abs(ly-fy) <= 1) {
        follower
    } else {
        fx + (lx - fx).sign to fy + (ly - fy).sign
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