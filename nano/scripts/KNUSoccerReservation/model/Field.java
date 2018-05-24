package model;

public enum Field {
    FOOTSAL1(28,1),
    FOOTSAL2(29,1),
    FOOTSAL3(30,1),
    FULL_COURT(1,1),
    FOOTSAL4(13, 8);

    int fc_sqno;
    int fc_grno;

    Field(int fc_sqno, int fc_grno) {
        this.fc_sqno = fc_sqno;
        this.fc_grno = fc_grno;
    }


    //아래는 fr_sqno를 표시
    //풋살장1 : 28 fr_grno==1
    //풋살장2 : 29 fr_grno==1
    //풋살장3 : 30 fr_grno==1
    //대운동장 : 1
    //족구장 옆 풋살장 : 13 (대신에 fr_grno == 8)
}
