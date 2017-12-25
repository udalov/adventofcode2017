import kotlin.math.abs

data class Vec(var x: Long, var y: Long, var z: Long) {
    operator fun plusAssign(o: Vec) {
        x += o.x
        y += o.y
        z += o.z
    }
}

data class Particle(val p: Vec, val v: Vec, val a: Vec)

fun main(args: Array<String>) {
    val regex = "p=<(.+),(.+),(.+)>, v=<(.+),(.+),(.+)>, a=<(.+),(.+),(.+)>".toRegex()
    val particles = generateSequence { readLine() }.map { line ->
        val (x, y, z, vx, vy, vz, ax, ay, az) = regex.matchEntire(line)!!.destructured
        val p = Vec(x.toLong(), y.toLong(), z.toLong())
        val v = Vec(vx.toLong(), vy.toLong(), vz.toLong())
        val a = Vec(ax.toLong(), ay.toLong(), az.toLong())
        Particle(p, v, a)
    }.toList()

    repeat(100000) {
        for ((p, v, a) in particles) {
            v += a
            p += v
        }
    }

    println(particles.indices.minBy { i ->
        val p = particles[i].p
        abs(p.x) + abs(p.y) + abs(p.z)
    })
}
