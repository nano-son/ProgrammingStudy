import java.util.*

fun main(args: Array<String>) {
    val keyboard = Scanner(System.`in`)
    val up = keyboard.nextInt()
    val down = keyboard.nextInt()
    val treeHeight = keyboard.nextInt()

    val oneDayMove = up - down
    var left = 1
    var right = treeHeight
    while(left < right) {
        val mid = (left + right)/2

        if((mid-1)*oneDayMove+up >= treeHeight) {
            right = mid
        } else {
            left = mid+1
        }
    }

    println(
            if ((left - 1) * oneDayMove + up >= treeHeight)
                left
            else
                right
    )
}
