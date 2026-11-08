package me.pjoh3.monhinelobby.service;

import me.pjoh3.di.LifecycleService;
import me.pjoh3.di.annotation.Inject;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigService implements LifecycleService {
    private final Plugin plugin;
    private final Map<String, Object> configurationMap = new ConcurrentHashMap<>();

    @Inject
    public ConfigService(Plugin plugin) {
        this.plugin = plugin;
        plugin.getLogger().warning("TEST");
        plugin.getLogger().warning("TEST");
        plugin.getLogger().warning("TEST");
    }

    @Override
    public void enable() {
        plugin.saveDefaultConfig();
        reload();
    }

    @Override
    public void disable() {
        configurationMap.clear();
    }

    public void reload() {
        plugin.reloadConfig();

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("");

        if(section == null) {
            plugin.getLogger().severe("[ConfigService] The config does not contain a configuration section.");
            return;
        }

        section.getKeys(false).forEach(string -> configurationMap.put(string, plugin.getConfig()
                .get(string)));
    }

    @SuppressWarnings("all")
    public <T> @NotNull T get(@NotNull String index) {
        T object = (T) configurationMap.get(index);

        if(object == null) {
            plugin.getLogger().severe("[ConfigService] index dont exists!" + index);
        }

        return object;
    }

    public boolean contains(@NotNull String index) {
        return configurationMap.get(index) != null;
    }

    public <T> void set(@NotNull String index, @NotNull T value) {
        plugin.getConfig().set(index, value);
        plugin.saveConfig();
        configurationMap.put(index, value);
    }
}
