package com.example.br.core;

import com.example.br.BattleRoyale;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaManager {

    private final BattleRoyale plugin;
    private final Map<String, Arena> arenas = new HashMap<>();

    public ArenaManager(BattleRoyale plugin) {
        this.plugin = plugin;
        load();
    }

    public Arena getArena(String name) {
        return arenas.get(name.toLowerCase());
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }

    public boolean create(String name) {
        if (arenas.containsKey(name.toLowerCase())) return false;
        arenas.put(name.toLowerCase(), new Arena(name));
        return true;
    }

    public boolean delete(String name) {
        if (!arenas.containsKey(name.toLowerCase())) return false;
        arenas.remove(name.toLowerCase());
        plugin.getConfig().set("arenas." + name.toLowerCase(), null);
        plugin.saveConfig();
        return true;
    }

    // --- Save to config ---
    public void save() {
        FileConfiguration cfg = plugin.getConfig();
        for (Arena a : arenas.values()) {
            String path = "arenas." + a.getName().toLowerCase();

            cfg.set(path + ".lobby-spawn", a.getLobbySpawn());
            cfg.set(path + ".game-spawn", a.getGameSpawn());
            cfg.set(path + ".plane-start", a.getPlaneStart());
            cfg.set(path + ".plane-end", a.getPlaneEnd());
            cfg.set(path + ".jump-point", a.getJumpPoint());
            cfg.set(path + ".border-initial", a.getBorderInitialRadius());
            cfg.set(path + ".border-final", a.getBorderFinalRadius());
            cfg.set(path + ".border-duration", a.getBorderShrinkDuration());

            // Loot chests
            cfg.set(path + ".loot-chests", null);
            for (int i = 0; i < a.getLootChests().size(); i++) {
                cfg.set(path + ".loot-chests." + i, a.getLootChests().get(i));
            }

            // Zombie spawns
            cfg.set(path + ".zombie-spawns", null);
            for (int i = 0; i < a.getZombieSpawns().size(); i++) {
                cfg.set(path + ".zombie-spawns." + i, a.getZombieSpawns().get(i));
            }

            // Villager spots
            cfg.set(path + ".villager-spots", null);
            for (int i = 0; i < a.getVillagerSpots().size(); i++) {
                cfg.set(path + ".villager-spots." + i, a.getVillagerSpots().get(i));
            }
        }
        plugin.saveConfig();
    }

    // --- Load from config ---
    private void load() {
        FileConfiguration cfg = plugin.getConfig();
        ConfigurationSection sec = cfg.getConfigurationSection("arenas");
        if (sec == null) return;

        for (String key : sec.getKeys(false)) {
            String path = "arenas." + key;
            Arena a = new Arena(key);

            a.setLobbySpawn(cfg.getLocation(path + ".lobby-spawn"));
            a.setGameSpawn(cfg.getLocation(path + ".game-spawn"));
            a.setPlaneStart(cfg.getLocation(path + ".plane-start"));
            a.setPlaneEnd(cfg.getLocation(path + ".plane-end"));
            a.setJumpPoint(cfg.getLocation(path + ".jump-point"));
            a.setBorderInitialRadius(cfg.getInt(path + ".border-initial", 500));
            a.setBorderFinalRadius(cfg.getInt(path + ".border-final", 50));
            a.setBorderShrinkDuration(cfg.getInt(path + ".border-duration", 600));

            // Loot chests
            ConfigurationSection lc = cfg.getConfigurationSection(path + ".loot-chests");
            if (lc != null) {
                for (String k : lc.getKeys(false)) {
                    Location l = lc.getLocation(k);
                    if (l != null) a.addLootChest(l);
                }
            }

            // Zombie spawns
            ConfigurationSection zs = cfg.getConfigurationSection(path + ".zombie-spawns");
            if (zs != null) {
                for (String k : zs.getKeys(false)) {
                    Location l = zs.getLocation(k);
                    if (l != null) a.addZombieSpawn(l);
                }
            }

            // Villager spots
            ConfigurationSection vs = cfg.getConfigurationSection(path + ".villager-spots");
            if (vs != null) {
                for (String k : vs.getKeys(false)) {
                    Location l = vs.getLocation(k);
                    if (l != null) a.addVillagerSpot(l);
                }
            }

            arenas.put(key, a);
        }
        plugin.getLogger().info("Loaded " + arenas.size() + " arena(s).");
    }
}
