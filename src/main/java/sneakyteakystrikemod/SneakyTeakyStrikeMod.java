package sneakyteakystrikemod;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class SneakyTeakyStrikeMod implements PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(SneakyTeakyStrikeMod.class.getName());

    public SneakyTeakyStrikeMod(){
        BaseMod.subscribe(this);
    }

    //Used by @SpireInitializer
    @SuppressWarnings("unused")
    public static void initialize(){
        new SneakyTeakyStrikeMod();
        logger.info("SneakyTeakyStrikeMod is here!");
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeImg = new Texture("sneakyteakystrikemod/images/badge.png"); //this isn't strictly necessary, but it makes the mod appear in the mods menu
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeImg, "Sneaky Teaky Strike", "JohnnyDevo", "Replaces the art on Sneaky Strike with Teaky art.", settingsPanel);
    }
}
