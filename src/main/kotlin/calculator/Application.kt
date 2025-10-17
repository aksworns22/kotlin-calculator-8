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

    fun sum(): Int {
        return numbers.sum()
    }
}

class StructuredInput {
    val delimiters: Array<String>
    val content: String

    constructor(input: String) {
        val separatedInput = separateDelimiterAndContent(input)
        this.content = separatedInput.content
        this.delimiters = if (separatedInput.customDelimiter != null) {
            arrayOf(",", ":", separatedInput.customDelimiter)
        } else {
            arrayOf(",", ":")
        }
        isValidContent(delimiters, content)
    }

    fun extractPositiveNumbers(): PositiveNumbers {
        if (content.isEmpty()) {
            return PositiveNumbers(listOf())
        }
        val numbers: MutableList<Int> = mutableListOf()
        for (number in content.split(*delimiters)) {
            if (number.isNotEmpty()) {
                numbers.add(number.toInt())
            }
        }
        return PositiveNumbers(numbers)
    }

    private fun isValidContent(delimiters: Array<String>, content: String): String {
        var isMustBeNumber = true
        for (character in content) {
            if (isMustBeNumber) {
                if (!character.isDigit()) throw IllegalArgumentException()
                else isMustBeNumber = false
            } else { // delimiters
                if (!delimiters.contains(character.toString())) throw IllegalArgumentException()
                isMustBeNumber = true
            }
        }
        return content
    }

    private fun separateDelimiterAndContent(input: String): SeparatedInput {
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

fun main() {
    // TODO: 프로그램 구현
}
