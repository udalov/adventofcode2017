fun CharArray.swap(i: Int, j: Int) {
    this[j] = this[i].also { this[i] = this[j] }
}

sealed class Move {
    data class Spin(val step: Int) : Move()
    data class Exchange(val i: Int, val j: Int) : Move()
    data class Partner(val p: Char, val q: Char) : Move()
}

fun main(args: Array<String>) {
    val regex = ".(\\w+)/(\\w+)".toRegex()
    val moves = readLine()!!.split(",").map { move ->
        when (move.first()) {
            's' ->  {
                Move.Spin(move.substring(1).toInt())
            }
            'x' -> {
                val (i, j) = regex.matchEntire(move)!!.destructured
                Move.Exchange(i.toInt(), j.toInt())
            }
            'p' -> {
                val (p, q) = regex.matchEntire(move)!!.destructured
                Move.Partner(p.single(), q.single())
            }
            else -> error(move)
        }
    }

    val n = 16
    var tmp = CharArray(n)

    fun CharArray.advance(): CharArray {
        val a = copyOf()
        for (move in moves) {
            when (move) {
                is Move.Spin -> {
                    for (i in a.indices) tmp[i] = a[i]
                    for (i in a.indices) a[i] = tmp[(i + n - move.step) % n]
                }
                is Move.Exchange -> a.swap(move.i, move.j)
                is Move.Partner -> a.swap(a.indexOf(move.p), a.indexOf(move.q))
            }
        }
        return a
    }

    fun CharArray.hash(): Long =
        fold(0L) { acc, c -> acc * 366239 + c.toLong() }

    var a = CharArray(n) { i -> 'a' + i }
    
    val mem = hashMapOf<Long, CharArray>()
    repeat(1000000000) {
        val next = mem[a.hash()]
        if (next != null) {
            for (i in a.indices) a[i] = next[i]
        } else {
            mem[a.hash()] = a.advance().also { a = it }.copyOf()
        }
    }

    println(a.joinToString(""))
}
