package application;

import java.sql.SQLException;

import Database.DatabaseManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("BangDia");
			primaryStage.setResizable(false);
	        FXMLLoader login_fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/LoginScene.fxml"));
	        Parent login_root = (Parent) login_fxmlLoader.load();
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();	
			primaryStage.setScene(new Scene(login_root));
	        primaryStage.centerOnScreen();
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DatabaseManager.getInstance().initDatabase();
		launch(args);
	
	}
}
