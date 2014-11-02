package ru.serjik.gameclient;

public class Player extends GameObject
{
	public String name;

	public Player(String[] values)
	{
		id = values[2];
		name = new String(values[3]);
		q = Integer.parseInt(values[4]);
		r = Integer.parseInt(values[5]);
		direction = Integer.parseInt(values[6]);
	}

	public void control(String command)
	{
		if (command.equals("mf"))
		{
			move(1);
		}
		else if (command.equals("mb"))
		{
			move(-1);
		}
		else if (command.equals("rl"))
		{
			rotate(-1);
		}
		else if (command.equals("rr"))
		{
			rotate(1);
		}
	}

	@Override
	public String toString()
	{
		return "Player [name=" + name + ", id=" + id + ", q=" + q + ", r=" + r + ", direction=" + direction + "]";
	}

}
