package cn.tedu.cloudnote.aop;

public class test {
	public static void main(String[] args) {
		String s="1,s,2,j";
		String[]s1=s.split(",");
		for (int i = 0; i < s1.length; i++) {	
			if(!isNumeric(s1[i])){
			System.out.println(i+1);
			}
		}
	}
	public static boolean isNumeric(String s){
		String regex="[0-9]";
		if(s.matches(regex)){
			return true;
		}else{
			return false;
		}
	}
		
}
