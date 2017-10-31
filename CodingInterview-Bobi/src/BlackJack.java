import java.io.IOException;
import java.util.ArrayList;

public class BlackJack {
	public static void main(String[] args) {
//		BlackJackGame.simulate();
		String[] cards = {"A", "5"};
		System.out.println(getBestScore(cards));
	}

	/**
	 * Given a list of cards, return the value that closest to 21.
	 * Note:
	 * 1)J, Q, K is 10.
	 * 2)A is either 1 or 11.
	 *
	 */
	static int getBestScore(String[] cards) {
		if (cards == null) {
			return 0;
		}

		int total = 0, A_count = 0;
		for (String c : cards) {
			if (!c.equals("A")) {
				total += getValue(c);
			} else {
				A_count++;
			}
		}
		for (int i=0; i<A_count; i++) {
			int remain = A_count - 1 - i;
			if (total + 11 + remain <= 21) {
				total += 11;
			} else {
				total += 1;
			}
		}
		return total;
	}

	private static int getValue(String card) {
		if (card.equals("A")) {
			throw new IllegalArgumentException("A is not illeagal");
		}

		if (card.equals("J") || card.equals("Q") || card.equals("K")) {
			return 10;
		} else {
			return Integer.valueOf(card); //could be 10
		}
	}
}

enum Suit {
	Club(0), Diamond(1), Heart(2), Spade(3);
	private int value;

	Suit(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}
}

abstract class Card {
	boolean isAvailable = true;
	int faceValue;
	Suit suit;

	Card (int value, Suit suit) {
		faceValue = value;
		this.suit = suit;
	}

	abstract int value();
	abstract Suit suit();
}

class BlackJackCard extends Card {

	BlackJackCard(int value, Suit suit) {
		super(value, suit);
	}

	int value() {
		if (faceValue >= 11 && faceValue <= 13) {
			return 10;
		} else {
			return faceValue;
		}
	}

	Suit suit() {
		return suit;
	}
}

class Deck <T extends Card> {
	ArrayList<T> cards;

	void setDeck(ArrayList<T> cards) {
		this.cards = cards;
	}

	T dealCard() {
		int remainCount = cards.size();
		if (remainCount > 0) {
			return cards.remove(remainCount - 1);
		} else {
			return null;
		}
	}

	int getRemainingCount() {
		return cards.size();
	}
}

class Hand <T extends Card> {
	ArrayList<T> cards = new ArrayList<>();

	int getScore() {
		int score = 0;
		for (T card : cards) {
			score += card.value();
		}
		return score;
	}

	void addCard(T card) {
		cards.add(card);
	}
}

class BlackJackHand extends Hand<BlackJackCard> {
	private static final int max = 21;
	int score;
	String name;

	BlackJackHand(String name) {
		this.name = name;
	}

	void addCard(BlackJackCard card) {
		super.addCard(card);
		score += card.value();
	}

	boolean isBusted() {
		return score > max;
	}

	boolean is21() {
		return score == max;
	}
}

class BlackJackGame {
	private ArrayList<BlackJackHand> hands;
	private Deck<BlackJackCard> deck;

	BlackJackGame() {
		hands = new ArrayList<>();
		deck = new Deck<BlackJackCard>();
		ArrayList<BlackJackCard> cards = new ArrayList<>();
		for (int i = 1; i < 13; i++) {
			for (Suit s : Suit.values()) {
				BlackJackCard card = new BlackJackCard(i, s);
				cards.add(card);
			}
		}
		deck.setDeck(cards);
	}

	void addPlayer(BlackJackHand hand) {
		hands.add(hand);
	}

	void start() {
		ArrayList<BlackJackHand> activePlayer = new ArrayList<>(hands);
		while (activePlayer.size() > 0 && deck.getRemainingCount() > 0) {
			int i = activePlayer.size() - 1;
			for (; i >= 0 ; i--) {
				BlackJackHand hand = activePlayer.get(i);
				if (!hand.isBusted()) {
					if (Math.random() > 0.5) {
						System.out.println("Hand " + hand.name + " stopped");
						activePlayer.remove(i);
					} else {
						hand.addCard(deck.dealCard());
						if (hand.isBusted()) {
							System.out.println("Hand " + hand.name + " busted");
							activePlayer.remove(i);
						}
					}
				}
			}
		}
		checkWinner();
	}

	void checkWinner() {
		int maxIndex = -1;
		for (int i=0; i < hands.size(); i++) {
			BlackJackHand hand = hands.get(i);
			if (!hand.isBusted() && ((maxIndex == -1) || (hand.score > hands.get(maxIndex).score))) {
				maxIndex = i;
			}
		}

		if (maxIndex == -1) {
			System.out.println("No one win");
		} else {
			System.out.println("Hand " + hands.get(maxIndex).name + " win");
		}
	}

	static void simulate() {
		BlackJackGame game = new BlackJackGame();
		game.addPlayer(new BlackJackHand("Tom"));
		game.addPlayer(new BlackJackHand("Harry"));
		game.addPlayer(new BlackJackHand("Peter"));
		game.addPlayer(new BlackJackHand("Jack"));
		game.start();
	}
}