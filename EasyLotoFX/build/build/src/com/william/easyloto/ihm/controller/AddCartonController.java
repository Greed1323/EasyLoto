package com.william.easyloto.ihm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.william.easyloto.Carton;
import com.william.easyloto.Game;
import com.william.easyloto.ihm.ViewManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddCartonController implements Initializable
{
	@FXML
	private GridPane carton;
	
	@FXML
	private TextField name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	
	@FXML
	public void Build()
	{
		Carton c = new Carton(name.getText());
		
		for(int i = 0; i < carton.getChildren().size(); i++)
		{
			TextField tf = (TextField)carton.getChildren().get(i);
			c.AddNum(Integer.parseInt(tf.getText()), i/5);
		}
		
		Game.getInstance().AddCarton(c);
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
	
	@FXML
	public void Cancel()
	{
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
	
}
