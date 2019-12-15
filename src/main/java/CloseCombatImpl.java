import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CloseCombatImpl implements CloseCombat {

    private List<Unit> initRankingList = new ArrayList<>();

    public static CloseCombat create(){
        return new CloseCombatImpl();
    }

    public CloseCombatImpl() {
    }

    @Override
    public List<Unit> initiativeTest(Unit unit1, Unit unit2) {
        initRankingList.add(unit1);
        initRankingList.add(unit2);
        Collections.sort(initRankingList,Collections.reverseOrder());
        return initRankingList;
    }

    @Override
    public int attackersHitsAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int weaponSkillTest = modelAttackFirst.getWeaponSkill() - modelAttackLast.getWeaponSkill();
        int attacksAmount = modelAttackFirst.getAttack();
        int attackersAmount = initRankingList.get(0).getUnitMap().get(0).size();
        int attackerHits = 0;

        for (int i = 0; i < attackersAmount; i++) {
            for (int j = 0; j < attacksAmount; j++) {
                if ((weaponSkillTest >= 1 && DiceD6.rollDice() >= 3) ||
                        ((weaponSkillTest < 1 && (modelAttackLast.getWeaponSkill() / modelAttackFirst.getWeaponSkill()) < 2) && DiceD6.rollDice() >= 4) ||
                        (modelAttackLast.getWeaponSkill() / modelAttackFirst.getWeaponSkill()) >= 2 && DiceD6.rollDice() >= 5) {
                    System.out.println(modelAttackFirst.getName() + " trafił " + modelAttackLast.getName());
                    attackerHits += 1;
                } else
                    System.out.println(modelAttackFirst.getName() + " nie trafił " + modelAttackLast.getName());
            }
        }
        return attackerHits;
    }

    @Override
    public int attackersWoundsAmount(List<Unit> initRankingList, int attackerHits) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int strengthTest = modelAttackFirst.getStrength() - modelAttackLast.getToughness();
        int attackerWounds = 0;

        while (attackerHits > 0) {
            if ((strengthTest >= 2 && DiceD6.rollDice() >= 2) ||
                    (strengthTest == 1 && DiceD6.rollDice() >= 3) ||
                    (strengthTest == 0 && DiceD6.rollDice() >= 4) ||
                    (strengthTest == -1 && DiceD6.rollDice() >= 5) ||
                    (strengthTest == -2 && DiceD6.rollDice() >= 6) ||
                    ((strengthTest == -3 && DiceD6.rollDice() == 6) && DiceD6.rollDice() >= 4) ||
                    ((strengthTest == -4 && DiceD6.rollDice() == 6) && DiceD6.rollDice() >= 5)) {
                System.out.println(modelAttackFirst.getName() + " ranił " + modelAttackLast.getName());
                attackerWounds += 1;
            } else
                System.out.println(modelAttackFirst.getName() + " nie zranił " + modelAttackLast.getName());
            attackerHits--;
        }
        return attackerWounds;
    }

    @Override
    public int defendersFallenAmount(List<Unit> initRankingList, int attackerWounds) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int modifier = modelAttackFirst.getStrength() - 3;
        int save = modelAttackLast.getSave();
        int defendersFallen = 0;

        if (modifier < 0) {
            modifier = 0;
        }

        while (attackerWounds > 0) {
            if (modelAttackLast.getSave() > 6) {
                defendersFallen += 1;
                if (modelAttackLast.getWounds() == 1) {
                    System.out.println(modelAttackLast.getName() + " poległ");
                } else {
                    modelAttackLast.setWounds(modelAttackLast.getWounds() - 1);
                    System.out.println(modelAttackLast.getName() + " otrzymał ranę");
                }
            } else if (save + modifier <= DiceD6.rollDice()) {
                System.out.println(modelAttackLast.getName() + " obronił się");
            } else {
                defendersFallen += 1;
                if (modelAttackLast.getWounds() == 1) {
                    System.out.println(modelAttackLast.getName() + " poległ");
                } else {
                    modelAttackLast.setWounds(modelAttackLast.getWounds() - 1);
                    System.out.println(modelAttackLast.getName() + " otrzymał ranę");
                }
            }
            attackerWounds--;
        }
        return defendersFallen;
    }

    @Override
    public int defendersHitsAmount(List<Unit> initRankingList, int defendersFallen) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int weaponSkillTest = modelAttackLast.getWeaponSkill() - modelAttackFirst.getWeaponSkill();
        int attacksAmount = modelAttackLast.getAttack();
        int attackersAmount = initRankingList.get(1).getUnitMap().get(0).size() - defendersFallen;
        int defenderHits = 0;

        if(attackersAmount > 0) {
            for (int i = 0; i < attackersAmount; i++) {
                for (int j = 0; j < attacksAmount; j++) {
                    if ((weaponSkillTest >= 1 && DiceD6.rollDice() >= 3) ||
                            ((weaponSkillTest < 1 && (modelAttackFirst.getWeaponSkill() / modelAttackLast.getWeaponSkill()) < 2) && DiceD6.rollDice() >= 4) ||
                            (modelAttackFirst.getWeaponSkill() / modelAttackLast.getWeaponSkill()) >= 2 && DiceD6.rollDice() >= 5) {
                        System.out.println(modelAttackLast.getName() + " trafił " + modelAttackFirst.getName());
                        defenderHits += 1;
                    } else
                        System.out.println(modelAttackLast.getName() + " nie trafił " + modelAttackFirst.getName());
                }
            }
            return defenderHits;
        } else
            return 0;
    }

    @Override
    public int defendersWoundsAmount(List<Unit> initRankingList, int defenderHits) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int strengthTest = modelAttackLast.getStrength() - modelAttackFirst.getToughness();
        int defenderWounds = 0;

        while (defenderHits > 0) {
            if ((strengthTest >= 2 && DiceD6.rollDice() >= 2) ||
                    (strengthTest == 1 && DiceD6.rollDice() >= 3) ||
                    (strengthTest == 0 && DiceD6.rollDice() >= 4) ||
                    (strengthTest == -1 && DiceD6.rollDice() >= 5) ||
                    (strengthTest == -2 && DiceD6.rollDice() >= 6) ||
                    ((strengthTest == -3 && DiceD6.rollDice() == 6) && DiceD6.rollDice() >= 4) ||
                    ((strengthTest == -4 && DiceD6.rollDice() == 6) && DiceD6.rollDice() >= 5)) {
                System.out.println(modelAttackLast.getName() + " ranił " + modelAttackFirst.getName());
                defenderWounds += 1;
            } else
                System.out.println(modelAttackLast.getName() + " nie zranił " + modelAttackFirst.getName());
            defenderHits--;
        }
        return defenderWounds;
    }

    @Override
    public int attackersFallenAmount(List<Unit> initRankingList, int defenderWounds) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int modifier = modelAttackLast.getStrength() - 3;
        int save = modelAttackFirst.getSave();
        int attackersFallen = 0;

        if (modifier < 0) {
            modifier = 0;
        }

        while (defenderWounds > 0) {
            if (modelAttackFirst.getSave() > 6) {
                attackersFallen += 1;
                if (modelAttackFirst.getWounds() == 1) {
                    System.out.println(modelAttackFirst.getName() + " poległ");
                } else {
                    modelAttackFirst.setWounds(modelAttackFirst.getWounds() - 1);
                    System.out.println(modelAttackFirst.getName() + " otrzymał ranę");
                }
            } else if (save + modifier <= DiceD6.rollDice()) {
                System.out.println(modelAttackFirst.getName() + " obronił się");
            } else {
                attackersFallen += 1;
                if (modelAttackFirst.getWounds() == 1) {
                    System.out.println(modelAttackFirst.getName() + " poległ");
                } else {
                    modelAttackFirst.setWounds(modelAttackFirst.getWounds() - 1);
                    System.out.println(modelAttackFirst.getName() + " otrzymał ranę");
                }
            }
            defenderWounds--;
        }
        return attackersFallen;
    }

    @Override
    public int unitCombatResult(Unit unit1, Unit unit2, int attackersFallen, int defendersFallen) {
        int unit1Points = 0;
        int unit2Points = 0;
        int unit1Us = 0;
        int unit2Us = 0;
        int ldModifier;

        for (int i=0; i < unit1.getUnitMap().size(); i++){
            unit1Us += unit1.getUnitMap().get(i).size();
        }
        for (int i=0; i < unit2.getUnitMap().size(); i++){
            unit2Us += unit2.getUnitMap().get(i).size();
        }

        if (unit1Us > unit2Us) {
            unit1Points += 1;
        } else if (unit1Us < unit2Us) {
            unit2Points += 1;
        }

        if (unit1.getUnitMap().size() > 1 && unit1.getUnitMap().size() < 5){
            for (int i=0; i < 3; i++){
                if (unit1.getUnitMap().get(i+1).size() >= 5){
                    //rzuca wyjątek bo próbuje pobrać nieistniejący index. Trzeba tu zmienić implementację
                    unit1Points += 1;
                }
            }
        } else if (unit1.getUnitMap().size() >= 5 && unit1.getUnitMap().get(3).size() >= 5){
            unit1Points += 3;
        }
        if (unit2.getUnitMap().size() > 1 && unit2.getUnitMap().size() < 5){
            for (int i=0; i < 3; i++){
                if (unit2.getUnitMap().get(i+1).size() >= 5){
                    unit2Points += 1;
                }
            }
        } else if (unit2.getUnitMap().size() >= 5 && unit2.getUnitMap().get(3).size() >= 5){
            unit2Points += 3;
        }

        if (unit1.isStandardBearer()){
            unit1Points += 1;
        }
        if (unit2.isStandardBearer()){
            unit2Points += 1;
        }

        int total1 = unit1Points + defendersFallen;
        int total2 = unit2Points + attackersFallen;

        ldModifier = total1 - total2;

        return Math.abs(ldModifier);
    }

    @Override
    public void leadershipTest(Unit unit) {

    }

}
