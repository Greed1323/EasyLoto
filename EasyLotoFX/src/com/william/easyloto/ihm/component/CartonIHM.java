package com.william.easyloto.ihm.component;

import java.io.IOException;
import java.util.List;

import com.william.easyloto.Carton;
import com.william.easyloto.ICarton;
import com.william.easyloto.Numero;
import com.william.easyloto.utils.Pair;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CartonIHM implements ICarton
{
	private GridPane box;
	private Carton carton;
	
	public CartonIHM(Carton carton)
	{
		this.carton = carton;
		Init();
	}
	
	public void Init()
	{
		FXMLLoader loader = new FXMLLoader();
		
		try
		{
			this.box = (GridPane) loader.load(getClass().getResourceAsStream("../views/Carton.fxml"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < box.getChildren().size(); i++)
		{
			if(this.box.getChildren().get(i) instanceof Label && this.box.getChildren().get(i).getId() != null && this.box.getChildren().get(i).getId().equals("name"))
			{
				Label tf = (Label)this.box.getChildren().get(i);
				tf.setText(this.carton.getName());
				this.box.getChildren().set(i, tf);
			}
		}
		
		for(int i = 0; i < this.carton.getNumeros().size(); i++)
			for(int j = 0; j < this.carton.getNumeros().get(i).size(); j++)
				this.setLabel(j, i+1, String.valueOf(this.carton.getNumeros().get(i).get(j).getNum()));
	}
	
	public Carton getCarton()
	{
		return this.carton;
	}
	
	private Node getNode(int col, int row, Class<?> clazz)
	{
		for(int i = 0; i < this.box.getChildren().size(); i++)
		{
			if(this.box.getChildren().get(i).getClass().equals(clazz))
				System.out.println(GridPane.getColumnIndex(this.box.getChildren().get(i)) + " " + GridPane.getRowIndex(this.box.getChildren().get(i)));
			
			if(this.box.getChildren().get(i).getClass().equals(clazz) && GridPane.getColumnIndex(this.box.getChildren().get(i)) == col 
					&& GridPane.getRowIndex(this.box.getChildren().get(i)) == row)
			{
				return this.box.getChildren().get(i);
			}
		}
		return null;
	}
	
	private void setLabel(int col, int row, String text)
	{
		for(int i = 0; i < box.getChildren().size(); i++)
		{
			if(this.box.getChildren().get(i) instanceof Label 
					&& GridPane.getColumnIndex(this.box.getChildren().get(i)) == col 
					&& GridPane.getRowIndex(this.box.getChildren().get(i)) == row)
			{
				Label tf = (Label)this.box.getChildren().get(i);
				tf.setText(text);
				this.box.getChildren().set(i, tf);
			}
		}
	}
	
	@Override
	public void AddNum(int num, int line)
	{
		this.carton.AddNum(num, line);
		this.setLabel(this.carton.getNumeros().get(line).size(), line, String.valueOf(num));
	}

	@Override
	public void UncheckNum(int num)
	{
		this.carton.UncheckNum(num);
		Pair<Integer, Integer> pos = this.carton.indexOfNumber(this.carton.searchNum(num));
		
		if(pos != null)
			this.getNode(pos.getB(), pos.getA()+1, Label.class).setStyle("-fx-background-color: green; -fx-font-weight: bold;");
	}

	@Override
	public String getName()
	{
		return this.carton.getName();
	}
	
	@Override
	public void CheckNum(int num)
	{
		this.carton.CheckNum(num);
		Pair<Integer, Integer> pos = this.carton.indexOfNumber(this.carton.searchNum(num));
		System.out.println(pos);
		if(pos != null)
			this.getNode(pos.getB(), pos.getA()+1, Label.class).setStyle("-fx-background-color: green; -fx-font-weight: bold;");
	}
	
	@Override
	public void UnckeckAll()
	{
		this.carton.UnckeckAll();
		
		for(int i = 0; i < this.box.getChildren().size(); i++)
			if(this.box.getChildren().get(i) instanceof Label)
				this.box.getChildren().get(i).setStyle("-fx-background-color: white; -fx-font-weight: normal;");
	}

	public GridPane getBox()
	{
		return box;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(List<Numero> ln: this.carton.getNumeros())
		{
			for(Numero n: ln)
				sb.append(n.getNum() + ";");
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
