import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun `day 6 task 1 test`() {

        Assertions.assertEquals(10, day6Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day6.txt")!!.readText()
        Assertions.assertEquals(1262, day6Task1(realInput))
    }

    @Test
    fun `day 6 task 2 test`() {

        Assertions.assertEquals(29, day6Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day6.txt")!!.readText()
        Assertions.assertEquals(3444, day6Task2(realInput))
    }

    companion object {

        const val testInput = """nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"""

    }

}