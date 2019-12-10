import java.util.List;

public interface CloseCombat {

    List<Unit> initiativeTest(Unit unit1, Unit unit2);
    List<Model> hitTest(List<Unit> initRankingList);
    List<Model> woundTest(List<Unit> initRankingList);
    List<Model> saveTest(List<Unit> initRankingList);
    int unitCombatResult(List<Model> attackersFallen, List<Model> defendersFallen);
    void leadershipTest(Unit unit);
}
