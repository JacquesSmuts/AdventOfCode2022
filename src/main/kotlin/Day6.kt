/**
 * https://adventofcode.com/2022/day/6
 */
fun day6Task1(input: String, sizeOfPacket: Int = 4): Int {

    input.windowed(size = sizeOfPacket, step = 1).forEachIndexed { index, text ->
        if (text.toList().distinct().size >= sizeOfPacket) {
            return index + sizeOfPacket
        }
    }

    return 0
}

fun day6Task2(input: String): Int = day6Task1(input, 14)