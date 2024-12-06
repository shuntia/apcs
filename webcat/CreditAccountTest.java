
public class CreditAccountTest {

	CreditAccount a1;
	CreditAccount a2;
	RewardsCreditAccount a3;
	RewardsCreditAccount a4;

	public static void assertEquals(double expected, double actual, double delta) {
		if (Math.abs(expected - actual) > delta) {
			fail("Expected " + expected + " but got " + actual);
		}
	}

	public static void assertEquals(String expected, String actual) {
		if (!expected.equals(actual)) {
			fail("Expected " + expected + " but got " + actual);
		}
	}

	public static void assertEquals(int expected, int actual) {
		if (expected != actual) {
			fail("Expected " + expected + " but got " + actual);
		}
	}

	public static void fail(String message) {
		throw new RuntimeException(message);
	}

	public static void main(String[] args) {
		CreditAccountTest test = new CreditAccountTest();
		boolean allTestsPassed = true;

		try {
			test.setUp();
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("setUp failed: " + ex.getMessage());
		}

		try {
			test.testConstructorThrowsIllegalArgumentException();
			System.out.println("testConstructorThrowsIllegalArgumentException passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testConstructorThrowsIllegalArgumentException failed: " + ex.getMessage());
		}

		try {
			test.testCalculateMinimumMonthlyPayment();
			System.out.println("testCalculateMinimumMonthlyPayment passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testCalculateMinimumMonthlyPayment failed: " + ex.getMessage());
		}

		try {
			test.testHowManyMonthsUsingConstantPayment();
			System.out.println("testHowManyMonthsUsingConstantPayment passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testHowManyMonthsUsingConstantPayment failed: " + ex.getMessage());
		}

		try {
			test.testHowManyMonthUsingConstantPaymentEqualToBalance();
			System.out.println("testHowManyMonthUsingConstantPaymentEqualToBalance passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testHowManyMonthUsingConstantPaymentEqualToBalance failed: " + ex.getMessage());
		}

		try {
			test.testMakeCharge();
			System.out.println("testMakeCharge passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testMakeCharge failed: " + ex.getMessage());
		}

		try {
			test.testMakePayment();
			System.out.println("testMakePayment passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testMakePayment failed: " + ex.getMessage());
		}

		try {
			test.testToString();
			System.out.println("testToString passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testToString failed: " + ex.getMessage());
		}

		try {
			test.testGetBalance();
			System.out.println("testGetBalance passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testGetBalance failed: " + ex.getMessage());
		}

		try {
			test.testGetInterestRate();
			System.out.println("testGetInterestRate passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testGetInterestRate failed: " + ex.getMessage());
		}

		try {
			test.testSetInterestRate();
			System.out.println("testSetInterestRate passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testSetInterestRate failed: " + ex.getMessage());
		}

		try {
			test.testSetInterestRateIllegalArgumentException();
			System.out.println("testSetInterestRateIllegalArgumentException passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testSetInterestRateIllegalArgumentException failed: " + ex.getMessage());
		}

		try {
			test.testSetAccountHolder();
			System.out.println("testSetAccountHolder passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testSetAccountHolder failed: " + ex.getMessage());
		}

		try {
			test.testBothRewardsCreditAccountConstructors();
			System.out.println("testBothRewardsCreditAccountConstructors passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testBothRewardsCreditAccountConstructors failed: " + ex.getMessage());
		}

		try {
			test.testMakeChargeRewards();
			System.out.println("testMakeChargeRewards passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testMakeChargeRewards failed: " + ex.getMessage());
		}

		try {
			test.testMakeChargeRewardsBalance();
			System.out.println("testMakeChargeRewardsBalance passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testMakeChargeRewardsBalance failed: " + ex.getMessage());
		}

		try {
			test.testToStringRewards();
			System.out.println("testToStringRewards passed");
		} catch (Exception ex) {
			allTestsPassed = false;
			System.out.println("testToStringRewards failed: " + ex.getMessage());
		}

		if (allTestsPassed) {
			System.out.println("All tests passed.");
		} else {
			System.out.println("Some tests failed.");
		}
	}

