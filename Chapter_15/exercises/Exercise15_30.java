package exercises;
//Written by Su Win

import javafx.application.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.animation.*;
import javafx.event.*;

//Slide Show
/* Slides are displayed in order and automatically displays them repeatedly
 * Each slide is shown for 2 seconds
 * When the last slide finishes, the first slide is redisplayed.
 * Click to pause if the animation is currently playing.
 * Click to resume if the animation is currently paused.
 */
public class Exercise15_30 extends Application{
	
	private ImageView slideView = new ImageView();
	private int slideIndex = 0, totalImages = 24;
	
	@Override
	public void start(Stage primaryStage) {

		StackPane stackPane = new StackPane();

		//event handler for changing slides
		EventHandler<ActionEvent> eventHandler = e ->{
				slideView.setImage((new Image("resources/slide"+slideIndex+".jpg")));
				stackPane.getChildren().clear();
				stackPane.getChildren().add(slideView);
				slideIndex = (slideIndex+1) % totalImages;
		};
		
		//animation for altering slides
		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(2), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE); //animation set to run indefinitely
		animation.play(); //start playing animation
		
		//When the mouse click, get the status of the animation if animation is pause then resume the animation.
		//If the status of the animation is running, pause the animation.
		slideView.setOnMouseClicked(e -> {
			if(animation.getStatus() == Animation.Status.PAUSED) { 
				animation.play();
			}else {
				animation.pause();
			}
		});
		
		Scene scene = new Scene(stackPane, 800, 600);
		primaryStage.setTitle("Slide Show");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
