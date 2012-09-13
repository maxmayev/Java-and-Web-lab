package binaryCounter;

import java.util.Scanner;

/**
 * @author Stanislav Chetvertkov
 *         Date: 27.06.12
 *         Time: 22:03
 */
public class Main {
    public static void main(String[] args){

        System.out.println("enter the number");
        Scanner in = new Scanner(System.in);
        int number=in.nextInt();
        in.close();

        //первый способ
        String str = Integer.toBinaryString(number);
        System.out.println(str);
        int counter = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='1'){
                counter++;
            }
        }

        System.out.println(counter);

        //второй способ
        int secondCounter = 0;
        while(number>0){
            if (number%2==1){
                secondCounter++;
            }
            number = number/2;
        }

        System.out.println(secondCounter);

    }
}
