package ru.serjik.gameclient;


public abstract class GameObject extends HexLocation
{
	protected String id;

	public int distance(GameObject object)
	{
		return HexUtils.distance(q, r, object.q, object.r);
	}

	public String id()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return "GameObject [id=" + id + ", q=" + q + ", r=" + r + ", direction=" + direction + "]";
	}
}
