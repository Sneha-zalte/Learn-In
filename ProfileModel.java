package com.example.mocktest_app.Models;

public class ProfileModel
{
    private String name;
    private String email;
    private String phone;
    private int BookmarkCount;

    public ProfileModel(String name, String email,String phone, int BookmarkCount) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.BookmarkCount = BookmarkCount;
    }

    public int getBookmarkCount() {
        return BookmarkCount;
    }

    public void setBookmarkCount(int bookmarkCount) {
        BookmarkCount = bookmarkCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
