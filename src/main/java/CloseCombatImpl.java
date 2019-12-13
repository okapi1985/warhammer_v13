import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CloseCombatImpl implements CloseCombat {

    private List<Unit> initRankingList = new ArrayList<>();
    private int attackerHits = 0;
    private int attackerWounds = 0;
    private int defendersFallen = 0;
    private int defenderHits = 0;
    private int defenderWounds = 0;
    private int attackersFallen = 0;


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
    public int attackersWoundsAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int strengthTest = modelAttackFirst.getStrength() - modelAttackLast.getToughness();

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
    public int defendersFallenAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int modifier = modelAttackFirst.getStrength() - 3;
        int save = modelAttackLast.getSave();

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
    public int defendersHitsAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int weaponSkillTest = modelAttackLast.getWeaponSkill() - modelAttackFirst.getWeaponSkill();
        int attacksAmount = modelAttackLast.getAttack();
        int attackersAmount = initRankingList.get(1).getUnitMap().get(0).size();

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
    }

    @Override
    public int defendersWoundsAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int strengthTest = modelAttackLast.getStrength() - modelAttackFirst.getToughness();

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
    public int attackersFallenAmount(List<Unit> initRankingList) {
        Model modelAttackFirst = initRankingList.get(0).getUnitMap().get(0).get(0);
        Model modelAttackLast = initRankingList.get(1).getUnitMap().get(0).get(0);
        int modifier = modelAttackLast.getStrength() - 3;
        int save = modelAttackFirst.getSave();

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
    public int unitCombatResult(List<Model> attackersFallen, List<Model> defendersFallen) {
        return 0;
    }

    @Override
    public void leadershipTest(Unit unit) {

    }

}
