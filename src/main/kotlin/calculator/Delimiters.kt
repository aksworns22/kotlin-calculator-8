package calculator

class Delimiters {
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
            throw IllegalArgumentException("올바르지 않은 커스텀 구분자 문법을 사용함")
        }
        if (input[2].isDigit()) throw IllegalArgumentException("숫자는 커스텀 구분자로 사용 불가능함")
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