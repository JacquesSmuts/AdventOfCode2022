import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun `day 7 task 1 test`() {

        Assertions.assertEquals(95437, day7Task1(testInput))

        val realInput = this::class.java.getResource("inputs/Day7.txt")!!.readText()
        Assertions.assertEquals(1297159, day7Task1(realInput))
    }

    @Test
    fun `day 7 task 2 test`() {

        Assertions.assertEquals(24933642, day7Task2(testInput))

        val realInput = this::class.java.getResource("inputs/Day7.txt")!!.readText()
        Assertions.assertEquals(3866390, day7Task2(realInput))
    }

    companion object {

        const val testInput = """${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"""

    }

}