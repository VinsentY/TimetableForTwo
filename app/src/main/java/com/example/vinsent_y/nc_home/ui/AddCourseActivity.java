package com.example.vinsent_y.nc_home.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinsent_y.nc_home.R;
import com.example.vinsent_y.nc_home.entity.Course;
import com.example.vinsent_y.nc_home.util.L;
import com.example.vinsent_y.nc_home.util.TextUtil;
import com.example.vinsent_y.nc_home.view.CustomDialog;

import java.util.Arrays;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name_course;
    private TextView week_num;
    private TextView class_num;
    private EditText position_class;
    private EditText number_class;

    private CustomDialog dialog;


    private NumberPicker num_pick_one;
    private NumberPicker num_pick_two;
    private NumberPicker num_pick_three;
    private TextView tv_back;
    private TextView tv_confirm;
    private TextView tv_title;
    private Button bt_submit;

    private String[] weeks;
    private String[] lessons;
    private String[] option;
    private String[] days;

    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        initData();
        initView();
    }

    private void initData() {
        course = new Course("社会", "520", "爱的基础", 1, 1, 16, 1, 2, 0);
        weeks = new String[]{
                "周一",
                "周二",
                "周三",
                "周四",
                "周五",
                "周六",
                "周七",

        };
        days = new String[]{
                "第一周",
                "第二周",
                "第三周",
                "第四周",
                "第五周",
                "第六周",
                "第七周",
                "第八周",
                "第九周",
                "第十周",
                "第十一周",
                "第十二周",
                "第十三周",
                "第十四周",
                "第十五周",
                "第十六周",
        };

        lessons = new String[]{
                "第一节",
                "第二节",
                "第三节",
                "第四节",
                "第五节",
                "第六节",
                "第七节",
                "第八节",
                "第九节",
                "第十节",
                "第十一节",
                "第十二节",
                "第十三节",
        };

        // 0 代表不区分 1 代表单周 2代表双周
        option = new String[]{
                "连续周",
                "只上单周",
                "只上双周",
        };
    }

    private void initView() {
        name_course = findViewById(R.id.name_course);
        week_num = findViewById(R.id.week_num);
        class_num = findViewById(R.id.class_num);
        position_class = findViewById(R.id.position_class);
        number_class = findViewById(R.id.number_class);

        week_num.setOnClickListener(this);
        class_num.setOnClickListener(this);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_number_pick, R.style.Theme_Dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        num_pick_one = dialog.findViewById(R.id.num_pick_one);
        num_pick_two = dialog.findViewById(R.id.num_pick_two);
        num_pick_three = dialog.findViewById(R.id.num_pick_three);
        num_pick_one.setWrapSelectorWheel(false);
        num_pick_one.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        num_pick_two.setWrapSelectorWheel(false);
        num_pick_two.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        num_pick_three.setWrapSelectorWheel(false);
        num_pick_three.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        tv_back = dialog.findViewById(R.id.tv_back);
        tv_confirm = dialog.findViewById(R.id.tv_confirm);
        tv_title = dialog.findViewById(R.id.tv_title);
        tv_back.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.week_num:
                tv_title.setText("选择周数");
                setNumberPicker(num_pick_one, days, course.getStartWeek());
                setNumberPicker(num_pick_two, Arrays.copyOfRange(days,course.getStartWeek() - 1,days.length),course.getEndWeek() - course.getStartWeek() + 1);
                setNumberPicker(num_pick_three, option, course.isSeparate() + 1);
                //设置滑动监听
                num_pick_one.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    //当NunberPicker的值发生改变时，将会激发该方法
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        setNumberPicker(num_pick_two, Arrays.copyOfRange(days, newVal - 1, days.length), 1);
                    }
                });
                num_pick_two.setOnValueChangedListener(null);
                num_pick_three.setOnValueChangedListener(null);
                dialog.show();
                break;
            case R.id.class_num:
                tv_title.setText("选择节数");
                setNumberPicker(num_pick_one, weeks, course.getWeek());
                setNumberPicker(num_pick_two, Arrays.copyOfRange(lessons,0,lessons.length - 1), course.getStartTime());
                setNumberPicker(num_pick_three, Arrays.copyOfRange(lessons,course.getStartTime(),lessons.length), course.getEndTime() - course.getStartTime());
                //设置滑动监听
                num_pick_two.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    //当NunberPicker的值发生改变时，将会激发该方法
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        setNumberPicker(num_pick_three, Arrays.copyOfRange(lessons, newVal, lessons.length), 1);
                    }
                });
                num_pick_one.setOnValueChangedListener(null);
                num_pick_three.setOnValueChangedListener(null);
                dialog.show();
                break;
            case R.id.tv_back:
                dialog.dismiss();
                break;
            case R.id.tv_confirm:
                inputCourse();
                break;
            case R.id.bt_submit:
                course.setLessonName(name_course.getText().toString());
                course.setClassRoom(position_class.getText().toString());
                course.setNumber(number_class.getText().toString());
                if (TextUtil.isEmpty(course.getLessonName())) {
                    Toast.makeText(this, "课程不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtil.isEmpty(course.getClassRoom())) {
                    Toast.makeText(this, "地点不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtil.isEmpty(course.getNumber())) {
                    Toast.makeText(this, "教室不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("addCourse", course);
                    setResult(RESULT_OK, intent);
                    finish();
//                    Toast.makeText(this, course.toString(), Toast.LENGTH_SHORT).show();
                }
                L.e(course.toString());
                break;
        }
    }

    private void inputCourse() {
        switch (tv_title.getText().toString()) {
            case "选择周数":
                int startWeek = num_pick_one.getValue();
                int endWeek = startWeek + num_pick_two.getValue() - 1;
                int option = num_pick_three.getValue();
                if (startWeek > endWeek) {
                    Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show();
                } else {
                    course.setStartWeek(startWeek);
                    course.setEndWeek(endWeek);
                    course.setIsSeparate(option - 1);
                    week_num.setText(startWeek + "-" + endWeek + "周");
                    dialog.dismiss();
                }
                break;
            case "选择节数":
                int week = num_pick_one.getValue();
                int startTime = num_pick_two.getValue();
                int endTime = startTime + num_pick_three.getValue();
                if (startTime > endTime) {
                    Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show();
                } else {
                    course.setStartTime(startTime);
                    course.setEndTime(endTime);
                    course.setWeek(week);
                    class_num.setText(weeks[week - 1] + " " + startTime + "-" + endTime + "节");
                    dialog.dismiss();
                }
                break;
        }
    }

    private void setNumberPicker(NumberPicker numberPicker, String[] content, int val) {
        numberPicker.setDisplayedValues(null);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(content.length);
        numberPicker.setDisplayedValues(content);
        numberPicker.setValue(val);
    }
}
