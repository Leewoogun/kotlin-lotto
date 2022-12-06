package view

import lotto.Lotto

class OutputView {
    fun showLottoCount(count : Int){
        println("\n${count}개를 구매했습니다.")
    }

    fun showLottoList(lottoList : List<Lotto>, count : Int){
        for (lotto in lottoList){
            println(lotto.lottoSort())
        }
    }
}