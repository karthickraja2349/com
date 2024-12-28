package com.zoho.padippaayvu.view;

import java.io.Console;

import java.util.Scanner;
import java.util.List;
import java.util.Map;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.zoho.padippaayvu.controller.PadippaayvuSystem;

import com.zoho.padippaayvu.supportfiles.ValidationCheck;
import com.zoho.padippaayvu.supportfiles.ErrorStatements;
import com.zoho.padippaayvu.supportfiles.InteractiveStatements;

import com.zoho.padippaayvu.model.Student;
import com.zoho.padippaayvu.model.Exam;
import com.zoho.padippaayvu.model.Question;
import com.zoho.padippaayvu.model.Admin;

class PadippaayvuManagement extends ValidationCheck{

      private Scanner input = new Scanner(System.in);
      private PadippaayvuSystem padippaayvuSystem = PadippaayvuSystem.getPadippaayvuSystemInstance();
      private static PadippaayvuManagement padippaayvuManagement;
      
      private PadippaayvuManagement(){
      
      }
      
      public static PadippaayvuManagement getPadippaayvuManagementInstance(){
          if(padippaayvuManagement == null)
               padippaayvuManagement = new PadippaayvuManagement();
          return padippaayvuManagement;
      }
      
      //admin login
       public  boolean loginAdmin(){
             String[] adminLoginDetails = loginCredentials();
             String adminUserName = adminLoginDetails[0];
             String adminPassword = adminLoginDetails[1];
             boolean checkAdmin = padippaayvuSystem.adminCheck(adminUserName, adminPassword);
             if(checkAdmin)
                    System.out.println(InteractiveStatements.ADMIN_WELCOME +  adminUserName);
             else{
                    System.err.println(ErrorStatements.ADMIN_INVALID_LOGIN);
                  // adminAuthentication.checking();
                   return false;
             }
             return true;             
       } 
       
     //authenticate root admin
       public boolean checkRootAdmin(){
             String[] adminLoginDetails = loginCredentials();
             String adminUserName = adminLoginDetails[0];
             String adminPassword = adminLoginDetails[1];            
            boolean checkRootAdmin  =  padippaayvuSystem.authorizeAdmin(adminUserName,adminPassword);
             if(checkRootAdmin)
                    System.out.println(InteractiveStatements.ADMIN_WELCOME + adminUserName);
             else{
                   System.err.println(ErrorStatements.ADMIN_INVALID_LOGIN);
                   return false;
             }
             return true;                          
       }       
       
              //add admin
       public  void addAdmin(){
            System.out.printf(InteractiveStatements.ADMIN_ADDING_CATALOGUE);
            System.out.println("--------------------");
	    String adminName  = getValidatedName(InteractiveStatements.NEW_SIGNUP_NAME_PROMPT);
	    String adminUserName = getValidatedName(InteractiveStatements.USER_NAME_PROMPT);
	    String password = getValidatedPassword(InteractiveStatements.PASSWORD_PROMPT);
	
	    Admin admin = new Admin(adminName,adminUserName,password);
	    padippaayvuSystem.addAdmin(admin);
	    System.out.println("-------------------------");
      }
      
       //get password using java.io.Console
       private String getPassword(String prompt){
            Console console = System.console();
            System.out.println(prompt);
            char passwordArray[] = console.readPassword();
            String password = new String(passwordArray);
            return password;
       }
       
       //get and validate name
       private String getValidatedName(String prompt){
           System.out.println(prompt);
           String studentName = input.nextLine();
           while(!validateName(studentName)){
               System.out.println(ErrorStatements.NAME_MISMATCH + prompt);
               studentName =  input.nextLine();
           }
           return studentName;   
       }       
       
       //validate password
       private String getValidatedPassword(String prompt){
           System.out.println(prompt);
           String studentPassword = input.nextLine();
           while(!validatePassword(studentPassword)){
              System.out.println(ErrorStatements.PASSWORD_MISMATCH + prompt);
              studentPassword = input.nextLine();
           }
           return studentPassword;   
       }
             
       //get and validate mailid       
       private String getValidatedMailId(String prompt){
           System.out.println(prompt);
           String studentMailID = input.nextLine();
           while(!validateMailID(studentMailID)){
              System.out.println(ErrorStatements.MAILID_MISMATCH + prompt);
              studentMailID = input.nextLine();
           }
           return studentMailID;
       }
                     
       //for taking input as username and password
       private String[] loginCredentials(){
            String[] loginDetails = new String[2];
            System.out.println("----------------------------------");
            System.out.println(InteractiveStatements.USER_NAME_PROMPT);
            String userName = input.nextLine();
            String password = getPassword(InteractiveStatements.PASSWORD_PROMPT);
            loginDetails[0] = userName;
            loginDetails[1] =  password;
            return loginDetails;
       }
       
