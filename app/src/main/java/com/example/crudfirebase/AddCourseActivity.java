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

public class AddCourseActivity extends AppCompatActivity {

    private TextInputEditText courseName_et, coursePrice_et, courseSuitedFor_et, courseImage_et,
            courseLink_et, courseDesc_et;
    private Button addCourse_btn;
    private ProgressBar loadingPB;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    private String cousrseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseName_et = findViewById(R.id.idEdt_CourseName);
        coursePrice_et = findViewById(R.id.idEdt_CoursePrice);
        courseSuitedFor_et = findViewById(R.id.idEdt_CourseSuitedFor);
        courseImage_et = findViewById(R.id.idEdt_CourseImageLink);
        courseLink_et = findViewById(R.id.idEdt_CourseLink);
        courseDesc_et = findViewById(R.id.idEdt_CourseDesc);

        addCourse_btn = findViewById(R.id.idBtnAddCourse);
        loadingPB = findViewById(R.id.idPBLoading);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference("Courses");

        addCourse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String courseName = courseName_et.getText().toString();
                String coursePrice = coursePrice_et.getText().toString();
                String courseSuitedFor = courseSuitedFor_et.getText().toString();
                String courseImage = courseImage_et.getText().toString();
                String courseLink = courseLink_et.getText().toString();
                String courseDesc = courseDesc_et.getText().toString();
                cousrseID = courseName;

                CourseRVModal courseRVModal = new CourseRVModal(cousrseID, courseName, courseDesc,
                        coursePrice, courseSuitedFor, courseImage, courseLink);

                dbReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        dbReference.child(cousrseID).setValue(courseRVModal);
                        Toast.makeText(AddCourseActivity.this, "Course added...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCourseActivity.this, "Error is" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}