	public void setUp() throws Exception {
		 a1 = new CreditAccount("Joy P. Sai-Chow", 1000, 0.12);
		 a2 = new CreditAccount("Ke$ha", 10000, 0.12);
	}
	
	public void testConstructorThrowsIllegalArgumentException() {
		try {
			CreditAccount impossibleAccount = new CreditAccount("neg", 1000, -0.25);
			fail("Constructor should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException expectedArgument) {
			
		}
	}

	public void testCalculateMinimumMonthlyPayment() {
		CreditAccount lowBalanceAccount = new CreditAccount("Mike", 12.35, 0.12);
		assertEquals(12.35, lowBalanceAccount.calculateMinimumMonthlyPayment(), 0);
		assertEquals(25.0, a1.calculateMinimumMonthlyPayment(),0);
		assertEquals(200.0, a2.calculateMinimumMonthlyPayment(),0);

	}

	public void testHowManyMonthsUsingConstantPayment() {
		assertEquals(58, a2.howManyMonthsUsingConstantPayment(230.0));
	}
	
	public void testHowManyMonthUsingConstantPaymentEqualToBalance() {
		assertEquals(2, a2.howManyMonthsUsingConstantPayment(a2.getBalance()));

	}

	public void testMakeCharge() {
		a2.makeCharge(9728.74);
		assertEquals(19728.74, a2.getBalance(),0.00000001);
	}

	public void testMakePayment() {
		System.out.println(a2.getBalance());
		a2.makePayment(9728.74);
		assertEquals(271.26, a2.getBalance(),0.00000001);
	}

	public void testToString() {
		 a2 = new CreditAccount("Ke$ha", 10000, 0.123);
		assertEquals("Ke$ha, $10000.00, 12.3%", a2.toString());
	}

	 
	public void testGetBalance() {
		assertEquals(10000, a2.getBalance(), 0);
	}


	 
	public void testGetInterestRate() {
		assertEquals(0.12, a2.getInterestRate(), 0);
	}

	 
	public void testSetInterestRate() {
		a1.setInterestRate(5.67);
		assertEquals(5.67, a1.getInterestRate(),0);
	}
	
	 
	public void testSetInterestRateIllegalArgumentException() {
		try {
			a1.setInterestRate(-0.15);
			fail("Should have thrown IllegalArgumentException in setInterestRate.");
		} catch (IllegalArgumentException expectedException) {
			
		}
	}


	 
	public void testSetAccountHolder() {
		a1.setAccountHolder("me");
		assertEquals("me", a1.getAccountHolder());
	}
	
	 
	public void testBothRewardsCreditAccountConstructors() {
		a3 = new RewardsCreditAccount("PsY", 1435.73, .24);
		a4 = new RewardsCreditAccount("Ziggy Top", 64.35, .12, 400);
	}
	
	 
	public void testMakeChargeRewards() {
		a3 = new RewardsCreditAccount("PsY", 1435.73, .24);
		a3.makeCharge(45);
		assertEquals(45, a3.getPoints());
		assertEquals(1480.73, a3.getBalance(), 0.01);
	}
	
	 
	public void testMakeChargeRewardsBalance() {
		a4 = new RewardsCreditAccount("Ziggy Top", 64.35, .12, 400);
		a4.makeCharge(49.99);
		assertEquals(449, a4.getPoints());
		assertEquals(114.35, a4.getBalance(), 0.01);
	}
	
	 
	public void testToStringRewards() {
		a4 = new RewardsCreditAccount("Ziggy Top?##", 64.3, .123, 432);
		assertEquals("Ziggy Top?##, $64.30, 12.3%, 432 points", a4.toString()	);
	}

}
