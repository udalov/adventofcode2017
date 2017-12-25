import kotlin.math.abs

fun main(args: Array<String>) {
    var x = 0
    var y = 0
    var step = 1
    var n = 1
    val s = readLine()!!.toInt()
    outer@ while (true) {
        if (n == s) break
        for (i in 0 until step) {
            x++
            if (++n == s) break@outer
        }
        for (i in 0 until step) {
            y--
            if (++n == s) break@outer
        }
        step++
        for (i in 0 until step) {
            x--
            if (++n == s) break@outer
        }
        for (i in 0 until step) {
            y++
            if (++n == s) break@outer
        }
        step++
    }
    println(abs(x) + abs(y))
}
