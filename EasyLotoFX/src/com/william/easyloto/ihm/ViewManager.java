package com.william.easyloto.ihm;

import java.io.IOException;
import com.william.easyloto.ihm.controller.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class ViewManager
{
	private static MainController controller;
	
	public final static String MAIN = "views/Main.fxml";
	public final static String MENU = "views/Menu.fxml";
	public final static String SHOWCARTON = "views/ShowCarton.fxml";
	public final static String ADDCARTON = "views/AddCarton.fxml";
	public final static String RUNGAME = "views/RunGame.fxml";
	
	public static void setMainController(MainController controller)
	{
		ViewManager.controller = controller;
	}
	
	public static void loadView(String view)
	{
		try {
			controller.setVista(FXMLLoader.load(Main.class.getResource(view)));
        }
		catch(IOException e)
		{
			System.out.println("Not find");
            e.printStackTrace();
        }
    }
	
	public static GridPane loadPane(String path)
	{
		GridPane grid = new GridPane();
		try
		{
			grid = (GridPane) FXMLLoader.load(Main.class.getResource(path));
			return grid;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}














