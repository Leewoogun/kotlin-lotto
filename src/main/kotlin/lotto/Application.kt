package lotto
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import controller.Controller
import java.text.DecimalFormat
import kotlin.IllegalArgumentException
import kotlin.math.round


fun main() {
    Controller().start()
}

fun inputLottoNum() {
    println("구입금액을 입력해 주세요.")
    val inputNum = Console.readLine()
    inputException(inputNum)
    val userLottoCount = lottoCount(inputNum)

    println("\n${userLottoCount}개를 구매했습니다.")
//    val userLottoNum = lottoNumber((userLottoCount))
    val userLottoNum = mutableListOf(mutableListOf(1,2,3,4,5,6), mutableListOf(1,2,3,7,8,9), mutableListOf(1,2,3,4,8,9),
        mutableListOf(1,2,3,7,8,9), mutableListOf(1,2,3,4,5,9), mutableListOf(1,2,3,10,11,12))
    showLottoNumber(userLottoNum)
    val winningNum = winningInputNumber()
    val bonusNum = bonusInputNumber()
    val countList = lottoCountList(userLottoNum, winningNum, bonusNum) // 데이터 클래스로 변경 가능할지도?
    val scoreList = makeScoreList(countList)
    val resultMoney = gainMoney(scoreList)
    showScoreBenefit(scoreList)
    resultBenefit(resultMoney, inputNum)

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
    val countList = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until userLottoNum.size){
        val countPair = correctCount(userLottoNum[i], winningNum, bonusNum)
        countList.add(countPair)
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

fun makeScoreList(countList : MutableList<Pair<Int, Int>>) : MutableList<Int>{
    var scoreList = MutableList(5) { _ -> 0 }
    for (i in countList){
        when(i.first){
            3 -> scoreList[0] += 1
            4 -> scoreList[1] += 1
            5 -> isBonus(i, scoreList)
            6 -> scoreList[4] += 1
        }
    }
    return scoreList
}

fun isBonus(five : Pair<Int, Int>, scoreList : MutableList<Int>){
    when (five.second){
        0 -> scoreList[2] += 1
        1 -> scoreList[3] += 1
    }
    return
}

fun gainMoney(scoreList : MutableList<Int>) : Double{
    var sum : Double = 0.0
    for (i in scoreList.indices){
        when(i){
            0-> sum += scoreList[i] * 5000
            1-> sum += scoreList[i] * 50000
            2-> sum += scoreList[i] * 1_500_000
            3-> sum += scoreList[i] * 30_000_000
            4-> sum += scoreList[i] * 2_000_000_000
        }
    }
    return sum
}

fun showScoreBenefit(scoreList : MutableList<Int>){
    println("""
        당첨 통계
        ---
        3개 일치 (5,000원) - ${scoreList[0]}개
        4개 일치 (50,000원) - ${scoreList[1]}개
        5개 일치 (1,500,000원) - ${scoreList[2]}개
        5개 일치, 보너스볼 일치 (30,000,000원) - ${scoreList[3]}개
        6개 일치 (2,000,000,000원) - ${scoreList[4]}개
    """.trimIndent())
}

fun resultBenefit(resultMoney : Double, inputNum : String){
    var benefit : Double = (resultMoney / inputNum.toDouble()) * 100
    benefit = round(benefit * 100) / 100 // 소수 둘째자리에서 반올림
    val decimalBenefit = DecimalFormat("#,###.#") // 천 단위로 ,찍기
    println("총 수익률은 ${decimalBenefit.format(benefit)}% 입니다.")
}











