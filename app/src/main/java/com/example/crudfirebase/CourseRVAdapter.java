package com.example.crudfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
    private ArrayList<CourseRVModal> courseRVModals;
    private Context context;
    private int lastPos = -1;
    private CourseClickInterface courseClickInterface;

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModals, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModals = courseRVModals;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, int position) {
        CourseRVModal courseRVModal = courseRVModals.get(position);
        holder.courseName_tv.setText(courseRVModal.getCourseName());
        holder.coursePrice_tv.setText("" + courseRVModal.getCoursePrice());
        Picasso.get().load(courseRVModal.getCourseImage()).into(holder.course_iv);

        setAnimation(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return courseRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseName_tv, coursePrice_tv;
        private ImageView course_iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName_tv = itemView.findViewById(R.id.idTVCourseName);
            coursePrice_tv = itemView.findViewById(R.id.idTVPrice);
            course_iv = itemView.findViewById(R.id.idIVCourse);
        }
    }

    public interface CourseClickInterface {
        public void onCourseClick(int pos);
    }
}
