

import com.google.common.base.Preconditions;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class UnitImpl implements Unit {

    public static Unit create(){
        return new UnitImpl();
    }

    @Override
    public Unit createUnit(Model model, int ranksAmount, int rankSize) {
        Preconditions.checkArgument(model != null, "Model must not be null");
        Preconditions.checkArgument(ranksAmount >= 1, "Unit must have at least one rank");
        Preconditions.checkArgument(rankSize >= 3, "Rank must have at least three models");
        TreeMap<Integer, Model> rank = new TreeMap<>();
        TreeMap<Integer, TreeMap<Integer, Model>> unitMap = new TreeMap<>();
        Unit unit = new UnitImpl(unitMap);
        for (int j = 0; j < ranksAmount; j++) {
            for (int i = 0; i < rankSize; i++) {
                rank.put(i, model);
            }
            unitMap.put(j, new TreeMap<>(rank));
        }
        return unit;
    }

    public void addModel(Unit unit) {
        Preconditions.checkArgument(unit != null, "Unit must not be null");
        int lastRankSize = unit.getUnitMap().get(lastRankIndex(unit)).size();
        int secondLastRankSize = unit.getUnitMap().get(lastRankIndex(unit)-1).size();
        TreeMap<Integer,Model> lastRank = unit.getUnitMap().get(lastRankIndex(unit));

        if (unit.getUnitMap().size() == 1 || (lastRankSize == secondLastRankSize)) {
            TreeMap<Integer, Model> newRank = new TreeMap<>();
            int firstEmptyInRank = newRank.size();
            int firstEmptyRank = unit.getUnitMap().size();
            newRank.put(firstEmptyInRank, getModel(unit));
            unit.getUnitMap().put(firstEmptyRank, newRank);
        }
        else
            lastRank.put(lastRank.size(),getModel(unit));
    }

    public Model getModel(Unit unit) {
        Preconditions.checkArgument(unit != null, "Unit must not be null");
        Model model = unit.getUnitMap().get(0).get(0);
        return model;
    }

    public void printUnit(Unit unit) {
        for (Map.Entry<Integer, TreeMap<Integer, Model>> entry: unit.getUnitMap().entrySet()){
            System.out.println(entry.getValue());
        }
    }

    public void removeModel(Unit unit, int amountToRemove) {
        Preconditions.checkArgument(unit != null, "Unit must not be null");

        for (int i = 0; i < amountToRemove; i++) {
            if (unit.getUnitMap().get(lastRankIndex(unit)).size() == 0) {
                unit.getUnitMap().pollLastEntry();
                if (unit.getUnitMap().size() > 0) {
                    unit.getUnitMap().get(lastRankIndex(unit) - 1).pollLastEntry();
                } else
                    System.out.println("Enemy unit has been killed off");
            } else {
                unit.getUnitMap().get(lastRankIndex(unit)).pollLastEntry();
                if (unit.getUnitMap().get(lastRankIndex(unit)).size() == 0) {
                    unit.getUnitMap().pollLastEntry();
                    if (unit.getUnitMap().size() == 0) {
                        System.out.println("Enemy unit has been killed off");
                    }
                }
            }
        }
    }

    public int size() {
        return unitMap.size();
    }

    @Override
    public int rankSize(Unit unit) {
        Preconditions.checkArgument(unitMap != null, "Unit map cannot be null");
        return unit.getUnitMap().get(0).size();
    }

    @Override
    public int lastRankIndex(Unit unit) {
        Preconditions.checkArgument(unitMap != null, "Unit map cannot be null");
        return unit.getUnitMap().size()-1;
    }

    public TreeMap<Integer, TreeMap<Integer, Model>> getUnitMap() {
        return unitMap;
    }

    public boolean isMusician() {
        return musician;
    }

    public void setMusician(boolean musician) {
        this.musician = musician;
    }

    public boolean isStandardBearer() {
        return standardBearer;
    }

    public void setStandardBearer(boolean standardBearer) {
        this.standardBearer = standardBearer;
    }

    public boolean isWonLastFight(){
        return wonLastFight;
    }

    public void setWonLastFight(boolean wonLastFight){
        this.wonLastFight = wonLastFight;
    }

    @Override
    public int getUnitValue() {
        unitValue = getUnitMap().size() * getUnitMap().get(0).size() * getUnitMap().get(0).get(0).getPoints();
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        Preconditions.checkArgument(unitValue > 0, "Unit value cannot be negative or zero");
        this.unitValue = unitValue;
    }

    @Override
    public int countTotalUnitStrength(Unit unit) {
        int totalUS = size() * rankSize(unit) * getModel(unit).getUnitStrength();
        return totalUS;
    }

    public static Comparator<Unit> UnitModelInit =
            Comparator.comparingInt(unit -> -unit.getUnitMap().get(0).get(0).getInitiative());

    private UnitImpl(){
    }

    private UnitImpl(boolean musician, boolean standardBearer, TreeMap<Integer, TreeMap<Integer, Model>> unitMap) {
        this.musician = musician;
        this.standardBearer = standardBearer;
        this.unitMap = unitMap;
    }

    public UnitImpl(boolean musician, boolean standardBearer, boolean wonLastFight, TreeMap<Integer, TreeMap<Integer, Model>> unitMap) {
        this.musician = musician;
        this.standardBearer = standardBearer;
        this.wonLastFight = wonLastFight;
        this.unitMap = unitMap;
    }

    public UnitImpl(int unitValue, boolean musician, boolean standardBearer, boolean wonLastFight, TreeMap<Integer, TreeMap<Integer, Model>> unitMap) {
        this.unitValue = unitValue;
        this.musician = musician;
        this.standardBearer = standardBearer;
        this.wonLastFight = wonLastFight;
        this.unitMap = unitMap;
    }

    private UnitImpl(TreeMap<Integer, TreeMap<Integer, Model>> unitMap) {
        this.unitMap = unitMap;
    }

    private int unitValue;
    private boolean musician;
    private boolean standardBearer;
    private boolean wonLastFight;
    private TreeMap<Integer, TreeMap<Integer, Model>> unitMap = new TreeMap<>();

}
