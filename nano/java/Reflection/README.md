Reflection 입문 
===============================
* 이 readme에는 기본 예제만 들어가있다. 심화 예제는 별도의 readme를 만들 예정

reflect : 보여주다, 나타내다, 반사하다.

java Reflection을 이용하면 실행시간에 객체를 분석하여, 해당 객체의 메소드를 호출할 수 있고, 멤버변수값들을 획득할 수 있다.

Reflection을 위해 사용해야하는 패키지는 java.lang.reflect 패키지다.

> reflect패키지의 기본적인 클래스

| Class         | Description                           |
| ------------- | -------------                         |
|  Constructor  | 클래스의 생성자 정보 하나를 나타낸다  |
| Field         | 클래스의 필드 정보 하나를 나타낸다    |
|  Method       | 클래스의 메소드 정보 하나를 나타낸다  |

이 클래스들을 획득하기 위해서 Class 클래스의 getFields, getMethods, getConstructors 메서드를 호출하면 된다.
하지만 모든 정보를 획들할 수 있는 것은 아니다. 예제로 살펴보자

##Reflection 기본 예제
```java
 class Reflection_Basic {
    public static void main(String[] args) {
        Student student = new Student("nano", 20, 180);
        ObjectInspector.Inspector.inspect(student);
    }
}

enum ObjectInspector {
    Inspector;

    /**
     *  getMethods, getFields, getConstructors는 접근제어자가 public인 것만 반환한다.
     */
    public void inspect(Object object) {
        Class<?> cl = object.getClass();
        Method[] methods = cl.getMethods();
        Field[] fields = cl.getFields();
        Constructor[] constructors = cl.getConstructors();

        System.out.println("-----------Method-------------");
        for (Method m : methods) {
            System.out.print("isPublic:"+Modifier.isPublic(m.getModifiers())
                    +"method name:"+m.getName()
                    +", parameter types:");
            for(Class t : m.getParameterTypes())
                System.out.print(t.getTypeName()+" ");
            System.out.println(" ");
        }

        System.out.println("-----------Field-------------");
        for (Field f : fields) {
            System.out.println("isPublic:"+Modifier.isPublic(f.getModifiers())
                    +"field name:"+f.getName()
                    +", field type:"+f.getType());
        }

        System.out.println("-----------Constructor-------------");
        for (Constructor c : constructors) {
            System.out.print("isPublic:"+Modifier.isPublic(c.getModifiers())
                    +", parameter types:");

            for(Class t : c.getParameterTypes())
                System.out.print(t.getTypeName()+" ");
            System.out.println(" ");
        }
    }
}

class Student {
    private String name;
    private int age;
    private Integer height;
    int thisFieldIsNotPrivateButNotPublic;
    public int thisFieldIsPublic_int;
    public Integer thisFieldIsPublic_Integer;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age, Integer height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public void greeting(String name) {
        System.out.println("hello! "+ name +"? my name is "+this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}ava

```

실행결과
<pre>
-----------Method-------------
isPublic:truemethod name:getName, parameter types: 
isPublic:truemethod name:setName, parameter types:java.lang.String  
isPublic:truemethod name:greeting, parameter types:java.lang.String  
isPublic:truemethod name:getAge, parameter types: 
isPublic:truemethod name:setAge, parameter types:int  
isPublic:truemethod name:getHeight, parameter types: 
isPublic:truemethod name:setHeight, parameter types:java.lang.Integer  
isPublic:truemethod name:wait, parameter types:long int  
isPublic:truemethod name:wait, parameter types:long  
isPublic:truemethod name:wait, parameter types: 
isPublic:truemethod name:equals, parameter types:java.lang.Object  
isPublic:truemethod name:toString, parameter types: 
isPublic:truemethod name:hashCode, parameter types: 
isPublic:truemethod name:getClass, parameter types: 
isPublic:truemethod name:notify, parameter types: 
isPublic:truemethod name:notifyAll, parameter types: 
-----------Field-------------
isPublic:truefield name:thisFieldIsPublic_int, field type:int
isPublic:truefield name:thisFieldIsPublic_Integer, field type:class java.lang.Integer
-----------Constructor-------------
isPublic:true, parameter types:java.lang.String  
isPublic:true, parameter types:java.lang.String int java.lang.Integer 
</pre>

