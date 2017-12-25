import kotlin.math.abs

data class Vec(var x: Long, var y: Long, var z: Long) {
    operator fun plusAssign(o: Vec) {
        x += o.x
        y += o.y
        z += o.z
    }
}

data class Particle(val p: Vec, val v: Vec, val a: Vec, var destroyed: Boolean = false)

fun main(args: Array<String>) {
    val regex = "p=<(.+),(.+),(.+)>, v=<(.+),(.+),(.+)>, a=<(.+),(.+),(.+)>".toRegex()
    val particles = generateSequence { readLine() }.map { line ->
        val (x, y, z, vx, vy, vz, ax, ay, az) = regex.matchEntire(line)!!.destructured
        val p = Vec(x.toLong(), y.toLong(), z.toLong())
        val v = Vec(vx.toLong(), vy.toLong(), vz.toLong())
        val a = Vec(ax.toLong(), ay.toLong(), az.toLong())
        Particle(p, v, a)
    }.toList()

    val positions = mutableMapOf<Vec, Int>()
    repeat(100000) {
        positions.clear()
        for ((p, v, a, destroyed) in particles) {
            if (destroyed) continue
            v += a
            p += v
            positions[p] = (positions[p] ?: 0) + 1
        }
        for (particle in particles) {
            if (!particle.destroyed && positions[particle.p]!! > 1) {
                particle.destroyed = true
            }
        }
    }

    println(particles.count { !it.destroyed })
}
