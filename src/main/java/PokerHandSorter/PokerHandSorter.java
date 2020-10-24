/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHandSorter;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author parth
 */
public class PokerHandSorter {
    
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(System.in, Charset.defaultCharset()))) {
                    String inputCards;
                    List<Players> players = new ArrayList<>();
                    while ((inputCards = bufferReader.readLine()) != null) {
                        if(!inputCards.isEmpty()) {
                            PokerHand playerOneHand = new PokerHand(inputCards.substring(0, 14));
                            PokerHand playerTwoHand = new PokerHand(inputCards.substring(15));
                            players.add(new Turn(playerOneHand, playerTwoHand).getPlayers());
                        } else {
                            break;
                        }
                    }
                    Long playerOneWins = players.stream().filter(winner -> winner == Players.Player1).count();
                    Long playerTwoWins = players.stream().filter(winner -> winner == Players.Player2).count();

                    System.out.println(String.format("Player 1: %d hands" , playerOneWins));
                    System.out.println(String.format("Player 2: %d hands" , playerTwoWins));
                }
    }
    
}

