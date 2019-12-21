import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClanratTest {

    @BeforeEach
    public void before(){

    }

    @Test
    public void saveWhenShieldAndHandWeaponArmed(){
        Clanrat clanrat = new Clanrat("Clanrat",5,3,3,3,3,
                1,4,1,5,1,7,5,
                true,false,false,false);
        clanrat.setShield(true);

        assertThat(clanrat.getSave()).isEqualTo(5);
    }

    @Test
    public void saveWhenShieldArmed(){
        Clanrat clanrat = new Clanrat("Clanrat",5,3,3,3,3,
                1,4,1,5,1,7,5,
                false,false,false,true);
        clanrat.setShield(true);

        assertThat(clanrat.getSave()).isEqualTo(6);
    }

    @Test
    public void saveWhenLightArmourShieldAndHandWeaponArmed(){
        Clanrat clanrat = new Clanrat("Clanrat",5,3,3,3,3,
                1,4,1,5,1,7,5,
                false,false,false,true);
        clanrat.setHandWeapon(true);
        clanrat.setShield(true);
        clanrat.setLightArmour(true);

        assertThat(clanrat.getSave()).isEqualTo(4);
    }

}