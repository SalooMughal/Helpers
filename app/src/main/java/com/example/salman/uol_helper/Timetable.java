package com.example.salman.uol_helper;

/**
 * Created by SALMAN on 5/11/2017.
 */

public class Timetable {
    String id;
    String day;
    String time_slot;
    String room_no;
    String course_name;
    String status;

    public Timetable(String id, String day, String time_slot, String room_no, String course_name, String status) {
        this.id = id;
        this.day = day;
        this.time_slot = time_slot;
        this.room_no = room_no;
        this.course_name = course_name;
        this.status = status;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

