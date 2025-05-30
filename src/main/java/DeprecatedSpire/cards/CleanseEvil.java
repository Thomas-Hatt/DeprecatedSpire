package DeprecatedSpire.cards;

import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.watcher.DivinePunishmentAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CleanseEvil extends BaseCard {
    //     "DESCRIPTION": "Add X *Smite into your hand.",
    //    "UPGRADE_DESCRIPTION": "Add X *Smite+ into your hand."

    public static final String ID = makeID(CleanseEvil.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.PURPLE,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1
    );

    public CleanseEvil() {
        super(ID, info);
        this.cardsToPreview = new Smite();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new Smite();
        if (this.upgraded) {
            ((AbstractCard) c).upgrade();
        }

        this.addToBot(new DivinePunishmentAction(c, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();

            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CleanseEvil();
    }
}