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

    val regex = Regex(numbersAsMap.keys.joinToString("|"))

    input.sumOf { row ->
        val first = regex.find(row)!!.value

        var last = "-"
        var i = -1
        do {
            val matchResult = regex.find(row, i + 1) ?: break
            last = matchResult.value
            i = matchResult.range.first
        } while (true)

        (numbersAsMap[first].toString() + numbersAsMap[last]).toInt()

    }.println()

}