       //student login
       public boolean studentLogin(){
            String[] studentLoginDetails = loginCredentials();
            String studentUserName = studentLoginDetails[0];
            String studentPassword = studentLoginDetails[1];
            boolean studentCheck = padippaayvuSystem.authenticateStudent(studentUserName, studentPassword);
            if(studentCheck)
                 System.out.println(InteractiveStatements.STUDENT_WELCOME+ studentUserName);
            else{
                   System.err.println(ErrorStatements.STUDENT_INVALID_LOGIN);
                   return false;     
            }
            return true;
       }
       
       //new Student
       public boolean studentSignup(){
           System.out.println("------------------------------------");
           System.out.printf(InteractiveStatements.NEW_STUDENT_CATALOGUE);
           String studentName = getValidatedName(InteractiveStatements.NEW_SIGNUP_NAME_PROMPT);
           System.out.println(InteractiveStatements.USER_STUDYING_CLASS_PROMPT);
           String className = input.nextLine();
           String studentUserName = getValidatedName(InteractiveStatements.USER_NAME_PROMPT);
           String studentPassword = getValidatedPassword(InteractiveStatements.PASSWORD_PROMPT);
           String MailId = getValidatedMailId(InteractiveStatements.MAILID_PROMPT);
           
           Student student = new Student(studentName, studentUserName, studentPassword, MailId,className);
           boolean studentEntry = padippaayvuSystem.addStudent(student);
           System.out.println(InteractiveStatements.SUCCESSFULL_SIGNUP);
           System.out.println(InteractiveStatements.PASSWORD_REMEMBERANCE + studentPassword);
           return studentEntry;
       }    
       
       //create Exam
       public boolean createExam() {
           boolean examEntry = false;
           System.out.println("------------------------------------");
           System.out.println(InteractiveStatements.EXAM_NAME_PROMPT);
           String examName = input.nextLine();
           System.out.println(InteractiveStatements.EXAM_DATE_PROMPT);
           String date = input.nextLine();

           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
           try {
               Date examDate = new java.sql.Date(simpleDateFormat.parse(date).getTime());
               Exam exam = new Exam(examName, examDate);
               examEntry = padippaayvuSystem.addExam(exam);
           } 
           catch (ParseException parseException) {
              System.err.println(ErrorStatements.INVALID_DATE_FORMAT);
           }
           if(examEntry)
               System.out.println(InteractiveStatements.EXAM_SUCCESSFULL_PROMPT + "ExamName:" +examName);  
           return examEntry;    
     }
     
     //set questions
     public boolean createQuestions() {
          boolean isSuccess = false;
          System.out.println("------------------------------------");
          System.out.println(InteractiveStatements.FINISH_QUESTION_TYPING);
    
         while (true) {
            System.out.println(InteractiveStatements.QUESTION_NUMBER_PROMPT);
            String QuestionNum = input.nextLine();
            if (QuestionNum.equalsIgnoreCase("STOP")) {
               break;  
            }
            
           short questionNumber = Short.parseShort(QuestionNum);
           System.out.println("-----------------");
           System.out.println(InteractiveStatements.QUESTION_PROMPT);
           String questionName = input.nextLine();
           
           System.out.println("(a)" + InteractiveStatements.OPTION_PROMPT );
           String option1 = input.nextLine();
        
           System.out.println("(b)" + InteractiveStatements.OPTION_PROMPT);
           String option2 = input.nextLine();
        
           System.out.println("(c)" +InteractiveStatements.OPTION_PROMPT);
           String option3 = input.nextLine();
        
           System.out.println("(d)" +InteractiveStatements.OPTION_PROMPT);
           String option4 = input.nextLine();
           
           System.out.println(InteractiveStatements.ANSWER_PROMPT);
           String answer = input.nextLine();

           Question question = new Question(questionNumber, questionName, option1, option2, option3, option4, answer);
           isSuccess = padippaayvuSystem.addQuestions(question);
           if (!isSuccess) {
              System.err.println(ErrorStatements.FAILED_TO_SET_QUESTIONS);
              break;  
           }
       }
       if (isSuccess) {
          System.out.println(InteractiveStatements.SUCCESSFULL_QUESTION_SETUP);
       }
       return isSuccess;
    }
    
