import javafx.scene.control.TextFormatter;

import java.util.*;

public class PokerGame {
    public List<Card> changeStringtoCard(String player1) {
        Map<String, Integer> CharWithNumber = new HashMap<>();
        CharWithNumber.put("T", 10);
        CharWithNumber.put("J", 11);
        CharWithNumber.put("Q", 12);
        CharWithNumber.put("K", 13);
        CharWithNumber.put("A", 14);
        List<Card> cardList = new ArrayList<>();
        String[] cards = player1.split(" ");
        for (String card : cards
        ) {
            int actualNumber = 0;
            String type = card.substring(1, 2);
            String number = card.substring(0, 1);
            if (CharWithNumber.get(number) != null) {
                actualNumber = CharWithNumber.get(number);
            } else {
                actualNumber = Integer.parseInt(number);
            }
            cardList.add(new Card(actualNumber, type));
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

    public Poker changeRangeOfSequence(Poker poker) {
        boolean isSequence = true;
        List<Card> cards = poker.getCardList();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getNumber() != cards.get(i + 1).getNumber() - 1)
                isSequence = false;
        }
        if (isSequence == true) poker.setRange(4);
        return poker;
    }

    public Poker changeRangeOfFlush(Poker poker) {
        boolean isFlush = true;
        List<Card> cards = poker.getCardList();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (!cards.get(i).getType().equals(cards.get(i + 1).getType())) {
                isFlush = false;
            }
        }
        if (isFlush == true) poker.setRange(5);
        return poker;
    }

    public Poker getRangeOfCardList(List<Card> cards) {
        Map<Integer, Integer> numberOfCard = new TreeMap<Integer, Integer>();
        int range = 0;
        boolean containsTwoEqual = false;
        boolean containsThreeEqual = false;
        for (int i = 0; i < cards.size(); i++) {
            Integer integer = numberOfCard.get(cards.get(i).getNumber());
            numberOfCard.put(cards.get(i).getNumber(), integer == null ? 1 : integer + 1);
        }
        for (Map.Entry<Integer, Integer> entry : numberOfCard.entrySet()) {
            switch (entry.getValue()) {
                case 2:
                    range++;
                    containsTwoEqual = true;
                    break;
                case 3:
                    range = 3;
                    containsThreeEqual = true;
                    break;
                case 4:
                    range=7;
                default:
                    break;
            }
        }
        numberOfCard = ((TreeMap) numberOfCard).descendingMap();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(numberOfCard.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Poker poker = new Poker(cards, range, list);
        poker = this.changeRangeOfSequence(poker);
        poker = this.changeRangeOfFlush(poker);
        if (containsTwoEqual && containsThreeEqual) poker.setRange(6);
        return poker;
    }

    public String getWinner(String player1, String player2) {
        List<Card> cardlist1 = this.changeStringtoCard(player1);
        List<Card> cardlist2 = this.changeStringtoCard(player2);
        Poker poker1 = this.getRangeOfCardList(cardlist1);
        Poker poker2 = this.getRangeOfCardList(cardlist2);
        String result = "draw";
        if (poker1.getRange() == poker2.getRange()) {
            List<Map.Entry<Integer, Integer>> numberOfCard1 = poker1.getNumberOfCard();
            List<Map.Entry<Integer, Integer>> numberOfCard2 = poker2.getNumberOfCard();
            for (int i = 0; i < numberOfCard1.size(); i++) {
                if (numberOfCard1.get(i).getKey() > numberOfCard2.get(i).getKey()) {
                    return "Winner:player1";
                } else if (numberOfCard1.get(i).getKey() < numberOfCard2.get(i).getKey()) {
                    return "Winner:player2";
                }
            }

        } else if (poker1.getRange() > poker2.getRange()) {
            result = "Winner:player1";
        } else {
            result = "Winner:player2";
        }
        return result;
    }
}
