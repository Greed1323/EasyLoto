package com.william.easyloto.gamemodes;

import java.util.ArrayList;
import java.util.List;

import com.william.easyloto.Carton;
import com.william.easyloto.Numero;

public class GameDoubleQuine implements IGameMode
{
	public GameDoubleQuine()
	{
		System.out.println("Double quine selectionné");
	}
	
	@Override
	public boolean HasWin(Carton carton)
	{
		List<List<Numero>> nums = carton.getNumeros();
		
		int count_line = 0;
		List<Integer> line_winning = new ArrayList<>();
		
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
				count_line++;
				line_winning.add(i+1);
			}
			
			if(count_line == 2)
			{
				System.out.println("Double QUINE !! Carton " + carton.getName() + " ligne " + line_winning.get(0) + " et " + line_winning.get(1));
				return true;
			}
		}
		
		if(count_line == 1)
		{
			int res = this.FindLineLast(nums);
			
			if(res != 0)
				System.out.println("Il manque le numero " + this.SearchLast(nums.get(res)).getNum() + " avant la DOUBLE QUINE sur le carton " + carton.getName());
		}
		
		return false;
	}
	
	private int FindLineLast(List<List<Numero>> nums)
	{
		for(int i = 0; i < nums.size(); i++)
		{
			int count_num = 0;
			
			for(Numero n: nums.get(i))
				if(n.isChecked())
					count_num++;
			
			if(count_num == 4)
				return i;
		}
		
		return 0;
	}
	
	private Numero SearchLast(List<Numero> nums)
	{
		for(Numero n: nums)
			if(!n.isChecked())
				return n;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return "Double quine";
	}
}










