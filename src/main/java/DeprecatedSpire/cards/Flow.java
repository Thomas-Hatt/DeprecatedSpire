package DeprecatedSpire.cards;

import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.deprecated.DEPRECATEDFlowPower;

public class Flow extends BaseCard {
    public static final String ID = makeID(Flow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.PURPLE,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );

    public Flow() {
        super(ID, info);

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DEPRECATEDFlowPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flow();
    }
}