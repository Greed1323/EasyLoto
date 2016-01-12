package com.william.easyloto.ihm.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class RunGameController implements Initializable
{
	@FXML
	private ListView<CartonIHM> listCarton;
	private ObservableList<CartonIHM> ocartons;
	
	@FXML
	private ComboBox<String> comboMode;
	private ObservableList<String> omode;
	
	@FXML
	private TextField txtNum;
	
	@FXML
	private Label txtMode;
	
	private List<Integer> listNum;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.ocartons = FXCollections.observableArrayList();
		this.omode = FXCollections.observableArrayList("Quine", "Carton plein");
		
		this.txtMode.setText(Game.getInstance().getGameMode());
		
		this.comboMode.setItems(omode);
		
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
		
		this.listNum = new ArrayList<>();
	}
	
	@FXML
	public void Start()
	{
		this.ResetCarton();
		
		if(this.comboMode.getValue().equals("Quine"))
			Game.getInstance().setGameMode(Game.GAMEMODE_QUINE);
		else if(this.comboMode.getValue().equals("Carton plein"))
			Game.getInstance().setGameMode(Game.GAMEMODE_CARTONPLEIN);
		
		this.txtMode.setText(Game.getInstance().getGameMode());
	}
	
	@FXML
	public void NextMode()
	{
		this.txtMode.setText(Game.getInstance().NextGameMode());
	}
	
	@FXML
	public void AddNum(KeyEvent event)
	{
		if(event.getCode() == KeyCode.ENTER && !this.txtNum.getText().equals(""))
		{
			this.listNum.add(0);
			int num = Integer.valueOf(this.txtNum.getText());
			
			for(CartonIHM carton: this.ocartons)
				carton.CheckNum(num);
			
			if(Game.getInstance().TestNumber(num))
			{
				System.out.println("WIN");
				this.NextMode();
			}
			
			this.txtNum.setText("");
		}
	}
	
	@FXML
	public void goHome()
	{
		this.ResetCarton();
		ViewManager.loadView(ViewManager.SHOWCARTON);
	}
	
	private void InitCartons()
	{
		for(Carton carton: Game.getInstance().getCartons())
			this.ocartons.add(new CartonIHM(carton));
	}
	
	private void ResetCarton()
	{
		for(CartonIHM carton: this.ocartons)
			carton.UnckeckAll();
	}
}
