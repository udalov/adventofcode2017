fun main(args: Array<String>) {
    val s = readLine()!!
    println(s.withIndex().filter { (i, c) -> s[(i + 1) % s.length] == c }.sumBy { (_, c) -> c - '0' })
}
