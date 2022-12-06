package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
    }

    fun lottoSort() : List<Int>{
        return numbers.sorted()
    }

    // TODO: 추가 기능 구현
}
