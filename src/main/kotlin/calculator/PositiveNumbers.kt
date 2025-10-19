package calculator

class PositiveNumbers {
    val numbers: List<Int>

    constructor(numbers: List<Int>) {
        for (number in numbers) {
            if (number < 0) throw IllegalArgumentException("음수는 허용되지 않음")
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
        val result = numbers.sum()
        if (result < 0) throw IllegalArgumentException("계산 결과과 데이터 범위를 초과함")
        return result
    }
}