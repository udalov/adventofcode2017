import kotlin.math.*

fun main(args: Array<String>) {
    println(generateSequence { readLine() }.map { line ->
        val a = line.split("[ \t]+".toRegex()).map(String::toInt)
        a.max()!! - a.min()!!
    }.sum())
}
