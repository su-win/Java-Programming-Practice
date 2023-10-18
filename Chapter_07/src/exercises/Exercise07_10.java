package exercises;
//Written by Su Win

import java.util.*;

//Find index of the smallest element
public class Exercise07_10 {

	public static void main(String[] args) {
		
		double[] nums = new double[10];
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter 10 decimal numbers: ");
		
		for(int i = 0; i <= nums.length-1; i++) {
			nums[i] = input.nextDouble();
		}
		
		input.close();
		
		System.out.println("Index of smallest element is " + indexOfSmallestElement(nums));

	}

	//1.9 2.5 3.7 2 1.5 6 3 4 5 2
	public static int indexOfSmallestElement(double[] array) {
		double min = array[0];
		int index = 0;
		for(int i = 1; i < array.length; i++) {
			if(array[i] < min) {
				min = array[i];
				index = i;
			}
		}
		return index;
	}
}
