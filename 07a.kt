fun main(args: Array<String>) {
    val regex = "(\\w+) (\\(\\d+\\))( -> (.+))?".toRegex()
    val names = hashSetOf<String>()
    val children = hashSetOf<String>()
    for (line in generateSequence { readLine() }) {
        val (name, _, _, childrenList) = regex.matchEntire(line)!!.destructured
        names.add(name)
        if (childrenList.isNotEmpty()) {
            children.addAll(childrenList.split(", "))
        }
    }
    println((names - children).single())
}
