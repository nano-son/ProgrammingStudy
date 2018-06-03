Template / Callback Pattern
====================================
객체지향 개발자라면 반드시 알아야할 패턴이 바로 Template / Callback Pattern이다.
템플릿 콜백 패턴은 Oepn-Closed Principle (개방 폐쇄 원칙)을 지키기 위해, Strategy pattern과 DI(Dependency Injection)을 적극활용한 객체지향 설계패턴이다.

만약 Strategy Pattern을 모른다면 먼저 읽고 오길 바란다.

계산기 코드 예시로 패턴 적용 전후를 살펴보자

#### 패턴 적용 전

``` java
/**
 * Created by Nano.son on 2018. 6. 3.
 */
public class Example_Before {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.calAdd("/Users/kakao/IdeaProjects/JavaStudy/src/main/resources/number.txt"));
    }
}


class Calculator {
    public int calAdd(String filePath) {
        File file = new File(filePath);
        int sum  = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while((line = br.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("file not found!");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return sum;
    }
}
```
위 코드는 텍스트 파일에서 한줄씩 읽어와서 특정 연산을 해주는 계산기 코드이다.
만약 add 뿐만아니라, 뺄셈, 곱셈등 다양한 연산이 추가되어야한다면 끔찍한 try-catch 블럭을 여러번 반복해야하고 이는 가독성 저하로 이어진다.

객체지향 개발자라면 이를 개선하고자하는 의지가 있어야한다.

우선 변화가 일어날 수 있는 부분과 아닌 부분을 구분해보자.

어떤 연산이건, 파일을 열고 스트림을 닫아주는 행위는 공통적일 수 밖에 없다.
그렇다면 변하는 부분은

``` java
int sum = 0
...
sum += Integer.parseInt(line)
```
이다.

변하지 않는 부분을 따로 분리 하여 보자

''' java
public class Example_MId1 {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.calAdd("/Users/kakao/IdeaProjects/JavaStudy/src/main/resources/number.txt"));
    }
}

class Calculator {
    public int calAdd(String filePath) {
        File file = new File(filePath);
        int sum  = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            sum = add(br);
        } catch (FileNotFoundException e) {
            System.err.println("file not found!");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return sum;
    }
    
    public int add(BufferedReader br) throws IOException{
        int sum = 0;
        String line = null;
        while((line = br.readLine()) != null) {
            sum += Integer.parseInt(line);
        }
        return sum;
    }

    public int multiply(BufferedReader br) throws IOException{
        int sum = 1;
        String line = null;
        while((line = br.readLine()) != null) {
            sum *= Integer.parseInt(line);
        }
        return sum;
    }
}
'''

어떤 연산을 사용할지는 분리해 냈지만 그것을 외부에서 자유롭게 결정할 수 없다. 왜냐하면 호출할 메소드가 Calculator안에 묶여있기 때문이다.
외부에서 결정하기 위해 외부에서 접근할 수 있는 클래스가 필요하다.
만약 클래스를 연산마다 만들어서 전달하려면 파라미터형에 클래스가 정의되고 이는 클래스간의 의존관계로 이어져서 결합도가 높아진다.

#### 이 때 인터페이스가 활용된다.

```java
public class Example_After {
    private static final String fPath = "/Users/kakao/IdeaProjects/JavaStudy/src/main/resources/number.txt";
    public static void main(String[] args) {
        Calculato_After c = new Calculato_After();
        System.out.println(c.cal(fPath, 0, new AddStrategy2()));
    }
}

class Calculato_After {
    public int cal(String filePath, int initVal, CalStrategy2 calStrategy) {
        File file = new File(filePath);
        int ret  = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            ret = calStrategy.cal(ret, br);
        } catch (FileNotFoundException e) {
            System.err.println("file not found!");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return ret;
    }
}

interface CalStrategy2 {
    int cal(int ret, BufferedReader br) throws IOException;
}

class AddStrategy2 implements CalStrategy2 {
    @Override
    public int cal(int ret, BufferedReader br) throws IOException {
        String line = null;
        while((line = br.readLine()) != null) {
            ret += Integer.parseInt(line);
        }
        return ret;
    }
}

class MulStrategy2 implements CalStrategy2 {
    @Override
    public int cal(int ret, BufferedReader br) throws IOException {
        String line = null;
        while((line = br.readLine()) != null) {
            ret *= Integer.parseInt(line);
        }
        return ret;
    }
}

class MinusStrategy2 implements CalStrategy2 {
    @Override
    public int cal(int ret, BufferedReader br) throws IOException {
        String line = null;
        while((line = br.readLine()) != null) {
            ret -= Integer.parseInt(line);
        }
        return ret;
    }
}
```
이렇게 하면 계산기는 인터페이스를 바라보고 있고 활용될 객체를 전달받지만 런타임시에 결정된다.
즉, 계산기와 사용될 객체는 런타임시에 관계가 결정되고 그 사이에는 인터페이스가 느슨하게 결합을 이어주고 있다.

결과적으로 연산을 바꾸더라도 계산기의 코드는 변할 일이 없고, 사용할 전략(CalStrategy 구현 객체)만 다르게 전달하면 된다.

Template Callback 패턴에서 계산기의 코드와 같이 변할 가능성이 없는 부분을 template, 혹은 context라고 한다.
그리고 이 템플릿을 사용하면서 전략을 결정하는 쪽을 client라고 한다. 현재는 main메소드에서 계산기를 사용하며, 전략을 결정하니 main method가 client가 된다.
그리고 템플릿에서 사용되기 위해서 전달되는 객체를 Callback이라고 한다. 

콜백은 범용적으로 사용될 수 있다면 별개의 클래스로 구성하는 것이 좋다.
하지만 특정 클래스 내부의 메소드들만 사용한다면 그 클래스의 내부 클래스로 구성하는 것이 좋다.
나아가 특정 메소드에서만 사용하면 재활용성이 낮으므로 차라리 익명 내부 클래스로 구성하는것이 좋다.

스프링, 안드로이드 플랫폼에서는 많은 Template_Callback 패턴이 사용되고 있다.
안드로이드에서는 대표적으로 onClickListener와 같은 메소드가 있다.
스프링은 대표적으로 JdbcTemplate를 꼽을 수 있다. 
복잡하고 변하지 않는 로직은 템플릿으로 구성하고 변화될 쿼리 등의 전략만 클라이언트로부터 넘겨받는 형식이다.
특히 JdbcTemplate의 executeSql와 같은 것은 템플릿과 콜백이 내부적으로 결합되어 있는 형식이다.

템플릿, 콜백 패턴은 클라이언트가 전략을 결정하고 템플릿으로 주입해주는 방식으로 Strategy pattern과 DI가 적극적으로 활용되는 패턴이다.
객체지향 개발자라면 반드시 알아야하는 패턴 중 하나이다.



