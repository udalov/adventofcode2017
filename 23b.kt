fun main(args: Array<String>) {
    var b = 109300
    val c = 126300
    var h = 0
    while (true) {
        var f = true
        for (d in 2 until b) {
            val e = b / d
            if (e in 2 until b && b == d * e) {
                f = false
            }
        }
        if (!f) {
            h++
        }
        if (b == c) {
            break
        }
        b += 17
    }
    println(h)
}
