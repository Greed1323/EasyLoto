package com.william.easyloto.gamemodes;

import java.util.List;

import com.william.easyloto.Carton;
import com.william.easyloto.Numero;

public class GameQuine implements IGameMode
{
	public GameQuine()
	{
		System.out.println("Quine selectionné");
	}
	
	@Override
	public boolean HasWin(Carton carton)
	{
		List<List<Numero>> nums = carton.getNumeros();
		
		for(int i = 0; i < nums.size(); i++)
		{
			int count_check = 0;
			
			for(Numero n: nums.get(i))
			{
				if(n.isChecked())
					count_check++;
			}
			
			if(count_check == 5)
			{
				System.out.println("QUINE !! Carton " + carton.getName() + " ligne " + (i+1));
				return true;
			}
			if(count_check == 4)
				System.out.println("Il manque le numero " + this.searchLast(nums.get(i)).getNum() + " sur le carton " + carton.getName() + " ligne " + (i+1));
		}
		
		return false;
	}
	
	private Numero searchLast(List<Numero> nums)
	{
		for(Numero n: nums)
			if(!n.isChecked())
				return n;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return "Quine";
	}
}











