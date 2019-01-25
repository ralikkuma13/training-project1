package application;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application
{
	MediaPlayer mediaPlayer;
	private Label time;
	Duration duration;
	Button fullScreenButton;
	Scene scene;
	Media media;
	double width;
	double height;
	MediaView mediaView;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
//			//音源定義
//			Media media = new Media(new File("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3").toURI().toString());
//			MediaPlayer mediaPlayer = new MediaPlayer(media);
//			mediaPlayer.setAutoPlay(true);
//
//			MediaView mediaView = new MediaView(mediaPlayer);
//
//			BorderPane root = new BorderPane();
//			root.setCenter(mediaView);
//			root.setBottom(addToolBar());

			scene = setScene(this.width, this.height);

			primaryStage.setTitle("Media Player");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Scene setScene(double width, double height)
	{
		this.height = height;
		this.width = width;

		//path to audio file
		String path = "C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3";
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		//wont autoplay at startup
		mediaPlayer.setAutoPlay(false);
		mediaView = new MediaView(mediaPlayer);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mediaView);
		borderPane.setBottom(addToolBar());
		borderPane.setStyle("-fx-background-color: White");

		scene = new Scene(borderPane, 600, 600);
		scene.setFill(Color.BLACK);

		return scene;
	}

	private HBox addToolBar()
	{
		//Node to group other leaf nodes together
		//given certain rules
		HBox toolBar = new HBox();
		toolBar.setPadding(new Insets(20));
		toolBar.setAlignment(Pos.CENTER);
		toolBar.alignmentProperty().isBound();
		toolBar.setSpacing(5);
		toolBar.setStyle("-fx-background-color: White");

		//再生ボタン
		Image playButtonImage = new Image(getClass().getResourceAsStream("play.png"), 32, 32, false, false);
		Button playButton = new Button();
		playButton.setGraphic(new ImageView(playButtonImage));
		playButton.setStyle("-fx-background-color: Black");

		playButton.setOnAction((ActionEvent e) -> {
			mediaPlayer.play();
		} );

		//マウスがボタン上をホバーした際に色変更
		playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
		playButton.setStyle("-fx-background-color: Black");
		playButton.setStyle("-fx-body-color: Black");
		});

		//マウスがボタン上から離れた際に元に戻す
		playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
		playButton.setStyle("-fx-background-color: Black");
		});


		//ストップボタン
		Image stopButtonImage = new Image(getClass().getResourceAsStream("stop.png"), 32, 32, false, false);
		Button stopButton = new Button();
		stopButton.setGraphic(new ImageView(stopButtonImage));
		stopButton.setStyle("-fx-background-color: Black");

		stopButton.setOnAction((ActionEvent e) -> {} );

		//マウスがボタン上をホバーした際に色変更
		stopButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
		stopButton.setStyle("-fx-background-color: Black");
		stopButton.setStyle("-fx-body-color: Black");
		});

		//マウスがボタン上から離れた際に元に戻す
		stopButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
		stopButton.setStyle("-fx-background-color: Black");
		});


		//一停止ボタン
		Image pauseButtonImage = new Image(getClass().getResourceAsStream("pause.png"), 32, 32, false, false);
		Button pauseButton = new Button();
		pauseButton.setGraphic(new ImageView(pauseButtonImage));
		pauseButton.setStyle("-fx-background-color: Black");

		pauseButton.setOnAction((ActionEvent e) -> {
			mediaPlayer.pause();
		} );

		//マウスがボタン上をホバーした際に色変更
		pauseButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
		pauseButton.setStyle("-fx-background-color: Black");
		pauseButton.setStyle("-fx-body-color: Black");
		});

		//マウスがボタン上から離れた際に元に戻す
		pauseButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
		pauseButton.setStyle("-fx-background-color: Black");
		});

		toolBar.getChildren().addAll(playButton, pauseButton, stopButton);

		return toolBar;
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
