fun main(args: Array<String>) {
    val line = readLine()!!
    var i = 0
    var nest = 0
    var ans = 0
    var garbage = false
    while (i < line.length) {
        if (!garbage) {
            when (line[i++]) {
                '{' -> nest++
                '}' -> ans += nest--
                '<' -> garbage = true
            }
        } else {
            when (line[i++]) {
                '!' -> i++
                '>' -> garbage = false
            }
        }
    }
    println(ans)
}
