import Inst.*
import Inst.Set
import java.util.ArrayDeque

sealed class Inst {
    data class Snd(val x: String) : Inst()
    data class Set(val x: String, val y: String) : Inst()
    data class Add(val x: String, val y: String) : Inst()
    data class Mul(val x: String, val y: String) : Inst()
    data class Mod(val x: String, val y: String) : Inst()
    data class Rcv(val x: String) : Inst()
    data class Jgz(val x: String, val y: String) : Inst()
}

class Exec(val id: Int) {
    var i = 0
    val values = mutableMapOf("p" to id.toLong())
    val queue = ArrayDeque<Long>()

    val String.value: Long
        get() = if (this[0].isLetter()) values[this] ?: 0 else toLong()
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

    val execs = listOf(Exec(0), Exec(1))
    var exec = 0
    var ans = 0
    try {
        while (true) {
            with(execs[exec]) {
                loop@ while (i in program.indices) {
                    val inst = program[i++]
                    when (inst) {
                        is Snd -> {
                            execs[1 - exec].queue.offerLast(inst.x.value)
                            if (exec == 1) ans++
                        }
                        is Set -> values[inst.x] = inst.y.value
                        is Add -> values[inst.x] = inst.x.value + inst.y.value
                        is Mul -> values[inst.x] = inst.x.value * inst.y.value
                        is Mod -> values[inst.x] = inst.x.value % inst.y.value
                        is Rcv -> {
                            if (queue.isEmpty()) {
                                i--
                                if (execs[1 - exec].queue.isEmpty()) return
                                break@loop
                            }
                            values[inst.x] = queue.pollFirst()
                        }
                        is Jgz -> if (inst.x.value > 0) i += inst.y.value.toInt() - 1
                    }
                }
            }
            exec = 1 - exec
        }
    } finally {
        println(ans)
    }
}
