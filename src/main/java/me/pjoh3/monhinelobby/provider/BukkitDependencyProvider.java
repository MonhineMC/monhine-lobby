package me.pjoh3.monhinelobby.provider;

import me.pjoh3.di.provider.DependencyProvider;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitDependencyProvider extends DependencyProvider {
    public BukkitDependencyProvider(Plugin plugin) {
        registerDependency(MiniMessage.class, MiniMessage.miniMessage());
        registerDependency(JavaPlugin.class, plugin);
        registerDependency(Plugin.class, plugin);
    }
}
