fun IntArray.swap(i: Int, j: Int) {
    this[j] = this[i].also { this[i] = this[j] }
}

fun knot(b: List<Int>): List<Int> {
    val n = 256
    val a = IntArray(n) { i -> i }
    var skip = 0
    var i = 0
    repeat(64) {
        for (len in b + listOf(17, 31, 73, 47, 23)) {
            for (k in 0 until len / 2) {
                a.swap((i + k) % n, (i + len - 1 - k) % n)
            }
            i += len + skip++
        }
    }
    return a.toList().chunked(16) { block -> block.reduce(Int::xor) }
}

fun main(args: Array<String>) {
    val n = 128
    val key = readLine()!!
    var g = Array(n) { BooleanArray(n) }
    for (i in 0 until n) {
        val c = knot((key + "-$i").map(Char::toInt))
        for (j in 0 until n) {
            g[i][j] = c[j / 8] and (1 shl (7 - j % 8)) != 0
        }
    }

    var ans = 0
    fun go(i: Int, j: Int) {
        if (g.getOrNull(i)?.getOrNull(j) != true) return
        g[i][j] = false
        go(i, j + 1)
        go(i - 1, j)
        go(i, j - 1)
        go(i + 1, j)
    }

    for (i in g.indices) {
        for (j in g[i].indices) if (g[i][j]) {
            ans++
            go(i, j)
        }
    }

    println(ans)
}
