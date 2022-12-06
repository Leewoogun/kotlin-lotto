package view
import camp.nextstep.edu.missionutils.Console
import exception.NumberException
import lotto.Lotto

class InputView {
    private val numberException = NumberException()
    fun buyLotto() : Int{
        val inputNum = Console.readLine()
        return numberException.inputException(inputNum)
    }

    fun inputWinningNum() : Lotto {
        println("\n당첨 금액을 입력해 주세요.")
        val winningNum = Console.readLine()
        return Lotto(numberException.winningNumException(winningNum))
    }
}