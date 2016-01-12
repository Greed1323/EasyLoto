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
	
	public void setGameMode(IGameMode mode)
	{
		this.game_mode = mode;
	}
	
	public boolean TestNumber(int num)
	{
		boolean win = false;
		
		for(Carton c: this.cartons)
			win = this.game_mode.HasWin(c);
		
		if(win  && this.game_mode.getClass().equals(GameQuine.class))
			this.game_mode = new GameDoubleQuine();
		else if(win && this.game_mode.getClass().equals(GameDoubleQuine.class))
			this.game_mode = new GameCartonPlein();
		
		return win;
	}
	
	public void insertStub()
	{
		Carton c = new Carton("Carton 1");
		c.AddNum(13, 0);
		c.AddNum(32, 0);
		c.AddNum(50, 0);
		c.AddNum(62, 0);
		c.AddNum(80, 0);
		
		c.AddNum(6, 1);
		c.AddNum(26, 1);
		c.AddNum(39, 1);
		c.AddNum(57, 1);
		c.AddNum(70, 1);
		
		c.AddNum(16, 2);
		c.AddNum(23, 2);
		c.AddNum(43, 2);
		c.AddNum(64, 2);
		c.AddNum(87, 2);
		
		this.cartons.add(c);
	}
}
