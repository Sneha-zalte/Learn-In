package com.example.mocktest_app.Models;

public class Questionmodel {
    private String qId;
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private int correctAns;
    private int selectedAns;
    private int status;
    private boolean isBookmarked;

    public Questionmodel(String qId,String question, String a, String b, String c, String d, int correctAns, int selectedAns, int status, boolean isBookmarked) {
        this.qId = qId;
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        this.correctAns = correctAns;
        this.selectedAns = selectedAns;
        this.status = status;
        this.isBookmarked = isBookmarked;
    }

    public Questionmodel(String id, String question, String a, String b, String c, String d, Long answer) {
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSelectedAns() {
        return selectedAns;
    }

    public void setSelectedAns(int selectedAns) {
        this.selectedAns = selectedAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }
}
