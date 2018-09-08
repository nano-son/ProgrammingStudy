import java.io.BufferedReader
import java.io.InputStreamReader

var m: Int = 0
var n: Int = 0
lateinit var d:Array<IntArray>
lateinit var b:Array<IntArray>
lateinit var history:IntArray
lateinit var resultHistory: IntArray
var max = 0

fun main(args: Array<String>) {
    val br  = BufferedReader(InputStreamReader(System.`in`))
    val temp: List<String> = br.readLine().split(" ")

    n = temp[0].toInt()
    m = temp[1].toInt()
    b = Array(n+1){ IntArray(m+1) }
    d = Array(m+1){ IntArray(n+1){-1} }
    history = IntArray(m+1){0}
    (1..n).forEach {
        val t = br.readLine().split(" ")
        (1..m).forEach { company ->
            b[t[0].toInt()][company] = t[company].toInt()
        }
    }
    recur(1, n, 0)
    println(max)
    for(company in 1..m) print("${resultHistory[company]} ")
}

fun recur(company:Int, money:Int, bSum:Int): Int {
    var ret = 0

    if(company > m) {
        if(max < bSum) {
            resultHistory = history.clone()
            max = bSum
        }
        return 0
    }

    if(d[company][money] != -1 && d[company][money] + bSum <= max)
        return d[company][money]

    for (i in money downTo 0) {
        history[company] = i
        ret = Math.max(
                ret,
                b[i][company] + recur(company+1, money-i, bSum + b[i][company])
        )
    }

    d[company][money] = ret
    return ret
}
