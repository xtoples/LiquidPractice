package dev.liquidnetwork.liquidpractice.util.external.duration;

import dev.liquidnetwork.liquidpractice.util.command.command.adapter.CommandTypeAdapter;

public class DurationTypeAdapter implements CommandTypeAdapter {

    @Override
    public <T> T convert(String string, Class<T> type) {
        return type.cast(Duration.fromString(string));
    }

}

