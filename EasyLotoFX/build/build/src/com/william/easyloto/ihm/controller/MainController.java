package com.william.easyloto.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class MainController
{
	@FXML
	private GridPane vistaHolder;
	
	public void setVista(Node node)
	{
		this.vistaHolder.getChildren().setAll(node);
	}
}
