import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun `day 9 task 1 test`() {

        Assertions.assertEquals(13, day9Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day9.txt")!!.readText()
        Assertions.assertEquals(6745, day9Task1(realInput))
    }

    @Test
    fun `day 9 task 2 test`() {

        Assertions.assertEquals(1, day9Task2(testInput))
        Assertions.assertEquals(36, day9Task2(testInput2))

        val realInput = this::class.java.getResource("inputs/Day9.txt")!!.readText()
        Assertions.assertEquals(2793, day9Task2(realInput))
    }

    companion object {

        const val testInput = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""

        const val testInput2 = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20"""

    }

}