package lotto
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import kotlin.IllegalArgumentException


fun main() {
    inputLottoNum()
}

fun inputLottoNum() {
    println("구입금액을 입력해 주세요.")
    val inputNum = Console.readLine()
    inputException(inputNum)
    val userLottoCount = lottoCount(inputNum)

    println("\n${userLottoCount}개를 구매했습니다.")
    val userLottoNum = lottoNumber((userLottoCount))
    showLottoNumber(userLottoNum)
    val winningNum = winningInputNumber()
    val bonusNum = bonusInputNumber()
    val countList = lottoCountList(userLottoNum, winningNum, bonusNum)
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

fun winningInputNumber() : List<Int>{
    println("\n당첨 번호를 입력해 주세요.")
    val winningNum = Console.readLine()!!.split(",").map {it.toInt()}
    lottoInputException(winningNum)
    return winningNum
}

fun bonusInputNumber() : Int{
    println("\n보너스 번호를 입력해 주세요.")
    val bonusNum = Console.readLine()!!.toInt()
    bonusInputException(bonusNum)
    return bonusNum
}


fun lottoInputException(winningNum : List<Int>){
    if (winningNum.size != 6){
        throw IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.")
    }
    for (i in winningNum){
        if (i < 0 || i > 45){
            throw IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
        }
    }
    return
}

fun bonusInputException(bonusNum : Int){
    if (bonusNum < 0 || bonusNum > 45){
        throw IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
    }
    return
}

fun lottoCountList(userLottoNum: MutableList<MutableList<Int>>, winningNum : List<Int>, bonusNum : Int) : MutableList<Pair<Int,Int>>{
    var countList = mutableListOf<Pair<Int,Int>>()
    for (i in 0 until userLottoNum.size){
        countList.add(correctCount(userLottoNum[i], winningNum, bonusNum))
    }
    return countList
}

fun correctCount(userLottoNum : MutableList<Int>, winningNum : List<Int>, bonusNum : Int) : Pair<Int, Int>{
    var lottoCount = 0
    var bonusCount = 0

    for (i in winningNum){
        if (userLottoNum.contains(i)){
            lottoCount += 1
        }
    }
    if (userLottoNum.contains(bonusNum)){
        bonusCount = 1
    }
    return Pair(lottoCount, bonusCount)
}

