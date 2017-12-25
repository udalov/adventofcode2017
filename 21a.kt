fun List<String>.rotate(): List<String> {
    return List(size) { i ->
        indices.joinToString("") { j ->
            this[size - j - 1][i].toString()
        }
    }
}

fun List<String>.flip(): List<String> {
    return List(size) { i -> this[size - i - 1] }
}

fun main(args: Array<String>) {
    val r3 = "(.+)/(.+)/(.+) => (.+)/(.+)/(.+)/(.+)".toRegex()
    val r2 = "(.+)/(.+) => (.+)/(.+)/(.+)".toRegex()
    val map = mutableMapOf<List<String>, List<String>>()
    for (line in generateSequence { readLine() }) {
        val m3 = r3.matchEntire(line)
        if (m3 != null) {
            val (i1, i2, i3, o1, o2, o3, o4) = m3.destructured
            var input = listOf(i1, i2, i3)
            val output = listOf(o1, o2, o3, o4)
            repeat(2) {
                repeat(4) {
                    assert(input !in map)
                    map[input] = output
                    input = input.rotate()
                }
                input = input.flip()
            }
            continue
        }

        val m2 = r2.matchEntire(line)
        if (m2 != null) {
            val (i1, i2, o1, o2, o3) = m2.destructured
            var input = listOf(i1, i2)
            val output = listOf(o1, o2, o3)
            repeat(2) {
                repeat(4) {
                    assert(input !in map)
                    map[input] = output
                    input = input.rotate()
                }
                input = input.flip()
            }
            continue
        }

        error(line)
    }

    var a = listOf(".#.", "..#", "###")

    repeat(5) {
        val n = a.size
        val k = listOf(2, 3).first { k -> n % k == 0 }
        val b = List(n / k) { i ->
            List(n / k) { j ->
                val block = (0 until k).map { x -> a[i * k + x].substring(j * k, (j + 1) * k) }
                map[block]!!
            }
        }
        a = List(n / k * (k + 1)) { i ->
            (0 until n / k).joinToString("") { j ->
                b[i / (k + 1)][j][i % (k + 1)]
            }
        }
    }

    println(a.sumBy { line -> line.count { it == '#' } })
}
