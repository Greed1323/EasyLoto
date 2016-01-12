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
	public boolean HasWin(Carton carton)
	{
		List<List<Numero>> nums = carton.getNumeros();
		
		int count_check = 0;
		
		for(int i = 0; i < nums.size(); i++)
		{
			for(Numero n: nums.get(i))
			{
				if(n.isChecked())
					count_check++;
			}
		}
		
		if(count_check == 15)
		{
			System.out.println("CARTON PLEIN sur le carton" + carton.getName() + " !!");
			return true;
		}
		else if(count_check == 14)
			System.out.println("Il manque le numero " + this.SearchLast(nums).getNum() + " avant le CARTON PLEIN sur le carton " + carton.getName() + " !!");
		
		return false;
	}
	
	private Numero SearchLast(List<List<Numero>> nums)
	{
		for(int i = 0; i < nums.size(); i++)
			for(Numero n: nums.get(i))
				if(!n.isChecked())
					return n;
		
		return null;
	}
}
