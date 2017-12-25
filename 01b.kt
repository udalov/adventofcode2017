fun main(args: Array<String>) {
    val s = readLine()!!
    println(s.withIndex().filter { (i, c) -> s[(i + s.length / 2) % s.length] == c }.sumBy { (_, c) -> c - '0' })
}
