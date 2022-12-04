import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day4Test {

    @Test
    fun `day 4 task 1 test`() {

        assertEquals(2, day4Task1(Day4Inputs.testInput))

        val realInput = this::class.java.getResource("inputs/Day4.txt")!!.readText()
        assertEquals(599, day4Task1(realInput))
    }

    @Test
    fun `day 4 task 2 test`() {

        assertEquals(4, day4Task2(Day4Inputs.testInput))

        val realInput = this::class.java.getResource("inputs/Day4.txt")!!.readText()
        assertEquals(928, day4Task2(realInput))
    }
}

private object Day4Inputs {
    const val testInput = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""
}