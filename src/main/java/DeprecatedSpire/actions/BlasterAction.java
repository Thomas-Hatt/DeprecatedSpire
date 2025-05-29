package DeprecatedSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlasterAction extends AbstractGameAction {
    public BlasterAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.ENERGY;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int numOrbs = AbstractDungeon.player.filledOrbCount();
            if (numOrbs > 0) {
                AbstractDungeon.player.gainEnergy(numOrbs);
            }
            this.isDone = true;
        }
        this.tickDuration();
    }
}