import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2Test {

    @Test
    fun `day 2 task 1 test`() {

        assertEquals(15, day2Task1(Day2Inputs.testInput))

        val realInput = Day2Test::class.java.getResource("inputs/Day2.txt")!!.readText()
        assertEquals(13005, day2Task1(realInput))
    }

    @Test
    fun `day 2 task 2 test`() {

        assertEquals(12, day2Task2(Day2Inputs.testInput))

        val realInput = Day2Test::class.java.getResource("inputs/Day2.txt")!!.readText()
        assertEquals(11373, day2Task2(realInput))
    }
}

object Day2Inputs {
    const val testInput = """A Y
B X
C Z"""
}