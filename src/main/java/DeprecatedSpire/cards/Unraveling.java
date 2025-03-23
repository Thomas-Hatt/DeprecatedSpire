package DeprecatedSpire.cards;

import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.watcher.UnravelingAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Unraveling extends BaseCard {
    // Play all of your cards from left to right. Targets are chosen randomly. Exhaust.

    public static final String ID = makeID(Unraveling.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.PURPLE,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );

    public Unraveling() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new UnravelingAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Unraveling();
    }
}