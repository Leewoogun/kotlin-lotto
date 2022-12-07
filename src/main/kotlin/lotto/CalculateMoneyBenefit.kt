package lotto

import view.OutputView
import java.text.DecimalFormat
import kotlin.math.round

class CalculateMoneyBenefit {
    private var sum : Double = 0.0
    private val outputView = OutputView()
    fun gainMoney(correctList : MutableList<Int>){

        for (index in correctList.indices){
            when(index){
                0-> sum += correctList[index] * 5_000
                1-> sum += correctList[index] * 50_000
                2-> sum += correctList[index] * 1_500_000
                3-> sum += correctList[index] * 30_000_000
                4-> sum += correctList[index] * 2_000_000_000
            }
        }
    }

    fun resultBenefit(inputNum : Int){
        var benefit : Double = ((sum / inputNum.toDouble()) * 100)
        benefit = round(benefit * 100) / 100
        val decimalFormat = DecimalFormat("#,###.#")
        outputView.showBenefit(decimalFormat, benefit)
    }
}