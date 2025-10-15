package calculator

fun parser(input: String): List<Int> {
    if (input.isEmpty()) {
        return listOf()
    }
    val rawNumbers: List<String> = input.split(",", ":")
    val cleanedNumbers: MutableList<Int> = mutableListOf()
    for (number in rawNumbers) {
        if (number.isNotEmpty()) {
            cleanedNumbers.add(number.toInt())
        }
    }
    return cleanedNumbers
}

fun calculator(input: String): Int {
    val numbers = parser(input)
    return numbers.sum()
}

fun main() {
    // TODO: 프로그램 구현
}
