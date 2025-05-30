package DeprecatedSpire.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ConserveBatteryPower extends AbstractPower {
    public static final String POWER_ID = "DeprecatedSpire:ConserveBatteryPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public int storedEnergy;

    public ConserveBatteryPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.loadRegion("retain");
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int unusedEnergy = EnergyPanel.getCurrentEnergy();
            if (unusedEnergy > 0) {
                this.flash();
                this.storedEnergy = unusedEnergy;
                updateDescription();
            }
        }
    }

    @Override
    public void onEnergyRecharge() {
        if (this.storedEnergy > 0) {
            this.flash();
            EnergyPanel.addEnergy(this.storedEnergy);
            this.storedEnergy = 0;
            updateDescription();
        }
        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    @Override
    public void updateDescription() {
        if (this.storedEnergy > 0) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + this.storedEnergy + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }
}