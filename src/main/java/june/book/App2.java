package june.book;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        final int SIZE = 100;
        int[] no = new int [SIZE];
        String[] categories = new String[SIZE];
        String[] language = new String[SIZE];
        String[] age = new String[SIZE];
        String[] character = new String[SIZE];
        String[] keyword = new String[SIZE];
        Date[] date = new Date[SIZE];
        
        int count = 0;
        
        for (int i = 0; i < SIZE; i++) {
          count++;
          
          System.out.print("번호? ");
          no[i] = keyboard.nextInt();
          
          keyboard.nextLine();
          
          System.out.print("카테고리? ");
          categories[i] = keyboard.nextLine();
          
          System.out.print("언어? ");
          language[i] = keyboard.nextLine();
          
          System.out.print("나이? ");
          age[i] = keyboard.nextLine();
          
          System.out.print("MBTI(성격)? ");
          character[i] = keyboard.nextLine();
          
          System.out.print("키워드(해시태그)? ");
          keyword[i] = keyboard.nextLine();
          
          date[i] = new Date(System.currentTimeMillis());
          
          System.out.println();
          
          
          System.out.print("계속 입력하시겠습니까?(Y/n) ");
          String response = keyboard.nextLine();
          if (!response.equalsIgnoreCase("y")) {
            break;
          } 
        }
        
        System.out.println();
        
        for(int i = 0; i < count; i++) {
          System.out.printf("%d, %s, %s, %s, %s, %s\n",
              no[i], categories[i], age[i], character[i], keyword[i], date[i]);
        }
        keyboard.close();
      }
    }
