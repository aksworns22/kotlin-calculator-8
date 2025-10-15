package calculator

fun parser(input: String): List<Int> {
    if (input.isEmpty()) {
        return listOf()
    }
    val rawNumbers: List<String> = input.split(",")
    return rawNumbers.map { it.toInt() }
}

fun calculator(input: String): Int {
    val numbers = parser(input)
    return numbers.sum()
}

fun main() {
    // TODO: 프로그램 구현
}
