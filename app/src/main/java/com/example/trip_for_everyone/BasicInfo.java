package com.example.trip_for_everyone;


public class BasicInfo {

    //변수 선언
    private String 웹사이트;
    private String 주소;
    private String 이용시간;
    private String 이용요금;
    private String 전화번호;
    private String 휴무일;
    private String latitude;
    private String longitude;


    public BasicInfo() {
    }

    //여기서부터 get,set 함수를 사용하는데 이부분을 통해 값을 가져옴
    public String get웹사이트() {
        return 웹사이트;
    }

    public void set웹사이트(String 웹사이트) {
        this.웹사이트 = 웹사이트;
    }

    public String get이용시간() {
        return 이용시간;
    }

    public void set이용시간(String 이용시간) {
        this.이용시간 = 이용시간;
    }

    public String get주소() {
        return 주소;
    }

    public void set주소(String 주소) {
        this.주소 = 주소;
    }



    public String get전화번호() { return 전화번호; }

    public void set전화번호(String 전화번호) {
        this.전화번호 = 전화번호;
    }

    public String get이용요금() {
        return 이용요금;
    }

    public void set이용요금(String 이용요금) {
        this.이용요금 = 이용요금;
    }


    public String get휴무일() {
        return 휴무일;
    }

    public void set휴무일(String 휴무일) {
        this.휴무일 = 휴무일;
    }


    //그룹 생성
    public BasicInfo(String 웹사이트, String 이용시간) {
        this.웹사이트 = 웹사이트;
        this.이용시간 = 이용시간;
        this.주소 = 주소;
        this.전화번호 = 전화번호;
        this.이용요금 = 이용요금;
        this.휴무일  = 휴무일;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}