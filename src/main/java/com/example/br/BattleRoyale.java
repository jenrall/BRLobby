package com.example.br;

import com.example.br.command.BRCommand;
import com.example.br.command.BRUserCommand;
import com.example.br.core.ArenaManager;
import com.example.br.core.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleRoyale extends JavaPlugin {

    private static BattleRoyale instance;
    private ArenaManager arenaManager;
    private GameState state = GameState.IDLE;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Core
        this.arenaManager = new ArenaManager(this);

        // Commands
        getCommand("br").setExecutor(new BRCommand(this));
        getCommand("bruser").setExecutor(new BRUserCommand(this));

        // Listeners (به زودی)

        getLogger().info("BRLobby enabled! State: " + state);
    }

    @Override
    public void onDisable() {
        if (arenaManager != null) arenaManager.save();
        getLogger().info("BRLobby disabled!");
    }

    public static BattleRoyale getInstance() { return instance; }
    public ArenaManager getArenaManager() { return arenaManager; }
    public GameState getState() { return state; }

    public void setState(GameState s) {
        this.state = s;
        getLogger().info("State changed to " + s);
    }
}
