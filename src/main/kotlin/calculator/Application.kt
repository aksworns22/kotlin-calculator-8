package calculator

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
        if (input[2].isDigit()) throw IllegalArgumentException()
        this.customDelimiter = input[2].toString()
    }

    fun getDelimiters(): Array<String> {
        return if (customDelimiter != null) {
            arrayOf(*defaultDelimiters, customDelimiter)
        } else {
            defaultDelimiters
        }
    }

    fun hasCustomDelimiter(): Boolean {
        return customDelimiter != null
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
        this.delimiters = Delimiter(input)
        if (delimiters.hasCustomDelimiter()) {
            this.content = input.drop(5)
        } else {
            this.content = input
        }
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
        if (content.isEmpty()) return content
        var isMustBeNumber = true
        for (character in content) {
            if (isMustBeNumber) {
                if (!character.isDigit()) throw IllegalArgumentException()
                else isMustBeNumber = false
            } else { // delimiters
                if (character.isDigit()) continue
                if (!delimiters.getDelimiters().contains(character.toString())) throw IllegalArgumentException()
                isMustBeNumber = true
            }
        }
        if (isMustBeNumber) throw IllegalArgumentException() // 마지막이 구분자로 끝나는 경우
        return content
    }
}

fun main() {
    // TODO: 프로그램 구현
}
