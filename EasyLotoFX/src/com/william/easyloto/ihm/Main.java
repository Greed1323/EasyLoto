package com.william.easyloto.ihm;
	
import java.io.IOException;

import com.william.easyloto.ihm.controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("EasyLoto");
		primaryStage.setScene(this.CreateScene(this.LoadMainPane()));
		primaryStage.show();
	}
	
	private Scene CreateScene(GridPane pane)
	{
		Scene scene = new Scene(pane, 800, 500);
		scene.getStylesheets().add(getClass().getResource("views/application.css").toExternalForm());
		
		return scene;
	}
	
	private GridPane LoadMainPane()
	{
		FXMLLoader loader = new FXMLLoader();

		GridPane mainPane;
		try
		{
			mainPane = (GridPane) loader.load(getClass().getResourceAsStream(ViewManager.MAIN));
			MainController mainController = loader.getController();

	        ViewManager.setMainController(mainController);
	        ViewManager.loadView(ViewManager.MENU);

	        return mainPane;
		}
		catch (IOException e)
		{
			System.out.println("View non trouvé");
			e.printStackTrace();
			return null;
		}

        
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
