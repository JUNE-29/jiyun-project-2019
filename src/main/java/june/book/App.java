package june.book;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        final int SIZE = 100;
        int[] no = new int [SIZE];
        String[] title = new String[SIZE];
        String[] author = new String[SIZE];
        String[] publisher = new String[SIZE];
        String[] categories = new String[SIZE];
        String[] publishedDate = new String[SIZE];
        
        int count = 0;
        
        for (int i = 0; i < SIZE; i++) {
          count++;
          
          System.out.print("번호? ");
          no[i] = keyboard.nextInt();
          
          keyboard.nextLine();
          
          System.out.print("도서명? ");
          title[i] = keyboard.nextLine();
          
          System.out.print("지은이? ");
          author[i] = keyboard.nextLine();
          
          System.out.print("출판사? ");
          publisher[i] = keyboard.nextLine();
          
          System.out.print("카테고리? ");
          categories[i] = keyboard.nextLine();
          
          System.out.print("출판 년도? ");
          publishedDate[i] = keyboard.nextLine();
          
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
              no[i], title[i], author[i], publisher[i], categories[i], publishedDate[i]);
        }
        keyboard.close();
      }
    }
