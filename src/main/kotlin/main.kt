package ru.netology

fun main() {
    val typeCard = "Mastercard"
    val transferAmount = 65_000_00
    val previousTransaction = 20_000_00

    val commission = calculationCommission(typeCard = typeCard, previousTransaction = previousTransaction, transferAmount = transferAmount)
    println("При переводе: $transferAmount копеек с карты: $typeCard, комиссия составит: $commission копеек")
}

fun calculationCommission(typeCard: String = "VK Pay", previousTransaction: Int = 0, transferAmount: Int): Int {
    val prefLimit = 75_000_00
    val overrunLimit = prefLimit - previousTransaction - transferAmount
    val minCommissionForMastercardMaestro = 20 * 100
    val commissionForMastercardMaestro = 0.006
    val minCommissionForVisaMir = 35 * 100
    val commissionForVisaMir = 0.0075
    val totalCommissionForVisaMir = transferAmount * commissionForVisaMir

    return when(typeCard) {
        "Mastercard", "Maestro" -> if (overrunLimit < 0) (kotlin.math.abs(overrunLimit) * commissionForMastercardMaestro + minCommissionForMastercardMaestro).toInt() else 0
        "Visa", "Мир" ->  if (totalCommissionForVisaMir > minCommissionForVisaMir) totalCommissionForVisaMir.toInt() else minCommissionForVisaMir
        else -> 0
    }
}