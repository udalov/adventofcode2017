fun main(args: Array<String>) {
    val a = generateSequence { readLine() }.toList().flatMap { line -> line.split("/").map(String::toInt) }
    val n = a.size

    var maxStrength = 0
    var maxLength = 0
    val used = BooleanArray(n / 2)
    fun go(x: Int, sum: Int, length: Int) {
        val strength = sum + x
        if (length > maxLength) {
            maxLength = length
            maxStrength = strength
        } else if (length == maxLength) {
            maxStrength = maxOf(maxStrength, strength)
        }
        for (i in 0 until n step 2) {
            if (!used[i / 2]) {
                if (a[i] == x) {
                    used[i / 2] = true
                    go(a[i + 1], sum + 2 * a[i], length + 1)
                    used[i / 2] = false
                } else if (a[i + 1] == x) {
                    used[i / 2] = true
                    go(a[i], sum + 2 * a[i + 1], length + 1)
                    used[i / 2] = false
                }
            }
        }
    }

    go(0, 0, 0)
    println(maxStrength)
}
