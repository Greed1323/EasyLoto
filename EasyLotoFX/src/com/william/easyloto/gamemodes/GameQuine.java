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
	public String HasWin(Carton carton)
	{
		List<List<Numero>> nums = carton.getNumeros();
		
		for(int i = 0; i < nums.size(); i++)
		{
			int count_check = 0;
			
			for(Numero n: nums.get(i))
				if(n.isChecked())
					count_check++;
			
			if(count_check == 5)
				return "win QUINE !! Carton " + carton.getName() + " ligne " + (i+1);
			if(count_check == 4)
				return "Il manque le numero " + this.searchLast(nums.get(i)).getNum() + " sur le carton " + carton.getName() + " ligne " + (i+1);
		}
		
		return "";
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











