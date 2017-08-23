package com.callor.todolist.DB;

/**
 * Created by callor on 2017-08-21.
 */

public class ToDoListVO {

    private String sDate ; // 할일 등록한 날짜
    private String sTime  ; // 할일 등록한 시각
    private String toDoMemo  ; // 할일 메모

    private String eDate ; // 완료 등록한 날짜
    private String eTime ; // 완료 등록한 시각

    public ToDoListVO() {
        super();
    }

    public ToDoListVO(String sDate, String sTime, String toDoMemo) {
        this.sDate = sDate;
        this.sTime = sTime;
        this.toDoMemo = toDoMemo;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getToDoMemo() {
        return toDoMemo;
    }

    public void setToDoMemo(String toDoMemo) {
        this.toDoMemo = toDoMemo;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }
}
