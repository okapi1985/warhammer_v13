import com.google.common.base.Preconditions;

public class Clanrat extends ModelImpl {

    private boolean handWeapon;
    private boolean lightArmour;
    private boolean shield;
    private boolean spear;

    public Clanrat(String name, int movement, int weaponSkill, int ballisticSkill, int strength, int toughness,
                   int wounds, int initiative, int attack, int leadership, int unitStrength, int save, int points,
                   boolean handWeapon, boolean lightArmour, boolean shield, boolean spear) {
        super(name, movement, weaponSkill, ballisticSkill, strength, toughness, wounds, initiative, attack, leadership,
                unitStrength, save, points);
        this.handWeapon = handWeapon;
        this.lightArmour = lightArmour;
        this.shield = shield;
        this.spear = spear;
    }

    public boolean isHandWeapon() {
        return handWeapon;
    }

    public void setHandWeapon(boolean handWeapon) {
        this.handWeapon = handWeapon;
    }

    public boolean isLightArmour() {
        return lightArmour;
    }

    public void setLightArmour(boolean lightArmour) {
        this.lightArmour = lightArmour;
        setSave(getSave() - 1);
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
        if (isHandWeapon() && isShield()) {
            setSave(getSave() - 2);
        }
        if (isShield() && !isHandWeapon()) {
            setSave(getSave() - 1);
        }
    }

    public boolean isSpear() {
        return spear;
    }

    public void setSpear(boolean spear) {
        this.spear = spear;
        setPoints(getPoints() + 1);
        if (handWeapon){
            setHandWeapon(false);
        }
        //TODO
        //for infantry second rank fights as well in round when not charging
    }

    @Override
    public String toString() {
        return getName();
    }
}
