package lotto

class CorrectLotto {
    private var correctList = mutableListOf<Int>(0,0,0,0,0)
    private var scorePairList = mutableListOf<Pair<Int, Int>>()

    fun checkCorrectLotto(lottoList : List<Lotto>, winningNum : Lotto, bonusNum : Int){
        for (lotto in lottoList){
            scorePairList.add(calculateLotto(winningNum, lotto, bonusNum))
        }
        makeScoreList()
    }

    fun calculateLotto(winningNum : Lotto, myLotto : Lotto, bonusNum : Int) : Pair<Int, Int>{
        val winningLotto = winningNum.lottoSort()
        val userLotto = myLotto.lottoSort()
        var lottoCount = 0
        var bonusCount = 0

        for (lotto in winningLotto){
            if (userLotto.contains(lotto)){
                lottoCount += 1
            }
        }
        if(userLotto.contains(bonusNum)){
            bonusCount += 1
        }
        return Pair(lottoCount, bonusCount)
    }

    fun makeScoreList(){
        for (pair in scorePairList){
            when (pair.first){
                3 -> correctList[0] += 1
                4 -> correctList[1] += 1
                5 -> isBonus(pair)
                6 -> correctList[4] += 1
            }
        }
    }

    fun isBonus(five : Pair<Int, Int>){
        when (five.second){
            0 -> correctList[2] += 1
            1 -> correctList[3] += 1
        }
    }

    fun getCorrectList() : MutableList<Int>{
        return this.correctList
    }


}