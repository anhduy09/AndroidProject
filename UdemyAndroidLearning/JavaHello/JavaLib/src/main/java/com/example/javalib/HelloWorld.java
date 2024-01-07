package com.example.javalib;

import com.sun.tools.jdeprscan.scan.Scan;

import org.graalvm.compiler.phases.graph.ScheduledNodeIterator;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class HelloWorld {
    public static void main(String[] args)
    {
        System.out.println("Hello my friend");
        //ex1
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter your first number ");
//        int a = in.nextInt();
//        System.out.println("Enter your second number: ");
//        int b = in.nextInt();
//
//        float result1 = (float) a/b;
//        float result2 = a%b;
//        System.out.println("result of ex1 is "+result1);
//        System.out.println("remainder of ex1 is "+result2);

        // ex2: write a program that gets from user the radius and print the area and perimeter of circle
//        Scanner ex2 = new Scanner(System.in);
//        System.out.println("Enter radius: ");
//        double r = ex2.nextDouble();
//        final double PI = 3.14;
//        System.out.println("The perimeter area of a circle is "+2*r*PI);
//        System.out.println("The area of a circle is "+r*r*PI);

        // ex3: write a program to convert decimal number into binary number
//        Scanner ex3 = new Scanner(System.in);
//        System.out.println("Enter your num you wana convert ");
//        int decimal = ex3.nextInt();
//        System.out.println("ex3 is "+dec2Bin(decimal));

//        // ex4: write a program to count the letters, spaces, numbers and other characters of an input String
//        Scanner ex4 = new Scanner(System.in);
//        System.out.println("Enter your String ");
//        String str = ex4.nextLine();
//        countString(str);

        // ex5 write a program to reverse  string
//        reverseString();

        //ex6 multiply arrays
//        multiplyArrays();

        //ex7 write a program that counts number of odd and even number of an array
//        countOddEvenOfArray();

        //ex8
//        pyramid();
        //ex9
//        add2Matrices();
        //ex10
        averageOfArray();

    }

    // this method for ex3
    public static String dec2Bin(int num) {
        String result[]=new String[100];
        int index = 0;
        if(num <= 0 )
        {
            result[index] = "0";
        } else {
            while(num >=1)
            {
                if(num%2 != 0)
                {
                    result[index] ="1";
                } else {
                    result[index] = "0";
                }
                num = num /2;
                index++;
            }
//            result += "0";
        }
        String res ="";
        for (int i = index-1; i >=0; --i) {
            res += result[i];
        }
        return res;
    }

    //for ex4
    public static void countString(String str) {
        char[] ch = str.toCharArray();
        int letter = 0, space = 0, num = 0, others = 0;
        for(int i=0; i < ch.length; i++)
        {
            if(Character.isLetter(ch[i])) {
                letter++;
            } else if(Character.isDigit(ch[i])) {
                num++;
            } else if (Character.isSpaceChar(ch[i])) {
                space++;
            } else {
                others++;
            }
        }
        System.out.println("letter = "+letter);
        System.out.println("num = "+num);
        System.out.println("space = "+space);
        System.out.println("others = "+others);
    }

    //ex5
    public static void reverseString() {
        Scanner ex5 = new Scanner(System.in);
        System.out.println("Enter your String: ");
        String str = ex5.nextLine();
        String res = "";
        char ch[] = str.toCharArray();

        for (int i = ch.length - 1; i >= 0; --i) {
            res += ch[i];
        }

        System.out.println("result of ex5 is "+res);
    }

    // ex6: write a program to multiply corresponding elements of two arrays of integer
    public static void multiplyArrays() {
        Scanner ex6 = new Scanner(System.in);
        System.out.println("Enter number of element of array ");
        int num = ex6.nextInt();
        int arr1[] = new int[num];
        int arr2[] = new int[num];
        System.out.println("Enter all elements of 1st arrays: ");
        for (int i=0; i < num; ++i)
        {
            arr1[i] = ex6.nextInt();
        }
        System.out.println("Enter all elements of 2nd arrays: ");
        for (int i=0; i < num; ++i) {
            arr2[i] = ex6.nextInt();
        }
        System.out.println(" Multiply those arrays: ");
        for(int i=0; i < num; ++i) {
            System.out.println(arr1[i]*arr2[i]+" ");
        }
    }

    //ex7:
    public static void countOddEvenOfArray(){
        System.out.println("Enter your number of element in array: ");
        Scanner ex7 = new Scanner(System.in);
        int num = ex7.nextInt();
        int arr[] = new int[num];
        System.out.println("Enter your element: ");
        for (int i = 0; i < num; ++i) {
            arr[i] = ex7.nextInt();
        }
        int numOdd = 0, numEven = 0;
        for (int i=0; i < num; ++i) {
            if (arr[i]%2 == 0) {
                numOdd++;
            } else {
                numEven++;
            }
        }
        System.out.println("odd number of array = "+numOdd+" even number of array = "+numEven);
    }

    //ex8 write a program to make such a pattern like a pyramid
    // with a number which will repeat at the same row
    public static void pyramid() {
        System.out.println("Enter your number: ");
        Scanner ex8 = new Scanner(System.in);
        int num = ex8.nextInt();
        int temp = num%2==0 ? (num+num-1)/2+1 : (num+num-1)/2;

        for (int i = 1; i <= num; ++i) {
            for (int k = 0; k < temp; ++k) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; ++j){

                System.out.print(i+" ");

            }
            temp--;
            System.out.println("\n");
        }
    }

    //ex9 add two matrices at the same size
    public static void add2Matrices() {
        System.out.println("Enter size of matrix: ");
        Scanner ex9 = new Scanner(System.in);
        int size = ex9.nextInt();
        int mat1[][] = new int[size][size];
        int mat2[][] = new int[size][size];

        System.out.println("Enter your first matrix");
        for (int i = 0; i < size; ++i){
            System.out.println(" row "+i);
            for(int j = 0; j < size; ++j) {
                System.out.println("column "+j);
                mat1[i][j] = ex9.nextInt();
            }
        }

        System.out.println("Enter your second matrix");
        for (int i = 0; i < size; ++i){
            System.out.println(" row "+i);
            for(int j = 0; j < size; ++j) {
                System.out.println("column "+j);
                mat2[i][j] = ex9.nextInt();
            }
        }

        System.out.println("the result of adding 2 matrices");
        for (int i = 0; i < size; ++i){

            for(int j = 0; j < size; ++j) {
                System.out.print(mat1[i][j]+mat2[i][j] + " ");

            }
            System.out.println("\n");
        }
    }

    //ex10: write a program to calculate the average value of array elements
    public static void averageOfArray()
    {
        System.out.println("Enter number of element: ");
        Scanner ex10 = new Scanner(System.in);
        int num = ex10.nextInt();
        int arr[] = new int[num];
        System.out.println("Enter your element: ");
        for (int i = 0; i < num; ++i) {
            arr[i] = ex10.nextInt();
        }
        double average=0.0;
        {
            for(int i:arr) {
                average+=i;
            }
            average /=num;
        }

        System.out.println(" average of array is: "+average);
    }

}