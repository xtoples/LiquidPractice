package dev.liquidnetwork.liquidpractice.util.command.command.adapter.impl;

import dev.liquidnetwork.liquidpractice.util.command.command.CommandOption;
import dev.liquidnetwork.liquidpractice.util.command.command.adapter.CommandTypeAdapter;

public class CommandOptionTypeAdapter implements CommandTypeAdapter
{
    @Override
    public <T> T convert(final String string, final Class<T> type) {
        if (string.startsWith("-")) {
            return type.cast(new CommandOption(string.substring(1)));
        }
        return null;
    }
}
