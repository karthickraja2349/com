package com.zoho.aedagam.model;

public class Book{

        private short bookId;
        private String bookName;
        private String authorName;
        private String ISBNNumber;
        private String genre;
        
        public Book(short bookId , String bookName, String authorName, String ISBNNumber, String genre){
              this.bookId = bookId;
              this.bookName = bookName;
              this.authorName = authorName;
              this.ISBNNumber = ISBNNumber;
              this.genre = genre;
        }
        
        public void setBookId(short bookId){
             this.bookId = bookId;
        }
        
        public short getBookId(){
             return bookId;
        }           
        
        public void setBookName(String bookName){
            this.bookName = bookName;
        }
        
        public String getBookName(){
            return bookName;
        }
        
        public void setAuthorName(String authorName){
            this.authorName = authorName;
        }
        
        public String getAuthorName(){
            return authorName;
        }
        
        public void setISBNNumber(String ISBNNumber){
            this.ISBNNumber = ISBNNumber;
        }
        
        public String getISBNNumber(){
            return ISBNNumber;
        }
        
        public void setGenre(String genre){
            this.genre = genre;
        }
        
        public String getGenre(){
            return genre;
        }
        
        public String toString(){
            StringBuilder book = new StringBuilder();
            book.append("-------------------------------------------\n");
            book.append(String.format("| %-16s : %-20s |\n", "Book  Id is", getBookId()));
            book.append(String.format("| %-16s : %-20s |\n", "Book Name is", getBookName()));
            book.append(String.format("| %-16s : %-20s |\n", "Author Name is", getAuthorName()));
            book.append(String.format("| %-16s : %-20s |\n", "ISBN Number is", getISBNNumber()));
            book.append(String.format("| %-16s : %-20s |\n", "Book Genre is", getGenre()));
            book.append("-------------------------------------------\n");            
            return book.toString();
        }
}
