fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { row ->
            val content = row.substringAfter(":")
            val split = content.split('|').map { numbers ->
                numbers.split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet()
            }
            val winningNumbers = split[0]
            val numbersIHave = split[1]
            val matchingNumbers = winningNumbers.intersect(numbersIHave)
            val points = if (matchingNumbers.isEmpty()) 0 else 2.pow(matchingNumbers.size - 1)
            points
        }
    }

    fun part2(input: List<String>): Int {

        val allCards = input.associate { row ->
            val cardNo = row.substringBefore(":").removePrefix("Card").trim().toInt()
            val content = row.substringAfter(":")
            val split = content.split('|').map { numbers ->
                numbers.split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet()
            }
            val winningNumbers = split[0]
            val numbersIHave = split[1]
            val matchingNumbers = winningNumbers.intersect(numbersIHave).size
            cardNo to matchingNumbers
        }

        fun countCards(cardNo: Int): Int {
            val matchingNumbers = allCards[cardNo]!!
            return 1 + (1..matchingNumbers).sumOf { countCards(cardNo + it) }
        }

        return allCards.keys.sumOf { cardNo ->
            countCards(cardNo)
        }
    }


    val inputTest = readInput("Day04_test")
    check(part1(inputTest) == 13)
    check(part2(inputTest) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()

}


/*tailrec does not work?! */ fun Int.pow(exp: Int): Int {
    if (exp == 0) return 1
    if (exp == 1) return this
    return this * this.pow(exp - 1)
}