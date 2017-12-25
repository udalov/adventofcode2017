import kotlin.math.*

fun main(args: Array<String>) {
    var x = 0
    var y = 0
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
    }
    var ans = 0
    x = abs(x)
    y = abs(y)
    while (x > 0 && y > 0) {
        ans++
        x--
        y--
    }
    ans += (x + y) / 2
    println(ans)
}
