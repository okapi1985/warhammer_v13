import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ModelImplTest {

    @BeforeEach
    public void before(){
        model = ModelImpl.create();
    }

    @Test
    public void createRegularStatistics(){
        model = new ModelImpl("Clanrat",5,3,3,3,3,
                1,5,1,5,1,5,5);
        assertThat(model.getName()).isEqualTo("Clanrat");
        assertThat(model.getMovement()).isGreaterThanOrEqualTo(0);
        assertThat(model.getWeaponSkill()).isBetween(0,10);
        assertThat(model.getBallisticSkill()).isBetween(0,10);
        assertThat(model.getStrength()).isBetween(0,10);
        assertThat(model.getToughness()).isBetween(0,10);
        assertThat(model.getWounds()).isGreaterThan(0);
        assertThat(model.getInitiative()).isBetween(0,10);
        assertThat(model.getAttack()).isGreaterThanOrEqualTo(1);
        assertThat(model.getLeadership()).isBetween(0,10);
        assertThat(model.getUnitStrength()).isGreaterThan(0);
        assertThat(model.getSave()).isLessThanOrEqualTo(7);
        assertThat(model.getPoints()).isGreaterThan(0);
    }

    @Test
    public void createBorderStatistics(){
        model = new ModelImpl("Some model",5,10,0,3,3,
                1,5,11,5,1,7,5);
        assertThat(model.getWeaponSkill()).isBetween(0,10);
        assertThat(model.getBallisticSkill()).isBetween(0,10);
        assertThat(model.getAttack()).isGreaterThan(0);
        assertThat(model.getSave()).isLessThanOrEqualTo(7);
    }

    @Test
    public void createExceededStatistics(){
        assertThrows(IllegalArgumentException.class, () -> model.setMovement(-1));
        assertThrows(IllegalArgumentException.class, () -> model.setWounds(0));
        assertThrows(IllegalArgumentException.class, () -> model.setSave(8));
    }

    private @Nonnull Model model;
}