package task1

import java.math.BigDecimal
import kotlin.math.roundToInt

fun main() {
    val amount = 200 // стоимость текущей продажи
    var total = 11_000 // сумма предыдущих продаж
    //val fee = calculateFee(200, 11_000, false)  // exclusive по умолчанию = false
    println("***Рассчёты для НЕэксклюзивных авторов***")
    println(resultString(amount, 100))
    println(resultString(amount, 1400))
    println(resultString(amount, 11000))
    println(resultString(amount, 50080))
    //println(resultString(200, -11000))
    println()
    println("***Рассчёты для эксклюзивных авторов***")
    println(resultString(amount, 100, true))
    println(resultString(amount, 1400, true))
    println(resultString(amount, 11000, true))
    println(resultString(amount, 50080, true))
    println(resultString(amount, -11000, true))

}

fun calculateFee(amount: Int, total: Int, exclusive: Boolean = false): BigDecimal {

    fun Double.exclusiveAccounting(exclusive: Boolean): BigDecimal {
        val bDecReceiver = BigDecimal.valueOf(this)
        return if (exclusive) bDecReceiver.minus(BigDecimal.valueOf(0.05)) else bDecReceiver
    }

    val feeCoefficient: BigDecimal = when (total) {
        in 0..1000 -> 0.3 //1.Если предыдущая сумма продаж от 0 до 1 000, то % составляет 30% от суммы
        in 1001..10000 -> 0.25 //2.Если предыдущая сумма продаж от 1 001 до 10 000, то % составляет 25% от суммы
        in 10001..50000 -> 0.2 //3.Если предыдущая сумма продаж от 10 001 до 50 000, то % составляет 20% от суммы
        else -> {
            if (total >= 50001) 0.15 //4.Если предыдущая сумма продаж от 50 001, то % составляет 15% от суммы
            else throw IndexOutOfBoundsException("Отрицательных предыдущих продаж в условиях задачи нет")
        }
    }.exclusiveAccounting(exclusive)
    return (BigDecimal.valueOf(amount.toLong()) * feeCoefficient)
}

fun resultString(amount: Int, total: Int, exclusive: Boolean = false): String {
    return "Учитывая предыдущую сумму продаж, которая составила $total, со стоимости текущей продажи ($amount) будет удержано ${calculateFee(
        amount,
        total,
        exclusive
    )} в пользу платформы."
}
