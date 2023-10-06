package exercises;
//Written by: Su Win

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.sql.*;

public class Exercise34_05 extends Application{

	private TextField tfTableName = new TextField();
	private Button btnShow = new Button("Show Contents");
	private TextArea taResult = new TextArea();
	private Label lblStatus = new Label();
	
	@Override
	public void start(Stage primaryStage) {
		
		initializeDB();
		
		lblStatus.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC,18));
		btnShow.setOnAction(e -> {showTableContents();});
		
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(new Label("Enter Table Name: "), 0, 0);
		gridPane.add(tfTableName, 1, 0);
		gridPane.add(btnShow, 2, 0);
		gridPane.add(lblStatus, 0, 1, 3, 1);
		gridPane.add(taResult, 0, 2, 3, 1);
		
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);
		
		Scene scene = new Scene(borderPane, 600, 300);
		
		primaryStage.setTitle("Exercise34_06");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Connection initializeDB() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/javabook","su","database");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void showTableContents() {
		
		try {
			Connection connect = initializeDB();
			String databaseName = connect.getCatalog(); //get the database name
			String tableName = tfTableName.getText();
			
			
			DatabaseMetaData metaData = connect.getMetaData();
			ResultSet rsTable = metaData.getTables(databaseName, null, tableName, null);
			
			if(rsTable.next()) {//if table exists in the database retrieve data from that table
				Statement stmt = connect.createStatement();
				ResultSet resultSet = stmt.executeQuery("Select * From " + tableName);
				
				ResultSetMetaData rsMetaData = resultSet.getMetaData();
				
				//Display Column Names in TextArea called taResult
				for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					taResult.appendText(rsMetaData.getColumnName(i)+"\t");
					
				}
				taResult.appendText("\n");
				
				//display data from table
				while(resultSet.next()) {
					for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
						taResult.appendText(resultSet.getObject(i)+"\t");
					}
					taResult.appendText("\n");
				}
			}else {
				lblStatus.setText("'" + tableName + "' table does not exist in the database.");
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
