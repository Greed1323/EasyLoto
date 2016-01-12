package com.william.easyloto.ihm.controller;

import com.william.easyloto.ihm.ViewManager;

import javafx.fxml.FXML;

public class MenuController
{
	@FXML
	public void goShowCarton()
	{
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
	
	@FXML
	public void goRun()
	{
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
	
	@FXML
	public void Quit()
	{
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
}
