package test;

public class StringExp {

	public static void main(String[] args) {

		System.out.println(check("[]]{{{{"));
	}

	/*
	 * public static boolean testString(){ boolean result= false; Pattern
	 * p=Pattern.compile("[][{()}]"); Matcher m=p.matcher("[}]{");
	 * result=m.find();
	 * 
	 * return result;
	 * 
	 * 
	 * }
	 */
	public static boolean check(String str) {
		int p = 0, q = 0, r = 0;
		boolean result = false;

		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (a == '[') {
				p++;
			} else if (a == ']') {
				if (p == 0) {
					return result;
				}
				p--;
			} else if (a == '{') {
				q++;
			} else if (a == '}') {
				if (q == 0) {
					return result;
				}
				q--;
			} else if (a == '(') {
				r++;
			} else if (a == ')') {
				if (r == 0) {
					return result;
				}
				r--;
			}

		}
		if(p>1 || q >1 || r >1)
			return false;
		return true;

	}
	
	public void others(String str){
		
	}

}
