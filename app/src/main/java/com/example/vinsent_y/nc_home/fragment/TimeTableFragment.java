package com.example.vinsent_y.nc_home.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinsent_y.nc_home.R;
import com.example.vinsent_y.nc_home.entity.Course;
import com.example.vinsent_y.nc_home.ui.AddCourseActivity;
import com.example.vinsent_y.nc_home.view.CustomDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static com.example.vinsent_y.nc_home.entity.Courses.ONE_DAY_TIMES;
import static com.example.vinsent_y.nc_home.entity.Courses.WEEK_NUM;

public class TimeTableFragment extends Fragment implements View.OnClickListener {
    private Course[][] courses;

    private TextView tv_week;

    //星期几
    private RelativeLayout day;
    private Calendar calendar;
    private CustomDialog dialog;
    private View view;
    private FloatingActionButton mButton;

    private Map<Course, View> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_table, null);
        initData();
        initView(view);
        return view;
    }

    private void addView(Course course) {
        courses[course.getStartTime()][course.getWeek()] = course;

        switch (course.getWeek()) {
            case 1:
                day = view.findViewById(R.id.ly_week_1);
                break;
            case 2:
                day = view.findViewById(R.id.ly_week_2);
                break;
            case 3:
                day = view.findViewById(R.id.ly_week_3);
                break;
            case 4:
                day = view.findViewById(R.id.ly_week_4);
                break;
            case 5:
                day = view.findViewById(R.id.ly_week_5);
                break;
            case 6:
                day = view.findViewById(R.id.ly_week_6);
                break;
            case 7:
                day = view.findViewById(R.id.ly_week_7);
                break;
        }

        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_course, null);
        float blockHeight = getActivity().getResources().getDimension(R.dimen.block_height);
        v.setY(blockHeight * (course.getStartTime() - 1)); //设置开始高度,即第几节课开始
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, (int) ((course.getEndTime() - course.getStartTime() + 1) * blockHeight - 8)); //设置布局高度,即跨多少节课
        v.setLayoutParams(params);
        setView(v, course);
        day.addView(v);
    }

    @SuppressLint("NewApi")
    private void setView(final View view_course, final Course course) {
        TextView tv_classRoom = view_course.findViewById(R.id.tv_classRoom);
        TextView tv_number = view_course.findViewById(R.id.tv_number);
        TextView tv_lessonName = view_course.findViewById(R.id.tv_lesson);

        tv_classRoom.setText(course.getClassRoom());
        tv_number.setText(course.getNumber());
        tv_lessonName.setText(course.getLessonName());

        int pureColor;
        //TODO 还未解决重复问题
        Random random = new Random();
        switch (random.nextInt(6)) {
            case 0:
                pureColor = R.color.pureColor6;
                break;
            case 1:
                pureColor = R.color.pureColor1;
                break;
            case 2:
                pureColor = R.color.pureColor2;
                break;
            case 3:
                pureColor = R.color.pureColor3;
                break;
            case 4:
                pureColor = R.color.pureColor4;
                break;
            case 5:
                pureColor = R.color.pureColor5;
                break;
            default:
                pureColor = R.color.pureColor5;
        }

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
        gradientDrawable.setCornerRadius(8f);//设置圆角Radius
        gradientDrawable.setColor(getResources().getColor(pureColor));//颜色

        view_course.setBackground(gradientDrawable);//设置为background
        view_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view_course, "确认删除该课程？", Snackbar.LENGTH_SHORT)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ViewGroup parent = (ViewGroup) view_course.getParent();
                                parent.removeView(view_course);
                                Toast.makeText(
                                        getActivity(), "删除成功", Toast.LENGTH_SHORT).
                                        show();
                            }
                        }).

                        show();
            }
        });


    }

    private void initData() {
        courses = new Course[ONE_DAY_TIMES + 1][WEEK_NUM + 1];
        addView(new Course("软件楼", "520", "爱的基础", 2, 1, 16, 1, 2, 0));
        addView(new Course("软件楼", "520", "爱的基础", 4, 1, 16, 3, 5, 0));

        addView(new Course("软件楼", "520", "爱的基础", 2, 1, 16, 5, 6, 0));
        addView(new Course("软件楼", "1314", "外观看出你的状态", 2, 1, 16, 7, 8, 0));
        addView(new Course("软件楼", "520", "爱的基础", 1, 1, 16, 5, 6, 0));

        calendar = Calendar.getInstance();
    }

    private void initView(View view) {

        mButton = view.findViewById(R.id.add_course);
        mButton.setOnClickListener(this);
        int colorPrimary = getResources().getColor(R.color.colorPrimary);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                tv_week = view.findViewById(R.id.tv_week_1);
                tv_week.setTextColor(colorPrimary);
                break;
            case 2:
                tv_week = view.findViewById(R.id.tv_week_2);
                tv_week.setTextColor(colorPrimary);
                break;
            case 3:
                tv_week = view.findViewById(R.id.tv_week_3);
                tv_week.setTextColor(colorPrimary);
                break;
            case 4:
                tv_week = view.findViewById(R.id.tv_week_4);
                tv_week.setTextColor(colorPrimary);
                break;
            case 5:
                tv_week = view.findViewById(R.id.tv_week_5);
                tv_week.setTextColor(colorPrimary);
                break;
            case 6:
                tv_week = view.findViewById(R.id.tv_week_6);
                tv_week.setTextColor(colorPrimary);
                break;
            case 7:
                tv_week = view.findViewById(R.id.tv_week_7);
                tv_week.setTextColor(colorPrimary);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_course:
                Intent intent = new Intent(getContext(), AddCourseActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Course course = (Course) data.getSerializableExtra("addCourse");
                    addView(course);
                }
                break;
        }
    }
}
