package view
import camp.nextstep.edu.missionutils.Console
import exception.NumberException

class InputView {
    private val numberException = NumberException()
    fun buyLotto() : Int{
        val inputNum = Console.readLine()
        return numberException.inputException(inputNum)
    }
}