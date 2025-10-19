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
                if (!character.isDigit()) throw IllegalArgumentException("잘못된 문법을 사용함")
                else isMustBeNumber = false
            } else { // delimiters
                if (character.isDigit()) continue
                if (!delimiters.getDelimiters().contains(character.toString())) {
                    throw IllegalArgumentException("정의되지 않은 문자는 사용할 수 없음")
                }
                isMustBeNumber = true
            }
        }
        if (isMustBeNumber) throw IllegalArgumentException("구분자로 입력을 끝낼 수 없음")
        return content
    }
}