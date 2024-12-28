package com.zoho.padippaayvu.model;

public class Question{
      
      private short questionNumber;
      private String question;
      private String option1;
      private String option2;
      private String option3;
      private String option4;
      private String answer;
      
      public Question(short questionNumber, String question,String option1, String option2,String option3, String option4,String answer){
           this.questionNumber = questionNumber;
           this.question = question;
           this.option1 = option1;
           this.option2 = option2;
           this.option3 = option3;
           this.option4 = option4;
           this.answer = answer;
      } 
      
      public Question(short questionNumber, String question,String option1, String option2,String option3, String option4){
           this.questionNumber = questionNumber;
           this.question = question;
           this.option1 = option1;
           this.option2 = option2;
           this.option3 = option3;
           this.option4 = option4;
      } 
      
      private Question(){
      
      }
      
      public void setQuestionNumber(short questionNumber){
          this.questionNumber = questionNumber;
      }
      
      public short getQuestionNumber(){
          return questionNumber;
      }
      
      public void setQuestion(String question){
          this.question = question;
      }
      
      public String getQuestion(){
          return question;
      }
      
      public void setOption1(String option1){
          this.option1 = option1;
      }
      
      public String getOption1(){
          return option1;
      }
      
      public void setOption2(String option2){
          this.option2 = option2;
      }
      
      public String getOption2(){
          return option2;
      }
      
      public void setOption3(String option3){
          this.option3 = option3;
      }
      
      public String getOption3(){
          return option3;
      }      
      public void setOption4(String option4){
          this.option4 = option4;
      }
      
      public String getOption4(){
          return option4;
      }            
      
      public void setAnswer(String answer){
          this.answer = answer;
      }
      
      public String getAnswer(){
          return answer;
      }    
      
      public String toString(){
         StringBuilder question = new StringBuilder();
         question.append("--------------------------------------------------------------------------------\n");
         question.append(String.format("| %-20s : %-55s |\n", "QuestionNumber is" , getQuestionNumber()));
         question.append(String.format("| %-20s : %-55s |\n", "Question is" , getQuestion()));
         question.append(String.format("| %-20s : %-55s |\n", "a " , getOption1()));
         question.append(String.format("| %-20s : %-55s |\n", "b " , getOption2()));
         question.append(String.format("| %-20s : %-55s |\n", "c " , getOption3()));
         question.append(String.format("| %-20s : %-55s |\n", "d " , getOption4()));
         question.append("----------------------------------------------------------------------------------\n");
         return question.toString();
      }

}
