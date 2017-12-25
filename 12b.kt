fun main(args: Array<String>) {
    val regex = "(\\d+) <-> ([\\d, ]+)".toRegex()
    val graph = mutableMapOf<Int, MutableList<Int>>()
    for (line in generateSequence { readLine() }) {
        val (x, ys) = regex.matchEntire(line)!!.destructured
        for (y in ys.split(", ")) {
            graph.getOrPut(x.toInt()) { mutableListOf() }.add(y.toInt())
        }
    }

    val ans = hashSetOf<Int>()
    fun dfs(v: Int) {
        if (ans.add(v)) graph[v]!!.forEach(::dfs)
    }

    println(graph.keys.count { v -> (v !in ans).also { dfs(v) } })
}
