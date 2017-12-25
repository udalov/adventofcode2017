fun main(args: Array<String>) {
    println(generateSequence { readLine() }.map { line ->
        val words = line.split(" ")
        words.indices.none { i ->
            words.indices.any { j ->
                i != j && words[i].toCharArray().sorted() == words[j].toCharArray().sorted()
            }
        }
    }.count(true::equals))
}
