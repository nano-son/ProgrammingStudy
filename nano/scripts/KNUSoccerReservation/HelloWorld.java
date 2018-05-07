import manager.LoginManager;
import manager.ReserveManager;
import model.Field;
import model.ReserveForm.*;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception{
        LoginManager loginManager = new LoginManager("아이디 입력", "비밀번호 입력");

        if( ! loginManager.login()){
            System.err.println("************[login fail]*************");
            return;
        }

        ReserveManager reserveManager = new ReserveManager();
        /**
         * EXAMPLE 1
         * 족구장 옆 풋살장 예약하기.
         * 2018-05-28일 17시부터 2시간동안 예약
         * 시간단위는 24시간 단위이며 오후 3시는 15시라고 적야함.
         */
        Builder builder = new Builder()
                .date("2018-05-28")
                .startHour(17)
                .useHour(2);
        boolean result = reserveManager.reserve(builder.build().makeEntity(Field.FOOTSAL4));
        if(! result)
            System.err.println("************[reserve fail]*************");

        /**
         * EXAMPLE 2
         * 풀코트 예약하기.
         * 2018-05-09일 10시부터 1시간동안 예약
         */
//        builder = new Builder()
//                .date("2018-05-09")
//                .startHour(10)
//                .useHour(1);
//        reserveManager.reserve(builder.build().makeEntity(Field.FULL_COURT));
    }
}
