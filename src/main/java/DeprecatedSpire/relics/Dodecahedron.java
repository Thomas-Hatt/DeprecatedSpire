package DeprecatedSpire.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static DeprecatedSpire.DeprecatedSpire.makeID;

public class Dodecahedron extends BaseRelic {
    private static final String NAME = "Dodecahedron"; // The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); // This adds the mod's prefix to the relic ID, resulting in modID:relic
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.UNCOMMON; // The relic's rarity.
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY; // The sound played when the relic is clicked.

    public Dodecahedron() {
        super(ID, NAME, RARITY, SOUND);
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
    }

    public void updateDescription(AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void atBattleStart() {
        this.controlPulse();
    }

    public void onVictory() {
        this.stopPulse();
    }

    public void atTurnStart() {
        this.addToBot(new AbstractGameAction() {
            public void update() {
                if (Dodecahedron.this.isActive()) {
                    Dodecahedron.this.flash();
                    this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, Dodecahedron.this));
                    this.addToBot(new GainEnergyAction(1));
                }

                this.isDone = true;
            }
        });
    }

    public int onPlayerHeal(int healAmount) {
        this.controlPulse();
        return super.onPlayerHeal(healAmount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.stopPulse();
        }

        return super.onAttacked(info, damageAmount);
    }

    private boolean isActive() {
        return AbstractDungeon.player.currentHealth >= AbstractDungeon.player.maxHealth;
    }

    private void controlPulse() {
        if (this.isActive()) {
            this.beginLongPulse();
        } else {
            this.stopPulse();
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}