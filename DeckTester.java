public class DeckTester {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		System.out.println("***Deck before shuffle***");
		System.out.println(testDeck);
		
		testDeck.shuffle();
		
		System.out.println("***Deck after shuffle***");
		System.out.println(testDeck);
	}
}
