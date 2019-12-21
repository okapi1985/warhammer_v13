import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClansmanTest {

    @Test
    public void whenHeavyArmourShieldAndHandWeaponArmed() {
        Clansman clansman = new Clansman("Clansman", 3, 4, 3, 3, 4,
                1, 2, 1, 9, 1, 7, 7,
                false, false, false, false, false);
        clansman.setHandWeapon(true);
        clansman.setShield(true);
        clansman.setHeavyArmour(true);

        assertThat(clansman.getSave()).isEqualTo(3);
        assertThat(clansman.getPoints()).isEqualTo(9);
    }

    @Test
    public void whenGreatWeaponArmed() {
        Clansman clansman = new Clansman("Clansman", 3, 4, 3, 3, 4,
                1, 2, 1, 9, 1, 7, 7,
                false, false, false, false, false);
        clansman.setGreatWeapon(true);

        assertThat(clansman.getStrength()).isEqualTo(5);
        assertThat(clansman.getInitiative()).isEqualTo(0);
    }

}