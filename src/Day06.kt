fun main() {

    fun part1(input: List<String>): Int {
        val (timeList, distanceRecordList) = input.map { row ->
            row.substringAfter(":").split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        }
        val races = timeList.zip(distanceRecordList)

        return races.map { race ->
            val (time, distanceRecord) = race
            (1..<time).map { buttonHoldDuration ->
                val speed = buttonHoldDuration
                val timeLeft = time - buttonHoldDuration
                val distance = timeLeft * speed
                if (distance > distanceRecord) 1 else 0
            }.sum()
        }.fold(1) { accumulator, element ->
            accumulator * element
        }
    }

    fun part2(input: List<String>): Int {
        val (time, distanceRecord) = input.map { row ->
            row.substringAfter(":").replace(" ", "").toLong()
        }

        return (1..<time).map { buttonHoldDuration ->
            val speed = buttonHoldDuration
            val timeLeft = time - buttonHoldDuration
            val distance = timeLeft * speed
            if (distance > distanceRecord) 1 else 0
        }.sum()
    }


    val inputTest = readInput("Day06_test")
    check(part1(inputTest) == 288)
    check(part2(inputTest) == 71503)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()

}
