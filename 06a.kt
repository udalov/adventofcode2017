fun hash(a: IntArray): Long =
    a.fold(0L) { acc, element -> (acc * 31) + element }

fun main(args: Array<String>) {
    val a = readLine()!!.split("\\s+".toRegex()).map(String::toInt).toTypedArray().toIntArray()
    val s = hashSetOf(hash(a))
    var ans = 0
    while (true) {
        ans++
        val (i, max) = a.withIndex().maxBy { it.value }!!
        a[i] = 0
        for (j in 1..max) {
            a[(i + j) % a.size]++
        }
        if (!s.add(hash(a))) break
    }
    println(ans)
}
