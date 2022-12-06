package lotto

import camp.nextstep.edu.missionutils.Randoms

class LottoList {
    fun lottoGenerate(count : Int) : MutableList<Lotto>{
        val lottoList = mutableListOf<Lotto>()
        for (i in 1..count){
            val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            lottoList.add(Lotto(numbers))
        }
        return lottoList
    }
}