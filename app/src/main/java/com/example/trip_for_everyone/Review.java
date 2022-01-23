package com.example.trip_for_everyone;

public class Review {

    private String content;
    private int star;
    private int nowTime;
    private int uid;
    private String name;

    public Integer getstar() {
        return star;
    }
    public void setstar(Integer star) {
        this.star = star;
    }

    public String getcontent() {
        return content;
    }
    public void setcontent(String content) {this.content = content; }

    public Integer getuid() { return uid; }
    public void setuid(Integer uid) { this.uid = uid; }

    public Integer nowTime() { return nowTime; }
    public void setNowTime(Integer nowTime) { this.nowTime = nowTime; }

    public String getname() { return name; }
    public void setname(String name) { this.name = name; }


}
