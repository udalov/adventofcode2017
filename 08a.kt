fun main(args: Array<String>) {
    val regex = "([a-z]+) (inc|dec) ([-\\d]+) if ([a-z]+) ([<=>!]+) ([-\\d]+)".toRegex()
    val a = hashMapOf<String, Int>()
    for (line in generateSequence { readLine() }) {
        val (r, inst, amount, c, sign, value) = regex.matchEntire(line)!!.destructured
        val condition = (a[c] ?: 0).compareTo(value.toInt())
        val success = when (sign) {
            ">" -> condition > 0
            ">=" -> condition >= 0
            "<" -> condition < 0
            "<=" -> condition <= 0
            "==" -> condition == 0
            "!=" -> condition != 0
            else -> error(sign)
        }
        if (success) {
            a[r] = (a[r] ?: 0) + amount.toInt() * (if (inst == "inc") 1 else -1)
        }
    }
    println(a.values.max()!!)
}
