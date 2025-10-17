package calculator

data class SeparatedInput(val customDelimiter: String?, val content: String)
class PositiveNumbers {
    val numbers: List<Int>

    constructor(numbers: List<Int>) {
        for (number in numbers) {
            if (number < 0) throw IllegalArgumentException()
        }
        this.numbers = numbers
    }

    override fun equals(other: Any?): Boolean {
        if (other is PositiveNumbers) {
            return other.numbers == this.numbers
        }
        return false
    }

    override fun hashCode(): Int {
        return numbers.hashCode()
    }
}

fun getCustomDelimiter(input: String): String? {
    if (input.length < 5) return null
    if (input.take(2) != "//") {
        return null
    }
    if (input.substring(3, 5) != "\\n") {
        throw IllegalArgumentException()
    }
    return input[2].toString()
}

fun separateDelimiterAndContent(input: String): SeparatedInput {
    if (input.isEmpty()) {
        return SeparatedInput(null, input)
    }
    val customDelimiter = getCustomDelimiter(input)
    return if (customDelimiter == null) {
        SeparatedInput(null, input)
    } else {
        SeparatedInput(customDelimiter, input.drop(5))
    }
}

fun extractNumbers(input: String): PositiveNumbers {
    val separatedInput = separateDelimiterAndContent(input)
    val content: String = separatedInput.content
    val delimiters = if (separatedInput.customDelimiter != null) {
        arrayOf(",", ":", separatedInput.customDelimiter)
    } else {
        arrayOf(",", ":")
    }
    if (content.isEmpty()) {
        return PositiveNumbers(listOf())
    }
    if (!content[0].isDigit()) {
        throw IllegalArgumentException()
    }
    val numbers: MutableList<Int> = mutableListOf()
    for (number in content.split(*delimiters)) {
        if (number.isNotEmpty()) {
            numbers.add(number.toInt())
        }
    }
    return PositiveNumbers(numbers)
}

fun addNumbers(numbers: List<Int>): Int {
    return numbers.sum()
}


fun main() {
    // TODO: 프로그램 구현
}
