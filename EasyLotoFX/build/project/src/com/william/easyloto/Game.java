package com.william.easyloto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.william.easyloto.gamemodes.GameCartonPlein;
import com.william.easyloto.gamemodes.GameDoubleQuine;
import com.william.easyloto.gamemodes.GameQuine;
import com.william.easyloto.gamemodes.IGameMode;
import com.william.easyloto.utils.LoadWrite;


public class Game
{
	private List<Carton> cartons;
	private static Game instance;
	private IGameMode game_mode;
	
	public static final int GAMEMODE_QUINE = 1;
	public static final int GAMEMODE_DOUBLEQUINE = 2;
	public static final int GAMEMODE_CARTONPLEIN = 3;
	
	private Game()
	{
		this.cartons = new ArrayList<>();
		this.game_mode = new GameQuine();
	}
	
	public static Game getInstance()
	{
		if(instance == null)
			instance = new Game();
		
		return instance;
	}
	
	public void AddCarton(Carton carton)
	{
		this.cartons.add(carton);
	}
	
	public List<Carton> getCartons()
	{
		return this.cartons;
	}
	
	public void DeleteCarton(Carton carton)
	{
		this.cartons.remove(carton);
	}
	
	public List<String> getCartonsName()
	{
		List<String> names = new ArrayList<>();
		
		for(Carton c: this.cartons)
			names.add(c.getName());
		
		return names;
	}
	
	public boolean SaveCartons(String save_name)
	{
		try
		{
			LoadWrite.getInstance().Save(save_name, this.cartons);
			return true;
		}
		catch (IOException e)
		{
			System.out.println("Echec de la sauvegarde : " + e.getClass());
			return false;
		}
	}
	
	public List<Carton> LoadCartons(String name_save)
	{
		try
		{
			this.cartons = LoadWrite.getInstance().Load(name_save);
			
		}
		catch (IOException e)
		{
			if(e.getClass() == FileNotFoundException.class)
				System.out.println("Fichier de sauvegarde inexistant !!");
			else
				System.out.println("Echec du chargement : " + e.getClass());
		}
		
		return this.cartons;
	}
	
	public String getGameMode()
	{
		return this.game_mode.toString();
	}
	
	public void setGameMode(int mode)
	{
		if(mode == 1)
			this.game_mode = new GameQuine();
		else if(mode == 2)
			this.game_mode = new GameDoubleQuine();
		else if(mode == 3)
			this.game_mode = new GameCartonPlein();
	}
	
	public String NextGameMode()
	{
		if(this.game_mode.getClass().equals(GameQuine.class))
			this.game_mode = new GameDoubleQuine();
		else if(this.game_mode.getClass().equals(GameDoubleQuine.class))
			this.game_mode = new GameCartonPlein();
		else
			this.game_mode = new GameQuine();
		
		return this.getGameMode();
	}
	
	public String TestNumber(int num)
	{
		StringBuilder win = new StringBuilder();
		
		for(Carton c: this.cartons)
			win.append(this.game_mode.HasWin(c) + "\n");
		
		if(win.toString().contains("win"))
			this.NextGameMode();
		
		return win.toString();
	}
}
