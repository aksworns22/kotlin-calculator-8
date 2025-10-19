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