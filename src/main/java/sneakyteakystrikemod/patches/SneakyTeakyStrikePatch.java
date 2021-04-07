package sneakyteakystrikemod.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.SneakyStrike;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

public class SneakyTeakyStrikePatch {
    public static final TextureAtlas.AtlasRegion SMALL_TEXTURE = new TextureAtlas.AtlasRegion(new Texture("sneakyteakystrikemod/images/smol.png"), 0, 0, 250, 190);
    public static final String LARGE_TEXTURE_PATH = "sneakyteakystrikemod/images/lorge.png";

    @SpirePatch( //tells ModTheSpire to stick this code at the end of SneakyStrike's constructor
            clz = SneakyStrike.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class CardPatch {
        public static void Postfix(AbstractCard __instance) {
            //a simple reflection shortcut to set the private "portrait" field
            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "portrait", SMALL_TEXTURE);
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "loadPortraitImg"
    )
    public static class PopupPatch {
        public static SpireReturn Prefix(SingleCardViewPopup __instance) {
            AbstractCard c = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (c.cardID.equals(SneakyStrike.ID)) {
                Texture t = new Texture(LARGE_TEXTURE_PATH); //we need a new texture because SingleCardViewPopup calls Texture.dispose when it's done
                ReflectionHacks.setPrivate(__instance, SingleCardViewPopup.class, "portraitImg", t);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue(); //find the texture as normal if it's any card other than Sneaky Strike
        }
    }

}
