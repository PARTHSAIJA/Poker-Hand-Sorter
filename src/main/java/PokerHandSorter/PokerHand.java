/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHandSorter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 *
 * @author parth
 */
public class PokerHand {
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public PokerHand(String pokerHand) {
        StringTokenizer stringTokenizer = new StringTokenizer(pokerHand, " ");
        cards = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            String card = stringTokenizer.nextToken();
            String val = card.substring(0, 1);
            Integer value = 0;
            if (val.equalsIgnoreCase("T")) {
                value = 10;
            } else if (val.equalsIgnoreCase("J")) {
                value = 11;
            } else if (val.equalsIgnoreCase("Q")) {
                value = 12;
            } else if (val.equalsIgnoreCase("K")) {
                value = 13;
            } else if (val.equalsIgnoreCase("A")) {
                value = 14;
            } else {
                value = Integer.parseInt(val);
            }
            cards.add(new Card(value, card.substring(1, 2).charAt(0)));
        }
    }

    public Integer getRank() {
        cards = cards.stream().sorted().collect(Collectors.toList());

        int pairIndex = -1;

        Integer rank = 1; //High Card

        
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue())) {
                pairIndex = i;
                rank = 2;//Pair
                i = 4;
            }
        }

        
        if (rank == 2) {
            for (int i = pairIndex + 2; i < 4; i++) {
                if (cards.get(i).getValue().equals(cards.get(i + 1).getValue())) {
                    rank = 3;//Two pairs
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue()) &&
                    cards.get(i + 1).getValue().equals(cards.get(i + 2).getValue())) {
                rank = 4;//Three of a kind
                if (i == 0 && cards.get(3).getValue().equals(cards.get(4).getValue()) ||
                        i == 2 && cards.get(0).getValue().equals(cards.get(1).getValue())) {
                    rank = 7;//Full house
                }
            }
        }

        for (int i = 0; i < 2; i++)
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue()) &&
                    cards.get(i + 1).getValue().equals(cards.get(i + 2).getValue()) &&
                    cards.get(i + 2).getValue().equals(cards.get(i + 3).getValue())) {
                rank = 8;//Four of a kind
            }

        if (rank == 1)
            if ((cards.get(4).getValue() - cards.get(0).getValue() == 4) ||
                    (cards.get(3).getValue() - cards.get(0).getValue() == 3 &&
                            cards.get(4).getValue() == 14 && cards.get(0).getValue() == 2)) {
                rank = 5;//Straight
            }

        boolean flush;
        if (rank == 1 || rank == 5) {
            flush = true;
            for (int i = 0; i < 4; i++)
                if (cards.get(i).getSuit() != cards.get(i + 1).getSuit()) {
                    flush = false;
                }
            if (flush && rank == 5)
                rank = 9;//Straight flush
            else if (flush)
                rank = 6;//Flush
        }
        
        if (rank == 9 && cards.get(4).getValue() == 14 && cards.get(0).getValue() == 10) {
            rank = 10;//Royal Flush
        }

        return rank;
    }
}
