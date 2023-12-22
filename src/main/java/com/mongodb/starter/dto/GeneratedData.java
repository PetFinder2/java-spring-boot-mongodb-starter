// GeneratedData.java

package com.mongodb.starter.dto;

public class GeneratedData {

    private String className;
    private String studentName;
    private String maturafachName;
    private int maturafachNote;

    public GeneratedData(String className, String studentName, String maturafachName, int maturafachNote) {
        this.className = className;
        this.studentName = studentName;
        this.maturafachName = maturafachName;
        this.maturafachNote = maturafachNote;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMaturafachName() {
        return maturafachName;
    }

    public void setMaturafachName(String maturafachName) {
        this.maturafachName = maturafachName;
    }

    public int getMaturafachNote() {
        return maturafachNote;
    }

    public void setMaturafachNote(int maturafachNote) {
        this.maturafachNote = maturafachNote;
    }

    @Override
    public String toString() {
        return "GeneratedData{" +
                "className='" + className + '\'' +
                ", studentName='" + studentName + '\'' +
                ", maturafachName='" + maturafachName + '\'' +
                ", maturafachNote=" + maturafachNote +
                '}';
    }
}
