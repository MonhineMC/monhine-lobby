package me.pjoh3.monhinelobby.serializer;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class LocationSerializer {
    public @NotNull String serialize(@NotNull Location location) {
        if (location.getWorld() == null) {
            throw new IllegalArgumentException("Location has no world");
        }

        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + ","
                + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    public @NotNull Location deserialize(@Nullable String string) {
        World randomWorld = Bukkit.getWorlds().get(0);
        Location nullLocation = new Location(randomWorld, 0, 0, 0, 0, 0);

        if (string == null || string.isEmpty()) {
            return nullLocation;
        }

        String[] parts = string.split(",", 6);
        if (parts.length != 6) {
            return nullLocation;
        }

        World world = Bukkit.getWorld(parts[0]);
        if (world == null) {
            return nullLocation;
        }

        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double z = Double.parseDouble(parts[3]);
        float  yaw = Float.parseFloat(parts[4]);
        float  pitch = Float.parseFloat(parts[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }
}
