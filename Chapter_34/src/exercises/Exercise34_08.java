package exercises;
//Written by Su Win

import java.util.*;
import java.io.*;
import java.sql.*;

public class Exercise34_08 {
	
	public static void main(String[] args) {
		
		File file = new File("resources/Salary.txt");
		readFile(file);
		
	}
	
	//read file using scanner
	private static void readFile(File file) {
		
		try {
			Scanner read = new Scanner(file);
			
			while(read.hasNextLine()) {
				if(read.nextLine().isEmpty()){
					continue;
				}else {
					while(read.hasNext()) {
						String fname = read.next();
						String lname = read.next();
						String title = read.next();
						float salary = read.nextFloat();
						
						insertSalaryData(fname, lname, title, salary);
					}
				}
			}
			read.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}
	
	//Function to insert data into database
	private static void insertSalaryData(String fname, String lname, String title, float salary) {
		System.out.println(salary);
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "su", "database"); //connect to local database
			
			CallableStatement cstmt = connect.prepareCall("{Call InsertSalary(?, ?, ?, ?)}"); //fname, lname, rank, salary
			cstmt.setString(1, fname);
			cstmt.setString(2, lname);
			cstmt.setString(3, title);
			cstmt.setFloat(4, salary);
			cstmt.execute();
			
			cstmt.close();
			connect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
