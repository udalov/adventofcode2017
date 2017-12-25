fun main(args: Array<String>) {
    val factorA = 16807
    val factorB = 48271
    val modulo = 2147483647
    val mask = ((1 shl 16) - 1).toLong()
    var a = readLine()!!.split(" ").last().toLong()
    var b = readLine()!!.split(" ").last().toLong()
    var ans = 0
    repeat(5000000) {
        do {
            a = (a * factorA) % modulo
        } while (a % 4 != 0L)
        do {
            b = (b * factorB) % modulo
        } while (b % 8 != 0L)
        if ((a and mask) == (b and mask)) ans++
    }
    println(ans)
}
