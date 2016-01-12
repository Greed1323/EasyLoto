package com.william.easyloto;

public class Numero
{
	private int num;
	private boolean checked;
	
	public Numero(int num)
	{
		this.num = num;
		this.checked = false;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}
	
	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}
}
