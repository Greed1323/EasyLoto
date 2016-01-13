package com.william.easyloto.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.william.easyloto.Carton;
import com.william.easyloto.Numero;

public class LoadWrite
{
	private static LoadWrite instance;
	
	private LoadWrite()
	{
		
	}
	
	public static LoadWrite getInstance()
	{
		if(instance == null)
			instance = new LoadWrite();
		
		return instance;
	}
	
	public void Save(String name_save, List<Carton> cartons) throws IOException
	{
		File f = new File(name_save);
		if(!f.exists())
			f.createNewFile();
		
		BufferedWriter file = new BufferedWriter(new FileWriter(f));
		
		for(Carton c: cartons)
		{
			StringBuilder str = new StringBuilder();
			str.append(c.getName() + ";");
			
			for(List<Numero> ln: c.getNumeros())
				for(Numero n: ln)
					str.append(n.getNum() + ";");
			
			file.write(str.toString());
			file.newLine();
		}
		
		
		file.close();
	}
	
	public List<Carton> Load(String name_save) throws IOException
	{
		List<Carton> cartons = new ArrayList<>();
		
		BufferedReader reader = new BufferedReader(new FileReader(name_save));
		
		String str = reader.readLine();
		
		while(str != null)
		{
			String[] ts = str.split(";");
			Carton c = new Carton(ts[0]);
			
			for(int i = 1; i < ts.length; i++)
			{
				int line = ((i-1) / 5);
				
				c.AddNum(Integer.parseInt(ts[i]), line);
			}
			
			cartons.add(c);
			
			str = reader.readLine();
		}
		
		reader.close();
		
		return cartons;
	}
}









