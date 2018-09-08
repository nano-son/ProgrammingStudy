import java.util.*

fun main(args: Array<String>) {
    val keyboard = Scanner(System.`in`)
    var H = keyboard.nextInt()
    var M = keyboard.nextInt()

    H = if(M>=45)H
    else ((H-1)+24)%24
    M = ((M-45)+60) % 60
    println ("${H} ${M}")
}
