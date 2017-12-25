fun main(args: Array<String>) {
    val factorA = 16807
    val factorB = 48271
    val modulo = 2147483647
    val mask = ((1 shl 16) - 1).toLong()
    var a = readLine()!!.split(" ").last().toLong()
    var b = readLine()!!.split(" ").last().toLong()
    var ans = 0
    repeat(40000000) {
        a = (a * factorA) % modulo
        b = (b * factorB) % modulo
        if ((a and mask) == (b and mask)) ans++
    }
    println(ans)
}
