package Report6;


public class Friend {
	String name;
	String group;
	String phone_number;
	String e_mail;
	String profile;
	
	public Friend() {
	}
	
	public void print() {
		String info = name+":"+group+":"+phone_number+":"+e_mail+":"+profile;
		System.out.println(info);
	}
}
