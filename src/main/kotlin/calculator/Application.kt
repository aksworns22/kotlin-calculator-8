package calculator

data class SeparatedInput(val customDelimiter: Delimiter?, val content: String)

class Delimiter {
    val defaultDelimiters = arrayOf(",", ":")
    val customDelimiter: String?

    constructor(input: String) {
        if (input.length < 5) {
            customDelimiter = null
            return
        }
        if (input.take(2) != "//") {
            customDelimiter = null
            return
        }
        if (input.substring(3, 5) != "\\n") {
            throw IllegalArgumentException()
        }
        this.customDelimiter = input[2].toString()
    }

    fun getDelimiters(): Array<String> {
        return if (customDelimiter != null) {
            arrayOf(*defaultDelimiters, customDelimiter)
        } else {
            defaultDelimiters
        }
    }
}

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
    val delimiters: Delimiter
    val content: String

    constructor(input: String) {
        val separatedInput = separateDelimiterAndContent(input)
        this.content = separatedInput.content
        this.delimiters = Delimiter(input)
        isValidContent(delimiters, content)
    }

    fun extractPositiveNumbers(): PositiveNumbers {
        if (content.isEmpty()) {
            return PositiveNumbers(listOf())
        }
        val numbers: MutableList<Int> = mutableListOf()
        for (number in content.split(*(delimiters.getDelimiters()))) {
            if (number.isNotEmpty()) {
                numbers.add(number.toInt())
            }
        }
        return PositiveNumbers(numbers)
    }

    private fun isValidContent(delimiters: Delimiter, content: String): String {
        var isMustBeNumber = true
        for (character in content) {
            if (isMustBeNumber) {
                if (!character.isDigit()) throw IllegalArgumentException()
                else isMustBeNumber = false
            } else { // delimiters
                if (!delimiters.getDelimiters().contains(character.toString())) throw IllegalArgumentException()
                isMustBeNumber = true
            }
        }
        return content
    }

    private fun separateDelimiterAndContent(input: String): SeparatedInput {
        if (input.isEmpty()) {
            return SeparatedInput(null, input)
        }
        val customDelimiter = Delimiter(input)
        return if (customDelimiter.customDelimiter == null) {
            SeparatedInput(null, input)
        } else {
            SeparatedInput(customDelimiter, input.drop(5))
        }
    }
}


fun main() {
    // TODO: 프로그램 구현
}
