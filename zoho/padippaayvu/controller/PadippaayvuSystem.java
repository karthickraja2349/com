package com.zoho.padippaayvu.controller;

import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.zoho.padippaayvu.database.DatabaseConnection;
import com.zoho.padippaayvu.database.SQLQueries;
import com.zoho.padippaayvu.model.Student;
import com.zoho.padippaayvu.model.Admin;
import com.zoho.padippaayvu.model.Exam;
import com.zoho.padippaayvu.model.Question;

public class PadippaayvuSystem{
       
       private static PadippaayvuSystem padippaayvuSystem;
       
       private PadippaayvuSystem(){
       
       }
       
       public static PadippaayvuSystem getPadippaayvuSystemInstance(){
             if(padippaayvuSystem == null)
                   padippaayvuSystem = new PadippaayvuSystem();
             return padippaayvuSystem;      
       }
       
       //for preparedStatement  
       private PreparedStatement getPreparedStatement(String query){
          PreparedStatement  preparedStatement = DatabaseConnection.getPreparedStatement(query);
          return preparedStatement;
       } 
       
       //authorize  root Admin to add new Admin
       public boolean authorizeAdmin(String userName, String password){
            if(userName.equals(Admin.getRootUserName()) && password.equals(Admin.getRootPassword()))
                    return true;
            return false;        
       }
       
