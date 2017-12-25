import kotlin.math.abs

fun main(args: Array<String>) {
    var x = 0
    var y = 0
    var step = 1
    var n = 1
    val s = readLine()!!.toInt()
    val m = hashMapOf<Pair<Int, Int>, Int>()
    m[Pair(x, y)] = n
    outer@ while (true) {
        fun go() =
            (-1..1).flatMap { dx -> (-1..1).map { dy -> dx to dy } }
                .sumBy { (dx, dy) -> m[Pair(x + dx, y + dy)] ?: 0 }
                .also {
                    n = it
                    m[Pair(x, y)] = it
                }

        for (i in 0 until step) {
            x++
            if (go() > s) break@outer
        }
        for (i in 0 until step) {
            y--
            if (go() > s) break@outer
        }
        step++
        for (i in 0 until step) {
            x--
            if (go() > s) break@outer
        }
        for (i in 0 until step) {
            y++
            if (go() > s) break@outer
        }
        step++
    }
    println(n)
}
