package model;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import javafx.util.Builder;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReserveForm {

    /**
     * 아래 5개는 건들지 마셈
     */
    //예약 페이지번호
    private int fc_grno;
    //구장 번호
    private int fc_sqno;

    //신청하고자 하는 날짜
    private String tDate;
    //예약하고자 하는 시작 시간
    private String fc_time;
    //사용시간
    private String use_time;

    //수정 ㄴㄴ
    private static final String FROM_URL = "/doc/class_info6_reserve.php";
    //신청자 이름(수정 필수)
    private static final String APNT_PRSN_NM = "니 이름";
    //뭔진 모르지만 계속 1 (수정 ㄴㄴ)
    private static final String LCTN_DVCD = "1";
    //학번 (수정 필수) (ex 2012111222)
    private static final String MEMB_ID = "니 학번";
    //연락처(수정 필수) (ex 01012345678)
    private static final String HP_NO = "니 전화번호";
    //학부
    private static final String BLNG_NM="컴퓨터학부";
    //신청자 학번(수정 필수) (ex 2012111222)
    private static final String EMNO="니 학번";
    //사용 인원(수정 필수)
    private static final int USER_QTY= 11;
    //같이 차는 인원들(수정 필수)
    private static final String USER_LIST="김명훈 외 10명, 손종영, 성지원, 정문주, 최종현, 김정환, 이민재, 신재민, 박상현, 우효원, 윤원철. 이상 총 11명";
    //행사 계획(수정 필수)
    private static final String EVNT_PLAN = "풋살. 뒷정리 깔끔히 하겠습니다. 풋살. 뒷정리 깔끔히 하겠습니다. 풋살. 뒷정리 깔끔히 하겠습니다. 풋살. 뒷정리 깔끔히 하겠습니다.";

    private ReserveForm(Builder builder) {
        this.tDate = builder.tDate;
        this.use_time = builder.use_time;
        this.fc_time = builder.fc_time;
    }

    public static class Builder {
        String tDate;
        String fc_time;
        String use_time;
        private Boolean ready;

        public Builder() {
            ready = false;
        }

        public Builder date(String tDate) {
            this.tDate = tDate;
            check();
            return this;
        }

        public Builder startHour(int startHour) {
            this.fc_time = String.valueOf(startHour);
            check();
            return this;
        }


        public Builder useHour(int useHour) {
            this.use_time = String.valueOf(useHour);
            check();
            return this;
        }

        public void check() {
            if(tDate!=null && fc_time!=null && use_time!=null)
                ready = true;
            else
                ready = false;
        }

        public ReserveForm build() {
            if(ready)
                return new ReserveForm(this);
            throw new InvalidStateException("make sure builder's instance variables");
        }
    }

    public HttpEntity makeEntity(Field field) throws UnsupportedEncodingException {
        //풋살장1,2,3, 대운동장
        //족구장 옆 풋살장
        this.fc_grno = field.fc_grno;
        this.fc_sqno = field.fc_sqno;

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("MEMB_ID="+ URLEncoder.encode(MEMB_ID, "UTF-8"))
                .append("&fc_grno="+fc_grno)
                .append("&fc_sqno="+fc_sqno)
                .append("&fc_time="+fc_time)
                .append("&tDATE="+tDate)
                .append("&APNT_PRSN_NM="+URLEncoder.encode(APNT_PRSN_NM,"UTF-8"))
                .append("&LCTN_DVCD="+LCTN_DVCD)
                .append("&FROM_URL="+URLEncoder.encode(FROM_URL,"UTF-8"))
                .append("&HP_NO="+HP_NO)
                .append("&BLNG_NM="+URLEncoder.encode(BLNG_NM, "UTF-8"))
                .append("&EMNO="+EMNO)
                .append("&use_time="+use_time)
                .append("&USER_QTY="+USER_QTY)
                .append("&USER_LIST="+URLEncoder.encode(USER_LIST, "UTF-8"))
                .append("&EVNT_PLAN="+URLEncoder.encode(EVNT_PLAN, "UTF-8"));
        HttpEntity httpEntity = new StringEntity(strBuilder.toString());
        return httpEntity;
    }
}
