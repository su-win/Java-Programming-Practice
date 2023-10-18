package exercises;
//Written by Su Win

import java.util.*;

public class Exercise07_08 {

	public static void main(String[] args) {
		
		int[] intNums = new int[10];

		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter 10 integers: ");

		for(int i = 0; i < intNums.length; i++) {
			intNums[i] = input.nextInt();
		}
		
		//display average value from 10 integers
		System.out.println("Average value from 10 integers: " + average(intNums));
		
		System.out.println();
		
		double[] doubleNums = new double[10];
		System.out.print("Enter 10 double values: ");
		
		for(int j = 0; j < doubleNums.length; j++) {
			doubleNums[j] = input.nextDouble();
		}
		
		input.close();
		
		//display average value from 10 double
		System.out.println("Average value from 10 double: " + average(doubleNums));
	}
	
	//Test: 1 2 3 2 1 6 3 4 5 2 => 2.9
	public static double average(int[] array) {
		
		double sum = 0;
		
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		return sum/array.length;
	}
	
	//Test: 1.84 3.59 4.37 7.08 5.79 3.88 2.84 8.72 1.39 6.88 => 4.638
	public static double average(double[] array) {
		
		double sum = 0;
		
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		return sum/array.length;
	}

}
