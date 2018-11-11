import java.util.*

fun main(args: Array<String>)  = with(Scanner(System.`in`)) {
    val A = this.nextInt()
    val B = this.nextInt()
    val C = this.nextInt()
    val D = this.nextInt()
    val quantity = this.nextInt()

    val resultX = A * quantity
    val resultY = if (quantity < C)
        B
    else
        B + (quantity - C) * D

    println(Math.min(resultX, resultY));
}
