typealias State = String
typealias Value = String
typealias Position = Int

enum class Movement {
    LEFT {
        override fun move(x: Position): Position = x - 1
    },
    RIGHT {
        override fun move(x: Position): Position = x + 1
    };

    abstract fun move(x: Position): Position
}

fun parse(regex: String): String {
    return regex.toRegex().matchEntire(readLine()!!)!!.destructured.component1()
}

fun main(args: Array<String>) {
    val start = parse("Begin in state (\\S+).")
    val steps = parse("Perform a diagnostic checksum after (\\S+) steps.").toInt()
    val a = mutableMapOf<Pair<State, Value>, Triple<Value, Movement, State>>()
    while (readLine() != null) {
        val from = parse("In state (\\S+):")
        repeat(2) {
            val old = parse("  If the current value is (\\S+):")
            val new = parse("    - Write the value (\\S+).")
            val move = parse("    - Move one slot to the (\\S+).").let { if (it == "left") Movement.LEFT else Movement.RIGHT }
            val next = parse("    - Continue with state (\\S+).")
            a[Pair(from, old)] = Triple(new, move, next)
        }
    }

    var i: Position = 0
    var state: State = start
    val values = mutableMapOf<Position, Value>()
    repeat(steps) {
        val (new, move, next) = a[Pair(state, values[i] ?: "0")]!!
        values[i] = new
        i = move.move(i)
        state = next
    }

    println(values.values.count { it == "1" })
}
