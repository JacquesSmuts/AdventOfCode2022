import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `day 5 task 1 test`() {

        Assertions.assertEquals("CMZ", day5Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day5.txt")!!.readText()
        Assertions.assertEquals("VRWBSFZWM", day5Task1(realInput))
    }

    @Test
    fun `day 5 task 2 test`() {

        Assertions.assertEquals("MCD", day5Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day5.txt")!!.readText()
        Assertions.assertEquals("RBTWJWMCF", day5Task2(realInput))
    }

    companion object {

        const val testInput = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""

    }

}