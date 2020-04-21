package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.*

@ExperimentalCoroutinesApi
fun main() {
    studyFlow()
    println(studyHigherOrderFunction(1, 2, ::intPlus))
    println(studyHigherOrderFunction(3, 7) {a : Int, b : Int ->
        a + b
    })
    studyInline ({"This is inline body"}, {"This is non-inline body"})
    studyInfix()
    studyCollection()
}

@ExperimentalCoroutinesApi
private fun studyFlow() {
    runBlocking {
        (1..5).asFlow()
            .flowOn(Dispatchers.IO)
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }
            .take(4)
            .onStart { /**/ }
            // .catch{ e -> println(e) } // use 'check' or 'throw' to catch exception
            .onCompletion { /**/ }
            .collect { response -> println(response) }
    }
}

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}

private fun studyHigherOrderFunction(p1 : Int, p2 : Int, plus : (Int, Int) -> Int) : Int {
    return plus(p1, p2)
}

private fun intPlus(a : Int, b : Int) : Int {
    return a + b
}

private inline fun <T> studyInline(body1 : () -> T, noinline body2 : () -> T) : T {
    // printBody(body1) // this will be error, because body1 is inline
    printBody(body2)
    return body1()
}

private fun <T> printBody(body : () -> T) {
    println(body())
}

private fun studyInfix() {
    println(100 add 200)
}

private infix fun Int.add(num : Int) : Int {
    return this + num
}

private fun studyCollection() {
    val stringList = listOf("one", "two", "three", "four", "five", "six")
    println(stringList.filter { it.length > 3 })
    println(stringList.filterNot { it.length < 4 })
    println(stringList.filterIndexed { index, s -> (index != 0) && (s.length < 5) })
    println(stringList.partition { it.length > 4 })
    println(stringList + "seven")
    println(stringList - listOf("three", "four"))
    println(stringList.any { it.endsWith("e") })
    println(stringList.none { it.endsWith("e") })
    println(stringList.all { it.endsWith("e") })
    println(stringList.groupBy { it.first().toUpperCase() })
    println(
        stringList.groupBy(
            keySelector = { it.first() },
            valueTransform = { it.toUpperCase(Locale.ROOT) })
    )
    println(stringList.groupingBy { it.first() }.eachCount())
    println(stringList.slice(0..4 step 2))
    println(stringList.slice(setOf(3, 5, 0)))
    println(stringList.take(3))
    println(stringList.takeLast(3))
    println(stringList.drop(3))
    println(stringList.dropLast(3))
    println(stringList.chunked(4))
    println(stringList.windowed(4))
    println(stringList.elementAtOrNull(8))
    println(stringList.elementAtOrElse(8) { index -> "The value for index $index is undefined." })
    println(stringList.first { it.startsWith("t") })
    println(stringList.find { it.startsWith("t") })
    println(stringList.random())
    val lengthComparator1 = kotlin.Comparator { o1 : String, o2 : String -> o1.length - o2.length }
    println(stringList.sortedWith(lengthComparator1))
    val lengthComparator2 = kotlin.Comparator { o1 : String, o2 : String -> o2.length - o1.length }
    println(stringList.sortedWith(lengthComparator2))
    println(stringList.sorted())
    println(stringList.sortedDescending())
    println(stringList.sortedBy { it.length })
    println(stringList.sortedByDescending { it.length })
    println(stringList.reversed())
    println(stringList.asReversed())
    val numbers = mutableListOf(1, 2, 3, 4, 5, 6)
    println(numbers.sum())
    println(numbers.average())
    println(numbers.maxBy { it % 10 })
    println(numbers.sumBy { it * 3 })
    println(numbers.reduce { sum, element -> sum + element * 2 })
    println(numbers.fold(0) { sum, element -> sum + element * 2 })
    numbers[0] = 10 // update element at specified index
    println(numbers)
    numbers[0] = 1
    val numberMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
    println(numberMap.keys)
    println(numberMap.values)
    println(numberMap.filter { (key, value) -> key.startsWith("t") && value > 2 })
    println(numberMap + Pair("six", 6))
    println(numberMap + mapOf("six" to 6, "seven" to 7))
    numberMap["one"] = 12 // update
    println(numberMap)
    numberMap["one"] = 1
    numberMap["six"] = 6 // add
    println(numberMap)
    numberMap.remove("ten") // there is no exception when operate a non-exist key
    println(numberMap)
}
