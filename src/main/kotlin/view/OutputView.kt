package view

import lotto.Lotto
import java.text.DecimalFormat


enum class OutMessage(val message : String){
    LOTTO_NUMBER("개를 구매했습니다."),
    WINNING_MESSAGE("당첨 통계\n---"),
    THREE_CORRECT("3개 일치 (5,000원) - "),
    FOUR_CORRECT("4개 일치 (50,000원) - "),
    FIVE_CORRECT_NO_BONUS("5개 일치 (1,500,000원) - "),
    FIVE_CORRECT_YES_BONUS("5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    SIX_CORRECT("6개 일치 (2,000,000,000원) - "),
    TOTAL_BENEFIT("총 수익률은 ");

    fun lottoCountMessage(count : Int) : String{
        return count.toString() + this.message
    }

    fun lottoScoreMessage(number : Int) : String{
        return this.message + number.toString() + "개"
    }

    fun benefitMessage(decimalBenefit: DecimalFormat, benefit : Double) : String{
        return this.message + decimalBenefit.format(benefit) + "%입니다."
    }
}

class OutputView {
    fun showLottoCount(count : Int){
        println(OutMessage.LOTTO_NUMBER.lottoCountMessage(count))
    }

    fun showLottoList(lottoList : List<Lotto>){
        for (lotto in lottoList){
            println(lotto.lottoSort())
        }
    }

    fun showLottoScore(correctList : MutableList<Int>){
        println()
        println(OutMessage.WINNING_MESSAGE.message)

        for (i in correctList.indices){
            when(i){
                0 -> println(OutMessage.THREE_CORRECT.lottoScoreMessage(correctList[i]))
                1 -> println(OutMessage.FOUR_CORRECT.lottoScoreMessage(correctList[i]))
                2 -> println(OutMessage.FIVE_CORRECT_NO_BONUS.lottoScoreMessage(correctList[i]))
                3 -> println(OutMessage.FIVE_CORRECT_YES_BONUS.lottoScoreMessage(correctList[i]))
                4 -> println(OutMessage.SIX_CORRECT.lottoScoreMessage(correctList[i]))
            }
        }
    }

    fun showBenefit(decimalBenefit : DecimalFormat, benefit : Double){
        println(OutMessage.TOTAL_BENEFIT.benefitMessage(decimalBenefit, benefit))
    }
}

