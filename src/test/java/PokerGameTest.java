import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokerGameTest {
    @Test
    public void should_return_player2_when_1H_3H_5S_6S_QD_VS_1H_3H_5S_JS_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 3H 5S 6S QD";
        String player2 = "1H 3H 5S JS QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player2",result);


    }
}
