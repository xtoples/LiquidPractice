package dev.liquidnetwork.liquidpractice.util.bootstrap;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import lombok.Getter;

@Getter
public class Bootstrapped {

    protected final LiquidPractice LiquidPractice;

    public Bootstrapped(LiquidPractice LiquidPractice) {
        this.LiquidPractice = LiquidPractice;
    }

}
