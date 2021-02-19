package me.Jhim.TacklArena.Arena;

import org.bukkit.Location;
import org.bukkit.Material;

public class Arena {

	public String name;
	public Location player1Pos;
	public float player1Yaw;
	public float player1Pitch;
	public Location player2Pos;
	public float player2Yaw;
	public float player2Pitch;
	public Location spectatorPos;
	public float spectatorYaw;
	public float spectatorPitch;
	public Material icon;
	
	boolean taken = false;
	
	public Arena(String name, Material icon, Location player1Pos, float player1Yaw, Location player2Pos, float player2Yaw) {
		this.name = name;
		this.icon = icon;
		this.player1Pos = player1Pos;
		this.player1Yaw = player1Yaw;
		this.player2Pos = player2Pos;
		this.player2Yaw = player2Yaw;
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public void setTaken(boolean takenVal) {
		taken = takenVal;
	}
}
