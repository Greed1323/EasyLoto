package com.william.easyloto.gamemodes;

import java.util.List;

import com.william.easyloto.Carton;
import com.william.easyloto.Numero;

public class GameCartonPlein implements IGameMode
{
	public GameCartonPlein()
	{
		System.out.println("Carton plein selectionné");
	}
	
	@Override
	public String HasWin(Carton carton)
	{
		List<List<Numero>> nums = carton.getNumeros();
		
		int count_check = 0;
		
		for(int i = 0; i < nums.size(); i++)
			for(Numero n: nums.get(i))
				if(n.isChecked())
					count_check++;
		
		if(count_check == 15)
			return "win CARTON PLEIN sur le carton" + carton.getName() + " !!";
		else if(count_check == 14)
			return "Il manque le numero " + this.SearchLast(nums).getNum() + " avant le CARTON PLEIN sur le carton " + carton.getName() + " !!";
		
		return "";
	}
	
	private Numero SearchLast(List<List<Numero>> nums)
	{
		for(int i = 0; i < nums.size(); i++)
			for(Numero n: nums.get(i))
				if(!n.isChecked())
					return n;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return "Carton plein";
	}
}
