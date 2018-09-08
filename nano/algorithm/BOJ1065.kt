import java.util.*

fun main(args: Array<String>) {
    val keyboard = Scanner(System.`in`)
    var count = 0
    val n = keyboard.nextInt()

    for(i in n downTo 1){
        if(i < 100) {
            count += i
            break
        }
        if (check(i)) count ++
    }

    println(count)
}

fun check(n: Int):Boolean {
    var now:Int = (n%100)/10
    var pre:Int = n%10
    val term = now - pre
    var mod = 1000

    while(true) {
        pre = now
        now = (n%mod)/(mod/10)
        if((now - pre) != term)
            return false
        if(mod > n)
            break
        mod *=10
    }
    return true
}
