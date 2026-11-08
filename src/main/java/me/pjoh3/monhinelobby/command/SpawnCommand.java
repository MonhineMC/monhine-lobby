package me.pjoh3.monhinelobby.command;

import dev.rollczi.litecommands.annotations.async.Async;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.pjoh3.di.annotation.Inject;
import me.pjoh3.di.annotation.command.NCommand;
import me.pjoh3.monhinelobby.serializer.LocationSerializer;
import me.pjoh3.monhinelobby.service.ConfigService;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@NCommand()
@Command(name = "spawn")
public class SpawnCommand {
    private final ConfigService configService;

    @Inject
    public SpawnCommand(ConfigService configService) {
        this.configService = configService;
    }

    @Async
    @Execute
    void spawn(@Context Player sender) {
        String stringLocation = configService.get("spawn");
        Location location = LocationSerializer.deserialize(stringLocation);
        sender.teleportAsync(location);
    }

    @Async
    @Execute(name = "set")
    @Permission("monhinelobby.setspawn")
    void setSpawn(@Context Player sender) {
        Location location = sender.getLocation();
        String stringLocation = LocationSerializer.serialize(location);
        configService.set("spawn", stringLocation);
    }
}