       //authenticate admin
       public boolean adminCheck(String userName, String password){
             try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.ADMIN_CHECK)){
                 preparedStatement.setString(1,userName);
                 try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                      String user_Name = resultSet.getString("admin_username");
                      String passWord = resultSet.getString("admin_password");
                      if(userName.equals(user_Name) && password.equals(passWord))
                            return true;
                   }
                }
            }
            catch(SQLException sqlException){
                   sqlException.getMessage();
            }
            return false;
       }
             
       //check previous records
       private boolean recordExists(String query, String param) throws SQLException {
             PreparedStatement existenceStatement = getPreparedStatement(query);
             existenceStatement.setString(1, param);
             ResultSet resultSet = existenceStatement.executeQuery();
             resultSet.next();
             return resultSet.getInt(1) > 0;
      }
      
      //authenticate Student
      public boolean authenticateStudent(String studentUserName, String studentPassword){
              try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.CHECK_STUDENT)){
                 preparedStatement.setString(1,studentUserName);
                 preparedStatement.setString(2,studentPassword);
                 try(ResultSet resultSet = preparedStatement.executeQuery()){
                   while(resultSet.next()){
                     String userName = resultSet.getString("student_username");
                     String password = resultSet.getString("student_password");
                     if((studentUserName.equals(userName)) && (studentPassword.equals(password)))
                         return true;
                  }
               }
            }
            catch(SQLException sqlException){
                  System.err.println("Sorry for the Inconvinience ! Try After one Hour");
            }
            return false;
     }       
     
     //new Student login
     public boolean addStudent(Student student){
            boolean isSuccess = false;
            try{
                if(recordExists(SQLQueries.CHECK_STUDENT_EXISTENCE,student.getStudentMailID())){
                     System.out.println("Student  Mail_ID already present:");
                     return false;
                }     
                 
                 PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.ADD_STUDENT);
                 preparedStatement.setString(1,student.getStudentName());
                 preparedStatement.setString(2,student.getStudentClass());
                 preparedStatement.executeUpdate();
                 isSuccess = true;
            }
            catch(SQLException sqlException){
                 System.err.println("Sorry for the Inconvinience ! Try After one Hour");
                 sqlException.printStackTrace();
            }
            if(isSuccess)
                return insertIntoLogin(student);
            else
                return false;              
     }
   
     //valid mailid
     public boolean verifyMailId(String mailId){
        try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.VERIFY_MAILID)){
              preparedStatement.setString(1,mailId);
              try(ResultSet resultSet = preparedStatement.executeQuery()){
                  if(resultSet.next())
                     return true;
              }
        }
        catch(SQLException sqlException){
             System.err.println("Sorry for the Inconvenience! Try After one Hour.");
             sqlException.printStackTrace();        
        }
        return false;
    }
     
     //insert into Student login table also while adding new Student
    private boolean insertIntoLogin(Student student) {
         try (PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.GET_STUDENT_ID)) {
              preparedStatement.setString(1, student.getStudentName());
              try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 if (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    try (PreparedStatement insertLoginStmt = DatabaseConnection.getPreparedStatement(SQLQueries.ADD_INTO_STUDENT_LOGIN)) {
                       insertLoginStmt.setInt(1, studentId);
                       insertLoginStmt.setString(2, student.getStudentUserName());
                       insertLoginStmt.setString(3, student.getStudentPassword());
                       insertLoginStmt.setString(4, student.getStudentMailID());
                       insertLoginStmt.executeUpdate();
                       return true;
                   }
                }
             } 
         } 
         catch (SQLException sqlException) {
                System.err.println("Sorry for the Inconvenience! Try After one Hour.");
                sqlException.printStackTrace();
         }
         return false;
    }
    
      //add admin     
    public boolean addAdmin(Admin admin){
         try{
            if(recordExists(SQLQueries.CHECK_ADMIN_EXISTENCE,admin.getAdminUserName())){
                System.out.println("Admin userName  already exists. Please Enter Different UserName:.");
                return false;     
            }                             
              
            PreparedStatement preparedStatement =getPreparedStatement(SQLQueries.ADD_ADMIN);
            preparedStatement.setString(1,admin.getAdminName());
            preparedStatement.setString(2,admin.getAdminUserName());
            preparedStatement.setString(3,admin.getAdminPassword());
            preparedStatement.executeUpdate();
            System.out.println("ADMIN ADDED SUCCESSFULLY");
            return true;
        }
        catch(SQLException sqlException){
                System.err.println("There was an issue in Add Admin. Verify the Query/Ensure the Database connectivity");
                sqlException.printStackTrace();
        }  
        return false;             
   }  
    
    //addExam
    public boolean addExam(Exam exam){
        boolean isSuccess = false;
        try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.ADD_EXAM)){
              preparedStatement.setString(1,exam.getExamName());
              preparedStatement.setDate(2,exam.getExamDate());
              preparedStatement.executeUpdate();
              isSuccess = true;
       }
       catch(SQLException sqlException){
          System.err.println("Sorry for the Inconvinience ! Try After one Hour");
          sqlException.printStackTrace();
       }  
       return isSuccess;              
    }
    
    //set questions
    public boolean addQuestions(Question question){
       boolean isSuccess = false;
       try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.SET_QUESTION)){
             preparedStatement.setInt(1, question.getQuestionNumber());
             preparedStatement.setString(2, question.getQuestion());
             preparedStatement.setString(3, question.getOption1());
             preparedStatement.setString(4, question.getOption2());
             preparedStatement.setString(5, question.getOption3());
             preparedStatement.setString(6, question.getOption4());
             preparedStatement.executeUpdate();
             isSuccess = true;
       }
       catch(SQLException sqlException){
          System.err.println("Sorry for the Inconvinience ! Try After one Hour");
          sqlException.printStackTrace();       
       } 
       if(isSuccess)
           return addAnswers(question);
       else    
         return false;        
    }
    
    //set answer for the Question
    private boolean addAnswers(Question question){
         try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.SET_ANSWER)){
               preparedStatement.setInt(1, question.getQuestionNumber());
               preparedStatement.setString(2, question.getAnswer());
               preparedStatement.executeUpdate();
               return true;
         }
         catch(SQLException sqlException){
            System.err.println("Sorry for the Inconvinience ! Try After one Hour");
            sqlException.printStackTrace();  
         }
         return false;
    }
    
    //takeTest
    public List<Question> takeTest(byte subject){
          List<Question> question = new ArrayList();
          String pattern = subject+"%";
          try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.TAKE_TEST)){
               preparedStatement.setString(1, pattern);

               try (ResultSet resultSet = preparedStatement.executeQuery()){
                  while (resultSet.next()){
                    question.add(new Question(
                         (short)resultSet.getInt("question_number"),
                          resultSet.getString("question"),
                          resultSet.getString("option1"),
                          resultSet.getString("option2"),
                          resultSet.getString("option3"),
                          resultSet.getString("option4")
                     ));
                  }
              }
         }
         catch(SQLException sqlException){
            System.err.println("Sorry for the Inconvinience ! Try After one Hour");
            sqlException.printStackTrace();           
         }
         return question;
    }
    
