package DeprecatedSpire.cards;

import DeprecatedSpire.actions.FluxCapacitorAction;
import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class FluxCapacitor extends BaseCard {

    //   "Flux Capacitor": {
    //    "NAME": "Flux Capacitor",
    //    "DESCRIPTION": "Replace all Channeled Orbs with Plasma. NL Exhaust."
    //  },
    public static final String ID = makeID(FluxCapacitor.class.getSimpleName());

    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            2
    );

    public FluxCapacitor() {
        super(ID, info);
        this.baseDamage = 7;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("CARD_POWER_IMPACT"));
        this.addToBot(new FluxCapacitorAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FluxCapacitor();
    }
}
