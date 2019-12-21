public class Clansman extends ModelImpl {

    private boolean handWeapon;
    private boolean lightArmour;
    private boolean heavyArmour;
    private boolean greatWeapon;
    private boolean shield;

    public Clansman(String name, int movement, int weaponSkill, int ballisticSkill, int strength, int toughness,
                    int wounds, int initiative, int attack, int leadership, int unitStrength, int save, int points,
                    boolean handWeapon, boolean lightArmour, boolean heavyArmour, boolean greatWeapon, boolean shield) {
        super(name, movement, weaponSkill, ballisticSkill, strength, toughness, wounds, initiative, attack, leadership,
                unitStrength, save, points);
        this.handWeapon = handWeapon;
        this.lightArmour = lightArmour;
        this.heavyArmour = heavyArmour;
        this.greatWeapon = greatWeapon;
        this.shield = shield;
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
        if (heavyArmour) {
            setHeavyArmour(false);
        }
    }

    public boolean isHeavyArmour() {
        return heavyArmour;
    }

    public void setHeavyArmour(boolean heavyArmour) {
        this.heavyArmour = heavyArmour;
        setSave(getSave() - 2);
        setPoints(getPoints() + 1);
        if (lightArmour){
            setLightArmour(false);
        }
    }

    public boolean isGreatWeapon() {
        return greatWeapon;
    }

    public void setGreatWeapon(boolean greatWeapon) {
        this.greatWeapon = greatWeapon;
        setStrength(getStrength() + 2);
        setPoints(getPoints() + 2);
        setInitiative(0);
        if (handWeapon){
            setHandWeapon(false);
        }
        if (shield){
            setShield(false);
        }
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
        setPoints(getPoints() + 1);
        if (isHandWeapon() && isShield()) {
            setSave(getSave() - 2);
        }
        if (isShield() && !isHandWeapon()) {
            setSave(getSave() - 1);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
