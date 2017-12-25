fun IntArray.swap(i: Int, j: Int) {
    this[j] = this[i].also { this[i] = this[j] }
}

fun main(args: Array<String>) {
    val n = 256
    val a = IntArray(n) { i -> i }
    var skip = 0
    var i = 0
    for (len in readLine()!!.split(",").map(String::toInt)) {
        for (k in 0 until len / 2) {
            a.swap((i + k) % n, (i + len - 1 - k) % n)
        }
        i += len + skip++
    }
    println(a[0] * a[1])
}
