fun main(args: Array<String>) {
    val a = hashMapOf<Int, Int>()
    for (line in generateSequence { readLine() }) {
        val (depth, range) = line.split(": ")
        a[depth.toInt()] = range.toInt()
    }

    val n = a.keys.max()!!
    var ans = 0
    for (i in 0..n) {
        val k = a[i] ?: continue
        val pos = if ((i / (k - 1)) % 2 == 0) i % (k - 1) else k - (i % (k - 1))
        if (pos == 0) ans += i * k
    }

    println(ans)
}
