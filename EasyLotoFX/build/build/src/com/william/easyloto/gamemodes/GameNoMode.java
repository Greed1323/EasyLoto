package com.william.easyloto.gamemodes;

import com.william.easyloto.Carton;

public class GameNoMode implements IGameMode {

	@Override
	public String HasWin(Carton carton)
	{
		return "Tu ne gagnera jamais !!";
	}
	
	@Override
	public String toString()
	{
		return "No Mode, please select a mode !";
	}
}
