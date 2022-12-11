/**
 * https://adventofcode.com/2022/day/10
 */
fun day11Task1(input: String): Long {

    val monkeys = input.split("Monkey ").drop(1).map {
        Monkey.parse(it)
    }.toMutableList()

    return calculateMonkeyBusiness(monkeys = monkeys, amountOfRounds = 20, shouldDivideBy3 = true)
}

fun day11Task2(input: String): Long {

    val monkeys = input.split("Monkey ").drop(1).map {
        Monkey.parse(it)
    }.toList()

    return calculateMonkeyBusiness(monkeys = monkeys, amountOfRounds = 10_000, shouldDivideBy3 = false)
}

fun calculateMonkeyBusiness(monkeys: List<Monkey>, amountOfRounds: Int, shouldDivideBy3: Boolean): Long {

    // I had to cheat to get this one. Apparently I need to understand the Chinese Remainder Theorem to understand why
    // modulusing by the multiple of all the (prime) values here is necessary/fine?
    val clamp = monkeys.map { it.testDivisible }.reduce { a, b -> a * b }

    repeat(amountOfRounds) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                val operationValue = monkey.operation.doOperation(item)
                val nuItemValue = if (shouldDivideBy3) {
                    operationValue/3
                } else {
                    operationValue
                } % clamp
                val test = (nuItemValue % monkey.testDivisible) == 0L
                monkeys[monkey.indexOfThrow(test)].items.add(nuItemValue)
            }
            monkey.inspectionsDone += monkey.items.size
            monkey.items.clear()
        }
    }

    val (monkey1, monkey2) = monkeys.sortedByDescending { it.inspectionsDone }
    return monkey1.inspectionsDone * monkey2.inspectionsDone
}


class Monkey(
    val items: MutableList<Long>,
    val operation: MonkeyOperation,
    val testDivisible: Long,
    val ifTrue: Int,
    val ifFalse: Int,
    var inspectionsDone: Long = 0,
) {

    fun indexOfThrow(testResult: Boolean): Int {
        return if (testResult) ifTrue else ifFalse
    }

    companion object {
        fun parse(input: String): Monkey {
            val lines = input.splitByLine()
            val numbersRegex = Regex("""\d+""")

            return Monkey(
                items = numbersRegex.findAll(lines[1]).map { it.value.toLong() }.toMutableList(),
                operation =  MonkeyOperation.parse(lines[2]),
                testDivisible = numbersRegex.find(lines[3])!!.value.toLong(),
                ifTrue = numbersRegex.find(lines[4])!!.value.toInt(),
                ifFalse = numbersRegex.find(lines[5])!!.value.toInt()
            )
        }
    }
}

sealed class MonkeyOperation() {
    class Add(val amount: Int): MonkeyOperation()
    class Multiply(val amount: Int): MonkeyOperation()
    object Square: MonkeyOperation()

    fun doOperation(input: Long): Long {
        return when (this) {
            is Add -> input + amount
            is Multiply -> input * amount
            Square -> input*input
        }
    }

    companion object {
        fun parse(input: String): MonkeyOperation {
            val amount = Regex("""\d+""").find(input)?.value?.toInt()
            return when {
                amount == null -> Square
                input.contains("+") -> Add(amount)
                else -> Multiply(amount)
            }
        }
    }
}