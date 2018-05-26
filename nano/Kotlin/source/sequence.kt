
/**
 * Created by Nano.son on 2018. 5. 26.
 * sequence는 이전 시퀀스의 연산내용을 알고 있다.
 * 컬렉션의 map, filter 함수는 각 연산마다 중간 결과로 새로운 컬렉션을 만들어낸다.
 * 이와 반대로 sequence는 최종적으로 원소가 사용되어야할때 비로소 원소에 적용될 연산들을 수행하는 '지연 연산'을 사용한다.
 *
 * sequence는
 */

fun main(args: Array<String>) {
    val collection = (1..10).toList()
            .map {
                println("list: $it 을 제곱한다")
                it * it
            }
            .filter {
                println("list: $it filtering 시도")
                it % 3 == 0
            }

    val sequence = (1..10).asSequence()
            .map {
                println("sequence: $it 을 제곱한다")
                it * it
            }
            .filter {
                println("sequence: $it filtering 시도")
                it % 3 == 0
            }


    println("변수 선언 종료")
    sequence.forEach { println(it) }

    println("sequence to list")
    sequence.toList()

    /**
     * 그리고 아래와 같은 경우에는 시퀀스를 사용하면 더더욱 좋다.
     */
    class Person(val name: String)

    val pList = listOf(Person("Adam"),
            Person("Nano"),
            Person("David"),
            Person("Kane"))

    val listTest = pList.map {
        println("map ${it.name}")
        it.name.toUpperCase()
    }.find { it.startsWith("N") }
    println(listTest)

    val sequenceTest = pList.asSequence()
            .map {
                println("map ${it.name}")
                it.name.toUpperCase()
            }.find { it.startsWith("N") }
    println(sequenceTest)

    /**
     * 시퀀스는 하나의 원소에 대해서 중간연산을 마지막에 한번에 수행하기에
     * 중간에 find를 만족하는 원소를 발견하면 다른 원소들에 연산을 적용하지 않는다.
     *
     * 반면에 컬렉션은 중간연산마다 임시 컬렉션을 만들어야하기에 위와 같은 상황에서는 비효율적일 수 있다.
     */
}
