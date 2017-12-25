import Inst.*
import Inst.Set

val values = mutableMapOf<String, Long>()

val String.value: Long
    get() = if (this[0].isLetter()) values[this] ?: 0 else toLong()

sealed class Inst {
    data class Set(val x: String, val y: String) : Inst()
    data class Sub(val x: String, val y: String) : Inst()
    data class Mul(val x: String, val y: String) : Inst()
    data class Jnz(val x: String, val y: String) : Inst()
}

fun main(args: Array<String>) {
    val regex = "(\\S+) (\\S+)( (\\S+))?".toRegex()
    val program = generateSequence { readLine() }.map { line ->
        val (name, x, _, y) = regex.matchEntire(line)!!.destructured
        when (name) {
            "set" -> Set(x, y)
            "sub" -> Sub(x, y)
            "mul" -> Mul(x, y)
            "jnz" -> Jnz(x, y)
            else -> error(name)
        }
    }.toList()

    var i = 0
    var ans = 0
    while (i in program.indices) {
        val inst = program[i++]
        when (inst) {
            is Set -> values[inst.x] = inst.y.value
            is Sub -> values[inst.x] = inst.x.value - inst.y.value
            is Mul -> {
                values[inst.x] = inst.x.value * inst.y.value
                ans++
            }
            is Jnz -> if (inst.x.value != 0L) i += inst.y.value.toInt() - 1
        }
    }

    println(ans)
}
