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
    val lengths = readLine()!!.map(Char::toInt)
    println(knot(lengths).joinToString("") { x -> "%02x".format(x) })
}
