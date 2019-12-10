import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiceD6Test {

    private DiceD6 diceD6;

    @Test
    public void d6RollTest(){

        assertThat(DiceD6.rollDice()).isBetween(1,6);
        assertThat(DiceD6.rollDice()).isBetween(1,6);
        assertThat(DiceD6.rollDice()).isBetween(1,6);
        assertThat(DiceD6.rollDice()).isBetween(1,6);
        assertThat(DiceD6.rollDice()).isBetween(1,6);
        assertThat(DiceD6.rollDice()).isBetween(1,6);
    }

}