package com.zoho.padippaayvu.supportfiles;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public class PadippaayvuMenu{
     
      private static PadippaayvuMenu padippaayvuMenu;

      private PadippaayvuMenu(){
      
      }
      
      public static PadippaayvuMenu getPadippaayvuMenuInstance(){
            if(padippaayvuMenu==null)
                padippaayvuMenu = new PadippaayvuMenu();
            return padippaayvuMenu;
      }
      
    public void noteMenu(){
           readFromFile("/home/zs-gsch24/zoho/com.zoho.padippaayvu/supportfiles/padippaayvu.txt");      
    }
    
    private void readFromFile(String FileName){
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            
            try{
                fileInputStream = new FileInputStream(FileName);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                
                int line = 0;
                while((line=bufferedInputStream.read())!=-1){
                     char characterAtLine = (char)line;
                     System.out.print(characterAtLine);
                }
          }
          catch(IOException ioException){
                System.err.println("The Content cannot be open at that time !, Sorry for the Inconvenience");
          }
          finally{
                try{
                   if (bufferedInputStream != null) {
                       bufferedInputStream.close();
                  }
               }
               catch(IOException ioException){
                   ioException.getMessage();
             }   
        }     
    }

}
