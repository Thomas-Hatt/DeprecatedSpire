package DeprecatedSpire.cards;

import DeprecatedSpire.actions.ReprieveAction;
import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Reprieve extends BaseCard {

    //     "${modID}:Reprieve": {
    //    "NAME": "Reprieve",
    //    "DESCRIPTION": "If an enemy does not intend to attack, gain !M! Focus."
    //  },
    public static final String ID = makeID(Reprieve.class.getSimpleName());

    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public Reprieve() {
        super(ID, info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ReprieveAction(this.magicNumber, m));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reprieve();
    }
}
