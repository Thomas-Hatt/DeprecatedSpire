package DeprecatedSpire.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.WrathStance;

public class AlwaysMadPower extends AbstractPower {
    public static final String POWER_ID = "AlwaysMad";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public AlwaysMadPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("anger");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    @SpirePatch(clz = WrathStance.class, method = "atDamageReceive")
    public static class AlwaysMadWrathPatch {
        @SpirePrefixPatch
        public static SpireReturn<Float> modifyWrathDamage(WrathStance __instance,
                                                           float damage,
                                                           DamageInfo.DamageType type) {
            // Check if player has AlwaysMadPower
            if (AbstractDungeon.player != null &&
                    AbstractDungeon.player.hasPower(AlwaysMadPower.POWER_ID)) {
                // Return original damage (cancel 2x multiplier)
                return SpireReturn.Return(damage);
            }
            // Proceed with normal Wrath behavior
            return SpireReturn.Continue();
        }
    }
}