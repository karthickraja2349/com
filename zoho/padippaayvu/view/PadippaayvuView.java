package com.zoho.padippaayvu.view;

import java.io.PrintWriter;
import java.util.Scanner;

import com.zoho.padippaayvu.supportfiles.PadippaayvuMenu;
import com.zoho.padippaayvu.supportfiles.ErrorStatements;
public class PadippaayvuView {
    
    private PrintWriter writer;
    private PadippaayvuMenu padippaayvuMenu = PadippaayvuMenu.getPadippaayvuMenuInstance();
    private PadippaayvuManagement padippaayvuManagement =PadippaayvuManagement.getPadippaayvuManagementInstance();
    private Scanner input = new Scanner(System.in);
    
    
   public void initialize() {
        padippaayvuMenu.noteMenu();
        writer = new PrintWriter(System.out,true); 
        byte choice;
        while (true) {
            displayMenu();
            
            if (input.hasNextByte()) {
                choice = input.nextByte(); 
            }
             else {
                System.err.println(ErrorStatements.INPUT_MISMATCH_ERROR);
                input.next(); 
                continue; 
            }

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                     studentMenu();
                    break;
                case 3:
                     exitMenu();
                    break;
                default:
                    System.err.println(ErrorStatements.INVALID_INPUT_ERROR + choice);
            }
        }
    }

    private void displayMenu() {
        writer.println("Welcome to Padippaayvu!");
        writer.println("-----------------------------------");
        writer.printf("| %-6s | %-22s |\n", "Option", "Main Menu");
        writer.println("|--------+------------------------|");
        writer.printf("| %-6d | %-22s |\n", 1, "Admin");
        writer.printf("| %-6d | %-22s |\n", 2, "Student");
        writer.printf("| %-6d | %-22s |\n", 3, "Exit");
        writer.println("-----------------------------------");
        writer.println("Enter Your choice: (Press the Option number)");
        writer.flush(); 
    }
    
  private void adminMenu() {
        byte adminChoice;

        while (true) {
           writer.println("-----------------------------------");
           writer.printf("| %-6s | %-22s |\n", "Option", "Admin Menu");
           writer.println("|--------+------------------------|");
           writer.printf("| %-6d | %-22s |\n", 1, "Login Admin");
           writer.printf("| %-6d | %-22s |\n", 2, "Add Admin");
           writer.printf("| %-6d | %-22s |\n", 3, "Back to Main Menu");
           writer.println("-----------------------------------");
           writer.println("Enter Your choice: (Press the Option number)");
           writer.flush();

           if (input.hasNextByte()) {
              adminChoice = input.nextByte(); 
          } 
          else {
             System.err.println(ErrorStatements.INPUT_MISMATCH_ERROR);
             input.next(); 
             continue; 
          }

          switch (adminChoice) {
             case 1:
                 input.nextLine();
                 if (padippaayvuManagement.loginAdmin())
                  adminSubMenu();
                  break; 
              case 2 :
                   input.nextLine();
                   if(padippaayvuManagement.checkRootAdmin())
                     padippaayvuManagement.addAdmin();
                   break;        
             case 3:
                 return; 
             default:
                 System.err.println(ErrorStatements.INVALID_INPUT_ERROR+ adminChoice);
          }  
      }
   }  
    
   private void studentMenu(){
        byte studentChoice;

        while (true) {
          writer.println("-----------------------------------");
          writer.printf("| %-6s | %-22s |\n", "Option", "Student Menu");
          writer.println("|--------+------------------------|");
          writer.printf("| %-6d | %-22s |\n", 1, "Student Login");
          writer.printf("| %-6d | %-22s |\n", 2, "New Student");
          writer.printf("| %-6d | %-22s |\n", 3, "Back to Main Menu");
          writer.println("-----------------------------------");
          writer.println("Enter Your choice: (Press the Option number)");
          writer.flush();   
          
          if (input.hasNextByte()) {
              studentChoice = input.nextByte(); 
          } 
          else {
             System.err.println(ErrorStatements.INPUT_MISMATCH_ERROR);
             input.next(); 
             continue; 
          }    
          
          switch(studentChoice){
             case 1 :
                  input.nextLine();
                  if(padippaayvuManagement.studentLogin())
                     studentSubMenu();      
                  break;
              case 2:
                  input.nextLine();
                  padippaayvuManagement.studentSignup();
                  break;    
              case 3:
                  return ;    
             default :
                  System.err.println(ErrorStatements.INVALID_INPUT_ERROR+ studentChoice);;
         }                        
      }
  }
  
  private void exitMenu(){
        byte exitChoice;
        
        while(true){
            writer.println("---------------------------------------------");
            writer.printf("| %-6s | %-32s |\n", "Option", "Exit Menu");
            writer.println("|--------+----------------------------------|");
            writer.printf("| %-6d | %-32s |\n", 1, "Are you Sure?(If it, press 1)");
            writer.printf("| %-6d | %-32s |\n", 2, "Stay Here");
            writer.println("---------------------------------------------");
            writer.println("Enter Your choice: (Press the Option number)");
            writer.flush();
            
            if(input.hasNextByte()){
                 exitChoice = input.nextByte();
            }
            else{
                System.err.println(ErrorStatements.INPUT_MISMATCH_ERROR);
                input.next();
                continue;
           }
           
           switch(exitChoice){
                case 1:
                    System.out.println("ThankYou ! Visit Again .. If you are Happy with us Suggest to Your Friends!!..FeedBack to padippaayuv@gmail.com");
                    System.exit(0);
                case 2:
                     return;
                default:
                    System.err.println(ErrorStatements.INVALID_INPUT_ERROR + exitChoice);     
           }
       }
   }
   
   private void adminSubMenu(){
         byte adminChoice;
         while(true){
              writer.println("--------------------------------------------------------");
              writer.printf("| %-6s | %-42s |\n", "Option", "AdminMenu");
              writer.println("|--------+--------------------------------------------|");      
              writer.printf("| %-6s | %-42s |\n", 1,"Create Exam");
              writer.printf("| %-6s | %-42s |\n", 2,"Create Questions");
              writer.printf("| %-6s | %-42s |\n", 3,"BACK");
              writer.println("--------------------------------------------------------");
              writer.println("Enter Your choice: (Press the Option number)");        
              writer.flush();
                            
              if (input.hasNextByte()) {
                 adminChoice = input.nextByte(); 
              } 
              else {
                 System.out.println("Invalid input. Please enter a number.");
                 input.next(); 
                 continue; 
            }                           
            
            switch(adminChoice){
                 case 1 :
                      input.nextLine();
                      padippaayvuManagement.createExam();
                      break;
                 case 2 :
                      input.nextLine();
                      padippaayvuManagement.createQuestions();   
                      break;  
                 case 3 :
                      return;     
                 default :
                      System.out.println("Invalid choice, Please Select Valid choice: " + adminChoice);
            }
         }
    }  
    
    private void studentSubMenu(){
        byte studentChoice;
        
        while(true){
              writer.println("--------------------------------------------------------");
              writer.printf("| %-6s | %-42s |\n", "Option", "StudentMenu");
              writer.println("|--------+--------------------------------------------|");      
              writer.printf("| %-6s | %-42s |\n", 1,"Take Test");   
              writer.printf("| %-6s | %-42s |\n", 2,"Test History");
              writer.printf("| %-6s | %-42s |\n", 3,"Create Questions");
              writer.printf("| %-6s | %-42s |\n", 4,"BACK");
              writer.println("--------------------------------------------------------");
              writer.println("Enter Your choice: (Press the Option number)");        
              writer.flush();      
                            
              if (input.hasNextByte()) {
                 studentChoice = input.nextByte(); 
              } 
              else {
                 System.out.println("Invalid input. Please enter a number.");
                 input.next(); 
                 continue; 
            }
            
            switch(studentChoice){
                case 1:
                    input.nextLine();
                    padippaayvuManagement.startTest();
                    break;
               case 2 :
                    input.nextLine();
                    padippaayvuManagement.viewTestHistory();     
                    break;
               case 4 :
                    return;
                default :
                    System.out.println("Invalid choice, Please Select Valid choice: " + studentChoice);         
            
            }               
                
        }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}     
