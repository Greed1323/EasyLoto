package com.william.easyloto.ihm;

import java.io.IOException;

import com.william.easyloto.ihm.controller.MainController;

import javafx.fxml.FXMLLoader;

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
			controller.setVista(FXMLLoader.load(ViewManager.class.getResource(view)));
        }
		catch(IOException e)
		{
            e.printStackTrace();
        }
    }
}














