package test;

public class StringCalculator {
	public int add(String text) {
		int sum=0;
		boolean shim = text.contains(",");
		boolean col = text.contains(":");
		
		// "" 일때
		if (text.equals("")) { sum = 0; }
		// 숫자 1개일때
		if (!shim && !col) {
			sum = Integer.parseInt(text);
		}
		// "," 로 구분할때
		if (shim) {
			String[] number = text.split(",");
			for(int i=0 ; i < number.length ; i++) {
				sum += Integer.parseInt(number[i]);
			}
		}
		// ":" 로 구분할때
		if (col) {
			String[] number = text.split(":");
			for(int i=0 ; i < number.length ; i++) {
				sum += Integer.parseInt(number[i]);
			}
		}
				
		return sum;
	}
}
