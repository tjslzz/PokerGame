import java.util.*;

public class PokerGame {
    public List<Card> changeStringtoCard(String player1){
        Map<String, Integer> CharWithNumber = new HashMap<>();
        CharWithNumber.put("T",10);
        CharWithNumber.put("J",11);
        CharWithNumber.put("Q",12);
        CharWithNumber.put("K",13);
        CharWithNumber.put("A",14);
        List<Card> cardList = new ArrayList<>();
        String[] cards = player1.split(" ");
        for (String card:cards
             ) {
            int actualNumber = 0;
            String type=card.substring(1, 2);
            String number = card.substring(0, 1);
            if (CharWithNumber.get(number)!=null){
                actualNumber=CharWithNumber.get(number);
            }else{
                actualNumber = Integer.parseInt(number);
            }
            cardList.add(new Card(actualNumber,type));
        }
        Collections.sort(cardList, new Comparator<Card>() {

            @Override
            public int compare(Card c1, Card c2) {
                if (c1.getNumber() > c2.getNumber()) {
                    return 1;
                }
                if (c1.getNumber() == c2.getNumber()) {
                    return 0;
                }
                return -1;
            }
        });
        return cardList;
    }
    public Poker getRangeOfCardList(List<Card> cards){
        Map<Integer,Integer> NumberOfCard=new HashMap<>();
        int countOfPair=0;
        for (int i = 0; i < cards.size(); i++) {
            Integer integer = NumberOfCard.get(cards.get(i).getNumber());
            NumberOfCard.put(cards.get(i).getNumber(), integer == null?1:integer+1);
        }
        for (Integer value : NumberOfCard.values()) {
            if (value==2)
                countOfPair++;
        }

            Poker poker=new Poker(cards,countOfPair);
        return poker;
    }

    public String getWinner(String player1, String player2) {
        List<Card> cardlist1 = this.changeStringtoCard(player1);
        List<Card> cardlist2 = this.changeStringtoCard(player2);
        Poker poker1 = this.getRangeOfCardList(cardlist1);
        Poker poker2 = this.getRangeOfCardList(cardlist2);
        String result="draw";
        if(poker1.getRange()==poker2.getRange()){
        for (int i=4;i>=0;i--) {
            int card1Number=cardlist1.get(i).getNumber();
            int card2Number=cardlist2.get(i).getNumber();
            if (card1Number>card2Number){
                return "Winner:player1";
            } else if (card1Number<card2Number){
                return "Winner:player2";
            } else {
                continue;
            }
        }
        }else if(poker1.getRange()>poker2.getRange()){
            result="Winner:player1";
        }else{
            result="Winner:player2";
        }
        return result;
    }
}
