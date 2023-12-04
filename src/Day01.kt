fun main() {

    val input = readInput("Day01")

    // part 1

    input.sumOf { row ->
        val firstDigit = row.first { it in '0'..'9' }
        val lastDigit = row.last { it in '0'..'9' }
        (firstDigit.toString() + lastDigit).toInt()
    }.println()

    // part 2

    val numbersAsMap = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        .mapIndexed { index, numberAsWord -> numberAsWord to index + 1 }.toMap() +
            (1..9).associateBy { it.toString() }

    val regexFirst = Regex(numbersAsMap.keys.joinToString("|"))
    val regexLast = Regex(numbersAsMap.keys.joinToString("|") { it.reversed() })

    input.sumOf { row ->
        val first = regexFirst.find(row)!!.value
        val last = regexLast.find(row.reversed())!!.value.reversed()

        (numbersAsMap[first].toString() + numbersAsMap[last]).toInt()

    }.println()

}
