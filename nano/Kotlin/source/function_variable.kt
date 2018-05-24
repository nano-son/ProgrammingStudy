/**
 * Created by Nano.son on 2018. 5. 3.
 * 변수와 함수에 대한 간단한 사용 방법들
 */

val str = "hello world!!!"
val num1: Int = 21
var num2: Int = 21

fun main(args: Array<String>) {
    println("hello world!")
    println(add(1,2))
    println(min(10,20))
    println(str)
    println(num1)
//    error!! val 로 선언된 변수는 변경 불가능
//    val : value, var: variable
//    num1=10
    num2 = 20


    val name = if (args.size>0) args[0] else "Kotlin"
    println("hello $name")
    //error
//    println("$name님 반가워요")
    println("${name}님 반가워욧")


    //블록 안에서는 선언하되, 초기화 하지 않아도된다. 단, val은 한번만 초기화 할 수 있다.
    val msg: String
    
}

/**
 * 함수가 본문 형태인 경우
 */
fun add(one: Int, two: Int):Int {
    return one+two
}

/**
 * 함수가 '식' 형태인 경우. 이 경우는 type inference가 들어간다.
 */
fun min(one: Int, two: Int) = if (one>two) one else two


