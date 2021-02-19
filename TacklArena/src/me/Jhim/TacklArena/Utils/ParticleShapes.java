package me.Jhim.TacklArena.Utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;

public class ParticleShapes {

	public void drawCircle(Particle particleEffect, Player player, float radius, DustOptions dustOptions) {
		
		for(float i = 0; i < 15; i += 0.5) {
			float x = radius* (float) Math.sin(i);
			float z = radius* (float) Math.cos(i);
			Location loc = player.getLocation();
			player.spawnParticle(particleEffect, loc.getX() + x, loc.getY() + 1, loc.getZ() + z, 25, dustOptions);
		}
	}
}
