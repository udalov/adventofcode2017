fun main(args: Array<String>) {
    val regex = "(\\w+) (\\(\\d+\\))( -> (.+))?".toRegex()
    val graph = hashMapOf<String, List<String>>()
    val weight = hashMapOf<String, Int>()
    for (line in generateSequence { readLine() }) {
        val (name, w, _, children) = regex.matchEntire(line)!!.destructured
        graph[name] = children.takeUnless(String::isEmpty)?.split(", ").orEmpty()
        weight[name] = w.removePrefix("(").removeSuffix(")").toInt()
    }

    fun get(v: String): List<String> =
        graph[v]!!

    val total = hashMapOf<String, Int>()
    fun getTotal(v: String): Int =
        total[v] ?: (weight[v]!! + get(v).sumBy(::getTotal)).also { total[v] = it }

    fun isBalanced(v: String): Boolean =
        get(v).map(::getTotal).toSet().size <= 1

    for (v in graph.keys) {
        val children = get(v)
        if (!isBalanced(v) && children.all(::isBalanced)) {
            val totals = children.withIndex()
                .map { (index, value) -> IndexedValue(index, getTotal(value)) }
                .sortedBy { (_, value) -> get(v).count { getTotal(it) == value } }
            println(weight[children[totals[0].index]]!! + totals[1].value - totals[0].value)
        }
    }
}
