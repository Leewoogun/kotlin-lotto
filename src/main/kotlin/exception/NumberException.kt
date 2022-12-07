package exception

import lotto.Lotto


enum class ExceptionMessage(val message : String){
    REQUIRE_INT_MESSAGE("[ERROR] 숫자를 입력해 주세요."),
    REQUIRE_1000_MESSAGE("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
    REQUIRE_6_WINNING_NUM("[ERROR] 당첨 번호는 6개여야 합니다."),
    REQUIRE_SPLIT_COMMA("[ERROR] 숫자는 ,로 구분해주세요."),
    REQUIRE_NUMBER_RANGE("[ERROR] 로또 번호 숫자는 1~45 사이여야 합니다."),
    NOT_DUPLICATE_NUMBER("[ERROR] 중복 숫자는 입력이 불가능 합니다.")
}
class NumberException {
    fun inputException(input : String) : Int{
        val inputNum = input.toIntOrNull() ?: throw IllegalArgumentException(ExceptionMessage.REQUIRE_INT_MESSAGE.message)
        if(inputNum % 1000 != 0){
            throw IllegalArgumentException(ExceptionMessage.REQUIRE_1000_MESSAGE.message)
        }
        return inputNum
    }

    fun winningNumException(input : String) : List<Int>{
        if(!input.contains(",")){
            throw IllegalArgumentException(ExceptionMessage.REQUIRE_SPLIT_COMMA.message)
        }
        val inputList = input.replace(" ",",").split(",")
        if (inputList.size != 6){
            throw IllegalArgumentException(ExceptionMessage.REQUIRE_6_WINNING_NUM.message)
        }
        return winningListException(inputList)
    }

    fun winningListException(inputList : List<String>) : List<Int>{
        val winningList = mutableListOf<Int>()

        for (str in inputList){
            val num = str.toIntOrNull() ?: throw IllegalArgumentException(ExceptionMessage.REQUIRE_INT_MESSAGE.message)

            if (num < 0 || num > 45){
                throw IllegalArgumentException(ExceptionMessage.REQUIRE_NUMBER_RANGE.message)
            }
            if (winningList.contains(num)){
                throw IllegalArgumentException(ExceptionMessage.NOT_DUPLICATE_NUMBER.message)
            }
            winningList.add(num)
        }
        return winningList
    }

    fun bonusNumException(input : String, winningNum : Lotto) : Int{
        val bonusNum = input.toIntOrNull() ?: throw IllegalArgumentException(ExceptionMessage.REQUIRE_INT_MESSAGE.message)
        if (bonusNum < 0 || bonusNum > 45){
            throw IllegalArgumentException(ExceptionMessage.REQUIRE_NUMBER_RANGE.message)
        }
        if (winningNum.lottoSort().contains(bonusNum)){
            throw IllegalArgumentException(ExceptionMessage.NOT_DUPLICATE_NUMBER.message)
        }
        return bonusNum
    }
}

