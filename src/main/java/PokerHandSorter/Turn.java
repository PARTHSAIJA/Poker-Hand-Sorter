/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHandSorter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author parth
 */
public class Turn {
    private final PokerHand playerOneHand;
    private final PokerHand playerTwoHand;

    public Turn(PokerHand playerOneHand, PokerHand playerTwoHand) {
        this.playerOneHand = playerOneHand;
        this.playerTwoHand = playerTwoHand;
    }
    
    public Players getPlayers() {
        int p1Sum = playerOneHand.getCards().stream().map(Card::getValue).reduce(0, (v1, v2) -> v1 + v2);
        int p2Sum = playerTwoHand.getCards().stream().map(Card::getValue).reduce(0, (v1, v2) -> v1 + v2);
        if (playerOneHand.getRank() > playerTwoHand.getRank()) {
            return Players.Player1;
        } else if (playerOneHand.getRank() < playerTwoHand.getRank()) {
            return Players.Player2;
        } else {
            if (playerOneHand.getRank() == 10 && playerTwoHand.getRank() == 10) {//Tie
                return Players.Tie;
            } else if (playerOneHand.getRank() == 9 && playerTwoHand.getRank() == 9) {//To compare
                return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
            } else if (playerOneHand.getRank() == 8 && playerTwoHand.getRank() == 8) {
                if (playerOneHand.getCards().get(0) == playerOneHand.getCards().get(3)) {
                    p1Sum = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue();
                } else {
                    p1Sum = playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                }

                if (playerTwoHand.getCards().get(0) == playerTwoHand.getCards().get(3)) {
                    p2Sum = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue();
                } else {
                    p2Sum = playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                }
                return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
            } else if (playerOneHand.getRank() == 7 && playerTwoHand.getRank() == 7) { 
                int p1Pair = 0;
                int p2Pair = 0;
                int p1Triple = 0;
                int p2Triple = 0;
                for (int i = 0; i < 3; i++) {
                    if (playerOneHand.getCards().get(i).getValue().equals(playerOneHand.getCards().get(i + 1).getValue()) &&
                            playerOneHand.getCards().get(i + 1).getValue().equals(playerOneHand.getCards().get(i + 2).getValue())) {
                        if (i == 0 && playerOneHand.getCards().get(3).getValue().equals(playerOneHand.getCards().get(4).getValue())) {
                            p1Pair = playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                            p1Triple = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue();
                        }
                        if (i == 2 && playerOneHand.getCards().get(0).getValue().equals(playerOneHand.getCards().get(1).getValue())) {
                            p1Pair = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue();
                            p1Triple = playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                        }
                    }
                    if (playerTwoHand.getCards().get(i).getValue().equals(playerTwoHand.getCards().get(i + 1).getValue()) &&
                            playerTwoHand.getCards().get(i + 1).getValue().equals(playerTwoHand.getCards().get(i + 2).getValue())) {
                        if (i == 0 && playerTwoHand.getCards().get(3).getValue().equals(playerTwoHand.getCards().get(4).getValue())) {
                            p2Pair = playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                            p2Triple = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue();
                        }
                        if (i == 2 && playerTwoHand.getCards().get(0).getValue().equals(playerTwoHand.getCards().get(1).getValue())) {
                            p2Pair = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue();
                            p2Triple = playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                        }
                    }
                }
                if(p1Triple == p2Triple){
                    return p1Pair > p2Pair ? Players.Player1 : Players.Player2;
                }
                return p1Triple > p2Triple ? Players.Player1 : Players.Player2;
            } else if (playerOneHand.getRank() == 6 && playerTwoHand.getRank() == 6) {
                return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
            } else if (playerOneHand.getRank() == 5 && playerTwoHand.getRank() == 5) {
                return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
            } else if (playerOneHand.getRank() == 4 && playerTwoHand.getRank() == 4) {
                int[] p1Remaining = new int[1];
                int[] p2Remaining = new int[1];
                if (playerOneHand.getCards().get(0).getValue() == (playerOneHand.getCards().get(2).getValue())) {
                    p1Sum = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue();
                    p1Remaining = new int[]{playerOneHand.getCards().get(3).getValue() , playerOneHand.getCards().get(4).getValue()};
                } else if (playerOneHand.getCards().get(1).getValue() == (playerOneHand.getCards().get(3).getValue())) {
                    p1Sum = playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue();
                    p1Remaining = new int[]{playerOneHand.getCards().get(0).getValue() , playerOneHand.getCards().get(4).getValue()};
                } else if (playerOneHand.getCards().get(2).getValue() == (playerOneHand.getCards().get(4)).getValue()) {
                    p1Sum = playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                    p1Remaining = new int[]{playerOneHand.getCards().get(0).getValue() , playerOneHand.getCards().get(1).getValue()};
                }

                if (playerTwoHand.getCards().get(0).getValue() == (playerTwoHand.getCards().get(2).getValue())) {
                    p2Sum = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue();
                    p2Remaining = new int[]{playerTwoHand.getCards().get(3).getValue() , playerTwoHand.getCards().get(4).getValue()};
                } else if (playerTwoHand.getCards().get(1).getValue() == (playerTwoHand.getCards().get(3).getValue())) {
                    p2Sum = playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue();
                    p2Remaining = new int[]{playerTwoHand.getCards().get(0).getValue() , playerTwoHand.getCards().get(4).getValue()};
                } else if (playerTwoHand.getCards().get(2).getValue() == (playerTwoHand.getCards().get(4).getValue())) {
                    p2Sum = playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                    p2Remaining = new int[]{playerTwoHand.getCards().get(0).getValue() , playerTwoHand.getCards().get(1).getValue()};
                }
                if(p1Sum == p2Sum){
                    int p1RemainingMax = Arrays.stream(p1Remaining).max().getAsInt();
                    int p1RemainingMin = Arrays.stream(p1Remaining).min().getAsInt();
                    int p2RemainingMax = Arrays.stream(p2Remaining).max().getAsInt();
                    int p2RemainingMin = Arrays.stream(p2Remaining).min().getAsInt();
                    if(p1RemainingMax == p2RemainingMax){
                        return p1RemainingMin > p2RemainingMin ? Players.Player1: Players.Player2;
                    } else {
                        return p1RemainingMax > p2RemainingMax ? Players.Player1: Players.Player2;
                    }
                } else {
                    return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
                }
            } else if (playerOneHand.getRank() == 3 && playerTwoHand.getRank() == 3) {
                int p1Remaining = 0, p1Pair1 = 0, p1Pair2 = 0;
                int p2Remaining = 0,p2Pair1 = 0, p2Pair2= 0;
                if (playerOneHand.getCards().get(0).getValue().equals(playerOneHand.getCards().get(1).getValue()) &&
                        playerOneHand.getCards().get(2).getValue().equals(playerOneHand.getCards().get(3).getValue())) {
                    p1Pair1 = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue();
                    p1Pair2 = playerOneHand.getCards().get(2).getValue() + playerOneHand.getCards().get(3).getValue();
                    p1Remaining = playerOneHand.getCards().get(4).getValue();
                } else if (playerOneHand.getCards().get(0).getValue().equals(playerOneHand.getCards().get(1).getValue()) &&
                        playerOneHand.getCards().get(3).getValue().equals(playerOneHand.getCards().get(4).getValue())) {
                    p1Pair1 = playerOneHand.getCards().get(0).getValue() + playerOneHand.getCards().get(1).getValue();
                    p1Pair2 = playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                    p1Remaining = playerOneHand.getCards().get(2).getValue();
                } else if (playerOneHand.getCards().get(1).getValue().equals(playerOneHand.getCards().get(2).getValue()) &&
                        playerOneHand.getCards().get(3).getValue().equals(playerOneHand.getCards().get(4).getValue())) {
                    p1Pair1 = playerOneHand.getCards().get(1).getValue() + playerOneHand.getCards().get(2).getValue() ;
                    p1Pair2 = playerOneHand.getCards().get(3).getValue() + playerOneHand.getCards().get(4).getValue();
                    p1Remaining = playerOneHand.getCards().get(0).getValue();
                }

                if (playerTwoHand.getCards().get(0).getValue().equals(playerTwoHand.getCards().get(1).getValue()) &&
                        playerTwoHand.getCards().get(2).getValue().equals(playerTwoHand.getCards().get(3).getValue())) {
                    p2Pair1 = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue() ;
                    p2Pair2 = playerTwoHand.getCards().get(2).getValue() + playerTwoHand.getCards().get(3).getValue();
                    p2Remaining = playerTwoHand.getCards().get(4).getValue();
                } else if (playerTwoHand.getCards().get(0).getValue().equals(playerTwoHand.getCards().get(1).getValue()) &&
                        playerTwoHand.getCards().get(3).getValue().equals(playerTwoHand.getCards().get(4).getValue())) {
                    p2Pair1 = playerTwoHand.getCards().get(0).getValue() + playerTwoHand.getCards().get(1).getValue() ;
                    p2Pair2 = playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                    p2Remaining = playerTwoHand.getCards().get(2).getValue();
                } else if (playerTwoHand.getCards().get(1).getValue().equals(playerTwoHand.getCards().get(2).getValue()) &&
                        playerTwoHand.getCards().get(3).getValue().equals(playerTwoHand.getCards().get(4).getValue())) {
                    p2Pair1 = playerTwoHand.getCards().get(1).getValue() + playerTwoHand.getCards().get(2).getValue() ;
                    p2Pair2 = playerTwoHand.getCards().get(3).getValue() + playerTwoHand.getCards().get(4).getValue();
                    p2Remaining = playerTwoHand.getCards().get(0).getValue();
                }
                if(p1Pair1 == p2Pair1 && p1Pair2 == p2Pair2){
                    return p1Remaining > p2Remaining ? Players.Player1 : Players.Player2;
                } else if(p1Pair1 == p2Pair1 && p1Pair2 != p2Pair2) {
                    return p1Pair2 > p2Pair2 ? Players.Player1 : Players.Player2;
                } else if(p1Pair1 != p2Pair1 && p1Pair2 == p2Pair2){
                    return p1Pair1 > p2Pair1 ? Players.Player1 : Players.Player2;
                } else if(p1Pair1 != p2Pair1 && p1Pair2 != p2Pair2){
                    return p1Pair1 > p2Pair1 ? Players.Player1 : Players.Player2;
                }
            } else if (playerOneHand.getRank() == 2 && playerTwoHand.getRank() == 2) {
                int pairIndex = -1;
                for (int i = 0; i < 4; i++) {
                    if (playerOneHand.getCards().get(i).getValue().equals(playerOneHand.getCards().get(i + 1).getValue())) {
                        pairIndex = i;
                        i = 4;
                    }
                }
                p1Sum = playerOneHand.getCards().get(pairIndex).getValue() + playerOneHand.getCards().get(pairIndex+1).getValue();
                playerOneHand.getCards().remove(pairIndex);
                playerOneHand.getCards().remove(pairIndex);
                List<Card> p1Remaining = playerOneHand.getCards().stream().sorted().collect(Collectors.toList());
                for (int i = 0; i < 4; i++) {
                    if (playerTwoHand.getCards().get(i).getValue().equals(playerTwoHand.getCards().get(i + 1).getValue())) {
                        pairIndex = i;
                        i = 4;
                    }
                }
                p2Sum = playerTwoHand.getCards().get(pairIndex).getValue() + playerTwoHand.getCards().get(pairIndex+1).getValue();
                playerTwoHand.getCards().remove(pairIndex);
                playerTwoHand.getCards().remove(pairIndex);
                List<Card> p2Remaining = playerTwoHand.getCards().stream().sorted().collect(Collectors.toList());
                if(p1Sum == p2Sum){
                    for(int i = 0 ; i < 3 ; i++){
                        if(p1Remaining.get(i).getValue() != p2Remaining.get(i).getValue()){
                            return p1Remaining.get(i).getValue() > p2Remaining.get(i).getValue() ? Players.Player1 : Players.Player2;
                        }
                    }
                } else {
                    return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
                }
            } else {
                return p1Sum > p2Sum ? Players.Player1 : Players.Player2;
            }
        }
        return Players.Tie;
    }
    
}