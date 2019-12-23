import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UnitImplTest {

    @BeforeEach
    public void before(){
        unit = UnitImpl.create();
        model = mock(Model.class);
    }

    @Test
    public void setRankAmountOver(){
        unit = unit.createUnit(model,2,4);
        assertThat(unit.getUnitMap().size()).isEqualTo(2);
    }

    @Test
    public void setRankSizeOver(){
        unit = unit.createUnit(model,2,4);
        assertThat(unit.getUnitMap().get(0).size()).isEqualTo(4);
    }

    @Test
    public void setRankAmountEqual(){
        unit = unit.createUnit(model,1,4);
        assertThat(unit.getUnitMap().size()).isEqualTo(1);
    }

    @Test
    public void setRankSizeEqual(){
        unit = unit.createUnit(model,2,3);
        assertThat(unit.getUnitMap().get(0).size()).isEqualTo(3);
    }

    @Test
    public void setRankAmountBelow(){
        assertThrows(IllegalArgumentException.class, () -> unit.createUnit(model,0,4));
    }

    @Test
    public void setRankSizeBelow(){
        assertThrows(IllegalArgumentException.class, () -> unit.createUnit(model,2,1));
    }

    @Test
    public void setModelNull(){
        assertThrows(IllegalArgumentException.class, () -> unit.createUnit(null,5,6));
    }

    @Test
    public void checkGettingModel(){
        unit = unit.createUnit(model,2,4);
        assertThat(unit.getModel(unit)).isEqualTo(model);
    }

    @Test
    public void checkLastRankIndex(){
        unit = unit.createUnit(model,2,4);
        assertThat(unit.lastRankIndex(unit)).isEqualTo(1);
    }

    @Test
    public void checkSecondNewModelInNewRank(){
        unit = unit.createUnit(model,2,4);
        unit.addModel(unit);
        unit.addModel(unit);
        unit.addModel(unit);
        assertThat(unit.getUnitMap().get(2).get(1)).isEqualTo(model);
    }

    @Test
    public void checkNewModelInNewRank(){
        unit = unit.createUnit(model,2,4);
        unit.addModel(unit);
        unit.addModel(unit);
        assertThat(unit.getUnitMap().get(2).get(0)).isEqualTo(model);
    }

    @Test
    public void removeFromLastRank(){
        unit = unit.createUnit(model,2,4);
        int amountToRemove = 1;
        unit.removeModel(unit,amountToRemove);
        assertThat(unit.getUnitMap().get(1).get(3)).isNull();
    }

    @Test
    public void removeFromSecondLastRank(){
        unit = unit.createUnit(model,2,4);
        int amountToRemove = 5;
        unit.removeModel(unit,amountToRemove);
        assertThat(unit.getUnitMap().get(0).get(3)).isNull();
    }

    @Test
    public void removeFromOneRankUnitEqual(){
        unit = unit.createUnit(model,1,4);
        int amountToRemove = 4;
        unit.removeModel(unit,amountToRemove);
        assertThat(unit.getUnitMap().size()).isZero();
    }

    @Test
    public void removeFromOneRankUnitTooMany(){
        unit = unit.createUnit(model,1,4);
        int amountToRemove = 5;
        assertThrows(NullPointerException.class, () -> unit.removeModel(unit,amountToRemove));
    }

    @Test
    public void setMusicianTest() {
        unit.setMusician(true);
        assertTrue(unit.isMusician());
    }

    @Test
    public void setStandardBearerTest() {
        unit.setStandardBearer(true);
        assertTrue(unit.isStandardBearer());
    }

    @Test
    public void countUnitValue(){
        model = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,5);
        unit = unit.createUnit(model,4,5);

        assertThat(unit.getUnitValue()).isEqualTo(100);
    }

    @Test
    public void countTotalUnitStrength(){
        model = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,5);
        unit = unit.createUnit(model,4,5);

        assertThat(unit.countTotalUnitStrength(unit)).isEqualTo(20);
    }

    @Test
    public void countClanratUnitValue(){
        model = new Clanrat("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,5,
                false,false,false,false);
        ((Clanrat) model).setSpear(true);
        unit = unit.createUnit(model,4,5);

        assertThat(unit.getUnitValue()).isEqualTo(120);
    }


    private Unit unit;
    private @Nonnull Model model;

}