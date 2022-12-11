import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `day 11 task 1 test`() {

        Assertions.assertEquals(10605, day11Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day11.txt")!!.readText()
        Assertions.assertEquals(90294, day11Task1(realInput))
    }

    @Test
    fun `day 11 task 2 test`() {

        Assertions.assertEquals(2713310158, day11Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day11.txt")!!.readText()
        Assertions.assertEquals(18170818354, day11Task2(realInput))
    }

    companion object {

        const val testInput = """Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1"""

    }

}