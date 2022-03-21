package dev.liquidnetwork.liquidpractice.arena;

import dev.liquidnetwork.liquidpractice.enums.ArenaType;
import dev.liquidnetwork.liquidpractice.util.command.command.adapter.CommandTypeAdapter;

public class ArenaTypeTypeAdapter implements CommandTypeAdapter {

    @Override
    public <T> T convert(String string, Class<T> type) {
        try {
            ArenaType arenaType = ArenaType.valueOf(string.toUpperCase());
            return type.cast(arenaType);
        } catch (Exception e) {
            return null;
        }
    }

}

