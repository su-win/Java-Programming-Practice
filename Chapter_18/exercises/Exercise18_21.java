package exercises;
//Written by Su Win

import java.util.Scanner;

//Write a recursive method that converts a decimal number into 
//a binary number as a string.
public class Exercise18_21 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter decimal number: ");
		int num = input.nextInt();
		
		System.out.println(dec2Bin(num)); //test: 156 return: 10011100
		
		input.close();
	}
	
	public static String dec2Bin(int value) {
		if(value == 0) {
			return "0";
		}
		if(value == 1) {
			return "1";
		}
		return dec2Bin(value/2) + value%2;
	}
}
