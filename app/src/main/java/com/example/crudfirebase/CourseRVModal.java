package com.example.crudfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModal implements Parcelable {
    private String courseID;
    private String courseName;
    private String courseDesc;
    private String coursePrice;
    private String courseSuitedFor;
    private String courseImage;
    private String courseLink;

    public CourseRVModal() {
    }

    public CourseRVModal(String courseID, String courseName, String courseDesc, String coursePrice,
                         String courseSuitedFor, String courseImage, String courseLink) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.coursePrice = coursePrice;
        this.courseSuitedFor = courseSuitedFor;
        this.courseImage = courseImage;
        this.courseLink = courseLink;
    }

    protected CourseRVModal(Parcel in) {
        courseID = in.readString();
        courseName = in.readString();
        courseDesc = in.readString();
        coursePrice = in.readString();
        courseSuitedFor = in.readString();
        courseImage = in.readString();
        courseLink = in.readString();
    }

    public static final Creator<CourseRVModal> CREATOR = new Creator<CourseRVModal>() {
        @Override
        public CourseRVModal createFromParcel(Parcel in) {
            return new CourseRVModal(in);
        }

        @Override
        public CourseRVModal[] newArray(int size) {
            return new CourseRVModal[size];
        }
    };

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseSuitedFor() {
        return courseSuitedFor;
    }

    public void setCourseSuitedFor(String courseSuitedFor) {
        this.courseSuitedFor = courseSuitedFor;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseID);
        parcel.writeString(courseName);
        parcel.writeString(courseDesc);
        parcel.writeString(coursePrice);
        parcel.writeString(courseSuitedFor);
        parcel.writeString(courseImage);
        parcel.writeString(courseLink);
    }
}
