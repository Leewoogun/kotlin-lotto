package view
import camp.nextstep.edu.missionutils.Console
import exception.NumberException
import lotto.Lotto


enum class InMessage(val message : String){
    PUT_WINNING_NUM("당첨 금액을 입력해 주세요."),
    PUT_BONUS_NUM("보너스 금액을 입력해 주세요.")
}
class InputView {
    private val numberException = NumberException()

    fun buyLotto() : Int{
        val inputNum = Console.readLine()
        return numberException.inputException(inputNum)
    }

    fun inputWinningNum() : Lotto {
        println(InMessage.PUT_WINNING_NUM.message)
        val winningNum = Console.readLine()
        return Lotto(numberException.winningNumException(winningNum))
    }

    fun inputBonusNum(winningNum : Lotto) : Int{
        println(InMessage.PUT_BONUS_NUM.message)
        val bonusNum = Console.readLine()
        return numberException.bonusNumException(bonusNum, winningNum)
    }
}

