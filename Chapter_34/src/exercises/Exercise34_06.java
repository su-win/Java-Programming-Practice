package exercises;
//Written by Su Win

import java.sql.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Exercise34_06 extends Application{

	private ComboBox<String> comboSelectTable = new ComboBox<>();
	private Button btnShow = new Button("Show Contents");
	private Button btnExit = new Button("Exit");
	private TextArea taResult = new TextArea();
	
	ArrayList<String> tableNames = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		getTableName(tableNames); //get table names from the database
		comboSelectTable.getItems().addAll(tableNames); //add all those tables names to the combo box
		comboSelectTable.getSelectionModel().select(0);;
		
		btnShow.setOnAction(e -> {showTableContents();});
		btnExit.setOnAction(e -> primaryStage.close());
		
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(new Label("Select Table Name: "), 0, 0);
		gridPane.add(comboSelectTable, 1, 0);
		gridPane.add(btnShow, 2, 0);
		gridPane.add(btnExit, 3, 0);
		gridPane.add(taResult, 0, 2, 4, 1);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);
		
		Scene scene = new Scene(borderPane, 600, 300);
		
		primaryStage.setTitle("Exercise34_06");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private void getTableName(ArrayList<String> tblNames) {
		try {
			Connection connect = initializeDB();
			DatabaseMetaData metaData = connect.getMetaData();
			ResultSet rsTables = metaData.getTables(connect.getCatalog(), null, null, new String[] {"TABLE"});
			
			while(rsTables.next()) { //add all table names from the database to array list
				tblNames.add(rsTables.getString("TABLE_NAME"));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			String tableName = comboSelectTable.getValue();
			
			taResult.clear();
			
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
			
			connect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
