package DeprecatedSpire.cards;

import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Blessed extends BaseCard {
    // "DESCRIPTION": "Shuffle !M! *Miracles into your draw pile. NL Exhaust.",
    // "UPGRADE_DESCRIPTION": "Shuffle !M! *Miracles+ into your draw pile. NL Exhaust."

    public static final String ID = makeID(Blessed.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.PURPLE,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public Blessed() {
        super(ID, info);
        this.exhaust = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Miracle();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard miracle = CardLibrary.getCard("Miracle").makeCopy();
        if (this.upgraded) {
            miracle.upgrade();
        }
        this.addToBot(new MakeTempCardInDrawPileAction(miracle, this.magicNumber, true, true, false));
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
        return new Blessed();
    }
}