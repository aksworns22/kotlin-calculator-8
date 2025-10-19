package calculator

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
            numbers.add(number.toInt())
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