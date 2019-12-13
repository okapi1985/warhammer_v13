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
        model1 = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,1);
        model2 = new ModelImpl("Clansman",3,4,3,3,4,
                1,2,1,9,1,6,1);
        unit1 = unit.createUnit(model1,4,4);
        unit2 = unit.createUnit(model2,4,4);
        initRankingList = closeCombat.initiativeTest(unit1,unit2);


    }

    @Test
    public void testOfInitiativeTest(){
        assertThat(initRankingList.get(0)).isEqualTo(unit1);
        //Co jeśli będzie równa inicjatywa?
    }

    @Test
    public void testOfAttackerHitTest(){
        int attackersAmount = initRankingList.get(0).getUnitMap().get(0).size();
        int modelAttackAmount = initRankingList.get(0).getUnitMap().get(0).get(0).getAttack();
        int attempsAmount = attackersAmount*modelAttackAmount;
        int attackerHits = closeCombat.attackersHitsAmount(initRankingList);
        assertThat(attackerHits).isBetween(0,attempsAmount);
    }

//    @Test
//    public void testOfAttackerWoundTest(){
//        System.out.println();
//        List<Model> listOfAttackersHit = new ArrayList<>(Arrays.asList(model1,model1,model1));
//        List<Model> listOfAttackersWound = closeCombat.attackerWoundTest(initRankingList);
//        assertThat(listOfAttackersWound.size()).isBetween(1,3);
//    }

    private CloseCombat closeCombat;
    private Unit unit;
    private Model model1;
    private Model model2;
    private Unit unit1;
    private Unit unit2;
    List<Unit> initRankingList = new ArrayList<>();

}