package lotto

import java.lang.IllegalArgumentException
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms


fun main() {
    inputLottoNum()
}

fun inputLottoNum(){
    println("구입금액을 입력해 주세요.")
    val inputNum = readLine()
    inputException(inputNum)
    val userLottoCount = lottoCount(inputNum)
    println("${userLottoCount}개를 구매했습니다.")
    val userLottoNum = lottoNumber((userLottoCount))
    showLottoNumber(userLottoNum)
}

fun inputException(input : String?){
    if (input?.length == 0){
        throw IllegalArgumentException("[ERROR] 잘못된 입력입니다.")
        return
    }

    if (input!!.toInt() % 1000 != 0 ){
        throw IllegalArgumentException("[ERROR] 잘못된 입력입니다.")
        return
    }
}

fun lottoCount(input : String?) : Int{
    return input!!.toInt() / 1000
}

fun lottoNumber(count : Int) : MutableList<MutableList<Int>>{
    val numberList = mutableListOf<MutableList<Int>>()

    for (i in 1..count){
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        numberList.add(numbers)
    }
    return numberList
}

fun showLottoNumber(LottoNum : MutableList<MutableList<Int>>){
    val length = LottoNum.size

    for (i in 0 until length){
        val showLotto = LottoNum[i].sorted()
        println(showLotto)
    }
}
