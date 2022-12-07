package controller

import lotto.CorrectLotto
import lotto.Lotto
import lotto.LottoList
import view.InputView
import view.OutputView

class Controller {
    private var inputNum = 0
    private lateinit var lottoList: List<Lotto>
    private lateinit var winningNum: Lotto
    private var bonusNum = 0
    private val inputView = InputView()
    private val outputView = OutputView()
    private val correctLotto = CorrectLotto()
    fun start() {
        println("구입 금액을 입력해 주세요.")
        inputNum = inputView.buyLotto()
        makeLotto(inputNum / 1000)
        winningNum = inputView.inputWinningNum()
        bonusNum = inputView.inputBonusNum(winningNum)
        correctLotto.checkCorrectLotto(lottoList, winningNum, bonusNum)

    }

    fun makeLotto(count: Int) {
        outputView.showLottoCount(count)
        lottoList = LottoList().lottoGenerate(count)
        outputView.showLottoList(lottoList, count)
    }




}