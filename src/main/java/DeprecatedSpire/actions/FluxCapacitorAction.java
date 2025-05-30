package DeprecatedSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;

public class FluxCapacitorAction extends AbstractGameAction {
    public FluxCapacitorAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.ENERGY;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int numOrbs = AbstractDungeon.player.filledOrbCount();

            // Remove orbs
            if (numOrbs > 0) {
                this.addToTop(new RemoveAllOrbsAction());
            }
            else {
                this.isDone = true;
                this.tickDuration();
            }

            // Replace removed orbs with Plasma orbs
            for (int i = 0; i < numOrbs; i++) {
                AbstractOrb orb = new Plasma();
                this.addToBot(new ChannelAction(orb));
            }

            this.isDone = true;
        }
        this.tickDuration();
    }
}