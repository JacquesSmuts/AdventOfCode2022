import kotlin.math.abs

/**
 * https://adventofcode.com/2022/day/10
 */
fun day10Task1(input: String): Int {

    var cycle = 1
    var x = 1
    var sumOfTwentyCycles = 0

    input.splitByLine().forEach { line ->
        val operation = CycleOperation.parse(line)

        for (i in 0 until operation.cycles) {
            if (cycle == 20 || (cycle-20)%40 == 0){
                sumOfTwentyCycles += cycle*x
            }
            cycle += 1
        }
        x += operation.plusX
    }

    return sumOfTwentyCycles
}

fun day10Task2(input: String): String {

    var cycle = 1
    var x = 1
    var outputLines = ""

    input.splitByLine().forEach { line ->
        val operation = CycleOperation.parse(line)

        for (i in 1..operation.cycles) {
            val position = (cycle-1)%40
            outputLines += if (abs(x - position)<=1) {
                "#"
            } else {
                "."
            }
            cycle += 1
        }
        x += operation.plusX
    }

    return outputLines.chunked(40).joinToString("\n")
}

private sealed class CycleOperation(val cycles: Int, val plusX: Int = 0) {
    object Noop: CycleOperation(1)
    class AddX(amount: Int): CycleOperation(2, amount)

    companion object {
        fun parse(line: String): CycleOperation {
            val inputs = line.split(" ")
            return if (inputs[0] == "noop") {
                Noop
            } else {
                AddX(inputs[1].toInt())
            }
        }
    }
}