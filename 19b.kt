data class Point(val x: Int, val y: Int)

operator fun List<String>.get(p: Point): Char? =
    this.getOrNull(p.x)?.getOrNull(p.y)

enum class Direction {
    DOWN {
        override fun move(p: Point) = Point(p.x + 1, p.y)
    },
    RIGHT {
        override fun move(p: Point) = Point(p.x, p.y + 1)
    },
    UP {
        override fun move(p: Point) = Point(p.x - 1, p.y)
    },
    LEFT {
        override fun move(p: Point) = Point(p.x, p.y - 1)
    };

    abstract fun move(p: Point): Point
}

fun main(args: Array<String>) {
    val a = generateSequence { readLine() }.toList()

    var p = Point(0, a[0].indices.single { y -> a[0][y] == '|' })
    var dir = Direction.DOWN
    var ans = 0
    while (true) {
        var q = dir.move(p)
        if (a[q] == '+') {
            dir = Direction.values().single { newDir ->
                val r = newDir.move(q)
                r != p && a[r] != null && a[r] != ' '
            }
            q = dir.move(q)
            ans++
        }
        if (a[q] == null) break
        ans++
        if (a[q] == ' ') break
        p = q
    }
    println(ans)
}
