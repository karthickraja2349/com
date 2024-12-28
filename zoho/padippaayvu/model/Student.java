package com.zoho.padippaayvu.model;

public class Student{

      private String studentName;
      private String studentUserName;
      private String studentClass;
      private String studentPassword;
      private String studentMailID;
      
      public Student(String studentName, String studentUserName, String studentPassword ,String studentMailID,String studentClass){
             this.studentName = studentName;
             this.studentUserName = studentUserName;
             this.studentPassword = studentPassword;
             this.studentMailID = studentMailID;  
             this.studentClass = studentClass;
      }
      
      public void setStudentName(String studentName){
             this.studentName = studentName;
      }
      
      public String getStudentName(){
             return studentName;
      }
      
      public void setStudentUserName(String studentUserName){
             this.studentUserName = studentUserName;
      }
      
      public String getStudentUserName(){
             return studentUserName;
      }                      
       
      public void setStudentPassword(String studentPassword){
             this.studentPassword = studentPassword;
      } 
      
      public String getStudentPassword(){
             return studentPassword;
      }
      
      public void setStudentMailID(String studentMailID){
             this.studentMailID = studentMailID;
      }
      
      public String getStudentMailID(){
             return studentMailID;
      }
      
      public void setStudentClass(String studentClass){
            this.studentClass = studentClass;
      }
      
      public String getStudentClass(){
           return studentClass;
      }
      
      public String toString(){
          StringBuilder student = new StringBuilder();
          student.append("-------------------------------------------\n");
          student.append(String.format("| %-26s : %-20s |\n", "Student Name is", getStudentName()));
          student.append(String.format("| %-26s : %-20s |\n", "Student userName is", getStudentUserName()));
          student.append(String.format("| %-26s : %-20s |\n", "Student password is","********"));
          student.append(String.format("| %-26s : %-20s |\n", "Student MailId is", getStudentMailID()));
          student.append("-------------------------------------------\n");
          return student.toString();
      }
}
