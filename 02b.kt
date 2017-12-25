import kotlin.math.*

fun main(args: Array<String>) {
    println(generateSequence { readLine() }.map { line ->
        val a = line.split("[ \t]+".toRegex()).map(String::toInt)
        val n = a.size
        (0 until n).flatMap { i -> (0 until n).map { j -> Pair(i, j) } }
                .filter { (i, j) -> i != j && a[i] % a[j] == 0 }
                .map { (i, j) -> a[i] / a[j] }
                .single()
    }.sum())
}
