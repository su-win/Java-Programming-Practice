package exercises;
//Written by Su Win

import java.util.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;

public class Exercise34_11 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		for (int i = 1; i <= 10; i++) {
			
			try {
				//format i as a string with 2 digits and pads with leading zero if necessary to able to read all files in the loop
				URL url = new URL("https://liveexample.pearsoncmg.com/data/babynamesranking20" + String.format("%02d", i) + ".txt");
				Scanner read = new Scanner(url.openStream());
				
				String fileName = Paths.get(url.getPath()).getFileName().toString(); //get the file name
				
				int year = Integer.parseInt(fileName.substring(16, 20)); //take the year from the file name
				
				while(read.hasNextLine()) {
					while(read.hasNext()) {
						read.next(); //skip the rank
						String boyName = read.next();
						int countBoy = read.nextInt();
						String girlName = read.next();
						int countGirl = read.nextInt();
						StoreBabyNames(year, boyName, 'M', countBoy);
						StoreBabyNames(year, girlName, 'F', countGirl);
					}
				}
				
				read.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Function to insert babay names from file
	private static void StoreBabyNames(int year, String name, char gender, int count) {
			
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "su", "database");
			
			CallableStatement cstmt = connect.prepareCall("{call InsertBabyNames(?, ?, ?, ?)}"); //year, name, gender, count
			
			cstmt.setInt(1, year);
			cstmt.setString(2, name);
			cstmt.setString(3, String.valueOf(gender));
			cstmt.setInt(4, count);
			cstmt.execute();
			
			cstmt.close();
			connect.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


/* Change these if you want to read from file instead of reading from URL
 * 		File file = new File("resources/babynamesranking20" + String.format("%02d", i) + ".txt");
 * 		String fileName = file.getName(); //get the file name
 */
