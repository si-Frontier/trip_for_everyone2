package com.example.trip_for_everyone;

public class Info {

    //변수 선언
    private String 가족화장실;
    private String 기저귀교환대;
    private String 매표소;
    private String 수유실;
    private String 시각장애인접근성;
    private String 유아차대여;
    private String 유아차보관소;
    private String 장애인엘리베이터;
    private String 장애인주차장;
    private String 장애인화장실;
    private String 진입로접근성;
    private String 청각장애인접근성;
    private String 휠체어;


    public Info() {
    }


    //여기서부터 get,set 함수를 사용하는데 이부분을 통해 값을 가져옴
    public String get가족화장실() { return 가족화장실; }
    public void set가족화장실(String 가족화장실) { this.가족화장실 = 가족화장실; }

    public String get매표소() { return 매표소; }
    public void set매표소(String 매표소) { this.매표소 = 매표소; }

    public String get기저귀교환대() { return 기저귀교환대; }
    public void set기저귀교환대(String 기저귀교환대) { this.기저귀교환대 = 기저귀교환대; }

    public String get시각장애인접근성() { return 시각장애인접근성; }
    public void set시각장애인접근성(String 시각장애인접근성) { this.시각장애인접근성 = 시각장애인접근성; }

    public String get수유실() { return 수유실; }
    public void set수유실(String 수유실) { this.수유실 = 수유실; }

    public String get유아차대여() { return 유아차대여; }
    public void set유아차대여(String 유아차대여) { this.유아차대여 = 유아차대여; }

    public String get유아차보관소() { return 유아차보관소; }
    public void set유아차보관소(String 유아차보관소) { this.유아차보관소 = 유아차보관소; }


    public String get장애인엘리베이터() { return 장애인엘리베이터; }
    public void set장애인엘리베이터(String 장애인엘리베이터) { this.장애인엘리베이터 = 장애인엘리베이터; }

    public String get장애인주차장() { return 장애인주차장; }
    public void set장애인주차장(String 장애인주차장) { this.장애인주차장 = 장애인주차장; }


    public String get장애인화장실() { return 장애인화장실; }
    public void set장애인화장실(String 장애인화장실) { this.장애인화장실 = 장애인화장실; }


    public String get진입로접근성() { return 진입로접근성; }
    public void set진입로접근성(String 진입로접근성) { this.진입로접근성 = 진입로접근성; }


    public String get청각장애인접근성() { return 청각장애인접근성; }
    public void set청각장애인접근성(String 청각장애인접근성) { this.청각장애인접근성 = 청각장애인접근성; }


    public String get휠체어() { return 휠체어; }
    public void set휠체어(String 휠체어) { this.휠체어 = 휠체어; }



    //그룹 생성
    public Info( String 가족화장실,String 기저귀교환대,String 매표소,String 수유실,String 시각장애인접근성,String 유아차대여,String 유아차보관소,String 장애인엘리베이터,String 장애인주차장,String 장애인화장실,String 진입로접근성,String 청각장애인접근성,String 휠체어) {
        this.가족화장실 = 가족화장실;
        this.매표소 = 매표소;
        this.기저귀교환대 = 기저귀교환대;
        this.시각장애인접근성 = 시각장애인접근성;
        this.수유실 = 수유실;
        this.유아차대여  = 유아차대여;
        this.장애인엘리베이터 = 장애인엘리베이터;
        this.장애인주차장 = 장애인주차장;
        this.휠체어 = 휠체어;
        this.청각장애인접근성 = 청각장애인접근성;
        this.진입로접근성 = 진입로접근성;
        this.장애인화장실 = 장애인화장실;
    }
}