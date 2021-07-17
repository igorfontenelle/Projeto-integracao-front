package main.java;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.controller.ControllerApi;
import main.java.controller.RootLayoutController;

public class MainApp extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Projeto de integração");
		
		initMainView();
	}

	private void initMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();
			
			Scene scene = new Scene(mainView);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			@SuppressWarnings("unused")
			RootLayoutController controller = loader.getController();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
		ControllerApi apiController = new ControllerApi();
		apiController.apiGetListMeasurers();
		System.exit(0);
	}
}