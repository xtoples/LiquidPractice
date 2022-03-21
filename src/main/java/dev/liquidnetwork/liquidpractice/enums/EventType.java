package dev.liquidnetwork.liquidpractice.enums;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import org.bukkit.Material;
import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.sumo.Sumo;

@AllArgsConstructor
@Getter
public enum EventType {
    BRACKETS(LiquidPractice.getInstance().getBracketsManager().getActiveBrackets(), "&b&lBrackets", Material.IRON_SWORD, Brackets.isEnabled(), Brackets.getMaxPlayers()),
    SUMO(LiquidPractice.getInstance().getSumoManager().getActiveSumo(), "&b&lSumo", Material.LEASH, Sumo.isEnabled(), Sumo.getMaxPlayers()),
    LMS(LiquidPractice.getInstance().getLMSManager().getActiveLMS(), "&b&lLMS", Material.DIAMOND_SWORD, dev.liquidnetwork.liquidpractice.event.types.lms.LMS.isEnabled(), dev.liquidnetwork.liquidpractice.event.types.lms.LMS.getMaxPlayers()),
    PARKOUR(LiquidPractice.getInstance().getParkourManager().getActiveParkour(), "&b&lParkour", Material.FEATHER, Parkour.isEnabled(), Parkour.getMaxPlayers()),
    WIZARD(LiquidPractice.getInstance().getWizardManager().getActiveWizard(), "&b&lWizard", Material.IRON_FENCE, Wizard.isEnabled(), Wizard.getMaxPlayers()),
    SPLEEF(LiquidPractice.getInstance().getSpleefManager().getActiveSpleef(), "&b&lSpleef", Material.SNOW_BALL, Spleef.isEnabled(), Spleef.getMaxPlayers()),
    OITC(null, "&c&lOITC", Material.BOW, false, 0),
    KOTH(null, "&c&lKoTH", Material.IRON_BOOTS, false, 0);

    private final Object object;
    private final String title;
    private final Material material;
    @Setter
    private boolean enabled;
    private final int limit;

}
