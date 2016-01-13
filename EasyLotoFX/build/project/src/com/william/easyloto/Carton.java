package com.william.easyloto;

import java.util.ArrayList;
import java.util.List;

import com.william.easyloto.utils.Pair;

public class Carton implements ICarton
{
	protected List<List<Numero>> nums;
	private String name;
	
	public Carton()
	{
		
	}
	
	public Carton(String name)
	{
		this.name = name;
		this.nums = new ArrayList<>();
		
		this.nums.add(new ArrayList<>());
		this.nums.add(new ArrayList<>());
		this.nums.add(new ArrayList<>());
	}
	
	@Override
	public void AddNum(int num, int line)
	{
		this.nums.get(line).add(new Numero(num));
	}
	
	@Override
	public void CheckNum(int num)
	{
		Numero n = this.searchNum(num);
		if(n != null)
			n.setChecked(true);
	}
	
	@Override
	public void UncheckNum(int num)
	{
		Numero n = this.searchNum(num);
		if(n != null)
			n.setChecked(false);
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void UnckeckAll()
	{
		for(List<Numero> l: this.nums)
			for(Numero n: l)
				n.setChecked(false);
	}
	
	public Numero searchNum(int num)
	{
		for(List<Numero> l: this.nums)
			for(Numero n: l)
				if(n.getNum() == num)
					return n;
		
		return null;
	}
	
	public Pair<Integer, Integer> indexOfNumber(Numero num)
	{
		for(int i = 0; i < this.nums.size(); i++)
			for(int j = 0; j < this.nums.get(i).size(); j++)
				if(this.nums.get(i).get(j) == num)
					return new Pair<Integer, Integer>(i, j);
		
		return null;
	}
	
	public List<List<Numero>> getNumeros()
	{
		return this.nums;
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		
		str.append(name + "\n");
		
		for(List<Numero> l: this.nums)
		{
			for(Numero n: l)
				str.append(n.getNum() + " ; ");
				
			str.append("\n");	
		}
		
		return str.toString();
	}
	
	
}
