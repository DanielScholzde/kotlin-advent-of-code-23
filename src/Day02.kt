import kotlin.math.max

fun main() {

    val bagItems = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun part1(input: List<String>): Int =
        input.sumOf { row ->
            val gameId = row.substringBefore(":").removePrefix("Game").trim().toInt()
            val gameIsPossible = row.substringAfter(":").split(';').all { singleDrawingStr ->
                val singleDrawing = singleDrawingStr.split(',').associate { countAndColorStr ->
                    countAndColorStr.trim().split(' ').let {
                        val count = it[0].toInt()
                        val color = it[1]
                        color to count
                    }
                }
                val drawIsPossible = bagItems.all { (color, count) -> (singleDrawing[color] ?: 0) <= count }
                drawIsPossible
            }
            if (gameIsPossible) gameId else 0
        }

    fun part2(input: List<String>): Int =
        input.sumOf { row ->
            val map = row.substringAfter(":").split(';').flatMap { singleDrawingStr ->
                singleDrawingStr.split(',').map { countAndColorStr ->
                    countAndColorStr.trim().split(' ').let {
                        val count = it[0].toInt()
                        val color = it[1]
                        color to count
                    }
                }
            }.fold(mapOf<String, Int>()) { accumulator, (color, count) ->
                accumulator + mapOf(color to max(accumulator[color] ?: 0, count))
            }

            val power = map.values.fold(1) { accumulator, count -> accumulator * count }
            power
        }

    // test if implementation meets criteria from the description:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}