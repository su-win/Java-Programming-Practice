package exercises;
//Written by Su Win

import java.util.*;

//Find the smallest element
public class Exercise07_09 {

	public static void main(String[] args) {
		double[] nums = new double[10];
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter 10 decimal numbers: ");
		
		for(int i = 0; i <= nums.length-1; i++) {
			nums[i] = input.nextDouble();
		}
		
		input.close();
		
		System.out.println("The minimum numver is " + min(nums));
		
	}
	
	public static double min(double[] array) {
		
		double min = array[0]; //get value from index 0 and set it as current min value
		
		for(int i = 1; i < array.length; i++) {
			if(array[i] < min) { //if value is less than current min value
				min = array[i]; //set it as new current min value
			}
		}
		return min;
	}

}