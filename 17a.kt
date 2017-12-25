fun main(args: Array<String>) {
    val n = readLine()!!.toInt() + 1
    var a = listOf(0)
    var i = 0
    for (k in 1..2017) {
        a = a.subList(0, i + 1) + k + a.subList(i + 1, a.size)
        i = (i + n) % a.size
    }
    println(a[a.indexOf(2017) + 1])
}
