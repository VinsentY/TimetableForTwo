package com.example.vinsent_y.nc_home.entity;

import java.util.ArrayList;
import java.util.List;

public class Courses {

    private ArrayList<Course>[][] courses;

    private int num_of_class = 0;

    public static int WEEK_NUM = 7;
    public static int ONE_DAY_TIMES = 13;

    public Courses() {
        courses = new ArrayList[ONE_DAY_TIMES + 1][WEEK_NUM + 1]; //列表数组初始化的正确方法
        for (int i = 1; i <= ONE_DAY_TIMES; i++) {
            for (int j = 1; j <= WEEK_NUM; j++) {
                courses[i][j] = new ArrayList<>();
            }
        }

    }


    public int getNum() {
        return num_of_class;
    }

    public void addCourse(String classRoom, String number, String lesson, int week, int startWeek, int endWeek, int startTime, int endTime, int isSeparate) {
        courses[startTime][week].add(new Course(classRoom, number, lesson, week, startWeek, endWeek, startTime, endTime, isSeparate));
        num_of_class++;
    }

    public boolean deleteCourse(int week, int startTime, String lesson) {
        num_of_class--;
        List<Course> course = courses[week][startTime];
        for (int i = 0; i < course.size(); i++) {
            Course t = course.get(i);
            if (t.getLessonName().equals(lesson)) {
                course.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Course> getCourse(int week, int startTime) {
        return courses[startTime][week];
    }



}


