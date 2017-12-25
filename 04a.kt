fun main(args: Array<String>) {
    println(generateSequence { readLine() }.map { line ->
        val words = line.split(" ")
        words.toSet().size == words.size
    }.count(true::equals))
}
