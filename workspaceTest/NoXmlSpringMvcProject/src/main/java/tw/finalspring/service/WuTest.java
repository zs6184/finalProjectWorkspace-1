package tw.finalspring.service;

import java.time.LocalDate;

public class WuTest {

	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		String nowDate = String.format("%tY%<tm%<td", now);
		String empId = "EMP" + nowDate;
		System.out.printf("%tY%<tm%<td\n", now);
		System.out.println(nowDate);
		System.out.println(empId);
		int i = 1;
		System.out.printf("%s%02d\n", empId, i);
		String subString = String.format("%s%02d", empId, i);

		System.out.println("----------");
		System.out.println(subString);
		System.out.println("----------");

		String nowDate1 = String.format("%td", now);

		System.out.println(nowDate1);

		String substring2 = subString.substring(9, 11);
		System.out.println("--------");
		System.out.println(substring2);

		System.out.println("測試");
		String db = "EMP2021101202".substring(9, 11);
		if (null != null) {
			if (nowDate1.equals(db)) {
				System.out.println("相同");
				System.out.println(db);
				System.out.println("EMP2021101202");
			} else {
				System.out.println("不同");
				System.out.printf(subString);
			}
		}else {
			System.out.println("null");
			System.out.printf(subString);
		}

	}

}
