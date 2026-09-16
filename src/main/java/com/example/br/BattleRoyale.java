package com.example.br;

import org.bukkit.plugin.java.JavaPlugin;

public final class BattleRoyale extends JavaPlugin {

    private static BattleRoyale instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getLogger().info("BRLobby enabled! (empty shell)");
    }

    @Override
    public void onDisable() {
        getLogger().info("BRLobby disabled!");
    }

    public static BattleRoyale getInstance() {
        return instance;
    }
}
