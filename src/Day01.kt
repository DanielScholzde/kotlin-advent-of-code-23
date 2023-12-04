fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { row ->
            val firstDigit = row.first { it in '0'..'9' }
            val lastDigit = row.last { it in '0'..'9' }
            (firstDigit.toString() + lastDigit).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val numbersAsMap = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
            .mapIndexed { index, numberAsWord -> numberAsWord to index + 1 }.toMap() +
                (1..9).associateBy { it.toString() }

        val regexFirst = Regex(numbersAsMap.keys.joinToString("|"))
        val regexLast = Regex(numbersAsMap.keys.joinToString("|") { it.reversed() })

        return input.sumOf { row ->
            val first = regexFirst.find(row)!!.value
            val last = regexLast.find(row.reversed())!!.value.reversed()

            (numbersAsMap[first].toString() + numbersAsMap[last]).toInt()
        }
    }

    check(part1(readInput("Day01_test1")) == 142)
    check(part2(readInput("Day01_test2")) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()

}
