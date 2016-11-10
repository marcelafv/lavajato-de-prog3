package principal;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coligacao pps = new Coligacao("PPS / PROS");
		Coligacao pros = new Coligacao("PPS / PROS");
		if(pps.equals(pros)) System.out.println("SIM");
		else System.out.println("NAO");
	}

}