getMethods, getFields, getConstructors 들은 접근제어자가 public인 경우만 가저온다.
따라서 객체의 모든 정보를 가져오기 위해서는  
getDeclaredMethods, getDeclaredFields, getDeclaredConstructors 를 이용하면 된다.

각 메소드를 변경하고 다시 실행하면 결과는 아래과 같다.
<pre>
-----------Method-------------
isPublic:truemethod name:getName, parameter types: 
isPublic:truemethod name:setName, parameter types:java.lang.String  
isPublic:truemethod name:greeting, parameter types:java.lang.String  
isPublic:truemethod name:getAge, parameter types: 
isPublic:truemethod name:setAge, parameter types:int  
isPublic:truemethod name:getHeight, parameter types: 
isPublic:truemethod name:setHeight, parameter types:java.lang.Integer  
-----------Field-------------
isPublic:falsefield name:name, field type:class java.lang.String
isPublic:falsefield name:age, field type:int
isPublic:falsefield name:height, field type:class java.lang.Integer
isPublic:falsefield name:thisFieldIsNotPrivateButNotPublic, field type:int
isPublic:truefield name:thisFieldIsPublic_int, field type:int
isPublic:truefield name:thisFieldIsPublic_Integer, field type:class java.lang.Integer
-----------Constructor-------------
isPublic:true, parameter types:java.lang.String  
isPublic:true, parameter types:java.lang.String int java.lang.Integer  
</pre>


## 특정 메소드를 호출하는 코드
```java
/**
 * Created by Nano.son on 2018. 6. 2.
 */
public class MethodInvokeExample {
    public static void main(String[] args) throws Exception {
        TestClass t = new TestClass();

        MethodInvoker.METHOD_INVOKER.invokeAnyMethodWithNoParameter(t);
        MethodInvoker.METHOD_INVOKER.invokeCertainMethod(t,
                "my_func",
                new Class<?>[]{String.class, Integer.class},
                new Object[]{"ttttt", 11111});
    }
}

enum MethodInvoker {
    METHOD_INVOKER;

    void invokeAnyMethodWithNoParameter(Object target) throws IllegalAccessException, InvocationTargetException {
        for (Method m : target.getClass().getDeclaredMethods()) {
            if(m.getParameterCount() == 0) {
                System.out.println("-----method name:"+m.getName()+"------");
                m.invoke(target);
            }
        }
    }

    void invokeCertainMethod(Object target, String methodName, Class<?>[] parameterTypes, Object[] parameters)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        Method m = null;
        try {
            m = target.getClass().getDeclaredMethod(methodName, parameterTypes);
            System.out.println("-----method name:" + m.getName() + "------");
            m.invoke(target, parameters);
        } catch (IllegalAccessException e) {
            //HINT private method를 그냥 호출하면 IllegalAccessException 발생. 따라서 setAccessible을 false에서 true로 변경해야한다.
            m.setAccessible(true);
            m.invoke(target, parameters);
        }
    }
}

class TestClass {
    public void helloWorld() {
        System.out.println("hello world!");
    }

    void helloReflection() {
        System.out.println("hello reflection");
    }

    private void my_func(String t, Integer i) {
        System.out.println("t = [" + t + "], i = [" + i + "]");
    }

}
```

실행결과
<pre>
-----method name:helloWorld------
hello world!
-----method name:helloReflection------
hello reflection
-----method name:my_func------
t = [ttttt], i = [11111]
</pre>



## 생성자 획득, 호출
```java
/**
 * Created by Nano.son on 2018. 6. 2.
 */
public class ConstructorInvokeExample {
    public static void main(String[] args) throws Exception{
        Person p = (Person)ConstructorInvoker.CONSTRUCTOR_INVOKER.invokeConstructor(
                        Person.class,
                        new Class<?>[]{int.class, String.class},
                        new Object[]{20,"nano"});
        p.greeting();
    }
}

enum ConstructorInvoker {
    CONSTRUCTOR_INVOKER;

    Object invokeConstructor(Class<?> targetClass, Class<?>[] parameterTypes, Object[] params) throws Exception{
        Constructor c = targetClass.getDeclaredConstructor(parameterTypes);
        c.setAccessible(true);
        return c.newInstance(params);
    }
}

class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void greeting() {
        System.out.println("hello! my name is "+name+", and im "+age+" years old");
    }
}
```

실행결과
<pre>
hello! my name is nano, and im 20 years old
</pre>



