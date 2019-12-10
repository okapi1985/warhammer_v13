public interface Model {

    String getName();
    int getMovement();
    int getWeaponSkill();
    int getBallisticSkill();
    int getStrength();
    int getToughness();
    int getWounds();
    int getInitiative();
    int getAttack();
    int getLeadership();
    int getUnitStrength();
    int getSave();
    int getPoints();

    void setName(String name);
    void setMovement(int movement);
    void setWeaponSkill(int weaponSkill);
    void setBallisticSkill(int ballisticSkill);
    void setStrength(int strength);
    void setToughness(int toughness);
    void setWounds(int wounds);
    void setInitiative(int initiative);
    void setAttack(int attack);
    void setLeadership(int leadership);
    void setUnitStrength(int unitStrength);
    void setSave(int save);
    void setPoints(int points);
}
