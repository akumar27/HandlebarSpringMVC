/**
 * 
 */
package com.voya.helper;

import java.io.IOException;
import java.math.BigDecimal;

import com.github.jknack.handlebars.Options;

/**
 * @author i707259
 *
 */
public class CustomHelper {
	private double epsilon = 0.000001d;

	public CustomHelper() {
	}

	public CharSequence if_eq(Object number1, Object number2, Options options)
			throws IOException {
		Double val1 = toDouble(number1);
		Double val2 = toDouble(number2);
		boolean cmp = (val1 != null) && (val2 != null)
				&& (compare(val1, val2) == 0);
		return options.isFalsy(cmp) ? options.inverse() : options.fn();
	}

	protected int compare(Double val1, Double val2) {
		return (Math.abs(val1 / val2 - 1) < epsilon) ? 0 : val1.compareTo(val2);
	}

	protected Double toDouble(Object obj) {
		Double dbl = null;
		if (obj instanceof Double)
			dbl = (Double) obj;
		if (obj instanceof Integer)
			dbl = new Double((Integer) obj);
		if (obj instanceof Long)
			dbl = new Double((Long) obj);
		if (obj instanceof BigDecimal)
			dbl = ((BigDecimal) obj).doubleValue();
		if (obj instanceof Float)
			dbl = new Double((Float) obj);
		if (obj instanceof String) {
			String str = (String) obj;
			if (str.matches("[0-9]*\\.?[0-9]+"))
				dbl = new Double(str);
		}
		System.out.println("Object: " + obj);
		if (obj != null)
			System.out.println("Double value for " + obj.getClass().getName()
					+ " : " + dbl);
		return dbl;
	}
}
