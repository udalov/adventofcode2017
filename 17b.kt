fun main(args: Array<String>) {
    val n = readLine()!!.toInt() + 1
    var ans = 0
    var i = 0
    for (k in 1..50000000) {
        if (i == 0) ans = k
        i = (i + n) % (k + 1)
    }
    println(ans)
}
