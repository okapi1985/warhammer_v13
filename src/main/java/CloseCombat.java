import java.util.List;

public interface CloseCombat {

    List<Unit> initiativeTest(Unit unit1, Unit unit2);
    int attackersHitsAmount(List<Unit> initRankingList);
    int attackersWoundsAmount(List<Unit> initRankingList);
    int defendersFallenAmount(List<Unit> initRankingList);
    int defendersHitsAmount(List<Unit> initRankingList);
    int defendersWoundsAmount(List<Unit> initRankingList);
    int attackersFallenAmount(List<Unit> initRankingList);
    int unitCombatResult(List<Model> attackersFallen, List<Model> defendersFallen);
    void leadershipTest(Unit unit);
}
