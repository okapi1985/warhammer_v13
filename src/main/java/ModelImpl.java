import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class ModelImpl implements Model {

    public static Model create(){
        return new ModelImpl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMovement() {
        return movement;
    }

    @Override
    public int getWeaponSkill() {
        return weaponSkill;
    }

    @Override
    public int getBallisticSkill() {
        return ballisticSkill;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getToughness() {
        return toughness;
    }

    @Override
    public int getWounds() {
        return wounds;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getLeadership() {
        return leadership;
    }

    @Override
    public int getUnitStrength() {
        return unitStrength;
    }

    @Override
    public int getSave() {
        return save;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),
                "Name cannot be null or empty");
        this.name = name;
    }

    @Override
    public void setMovement(int movement) {
        Preconditions.checkArgument(movement >= 0,
                "Movement cannot be negative");
        this.movement = movement;
    }

    @Override
    public void setWeaponSkill(int weaponSkill) {
        Preconditions.checkArgument(weaponSkill >= 0 && weaponSkill <= 10,
                "Weapon skill must be between 0 and 10");
        this.weaponSkill = weaponSkill;
    }

    @Override
    public void setBallisticSkill(int ballisticSkill) {
        Preconditions.checkArgument(ballisticSkill >= 0 && ballisticSkill <= 10,
                "Ballistic skill must be between 0 and 10");
        this.ballisticSkill = ballisticSkill;
    }

    @Override
    public void setStrength(int strength) {
        Preconditions.checkArgument(strength >= 0 && strength <= 10,
                "Strength must be between 0 and 10");
        this.strength = strength;
    }

    @Override
    public void setToughness(int toughness) {
        Preconditions.checkArgument(toughness >= 0 && toughness <= 10,
                "Toughness must be between 0 and 10");
        this.toughness = toughness;
    }

    @Override
    public void setWounds(int wounds) {
        Preconditions.checkArgument(wounds > 0,
                "Wounds must be at least 1");
        this.wounds = wounds;
    }

    @Override
    public void setInitiative(int initiative) {
        Preconditions.checkArgument(initiative >= 0 && initiative <= 10,
                "Initiative must be between 0 and 10");
        this.initiative = initiative;
    }

    @Override
    public void setAttack(int attack) {
        Preconditions.checkArgument(attack >= 0,
                "Attack must be at least 0");
        this.attack = attack;
    }

    @Override
    public void setLeadership(int leadership) {
        Preconditions.checkArgument(leadership >= 0 && leadership <= 10,
                "Leadership must be between 0 and 10");
        this.leadership = leadership;
    }

    @Override
    public void setUnitStrength(int unitStrength) {
        Preconditions.checkArgument(unitStrength > 0,
                "Unit strength must be at least 1");
        this.unitStrength = unitStrength;
    }

    @Override
    public void setSave(int save) {
        Preconditions.checkArgument(save <= 7,
                "Save must be maximum 7");
        this.save = save;
    }

    @Override
    public void setPoints(int points) {
        Preconditions.checkArgument(points > 0,
                "Model must be worth at least 1 point");
        this.points = points;
    }

    @Override
    public String toString() {
        return name;
    }

    public ModelImpl(String name, int movement, int weaponSkill, int ballisticSkill, int strength, int toughness,
                     int wounds, int initiative, int attack, int leadership, int unitStrength, int save, int points) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),
                "Name cannot be null or empty");
        Preconditions.checkArgument(movement >= 0,
                "Movement cannot be negative");
        Preconditions.checkArgument(weaponSkill >= 0 && weaponSkill <= 10,
                "Weapon skill must be between 0 and 10");
        Preconditions.checkArgument(ballisticSkill >= 0 && ballisticSkill <= 10,
                "Ballistic skill must be between 0 and 10");
        Preconditions.checkArgument(strength >= 0 && strength <= 10,
                "Strength must be between 0 and 10");
        Preconditions.checkArgument(toughness >= 0 && toughness <= 10,
                "Toughness must be between 0 and 10");
        Preconditions.checkArgument(wounds > 0,
                "Wounds must be at least 1");
        Preconditions.checkArgument(initiative >= 0 && initiative <= 10,
                "Initiative must be between 0 and 10");
        Preconditions.checkArgument(attack >= 0,
                "Attack must be at least 0");
        Preconditions.checkArgument(leadership >= 0 && leadership <= 10,
                "Leadership must be between 0 and 10");
        Preconditions.checkArgument(unitStrength > 0,
                "Unit strength must be at least 1");
        Preconditions.checkArgument(save <= 7,
                "Save must be maximum 7");
        Preconditions.checkArgument(points > 0,
                "Model must be worth at least 1 point");

        this.name = name;
        this.movement = movement;
        this.weaponSkill = weaponSkill;
        this.ballisticSkill = ballisticSkill;
        this.strength = strength;
        this.toughness = toughness;
        this.wounds = wounds;
        this.initiative = initiative;
        this.attack = attack;
        this.leadership = leadership;
        this.unitStrength = unitStrength;
        this.save = save;
        this.points = points;
    }

    public ModelImpl() {
    }

    private String name;
    private int movement;
    private int weaponSkill;
    private int ballisticSkill;
    private int strength;
    private int toughness;
    private int wounds;
    private int initiative;
    private int attack;
    private int leadership;

    private int unitStrength;
    private int save;
    private int points;
}
