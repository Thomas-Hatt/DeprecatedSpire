package DeprecatedSpire.cards;

import DeprecatedSpire.powers.AlwaysMadPower;
import DeprecatedSpire.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AlwaysMad extends BaseCard {
    public static final String ID = makeID(AlwaysMad.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.PURPLE,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public AlwaysMad() {
        super(ID, info);
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AlwaysMadPower(p)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AlwaysMad();
    }
}