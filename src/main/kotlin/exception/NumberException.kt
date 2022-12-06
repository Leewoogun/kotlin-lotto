package exception

class NumberException {
    fun inputException(input : String) : Int{
        val inputNum = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해주세요.")
        if(inputNum % 1000 != 0){
            throw IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
        }
        return inputNum
    }
}