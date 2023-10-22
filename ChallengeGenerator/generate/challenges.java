package generate;
//Written by Su Win
//GitHub: https://github.com/su-win

import java.io.*;
import java.util.*;
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class challenges extends Application{

	private Label lblTitle = new Label("Let's Challenge Ourselves!!"),
				lblWish = new Label("Good Luck!");
	private Button btnOneChallenge = new Button("One Challenge"),
					btnThreeChallenges = new Button("Three Challenges"),
					btnFiveChallenges = new Button("Five Challenges"),
					btnExit = new Button("Exit");
	private TextArea taResult = new TextArea();
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane UI = settingStage(primaryStage);
		
		Scene scene = new Scene(UI, 400, 350);
		
		primaryStage.setTitle("Let's Do It!");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
	      launch(args);
	}
	
	
	private BorderPane settingStage(Stage primaryStage) {
		
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 27));
		lblWish.setFont(Font.font("Gerogia", FontWeight.BOLD, 20));
		lblWish.setVisible(false);
		taResult.setPrefSize(200, 125);
		
		VBox buttonHolder = new VBox(20);
		buttonHolder.getChildren().addAll(btnOneChallenge, btnThreeChallenges, btnFiveChallenges);
		
		btnOneChallenge.setMaxWidth(160);
		btnThreeChallenges.setMaxWidth(160);
		btnFiveChallenges.setMaxWidth(160);
		btnExit.setMaxWidth(100);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(20);
		gridPane.setHgap(10);
		
		gridPane.add(lblTitle, 0, 0, 2, 1);
		gridPane.add(new Label(), 0, 0, 2, 1);
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		gridPane.add(buttonHolder, 0, 2);
		gridPane.add(taResult, 1, 2);
		gridPane.add(lblWish, 0, 3, 2, 1);
		GridPane.setHalignment(lblWish, HPos.RIGHT);
		gridPane.add(btnExit, 0, 4, 3, 1);
		GridPane.setHalignment(btnExit, HPos.CENTER);
		
		EventHandler<ActionEvent> buttonHandler = event -> handleButtonAction(event);
		btnOneChallenge.setOnAction(buttonHandler);
		btnThreeChallenges.setOnAction(buttonHandler);
		btnFiveChallenges.setOnAction(buttonHandler);
		
		btnExit.setOnAction(e -> primaryStage.close());
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);
		
		return borderPane;
	}
	
	private ArrayList<String> readFile() throws FileNotFoundException {
			
		Scanner file = new Scanner(new File("exercises.csv"));
		file.useDelimiter(",");
		ArrayList<String> exercises = new ArrayList<>();
		
		while(file.hasNext()) {
			String exercise = file.next().trim();
			if(!exercise.isEmpty()) {
				exercises.add(exercise);
			}
		}
		
		file.close();
		return exercises;
	}
	
	private void handleButtonAction(ActionEvent event) {
		
		int numOfChallenge = 0;
		
		Button btnClick = (Button) event.getSource();
		
		if(btnClick == btnOneChallenge) {
			numOfChallenge = 1;
		}else if(btnClick == btnThreeChallenges){
			numOfChallenge = 3;
		}else if(btnClick == btnFiveChallenges){
			numOfChallenge = 5;
		}
		
		taResult.clear();
		getRandomChallenge(numOfChallenge);
		
	}
	
	private void getRandomChallenge(int numOfChallenge) {
		
		try {
			
			ArrayList<String> exercises =  readFile();
			HashSet<String> result = new HashSet<>();
		
			Random random = new Random();
			
			for(int i = 0; i < numOfChallenge; i++) {
				int randomIndex = random.nextInt(exercises.size());
				String randomItem = exercises.get(randomIndex);
				
				if(result.contains(randomItem)) { //If random item is already in the set, reduce counter
					i--;
				}else {
					result.add(randomItem);
				}
			}
			
			String displayResult = String.join("\n", result);
			taResult.setText(displayResult);
			lblWish.setVisible(true);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
