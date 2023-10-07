package exercises;
//Written by Su Win

import java.io.*;
import java.util.*;
import java.sql.*;

public class Exercise34_07 {

	public static void main(String[] args) {
		
		try {
			Scanner read = new Scanner(new File ("resources/Quiz.txt"));
			
			Map<String, String> data = new HashMap<>();
			
			while(read.hasNextLine()) {
				String line = read.nextLine().trim();
				String[] parts = line.split("[.:]");

				
				if(parts.length == 2) {
					
					if(parts[0].matches("\\d+")) {
						if(!data.isEmpty()) {
							insertData(data);
							data.clear();
						}
						data.put("questionId",parts[0].trim());
						data.put("question", parts[1].trim());
					}else if(parts[0].matches("a")) {
						data.put("choiceA", parts[1].trim());
					}else if(parts[0].matches("b")){
						data.put("choiceB", parts[1].trim());
					}else if(parts[0].matches("c")){
						data.put("choiceC", parts[1].trim());
					}else if(parts[0].matches("d")){
						data.put("choiceD", parts[1].trim());
					}else if(parts[0].matches("Answer")){
						data.put("answer", parts[1]);
					}
				}else{
					continue;	
				}
			}
			
			insertData(data);
			
			read.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File does not exist");
		}
		
	}

	private static Connection initializeDB() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/javabook", "su", "database");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void insertData(Map<String, String> data) {
		
		Connection connect = initializeDB();
		
		try {
			
			String query = "Insert into Quiz (questionId, question, choicea, choiceb, choicec, choiced, answer) "
					+ "Values(?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = connect.prepareStatement(query);
		
			pstmt.setInt(1, Integer.parseInt(data.get("questionId")));
			pstmt.setString(2, data.get("question"));
			pstmt.setString(3, data.get("choiceA"));
			pstmt.setString(4, data.get("choiceB"));
			pstmt.setString(5, data.get("choiceC"));
			pstmt.setString(6, data.get("choiceD"));
			pstmt.setString(7, data.get("answer"));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}