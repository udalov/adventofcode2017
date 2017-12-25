import Inst.*
import Inst.Set

val values = mutableMapOf<String, Long>()

val String.value: Long
    get() = if (this[0].isLetter()) values[this] ?: 0 else toLong()

sealed class Inst {
    data class Snd(val x: String) : Inst()
    data class Set(val x: String, val y: String) : Inst()
    data class Add(val x: String, val y: String) : Inst()
    data class Mul(val x: String, val y: String) : Inst()
    data class Mod(val x: String, val y: String) : Inst()
    data class Rcv(val x: String) : Inst()
    data class Jgz(val x: String, val y: String) : Inst()
}

fun main(args: Array<String>) {
    val regex = "(\\S+) (\\S+)( (\\S+))?".toRegex()
    val program = generateSequence { readLine() }.map { line ->
        val (name, x, _, y) = regex.matchEntire(line)!!.destructured
        when (name) {
            "snd" -> Snd(x)
            "set" -> Set(x, y)
            "add" -> Add(x, y)
            "mul" -> Mul(x, y)
            "mod" -> Mod(x, y)
            "rcv" -> Rcv(x)
            "jgz" -> Jgz(x, y)
            else -> error(name)
        }
    }.toList()

    var i = 0
    var freq = -1L
    while (i in program.indices) {
        val inst = program[i++]
        when (inst) {
            is Snd -> freq = inst.x.value
            is Set -> values[inst.x] = inst.y.value
            is Add -> values[inst.x] = inst.x.value + inst.y.value
            is Mul -> values[inst.x] = inst.x.value * inst.y.value
            is Mod -> values[inst.x] = inst.x.value % inst.y.value
            is Rcv -> if (inst.x.value != 0L) { println(freq); return }
            is Jgz -> if (inst.x.value > 0) i += inst.y.value.toInt() - 1
        }
    }
}
