package DeprecatedSpire.cards;

import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Nova extends BaseCard {
    // "DESCRIPTION": "Deals !D! damage to all enemies. Damage is increased by 3 times the number of Power cards played this combat.",
    // "UPGRADE_DESCRIPTION": "Deals !D! damage to all enemies. Damage is increased by 4 times the number of Power cards played this combat."

    public static final String ID = makeID(Nova.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.BLUE,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int MULTIPLIER = 3;
    private static final int UPGRADE_MULTIPLIER = 4;

    public Nova() {
        super(ID, info);
        this.baseDamage = 0;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyPowers(); // Calculate damage based on current combat state
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void applyPowers() {
        int powerCardCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.POWER) {
                powerCardCount++;
            }
        }
        int multiplier = this.upgraded ? UPGRADE_MULTIPLIER : MULTIPLIER;
        this.baseDamage = powerCardCount * multiplier;
        super.applyPowers(); // This is important to apply modifiers like Strength
        this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgraded = true; // Don't forget to set the upgraded flag!
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nova();
    }
}