    //retrive Available tests
    private String availableTests() {
       StringBuilder test = new StringBuilder();
       test.append("----------------------------------\n");
       test.append(String.format("| %-5s | %-20s |\n", "No.", "Subject"));
       test.append("----------------------------------\n");
       test.append(String.format("| %-5s | %-20s |\n", "1", "Social"));
       test.append(String.format("| %-5s | %-20s |\n", "2", "Chemistry"));
       test.append(String.format("| %-5s | %-20s |\n", "3", "Tamil"));
       test.append("----------------------------------\n");
       return test.toString();
    }
    
    //get Registered UserMailId
    private String getMailId(){
       System.out.println("-------------------------------------");
       System.out.println(InteractiveStatements.MAILID_PROMPT);
       String mailId = input.nextLine();
       return mailId;
    }
    
    //verify the mailId is registered or not
    private boolean verifyMailId(String mailId){    
       boolean validMailId = padippaayvuSystem.verifyMailId(mailId);
       if(validMailId)
          return true;
       else
          return false;
    }
    
    //input and test subject selection
    public void startTest() {
       String mailId = getMailId();
       while (!verifyMailId(mailId)) {
          System.out.println(ErrorStatements.INVALID_MAILID);
          mailId = getMailId();
       }
       List<Question> questions = null;
       byte userTestChoice;
       while (true) {
          System.out.println("-----------------------------------------------");
          System.out.println(InteractiveStatements.AVAILABLE_SUBJECTS);
          System.out.println(availableTests());
          System.out.print(InteractiveStatements.SUBJECT_CHOOSE_PROMPT );

          if (input.hasNextByte()) {
            userTestChoice = input.nextByte();
            if (userTestChoice == 1 || userTestChoice == 2 || userTestChoice == 3 ) {
                questions = padippaayvuSystem.takeTest(userTestChoice);
                break;
            } 
            else
                System.out.println(ErrorStatements.INVALID_INPUT_PRESS +"Please select 1 or 2 or 3.");
          } 
         else {
            System.err.println(ErrorStatements.INVALID_INPUT_PRESS);
            input.next();  
         }
      }

      if (questions != null && !questions.isEmpty()) 
        processTest(questions, userTestChoice, mailId);
      else 
        System.out.println(InteractiveStatements.NO_QUESTION_FOUND);
   }

    //  process the answers and calculate marks
   private void processTest(List<Question> questions, byte userTestChoice, String mailId) {
      int totalMarks = 0;
      for (Question question : questions) {
        totalMarks += answerTheQuestion(question, userTestChoice, mailId);
      }

      padippaayvuSystem.saveAnswer(userTestChoice, (byte) totalMarks, mailId);
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("Test Completed Successfully!");
      System.out.println("Your total marks: " + totalMarks + " out of " + questions.size());
      System.out.println("--------------------------------------------------------------------------------");
   }

   //Qustion display and Answer
   private int answerTheQuestion(Question question, byte examId,String userMailId) {
       StringBuilder questionDisplay = new StringBuilder();
       questionDisplay.append("--------------------------------------------------------------------------------\n");
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "QuestionNumber is", question.getQuestionNumber()));
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "Question is", question.getQuestion()));
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "a", question.getOption1()));
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "b", question.getOption2()));
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "c", question.getOption3()));
       questionDisplay.append(String.format("| %-20s : %-55s |\n", "d", question.getOption4()));
       questionDisplay.append("--------------------------------------------------------------------------------\n");

       System.out.print(questionDisplay.toString());
       System.out.print("Enter your answer: ");
       String answer = input.next();

       return padippaayvuSystem.saveAnswer(question.getQuestionNumber(), answer, examId, userMailId);
   }
   
   //view profile of the user
   public void viewTestHistory() {
      String mailId = getMailId();
      while (!verifyMailId(mailId)) {
        System.out.println(ErrorStatements.INVALID_MAILID);
        mailId = getMailId();
      }
      List<Map<String, String>> history = padippaayvuSystem.viewTestHistory(mailId);
      if (history.isEmpty()) {
        System.out.println("No test history found for the provided email ID.");
        return;
     }
     for (Map<String, String> record : history) {
        StringBuilder historyDisplay = new StringBuilder();
        historyDisplay.append("--------------------------------------------------------------------------------\n");
        historyDisplay.append(String.format("| %-20s : %-55s |\n", "Student Name", record.get("StudentName")));
        historyDisplay.append(String.format("| %-20s : %-55s |\n", "Exam Name", record.get("ExamName")));
        historyDisplay.append(String.format("| %-20s : %-55s |\n", "Exam Id", record.get("ExamId")));
        historyDisplay.append(String.format("| %-20s : %-55s |\n", "Marks", record.get("Marks")));
        historyDisplay.append("--------------------------------------------------------------------------------\n");
        System.out.print(historyDisplay.toString());
    }
  }



       
       

}
