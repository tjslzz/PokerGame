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
    @Test
    public void should_return_draw_when_1H_3H_5S_6S_QD_VS_1H_3H_5S_6S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 3H 5S 6S QD";
        String player2 = "1H 3H 5S 6S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("draw",result);
    }
    @Test
    public void should_return_player1_when_1H_1H_5S_6S_QD_VS_1H_3H_5S_6S_KD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 1H 5S 6S QD";
        String player2 = "1H 3H 5S 6S KD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player1",result);
    }
    @Test
    public void should_return_player2_when_1H_1H_5S_6S_KD_VS_3H_3H_5S_6S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 1H 5S 6S KD";
        String player2 = "3H 3H 5S 6S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player2",result);
    }
    @Test
    public void should_return_player1_when_1H_1H_5S_5S_QD_VS_3H_3H_5S_6S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 1H 5S 5S QD";
        String player2 = "3H 3H 5S 6S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player1",result);
    }
    @Test
    public void should_return_player2_when_1H_1H_6S_6S_QD_VS_3H_3H_5S_5S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 1H 6S 6S QD";
        String player2 = "3H 3H 5S 5S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player1",result);
    }
    @Test
    public void should_return_player2_when_1H_1H_5S_5S_QD_VS_3H_3H_3S_6S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 1H 5S 5S QD";
        String player2 = "3H 3H 3S 6S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player2",result);
    }
    @Test
    public void should_return_player1_when_4H_4H_4S_5S_QD_VS_3H_3H_3S_6S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "4H 4H 4S 5S QD";
        String player2 = "3H 3H 3S 6S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player1",result);
    }
    @Test
    public void should_return_draw_when_4H_4H_4S_5S_QD_VS_4H_4H_4S_5S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "4H 4H 4S 5S QD";
        String player2 = "4H 4H 4S 5S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("draw",result);
    }
    @Test
    public void should_return_player2_when_3H_3H_3S_5S_KD_VS_4H_4H_4S_5S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "3H 3H 3S 5S KD";
        String player2 = "4H 4H 4S 5S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player2",result);
    }
    @Test
    public void should_return_player1_when_1H_2H_3S_4S_5D_VS_4H_4H_4S_5S_QD(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 2H 3S 4S 5D";
        String player2 = "4H 4H 4S 5S QD";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player1",result);
    }
    @Test
    public void should_return_player1_when_1H_2H_3S_4S_5D_VS_2H_3H_4S_5S_6D(){
        PokerGame pokerGame=new PokerGame();
        String player1 = "1H 2H 3S 4S 5D";
        String player2 = "2H 3H 4S 5S 6D";
        String result = pokerGame.getWinner(player1, player2);
        assertEquals("Winner:player2",result);
    }

}
