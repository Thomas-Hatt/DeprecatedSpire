package DeprecatedSpire.powers;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SerenityPower extends AbstractPower {
    public static final String POWER_ID = "Serenity";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public SerenityPower(AbstractCreature owner, int amt) {
        this.name = NAME;
        this.ID = "Serenity";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("platedarmor");
    }

    public void playApplyPowerSfx() {
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + LocalizedStrings.PERIOD;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && ((AbstractPlayer)this.owner).stance.ID.equals("Calm")) {
            this.flash();
            damageAmount -= this.amount;
            if (damageAmount < 0) {
                damageAmount = 0;
            }
        }
        return damageAmount;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Serenity");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
