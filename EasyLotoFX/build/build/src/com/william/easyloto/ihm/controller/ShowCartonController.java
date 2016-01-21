package com.william.easyloto.ihm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.william.easyloto.Carton;
import com.william.easyloto.Game;
import com.william.easyloto.ihm.ViewManager;
import com.william.easyloto.ihm.component.CartonIHM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ShowCartonController implements Initializable
{
	@FXML
	private TextField save_name;
	
	@FXML
	private ComboBox<String> comboCarton;
	private ObservableList<String> oname;
	
	@FXML
	protected ListView<CartonIHM> listCarton;
	private ObservableList<CartonIHM> ocartons;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.ocartons = FXCollections.observableArrayList();
		this.oname = FXCollections.observableArrayList(Game.getInstance().getCartonsName());
		
		this.comboCarton.setItems(this.oname);
		
		this.listCarton.setItems(this.ocartons);
		this.listCarton.setCellFactory(new Callback<ListView<CartonIHM>, ListCell<CartonIHM>>()
        {
            @Override
            public ListCell<CartonIHM> call(ListView<CartonIHM> listView)
            {
                return new ListCell<CartonIHM>(){
                	@Override
                	protected void updateItem(CartonIHM carton, boolean empty)
                	{
                		super.updateItem(carton, empty);
                		
                		if(carton != null)
							this.setGraphic(carton.getBox());
                	}
                };
            }
        });
		
		this.InitCartons();
	}
	
	@FXML
	public void goCreateCarton()
	{
		ViewManager.loadView(ViewManager.ADDCARTON);
	}
	
	@FXML
	public void Save()
	{
		if(!this.save_name.getText().equals(""))
			if(Game.getInstance().SaveCartons(this.save_name.getText()))
				this.save_name.setText("");
	}
	
	@FXML
	public void Load()
	{
		if(!this.save_name.getText().equals(""))
			for(Carton carton: Game.getInstance().LoadCartons(this.save_name.getText()))
				this.ocartons.add(new CartonIHM(carton));
				
		this.save_name.setText("");
		
		this.oname.clear();
		this.oname.addAll(Game.getInstance().getCartonsName());
	}
	
	@FXML
	public void goPlay()
	{
		ViewManager.loadView(ViewManager.RUNGAME);
	}
	
	@FXML
	public void DeleteCarton()
	{
		for(int i = 0; i < this.ocartons.size(); i++)
			if(this.comboCarton.getValue().equals(this.ocartons.get(i).getName()))
			{
				Game.getInstance().DeleteCarton(this.ocartons.get(i).getCarton());
				this.ocartons.remove(i);
				this.oname.remove(this.comboCarton.getValue());
				break;
			}
	}
	
	private void InitCartons()
	{
		for(Carton carton: Game.getInstance().getCartons())
			this.ocartons.add(new CartonIHM(carton));
	}
}
