package com.example.trip_for_everyone;

public class User {
    private String uid;
    private String userName;
    private Bookmark bookmark;
    private String address;

    public User() {
    }

    class Bookmark{
        private String spot;
        public String getSpot(){
            return spot;
        }
        public void setSpot(String spot){
            this.spot = spot;
        }
    }
    public String getUid(){
        return uid;
    }
    public String getUserName(){
        return userName;
    }
    public Bookmark getBookMark(){
        return bookmark;
    }
    public String getAddress(){
        return address;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setBookmark(Bookmark bookmark){
        this.bookmark.setSpot(bookmark.getSpot());
    }
    public void setAddress(String address){
        this.address = address;
    }
}
