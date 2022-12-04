import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day3Test {

    @Test
    fun `day 3 task 1 test`() {

        assertEquals(157, day3Task1(Day3Inputs.testInput))

        val realInput = Day3Test::class.java.getResource("inputs/Day3.txt")!!.readText()
        assertEquals(8298, day3Task1(realInput))
    }

    @Test
    fun `day 3 task 2 test`() {

        assertEquals(70, day3Task2(Day3Inputs.testInput))

        val realInput = Day2Test::class.java.getResource("inputs/Day3.txt")!!.readText()
        assertEquals(2708, day3Task2(realInput))
    }

    private object Day3Inputs {
        const val testInput = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""
    }
}
