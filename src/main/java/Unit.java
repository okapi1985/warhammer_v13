import java.util.Map;
import java.util.TreeMap;

public interface Unit {

    Unit createUnit(Model model, int ranksAmount, int rankSize);
    void addModel(Unit unit);
    Model getModel(Unit unit);
    void printUnit(Unit unit);
    void removeModel(Unit unit);
    int size();
    int rankSize(Unit unit);
    int lastRankIndex(Unit unit);
    TreeMap<Integer, Map<Integer, Model>> getUnitMap();
    boolean isMusician();
    void setMusician(boolean musician);
    boolean isStandardBearer();
    void setStandardBearer(boolean standardBearer);
    boolean isWonLastFight();
    void setWonLastFight(boolean wonLastFight);
    int getUnitValue();
    void setUnitValue(int unitValue);
    int countTotalUnitStrength(Unit unit);
}
