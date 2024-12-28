package com.zoho.padippaayvu.model;

import java.sql.Date;

public class Exam {
    private short examId;       
    private String examName;   
    private Date examDate;       


    public Exam(short examId, String examName, Date examDate) {
        this.examId = examId;
        this.examName = examName;
        this.examDate = examDate;
    }

  
    public Exam(String examName, Date examDate) {
        this.examName = examName;
        this.examDate = examDate;
    }
    

   
    public short getExamId() {
        return examId;
    }

    
    public String getExamName() {
        return examName;
    }

  
    public void setExamName(String examName) {
        this.examName = examName;
    }

    
    public Date getExamDate() {
        return examDate;
    }

   
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    
    
    public String toString() {
        StringBuilder exam = new StringBuilder();
        exam.append("-------------------------------------------\n");
        exam.append(String.format("| %-16s : %-20s |\n", "Exam ID", getExamId()));
        exam.append(String.format("| %-16s : %-20s |\n", "Exam Name", getExamName()));
        exam.append(String.format("| %-16s : %-20s |\n", "Exam Date", getExamDate()));
        exam.append("-------------------------------------------\n");
        return exam.toString();
    }
}

