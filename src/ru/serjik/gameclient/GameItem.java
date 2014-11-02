package ru.serjik.gameclient;

public class GameItem extends GameObject
{
	public String type;
	public int count;

	public GameItem(String[] values)
	{
		id = values[2];
		type = values[3];
		count = Integer.parseInt(values[4]);
		q = Integer.parseInt(values[5]);
		r = Integer.parseInt(values[6]);
		direction = Integer.parseInt(values[7]);
	}

	@Override
	public String toString()
	{
		return "GameItem [type=" + type + ", count=" + count + ", id=" + id + ", q=" + q + ", r=" + r + "]";
	}
}
