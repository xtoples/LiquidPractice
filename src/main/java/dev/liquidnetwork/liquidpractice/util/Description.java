package dev.liquidnetwork.liquidpractice.util;

import dev.liquidnetwork.liquidpractice.LiquidPractice;

public class Description {
    public static String getVersion() {
        return LiquidPractice.getInstance().getDescription().getVersion();
    }

    public static String getAuthor() {
        return LiquidPractice.getInstance().getDescription().getAuthors().toString().replace("[", "").replace("]", "");
    }

    public static String getName() {
        return LiquidPractice.getInstance().getDescription().getName();
    }
}
