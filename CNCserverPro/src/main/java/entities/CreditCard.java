package entities;

import javax.persistence.Entity;

@Entity
public class CreditCard {
	public long getCreditCardNum() {
		return 0;

	}

	public String getType() {
		String cType = null;

		final String cardNumber = " ";

		if (cardNumber.startsWith("4")) {
			cType = "Visa";
		} else if (cardNumber.startsWith("5")) {
			cType = "MasterCard";
		} else if (cardNumber.startsWith("6")) {
			cType = "Discover";
		} else if (cardNumber.startsWith("37")) {
			cType = "American Express";
		} else {
			cType = "Unknown type";
		}
		return cType;

	}

	public String getExpDate() {
		return null;

	}

	// ***************************isValid******************
	public static boolean isValid(long number) {
		return (getSize(number) >= 13 && getSize(number) <= 16)
				&& (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 6)
						|| prefixMatched(number, 37))
				&& ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
	}

	private static int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

		return sum;
	}

	// Return this number if it is a single digit, otherwise,
	// return the sum of the two digits
	private static int getDigit(int number) {
		if (number < 9)
			return number;
		return number / 10 + number % 10;
	}

	// Return sum of odd-place digits in number
	private static int sumOfOddPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	// Return true if the digit d is a prefix for number
	private static boolean prefixMatched(long number, int d) {
		return getPrefix(number, getSize(d)) == d;
	}

	// Return the number of digits in d
	private static int getSize(long d) {
		String num = d + "";
		return num.length();
	}

	// Return the first k number of digits from
	// number. If the number of digits in number
	// is less than k, return number.
	private static long getPrefix(long number, int k) {
		if (getSize(number) > k) {
			String num = number + "";
			return Long.parseLong(num.substring(0, k));
		}
		return number;
	}
}
