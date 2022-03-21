package dev.liquidnetwork.liquidpractice.enums;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import java.beans.ConstructorProperties;
import java.text.MessageFormat;

public enum PartyMessageType {
    YOU_HAVE_BEEN_INVITED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.YOU_HAVE_BEEN_INVITED")),
    CLICK_TO_JOIN(LiquidPractice.getInstance().getMessagesConfig().getString("Party.CLICK_TO_JOIN")),
    PLAYER_INVITED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.PLAYER_INVITED")),
    PLAYER_JOINED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.PLAYER_JOINED")),
    PLAYER_LEFT(LiquidPractice.getInstance().getMessagesConfig().getString("Party.PLAYER_LEFT")),
    CREATED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.CREATED")),
    DISBANDED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.DISBANDED")),
    PUBLIC(LiquidPractice.getInstance().getMessagesConfig().getString("Party.PUBLIC")),
    PRIVACY_CHANGED(LiquidPractice.getInstance().getMessagesConfig().getString("Party.PRIVACY_CHANGED"));
    
    private final String message;
    
    public String format(final Object... objects) {
        return CC.translate(new MessageFormat(this.message).format(objects));
    }
    
    @ConstructorProperties({ "message" })
    PartyMessageType(final String message) {
        this.message = message;
    }
}
