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

    fun previous(): Direction = values()[(ordinal + 3) % 4]
    fun next(): Direction = values()[(ordinal + 1) % 4]
}

enum class State {
    WEAKENED,
    INFECTED,
    FLAGGED,
}

fun main(args: Array<String>) {
    val map = generateSequence { readLine() }.toList()
    val n = map.size.also { n -> assert(map.all { it.length == n }) }

    val a = mutableMapOf<Point, State>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == '#') a[Point(i, j)] = State.INFECTED
        }
    }

    var p = Point(n / 2, n / 2)
    var dir = Direction.UP
    var ans = 0
    repeat(10000000) {
        dir = when (a[p]) {
            State.WEAKENED -> dir
            State.INFECTED -> dir.previous()
            State.FLAGGED -> dir.next().next()
            null -> dir.next()
        }
        when (a[p]) {
            State.WEAKENED -> {
                a[p] = State.INFECTED
                ans++
            }
            State.INFECTED -> a[p] = State.FLAGGED
            State.FLAGGED -> a.remove(p)
            null -> a[p] = State.WEAKENED
        }
        p = dir.move(p)
    }

    println(ans)
}
