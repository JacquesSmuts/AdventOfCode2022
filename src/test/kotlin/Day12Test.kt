import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `day 12 task 1 test`() {

        Assertions.assertEquals(31, day12Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day12.txt")!!.readText()
        Assertions.assertEquals(339, day12Task1(realInput))
    }

    @Test
    fun `day 12 task 2 test`() {

        Assertions.assertEquals(29, day12Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day12.txt")!!.readText()
        Assertions.assertEquals(332, day12Task2(realInput))
    }

    companion object {

        const val testInput = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""

    }

}