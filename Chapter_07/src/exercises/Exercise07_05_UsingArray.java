package exercises;
//Written by Su Win

import java.util.*;

//reads in 10 numbers and displays the number of distinct numbers and 
//the distinct numbers in their input order and separated by exactly one space
public class Exercise07_05_UsingArray {

	public static void main(String[] args) {
		
		//1 2 3 2 1 6 3 4 5 2
		System.out.print("Enter 10 numbers: ");
		
		Scanner input = new Scanner(System.in);
		int[] nums = new int[10];
		int counter = 0;
		
		for(int i = 0; i < 10; i++) {
			int n = input.nextInt();
			boolean distinct = true;
			
			//check whether the num is already in the array or not
			for(int j = 0; j < counter; j++) {
				//if number is already in the array, 
				//it's not distinct anymore and distinct turns to false. And break the loop
				if(nums[j] == n) { 
					distinct = false;
					break;
				}
			}
			
			if(distinct) {
				nums[counter] = n;
				counter++;
			}

		}
		
		input.close();
		
		System.out.println("The number of distinct numbers is " + counter);
		System.out.print("The distinct numbers are: ");
		for(int num: nums) {
			System.out.print(num + " ");
		}

	}

}
