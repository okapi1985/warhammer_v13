import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CloseCombatImplTest {

    @BeforeEach
    public void before(){
        closeCombat = CloseCombatImpl.create();
        unit = UnitImpl.create();
        Model model1 = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,1);
        Model model2 = new ModelImpl("Clansman",3,4,3,3,4,
                1,2,1,9,1,4,1);
        unit1 = unit.createUnit(model1,4,4);
        unit2 = unit.createUnit(model2,4,4);
        initRankingList = closeCombat.initiativeTest(unit1,unit2);


    }

    @Test
    public void testOfInitiative(){
        assertThat(initRankingList.get(0)).isEqualTo(unit1);
        //Co jeśli będzie równa inicjatywa?
    }

    @Test
    public void testOfAttackerHit(){
        int attackersAmount = initRankingList.get(0).getUnitMap().get(0).size();
        int modelAttackAmount = initRankingList.get(0).getUnitMap().get(0).get(0).getAttack();
        int attempsAmount = attackersAmount*modelAttackAmount;
        int attackerHits = closeCombat.attackersHitsAmount(initRankingList);
        assertThat(attackerHits).isBetween(0,attempsAmount);
    }

    @Test
    public void testOfAttackerWound(){
        int attackerHits = 3;
        int attackerWounds = closeCombat.attackersWoundsAmount(initRankingList,attackerHits);
        assertThat(attackerHits).isBetween(0,attackerHits);
    }

    @Test
    public void testOfDefenderSave(){
        int attackerWounds = 2;
        int defendersFallen = closeCombat.defendersFallenAmount(initRankingList,attackerWounds);
        assertThat(defendersFallen).isBetween(0,attackerWounds);
    }

    @Test
    public void testOfDefenderHit(){
        int defendersFallen = 2;
        int defendersAmount = initRankingList.get(1).getUnitMap().get(0).size() - defendersFallen;
        int modelAttackAmount = initRankingList.get(1).getUnitMap().get(0).get(0).getAttack();
        int attempsAmount = defendersAmount*modelAttackAmount;
        int defenderHits = closeCombat.defendersHitsAmount(initRankingList,defendersFallen);
        assertThat(defenderHits).isBetween(0,attempsAmount);
    }

    @Test
    public void testOfDefenderWound(){
        int defenderHits = 3;
        int defenderWounds = closeCombat.defendersWoundsAmount(initRankingList,defenderHits);
        assertThat(defenderWounds).isBetween(0,defenderHits);
    }

    @Test
    public void testOfAttackerSave(){
        int defenderWounds = 1;
        int attackersFallen = closeCombat.attackersFallenAmount(initRankingList,defenderWounds);
        assertThat(attackersFallen).isBetween(0,defenderWounds);
    }

    @Test
    public void calculateResultDifferentUnitSize(){
        Model model3 = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,5);
        Model model4 = new ModelImpl("Clansman",3,4,3,3,4,
                1,2,1,9,1,4,7);
        Unit unit3 = unit.createUnit(model3,3,5);
        Unit unit4 = unit.createUnit(model4,2,5);
        int defendersFallen = 1;
        int attackersFallen = 2;

        assertThat(closeCombat.unitCombatResult(unit3,unit4,attackersFallen,defendersFallen)).isEqualTo(1);
    }

    @Test
    public void combatResultSameUnitSize(){
        int attackersFallen = 2;
        int defendersFallen = 1;
        unit1.setStandardBearer(true);
        unit2.setStandardBearer(true);

        assertThat(closeCombat.unitCombatResult(unit1,unit2,attackersFallen,defendersFallen)).isEqualTo(1);
    }


    private CloseCombat closeCombat;
    private Unit unit;
    private Unit unit1;
    private Unit unit2;
    List<Unit> initRankingList = new ArrayList<>();

}