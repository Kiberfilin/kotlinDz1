package task2

import java.lang.IndexOutOfBoundsException
import java.math.BigDecimal

fun main() {
    val index = bodyMassIndex(1.76, 70.0)
    val verdict = when (index) {
        in 0.0..16.0 -> println("ИМТ = $index, Вердикт: \nВыраженный дефицит массы тела.")
        in 16.0..18.5 -> println("ИМТ = $index, Вердикт: \nНедостаточная (дефицит) масса тела.")
        in 18.5..24.99 -> println("ИМТ = $index, Вердикт: \nНорма.")
        in 25.0..30.0 -> println("ИМТ = $index, Вердикт: \nИзбыточная масса тела (предожирение).")
        in 30.0..35.0 -> println("ИМТ = $index, Вердикт: \nОжирение.")
        in 35.0..40.0 -> println("ИМТ = $index, Вердикт: \nОжирение резкое.")
        else -> if (index >= 0) println("ИМТ = $index, Вердикт: \nОчень резкое ожирение.")
        else throw IndexOutOfBoundsException("ИМТ не может быть отрицательным.")
    }
    println(verdict)
}

fun bodyMassIndex(heightInMeters: Double, massInKg: Double): Double {
    val bDecMass = BigDecimal.valueOf(massInKg)
    val bDecheight = BigDecimal.valueOf(heightInMeters)
    return (bDecMass / (bDecheight * bDecheight)).toDouble()
}
