package june.book;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {
      
      Scanner keyboard = new Scanner(System.in);
      
      final int SIZE = 100;
      
      int[] no = new int[SIZE];
      String[] bookTitle = new String[SIZE];
      String[] title = new String[SIZE];
      String[] contents = new String[SIZE];
      String[] photo = new String[SIZE];
      Date[] date = new Date[SIZE];
      float[] score = new float[SIZE];
      int[] viewCount = new int[SIZE];
      String response;
      
      int count = 0;
      for (int i = 0; i < SIZE; i++) {
        System.out.print("번호? ");
        no[i] = keyboard.nextInt();
        keyboard.nextLine();
        
        System.out.print("도서명? ");
        bookTitle[i] = keyboard.nextLine();
        
        System.out.print("게시판 제목? ");
        title[i] = keyboard.nextLine();
        
        System.out.print("내용? ");
        contents[i] = keyboard.nextLine();
        
        System.out.print("이미지? ");
        photo[i] = keyboard.nextLine();
        
        System.out.print("책에 대한 점수(5.0점만점)? ");
        score[i] =keyboard.nextFloat();
        keyboard.nextLine();
        
        date[i] = new Date(System.currentTimeMillis());
        viewCount[i] = 0;
        
        count++;
        
        System.out.println();
        
        System.out.print("계속 입력하시겠습니까?(Y/n) ");
        response = keyboard.nextLine();
        if(!response.equalsIgnoreCase("y")) {
          break;
        }
      }
      keyboard.close();
      
      System.out.println();
      
      for(int i = 0; i < count; i++) {
        System.out.printf("%d, %s, %s, %1.1f점 / 5점, %s, %d\n",
            no[i], bookTitle[i], title[i], score[i], date[i], viewCount[i]);
      }
      
     }
   }
