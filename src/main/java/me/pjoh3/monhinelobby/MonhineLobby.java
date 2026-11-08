package me.pjoh3.monhinelobby;

import me.pjoh3.monhinelobby.provider.BukkitDependencyProvider;
import me.pjoh3.monhinemc.di.injector.BukkitDependencyInjector;
import org.bukkit.plugin.java.JavaPlugin;

public class MonhineLobby extends JavaPlugin {
    @Override
    public void onEnable () {
        getLogger().info("lobby successful enabled");

        BukkitDependencyInjector injector = new BukkitDependencyInjector(this);
        injector.setProvider(new BukkitDependencyProvider(this));
        injector.enable("me.pjoh3");
    }

    @Override
    public void onDisable() {
        getLogger().info("lobby disabled");
    }
}
