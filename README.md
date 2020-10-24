# Poker-Hand-Sorter

It is a program that takes input, via STDIN, a "stream" of hands for a two-player
poker game. At the completion of the stream, the program prints to STDOUT the number of
hands won by Player 1, and the number of hands won by Player 2.

### Input

Each line read via STDIN will be a set of 10 cards. Each card is represented by 2 characters - the value
and the suit. The first 5 cards in the line have been dealt to Player 1, the last 5 cards in the line
belong to Player 2.


## Usage

```java
Go to the libs folder using windows power shell
..\..\Poker-Hand-Sorter\build\libs>
Write the following command: cat ..\..\poker-hands.txt | java -jar .\PokerHandSorter-1.0.0.jar
Output:
Player 1: 263 hands
Player 2: 237 hands

To give manual input, stay in the libs folder
..\..\Poker-Hand-Sorter\build\libs>
Write the following command: java -jar .\PokerHandSorter-1.0.0.jar
Give it an input of cards: 2H KD 6C TC TD 5H 4D 3H 2C AS
Output:
Player 1: 0 hands
Player 2: 1 hands
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update the tests as appropriate.

