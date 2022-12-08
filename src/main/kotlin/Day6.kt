/**
 * https://adventofcode.com/2022/day/6
 */
fun day6Task1(input: String, sizeOfPacket: Int = 4): Int {

    return input.windowed(size = sizeOfPacket, step = 1).indexOfFirst { text ->
        text.toList().distinct().size >= sizeOfPacket
    } + sizeOfPacket
}

fun day6Task2(input: String): Int = day6Task1(input, 14)