/*   public boolean setAnswer(short questionNumber, String answer) {
         String query = "INSERT INTO Answers (question_number, answer) VALUES (?, ?)";

         try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
                preparedStatement.setShort(1, questionNumber);
                preparedStatement.setString(2, answer);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; 
         }
         catch (SQLException sqlException) {
               System.err.println("Error while saving the answer. Please try again.");
               sqlException.printStackTrace();
              return false; 
        }
    }*/
    
    //save and correct Answer
    public byte saveAnswer(short questionNumber, String answer, byte examId,String userMailId){
          byte marksObtained = 0;
          try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.CHECK_ANSWER)){
                preparedStatement.setInt(1, examId);
                preparedStatement.setInt(2, questionNumber);
                preparedStatement.setString(3, answer);
                
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {
                     String correctAnswer = resultSet.getString("answer");  
                     if (answer.equalsIgnoreCase(correctAnswer)) {
                        marksObtained = 1;
                     } 
                     else {
                       System.out.println("Incorrect answer for question number " + questionNumber + ". Correct answer: " + correctAnswer);
                     }
                 }
              }
         }
         catch (SQLException sqlException) {
             System.err.println("An error occurred while saving the answer.");
             sqlException.printStackTrace();
         }    
         return marksObtained;
    }
    
    //store the Result  
    public  void saveAnswer(byte examId, byte marksObtained, String userMailId){
         byte studentId = getStudentId(userMailId);
         try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.STORE_RESULT)){
               preparedStatement.setInt(1,studentId);
               preparedStatement.setInt(2,examId);
               preparedStatement.setInt(3,marksObtained);
               preparedStatement.executeUpdate();
         }
         catch(SQLException sqlException){
             System.err.println("An error occurred while saving the answer.");
             sqlException.printStackTrace();         
         }
    }
    
    //get studentId by mailId
    private byte getStudentId(String userMailId){
       byte studentId = 0;
       try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.GET_USER_ID)){
             preparedStatement.setString(1,userMailId);
              try (ResultSet resultSet = preparedStatement.executeQuery()){
                  if(resultSet.next()){
                     studentId = resultSet.getByte("student_id");
                     return studentId;
                  }   
               }
       }
       catch(SQLException sqlException){
              System.err.println("An error occurred while saving the answer.");
             sqlException.printStackTrace();      
       }
       return 0;
   }       
   
   public List<Map<String, String>> viewTestHistory(String mailId){
       List<Map<String, String>> history = new ArrayList<>();
       try(PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.GET_MARK_DETAILS)){
             preparedStatement.setString(1, mailId);
             
             try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                   Map<String, String> record = new HashMap<>();
                   record.put("StudentName", resultSet.getString("StudentName"));
                   record.put("ExamName", resultSet.getString("ExamName"));
                   record.put("ExamId",resultSet.getString("ExamId"));
                   record.put("Marks", resultSet.getString("Marks"));
                   history.add(record);
                }
            }
       }
       catch(SQLException sqlException){
                System.err.println("An error occurred while saving the answer.");
               sqlException.printStackTrace();           
       }
       return history;
       
        
   }
   
   
    
   // executor service framework
  //streams
  //method reference and lambda
  //reduce and collect
  //reentrant lock
  //fork join framework
  //race condition
  //thread local class    

                              
       
       
       
       
       
}
