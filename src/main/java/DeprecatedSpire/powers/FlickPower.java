package DeprecatedSpire.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FlickPower extends AbstractPower {
    public static final String POWER_ID = "FlickPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public FlickPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("talk_to_hand");
        this.type = PowerType.DEBUFF;
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 3) {
            this.addToBot(new DamageAction(this.owner, new DamageInfo((AbstractCreature) null, 50, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, (AbstractCreature) null, "FlickPower"));
        } else {
            this.fontScale = 8.0F;
            this.updateDescription();
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[1] + 50 + powerStrings.DESCRIPTIONS[3];
        } else {
            this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[2] + 50 + powerStrings.DESCRIPTIONS[3];
        }
    }
}