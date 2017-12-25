fun main(args: Array<String>) {
    val a = generateSequence { readLine()?.toInt() }.toList().toTypedArray().toIntArray()
    var i = 0
    var ans = 0
    while (i in a.indices) {
        i += a[i].also { a[i]++ }
        ans++
    }
    println(ans)
}
