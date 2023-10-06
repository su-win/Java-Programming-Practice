package exercises;
//Written by Su Win

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.sql.*;

public class Exercise34_04 extends Application{

	private TextField tfSSN = new TextField();
	private Button btnShow = new Button("Show Grade");
	private TextArea taResult = new TextArea();
	private Label lblStatus = new Label();
	
	@Override
	public void start(Stage primaryStage) {
		
		initializeDB();
		
		lblStatus.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC,18));
		btnShow.setOnAction(e -> {showGrade();});
		
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(new Label("Enter Student's SSN: "), 0, 0);
		gridPane.add(tfSSN, 1, 0);
		gridPane.add(btnShow, 2, 0);
		gridPane.add(lblStatus, 0, 1, 3, 1);
		gridPane.add(taResult, 0, 2, 3, 1);
	
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);
		
		Scene scene = new Scene(borderPane, 600, 300);
		
		primaryStage.setTitle("Exercise34_04");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Connection initializeDB() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/javabook", "su", "database");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void showGrade() {
		
		String ssn = tfSSN.getText();
		int count = 0;
		
		Connection connect = initializeDB();
		
		try {
			CallableStatement cstmt = connect.prepareCall("{call GetGrade(?)}");
			cstmt.setString(1, ssn); //input parameter
			cstmt.execute(); //execute statement
			ResultSet rs = cstmt.getResultSet(); //retrieve result set
			
			taResult.clear();
			lblStatus.setText("");
			
			if(rs.next()) {
				do {
					String studentGrade = rs.getString(1);
					taResult.appendText(studentGrade + "\n");
					count++;
				}while(rs.next());
				lblStatus.setText(count + " courses found");	
			}else {
				lblStatus.setText("No Courses Found for this SSN.");
			}
			
			connect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
