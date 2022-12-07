package view

import lotto.Lotto
import java.text.DecimalFormat

class OutputView {
    fun showLottoCount(count : Int){
        println("\n${count}개를 구매했습니다.")
    }

    fun showLottoList(lottoList : List<Lotto>, count : Int){
        for (lotto in lottoList){
            println(lotto.lottoSort())
        }
    }

    fun showLottoScore(correctList : MutableList<Int>){
        println()
        println("""
        당첨 통계
        ---
        3개 일치 (5,000원) - ${correctList[0]}개
        4개 일치 (50,000원) - ${correctList[1]}개
        5개 일치 (1,500,000원) - ${correctList[2]}개
        5개 일치, 보너스볼 일치 (30,000,000원) - ${correctList[3]}개
        6개 일치 (2,000,000,000원) - ${correctList[4]}개
    """.trimIndent())
    }

    fun showBenefit(decimalBenefit : DecimalFormat, benefit : Double){
        println("총 수익률은 ${decimalBenefit.format(benefit)}% 입니다.")
    }
}