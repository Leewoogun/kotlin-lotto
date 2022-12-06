package controller

import view.InputView

class Controller {
    private var inputNum = 0
    private val inputView = InputView()
    fun start(){
        println("구입 금액을 입력해 주세요.")
        inputNum = inputView.buyLotto()
    }


}