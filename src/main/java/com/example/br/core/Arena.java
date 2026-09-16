package com.example.br.core;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private final String name;
    private Location lobbySpawn;
    private Location gameSpawn;
    private Location planeStart;
    private Location planeEnd;
    private Location jumpPoint;
    private final List<Location> lootChests = new ArrayList<>();
    private final List<Location> zombieSpawns = new ArrayList<>();
    private final List<Location> villagerSpots = new ArrayList<>();

    private int borderInitialRadius = 500;
    private int borderFinalRadius = 50;
    private int borderShrinkDuration = 600;

    public Arena(String name) {
        this.name = name;
    }

    // --- Getters ---
    public String getName() { return name; }
    public Location getLobbySpawn() { return lobbySpawn; }
    public Location getGameSpawn() { return gameSpawn; }
    public Location getPlaneStart() { return planeStart; }
    public Location getPlaneEnd() { return planeEnd; }
    public Location getJumpPoint() { return jumpPoint; }
    public List<Location> getLootChests() { return lootChests; }
    public List<Location> getZombieSpawns() { return zombieSpawns; }
    public List<Location> getVillagerSpots() { return villagerSpots; }
    public int getBorderInitialRadius() { return borderInitialRadius; }
    public int getBorderFinalRadius() { return borderFinalRadius; }
    public int getBorderShrinkDuration() { return borderShrinkDuration; }

    // --- Setters ---
    public void setLobbySpawn(Location l) { this.lobbySpawn = l; }
    public void setGameSpawn(Location l) { this.gameSpawn = l; }
    public void setPlaneStart(Location l) { this.planeStart = l; }
    public void setPlaneEnd(Location l) { this.planeEnd = l; }
    public void setJumpPoint(Location l) { this.jumpPoint = l; }
    public void setBorderInitialRadius(int r) { this.borderInitialRadius = r; }
    public void setBorderFinalRadius(int r) { this.borderFinalRadius = r; }
    public void setBorderShrinkDuration(int d) { this.borderShrinkDuration = d; }

    public void addLootChest(Location l) { lootChests.add(l); }
    public void addZombieSpawn(Location l) { zombieSpawns.add(l); }
    public void addVillagerSpot(Location l) { villagerSpots.add(l); }

    public void clearLootChests() { lootChests.clear(); }
    public void clearZombieSpawns() { zombieSpawns.clear(); }
    public void clearVillagerSpots() { villagerSpots.clear(); }

    // --- Check ---
    public boolean isReady() {
        return lobbySpawn != null && gameSpawn != null;
    }

    public boolean isFullReady() {
        return isReady() && planeStart != null && planeEnd != null;
    }
}
