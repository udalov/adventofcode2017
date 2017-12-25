fun CharArray.swap(i: Int, j: Int) {
    this[j] = this[i].also { this[i] = this[j] }
}

fun main(args: Array<String>) {
    val n = 16
    var a = CharArray(n) { i -> 'a' + i }
    val regex = ".(\\w+)/(\\w+)".toRegex()
    for (move in readLine()!!.split(",")) {
        when (move.first()) {
            's' -> {
                val spin = move.substring(1).toInt()
                a = CharArray(n) { i -> a[(i + n - spin) % n] }
            }
            'x' -> {
                val (i, j) = regex.matchEntire(move)!!.destructured
                a.swap(i.toInt(), j.toInt())
            }
            'p' -> {
                val (p, q) = regex.matchEntire(move)!!.destructured
                a.swap(a.indexOf(p.single()), a.indexOf(q.single()))
            }
        }
    }
    println(a.joinToString(""))
}
