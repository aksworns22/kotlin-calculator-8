package calculator

fun isValidCustomDelimiter(input: String): Boolean {
    if (input.length < 5) return false
    if (input.take(2) != "//") {
        return false
    }
    if (input.substring(3, 5) != "\\n") {
        return false
    }
    return true
}

fun parser(input: String): List<Int> {
    if (input.isEmpty()) {
        return listOf()
    }
    var hasCustomDelimiter: Boolean = false
    val delimiters = if (isValidCustomDelimiter(input)) {
        hasCustomDelimiter = true
        arrayOf(",", ":", "@")
    } else {
        arrayOf(",", ":")
    }

    if (hasCustomDelimiter) {
        val rawNumbers: List<String> = input.drop(5).split(*delimiters)
        val cleanedNumbers: MutableList<Int> = mutableListOf()
        for (number in rawNumbers) {
            if (number.isNotEmpty()) {
                cleanedNumbers.add(number.toInt())
            }
        }
        return cleanedNumbers
    } else {
        val rawNumbers: List<String> = input.split(*delimiters)
        val cleanedNumbers: MutableList<Int> = mutableListOf()
        for (number in rawNumbers) {
            if (number.isNotEmpty()) {
                cleanedNumbers.add(number.toInt())
            }
        }
        // 구분자만 입력받은 경우
        if (cleanedNumbers.isEmpty()) {
            throw IllegalArgumentException()
        }
        return cleanedNumbers
    }
}

fun calculator(numbers: List<Int>): Int {
    return numbers.sum()
}

fun main() {
    // TODO: 프로그램 구현
}
