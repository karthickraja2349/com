package com.zoho.padippaayvu.database;

public final class SQLQueries{
       
       private SQLQueries(){
       
       }
       
       public static final String CHECK_ADMIN_EXISTENCE = 
           "SELECT COUNT(*) FROM Admin WHERE admin_Username = ?";

       public static final String ADD_ADMIN = 
        "INSERT INTO Admin(admin_name, admin_userName, admin_password) VALUES (?,?,?)";
        
       public static final String CHECK_STUDENT = 
           "SELECT student_username, student_password FROM Student_Login WHERE student_username = ? AND student_password = ?";
       
       public static final String ADD_STUDENT = 
            "INSERT INTO Student(student_name, student_class) VALUES (?,?)";    
       
       public static final String ADD_INTO_STUDENT_LOGIN = 
            "INSERT INTO Student_Login(student_id , student_username, student_password, student_mailID) VALUES (?,?,?,?)";
            
       public static final String GET_STUDENT_ID = 
            "SELECT student_id FROM Student where student_name = ?";   
       
       public static final String CHECK_STUDENT_EXISTENCE = 
           "SELECT COUNT(*) FROM Student_Login WHERE student_mailID = ?";     
           
       public static final String ADMIN_CHECK = 
           "SELECT admin_username, admin_password FROM Admin WHERE admin_username = ?";      
           
       public static final String ADD_EXAM = 
          "INSERT INTO Exam(exam_name, exam_date) VALUES (?,?)";    
           
       public static final String SET_QUESTION = 
          "INSERT INTO Questions(question_number, question, option1, option2, option3, option4)VALUES(?,?,?,?,?,?)";  
       
       public static final String SET_ANSWER = 
          "INSERT INTO Answers(question_number,answer) VALUES (?,?)";
          
       public static final String TAKE_TEST = 
          "SELECT question_number, question, option1, option2, option3, option4  FROM Questions WHERE CAST(question_number AS CHAR) LIKE ?";
          
       public static final String CHECK_ANSWER = 
           "SELECT question_number, answer, exam_id FROM Answers WHERE exam_id = ? AND question_number = ? AND answer = ?"; 
       
       public static final String STORE_RESULT = 
          "INSERT INTO Result(student_id, exam_id, marks_obtained)VALUES (?,?,?)";
          
       public static final String GET_USER_ID = 
           "SELECT student_id from Student_Login WHERE student_mailId = ?";
       
       public static final String VERIFY_MAILID =
           "SELECT student_mailId FROM Student_Login WHERE student_mailId = ?";   
       
       public static final String GET_NAME = 
            "SELECT s.student_name FROM Student s join Student_Login sl on s.student_id = sl.student_id WHERE sl.student_id = ?";   
      
       public static final String GET_MARK_DETAILS = 
           "SELECT " +
           "  S.student_name AS StudentName, " +
           "  E.exam_name AS ExamName, " +
           "  E.exam_id AS ExamId, " +
           "  R.marks_obtained AS Marks " +
           "FROM " +
           "  Student_Login SL " +
           "JOIN " +
           "  Student S ON SL.student_id = S.student_id " +
           "JOIN " +
          "   Result R ON S.student_id = R.student_id " +
          "JOIN " +
          "   Exam E ON R.exam_id = E.exam_id " +
          "WHERE " +
          " SL.student_mailId = ?";

     
            
            
                 

                   


}
