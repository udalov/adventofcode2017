fun main(args: Array<String>) {
    val a = hashMapOf<Int, Int>()
    for (line in generateSequence { readLine() }) {
        val (depth, range) = line.split(": ")
        a[depth.toInt()] = range.toInt()
    }

    val n = a.keys.max()!!
    println((0..Int.MAX_VALUE).first { delay ->
        (0..n).all { i ->
            a[i]?.let { k ->
                val time = delay + i
                val pos = if ((time / (k - 1)) % 2 == 0) time % (k - 1) else k - (time % (k - 1))
                pos != 0
            } ?: true
        }
    })
}
