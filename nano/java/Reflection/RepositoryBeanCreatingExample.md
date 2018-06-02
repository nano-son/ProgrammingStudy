Reflection을 이용하여 프레임워크 만들어보기
===========================================

Reflection은 실행시간에 객체의 타입, 프로퍼티, 메소드등을 분석하여 어떤 타입이건 조작할 수 있다.

프레임워크를 만드려면 reflection을 사용해야한다.
## 프레임워크: 제어의 역전으로, 개발자가 작성한 코드를 사용한다.

대표적으로 스프링 프레임워크를 꼽을 수 있는데, 개발자가 작성한 POJO를 빈으로 만들어 관리해준다.

## Spring의 Repository 모방구현한 작은 프레임워크 만들기
스프링에서는 @Repository 어노테이션이 붙은 인터페이스에 findById와 같은 메소드를 정의해두면 이를 인식하여 jpa를 통해 DB에서 해당하는 엔티티를 가져온다.

처음에는 어떻게 내가 정의한 메소드 이름만으로 알맞은 엔티티를 찾아올까 의문이었다. (인터페이스니까 메소드 몸체는 없다)
리플렉션을 알게되면서 원리를 알게 되었다. (사실 나의 추측이긴하다)
메소드 이름, 파라미터 타입, 반환 타입들을 고려하여 알맞은 엔티티를 찾아올 sql문을 작성하는데, 이를 위해서는 반드시 Reflection이 필요하다.

아래 코드는 내가 정의한 UserRepository를 enum 싱글턴(마치 스프링의 ApplicationContext역할)이 읽어가서 구현객체로 만들어주는데, 이때 메소드를 분석해서 각 메소드의 호출시 로직을 만들었다.
편의상 진짜 DB사용 대신, 로컬에 컬렉션으로 대체했다.

```java
/**
 * Created by Nano.son on 2018. 6. 2.
 */
public class RepositoryExample {
    public static void main(String[] args) throws Exception{
        DB.userDB.add(new User(1,"jack"));
        DB.userDB.add(new User(2,"hello"));
        DB.userDB.add(new User(3,"world"));
        DB.userDB.add(new User(4,"hello"));

        UserRepository userDao = (UserRepository)RepositoryBeanFactory.BEAN_FACTORY.makeBean(UserRepository.class);
        System.out.println(userDao.findById(1).toString());
        userDao.findByNickName("hello").stream().forEach(System.out::println);
    }
}

enum RepositoryBeanFactory {
    //findUserById 등과 같은 메소드를 읽어서 동작을 결정해야한다.
    BEAN_FACTORY;
    Object makeBean(Class<? extends MyRepository> classType) {
        Method[] methods = classType.getClass().getDeclaredMethods();
        InvocationHandler h = (Object proxy, Method method, Object[] args) ->{
            Object ret =null;
            //TODO: check is there any another good sloution
            if(method.getReturnType().getName().contains("java.util.List"))
                ret = new ArrayList<Object>();

            if(method.getName().startsWith("findBy")) {
                final String query = method.getName().split("findBy")[1];
                Iterator iter = DB.userDB.iterator();
                while(iter.hasNext()) {
                    Object e = iter.next();
                    //query필드를 가져와서 비교해야한다.
                    Field field = null;
                    if((field = e.getClass().getDeclaredField(query.substring(0,1).toLowerCase()+query.substring(1))) == null)
                        continue;
                    field.setAccessible(true);
                    if( field.get(e).equals(args[0])) {
                        if(ret instanceof Collection)
                            ((Collection) ret).add(e);
                        else {
                            ret = e; //사실 이렇게 주기보단 복사해서 주는게 안전.
                            break;
                        }
                    }
                }
            }
            return ret;
        };

        return Proxy.newProxyInstance(classType.getClassLoader(), new Class[]{classType}, h);
    }
}

interface MyRepository {}

interface UserRepository extends MyRepository{
    User findById(int id);
    List<User> findByNickName(String nickName);
}

class DB {
    public static Set<User> userDB = new HashSet<User>();
}

class User {
    private int id;
    private String nickName;

    public User() {
    }

    public User(int id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName);
    }
}
```

실행결과
<pre>
User{id=1, nickName='jack'}
User{id=4, nickName='hello'}
User{id=2, nickName='hello'}
</pre>

좀 지저분하고 완벽하게 짜여지진 않았지만 동작하니 신기하다.
