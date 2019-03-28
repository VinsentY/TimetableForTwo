package com.example.vinsent_y.nc_home.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String classRoom;
    private String number;
    private String lessonName;
    private int week;
    private int startWeek;
    private int endWeek;
    private int startTime;
    private int endTime;
    private int isSeparate; //是否区分单双周  0 代表不区分 1 代表单周 2代表双周

    public Course() {

    }

    public Course(String classRoom, String number, String lessonName, int week, int startWeek, int endWeek, int startTime, int endTime, int isSeparate) {
        this.classRoom = classRoom;
        this.number = number;
        this.lessonName = lessonName;
        this.week = week;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isSeparate = isSeparate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "classRoom='" + classRoom + '\'' +
                ", number='" + number + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", week=" + week +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isSeparate=" + isSeparate +
                '}';
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int isSeparate() {
        return isSeparate;
    }

    public void setSeparate(int separate) {
        isSeparate = separate;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getIsSeparate() {
        return isSeparate;
    }

    public void setIsSeparate(int isSeparate) {
        this.isSeparate = isSeparate;
    }
}
