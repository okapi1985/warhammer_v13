import java.util.List;
import java.util.Map;

public interface CloseCombat {

    List<Unit> initiativeTest(Unit unit1, Unit unit2);
    int attackersHitsAmount(List<Unit> initRankingList);
    int attackersWoundsAmount(List<Unit> initRankingList, int attackerHits);
    int defendersFallenAmount(List<Unit> initRankingList, int attackerWounds);
    int defendersHitsAmount(List<Unit> initRankingList, int defendersFallen);
    int defendersWoundsAmount(List<Unit> initRankingList, int defenderHits);
    int attackersFallenAmount(List<Unit> initRankingList, int defenderWounds);
    Map<Unit, Integer> unitCombatResult(Unit unit1, Unit unit2, int attackersFallen, int defendersFallen);
    void leadershipTest(Unit unit);
}
