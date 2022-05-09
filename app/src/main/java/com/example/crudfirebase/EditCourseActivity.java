package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private TextInputEditText courseName_et, coursePrice_et, courseSuitedFor_et, courseImage_et,
            courseLink_et, courseDesc_et;
    private Button updateCourse_btn, deleteCourse_btn;
    private ProgressBar loadingPB;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    private String courseID;

    private CourseRVModal courseRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        firebaseDatabase = FirebaseDatabase.getInstance();


        courseName_et = findViewById(R.id.idEdt_CourseName);
        coursePrice_et = findViewById(R.id.idEdt_CoursePrice);
        courseSuitedFor_et = findViewById(R.id.idEdt_CourseSuitedFor);
        courseImage_et = findViewById(R.id.idEdt_CourseImageLink);
        courseLink_et = findViewById(R.id.idEdt_CourseLink);
        courseDesc_et = findViewById(R.id.idEdt_CourseDesc);

        updateCourse_btn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourse_btn = findViewById(R.id.idBtnDeleteCourse);
        loadingPB = findViewById(R.id.idPBLoading);

        courseRVModal = getIntent().getParcelableExtra("course");
        if (courseRVModal != null) {
            courseName_et.setText(courseRVModal.getCourseName());
            coursePrice_et.setText(courseRVModal.getCoursePrice());
            courseSuitedFor_et.setText(courseRVModal.getCourseSuitedFor());
            courseImage_et.setText(courseRVModal.getCourseImage());
            courseLink_et.setText(courseRVModal.getCourseLink());
            courseDesc_et.setText(courseRVModal.getCourseDesc());
            courseID = courseRVModal.getCourseID();
        }

        dbReference = firebaseDatabase.getReference("Course").child(courseID);
        updateCourse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String courseName = courseName_et.getText().toString();
                String coursePrice = coursePrice_et.getText().toString();
                String courseSuitedFor = courseSuitedFor_et.getText().toString();
                String courseImage = courseImage_et.getText().toString();
                String courseLink = courseLink_et.getText().toString();
                String courseDesc = courseDesc_et.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("courseID", courseID);
                map.put("courseName", courseName);
                map.put("courseDesc", courseDesc);
                map.put("coursePrice", coursePrice);
                map.put("courseSuitedFor", courseSuitedFor);
                map.put("courseImage", courseImage);
                map.put("courseLink", courseLink);

                dbReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        dbReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this, "Fail to update course...", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        deleteCourse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });
    }

    private void deleteCourse() {
        dbReference.removeValue();
        Toast.makeText(this, "Course deleted...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
    }
}