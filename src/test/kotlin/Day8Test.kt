import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun `day 8 task 1 test`() {

        Assertions.assertEquals(21, day8Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day8.txt")!!.readText()
        Assertions.assertEquals(1733, day8Task1(realInput))
    }

    @Test
    fun `day 8 task 2 test`() {

        Assertions.assertEquals(8, day8Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day8.txt")!!.readText()
        Assertions.assertEquals(284648, day8Task2(realInput))
    }

    companion object {

        const val testInput = """30373
25512
65332
33549
35390"""

    }

}