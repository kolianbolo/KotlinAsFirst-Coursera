@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.disassemble
import java.lang.Math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { acc, d -> acc + d * d })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) {
        return 0.0
    }
    return list.fold(0.0) { acc, d -> acc + d } / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    for (i in 0 until a.size) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double = p.foldIndexed(0.0) { index, acc, d -> acc + d * pow(x, index.toDouble()) }

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            if (isSimple(i)) {
                result.add(i)
                break
            }
        }
    }
    val quotient = n.divideByList(result)
    if (isSimple(quotient)) {
        return (result + quotient).sorted()
    }
    return result + factorize(quotient)
}

private fun Int.divideByList(list: List<Int>): Int {
    var result = this
    for (i in 0 until list.size) {
        result /= list[i]
    }
    return result
}

private fun isSimple(n: Int): Boolean {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n / base == 0) {
        return mutableListOf(n)
    }
    return convert(n / base, base) + n % base
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    return convert(n, base).fold("") { acc, i ->
        acc + if (i > 9) {
            (96 + (i - 9)).toChar()
        } else {
            i.toString()
        }
    }
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in 0 until digits.size) {
        result += digits[i] * pow(base.toDouble(), (digits.size - i - 1).toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    return decimal(str.fold(mutableListOf())
    { acc, c ->
        acc.add(if (c.toInt() < 58) {
            c.toInt() - 48
        } else {
            9 + c.toInt() - 96
        })
        return@fold acc
    }, base)
}

private val romanDictionary = mapOf(1 to "I",
    4 to "IV",
    5 to "V",
    9 to "IX",
    10 to "X",
    40 to "XL",
    50 to "L",
    90 to "XC",
    100 to "C",
    400 to "CD",
    500 to "D",
    900 to "CM",
    1000 to "M")

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    val romanKey = romanDictionary.keys.sorted().reversed().first { it <= n }
    result += romanDictionary[romanKey]
    if (n - romanKey != 0) {
        result += roman(n - romanKey)
    }
    return result
}

private val onesOfThousands = mapOf(1 to "одна", 2 to "две", 3 to "три", 4 to "четыре", 5 to "пять", 6 to "шесть", 7 to "семь", 8 to "восемь", 9 to "девять")
private val hundreds = mapOf(1 to "сто", 2 to "двести", 3 to "триста", 4 to "четыреста", 5 to "пятьсот", 6 to "шетьсот", 7 to "семьсот", 8 to "восемьсот", 9 to "девятьсот")
private val tens = mapOf(2 to "двадцать", 3 to "тридцать", 4 to "сорок", 5 to "пятьдесят", 6 to "шестьдесят", 7 to "семьдесят", 8 to "восемьдесят", 9 to "девяносто")
private val twenties = mapOf(10 to "десять", 11 to "одинадцать", 12 to "двенадцать", 13 to "тринадцать", 14 to "четырнадцать", 15 to "пятнадцать", 16 to "шестнадцать", 17 to "семнадцать", 18 to "восемнадцать", 19 to "девятнадцать")
private val ones = mapOf(1 to "один", 2 to "два", 3 to "три", 4 to "четыре", 5 to "пять", 6 to "шесть", 7 to "семь", 8 to "восемь", 9 to "девять")
/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val digits = disassemble(n)
    val resultList = mutableListOf<String>()
    var needThousandsPostfix = false
    var addOnes = true
    label@ for (i in digits.size - 1 downTo 0) {
        val currentDigit = digits[i]
        if (i == 3) {
            needThousandsPostfix = true
        }
        when (i) {
            2, 5 -> hundreds[currentDigit]?.let { resultList.add(it) }
            1, 4 -> {
                if (currentDigit == 1) {
                    val nextDigit = digits[i - 1]
                    twenties[currentDigit * 10 + nextDigit]?.let { resultList.add(it) }
                    addOnes = false
                } else {
                    tens[currentDigit]?.let { resultList.add(it) }
                }
            }
            3 -> {
                if (addOnes) {
                    onesOfThousands[currentDigit]?.let { resultList.add(it) }
                }
                addOnes = true
            }
            0 -> {
                if (addOnes) {
                    ones[currentDigit]?.let { resultList.add(it) }
                }
                addOnes = true
            }
        }
        if (needThousandsPostfix) {
            resultList.add(thousandsPostfix(n))
            needThousandsPostfix = needThousandsPostfix.not()
        }
    }
    return assembleRussianDigits(resultList)
}

private fun assembleRussianDigits(parts: List<String>) = parts.joinToString(separator = " ")

private fun thousandsPostfix(n: Int): String {
    if (n < 999) {
        return ""
    }
    val digits = disassemble(n)
    if (digits.size >= 5 && digits[4] == 1) {
        return "тысяч"
    }
    val onesOfThousands = digits[3]
    when (onesOfThousands) {
        1 -> return "тысяча"
        2, 3, 4 -> return "тысячи"
    }
    return "тысяч"
}