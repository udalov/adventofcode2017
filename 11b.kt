import kotlin.math.*

fun main(args: Array<String>) {
    var x = 0
    var y = 0
    var ans = 0
    for (dir in readLine()!!.split(",")) {
        when (dir) {
            "n" -> { x -= 2 }
            "ne" -> { x--; y++ }
            "se" -> { x++; y++ }
            "s" -> { x += 2 }
            "sw" -> { x++; y-- }
            "nw" -> { x--; y-- }
            else -> error(dir)
        }
        var dx = abs(x)
        var dy = abs(y)
        var cur = 0
        while (dx > 0 && dy > 0) {
            cur++
            dx--
            dy--
        }
        cur += (dx + dy) / 2
        ans = maxOf(ans, cur)
    }
    println(ans)
}
