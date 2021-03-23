package gamelogic;

import java.util.Random;

/**
 * Class to generate random sets of digits
 * @author gokul
 *
 */
public class NumberGenerator {
	/**
	 * Generate a number with given no of digits
	 * @param noofdigits
	 * @return
	 */
	public static String generatenumber(int noofdigits) {
		String digitstring = "";
		for(int i=0;i<noofdigits;i++) {
			Random r = new Random();
			int num = r.nextInt(9);
			digitstring = digitstring + num;
		}
		return digitstring;
	}
}
