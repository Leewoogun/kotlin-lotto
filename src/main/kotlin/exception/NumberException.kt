package exception

class NumberException {
    fun inputException(input : String) : Int{
        val inputNum = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해주세요.")
        if(inputNum % 1000 != 0){
            throw IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
        }
        return inputNum
    }

    fun winningNumException(input : String) : List<Int>{
        if(!input.contains(",")){
            throw IllegalArgumentException("[ERROR] 숫자는 ,로 구분해주세요.")
        }
        val inputList = input.replace(" ",",").split(",")
        if (inputList.size != 6){
            throw IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.")
        }
        return winningListException(inputList)
    }

    fun winningListException(inputList : List<String>) : List<Int>{
        val winningList = mutableListOf<Int>()

        for (str in inputList){
            val num = str.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")

            if (num < 0 || num > 45){
                throw IllegalArgumentException("[ERROR] 로또 번호 숫자는 1~45 사이여야 합니다.")
            }
            if (winningList.contains(num)){
                throw IllegalArgumentException("[ERROR] 중복 숫자는 입력이 불가능 합니다.")
            }
            winningList.add(num)
        }
        return winningList
    }
}