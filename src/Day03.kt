import kotlin.math.max
import kotlin.math.min

fun main() {

    val numberRegex = Regex("[0-9]+")
    val dotsRegex = Regex("\\.+")
    val gearRegex = Regex("\\*")

    fun part1(input: List<String>): Int =
        input.mapIndexed { rowIndex, row ->
            numberRegex.findAll(row).sumOf {
                val first = it.range.first
                val last = it.range.last
                if (
                    (first > 0 && row[first - 1] != '.')
                    || (last < row.lastIndex && row[last + 1] != '.')
                    || (rowIndex > 0 &&
                            !input[rowIndex - 1].substring(max(0, first - 1)..min(last + 1, row.lastIndex))
                                .matches(dotsRegex))
                    || (rowIndex < input.lastIndex &&
                            !input[rowIndex + 1].substring(max(0, first - 1)..min(last + 1, row.lastIndex))
                                .matches(dotsRegex))
                ) {
                    it.value.toInt()
                } else {
                    0
                }
            }
        }.sum()


    fun part2(input: List<String>): Int {

        val gears = input.flatMapIndexed { rowIndex, row ->
            gearRegex.findAll(row).map {
                val pos = it.range.first
                rowIndex to pos
            }.toList()
        }

        val numbers = input.flatMapIndexed { rowIndex, row ->
            numberRegex.findAll(row).map {
                val firstPos = it.range.first
                val lastPos = it.range.last
                Triple(it.value.toInt(), rowIndex, (firstPos..lastPos))
            }
        }

        return gears.sumOf { (gearRow, gearCol) ->
            val adjacentNumbers = numbers.filter { (_, numberRow, numberPos) ->
                numberRow == gearRow && (gearCol == numberPos.first - 1 || gearCol == numberPos.last + 1)
                        || numberRow == gearRow - 1 && (gearCol in numberPos.first - 1..numberPos.last + 1)
                        || numberRow == gearRow + 1 && (gearCol in numberPos.first - 1..numberPos.last + 1)
            }.map { it.first }
            if (adjacentNumbers.size == 2) {
                adjacentNumbers[0] * adjacentNumbers[1]
            } else 0
        }
    }


    // test if implementation meets criteria from the description:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

}