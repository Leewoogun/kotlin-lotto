package lotto

class CalculateMoneyBenefit {

    fun gainMoney(correctList : MutableList<Int>) : Double{
        var sum : Double = 0.0

        for (index in correctList.indices){
            when(index){
                0-> sum += correctList[index] * 5_000
                1-> sum += correctList[index] * 50_000
                2-> sum += correctList[index] * 1_500_000
                3-> sum += correctList[index] * 30_000_000
                4-> sum += correctList[index] * 2_000_000_000
            }
        }
        return sum
    }
}