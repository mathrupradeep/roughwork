package puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DivisibleBy7 {
	
	private static String number;
	private static int question;
	private static List<String> indicies = new ArrayList();
	
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	  //  System.out.println("Enter an integer");
	    number = reader.readLine();
	   // System.out.println("Questions ");
	 
	    question = Integer.parseInt(reader.readLine());
	    for(int i = 0; i< question;i++){
	    	//System.out.println("Enter the indices");
	    	String readString = reader.readLine();
	    	//System.out.println(readString);
	    	indicies.add(readString);
	    }
		try{
	    determineDivisibility();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static  void determineDivisibility(){
		
		for(int i=0;i<indicies.size();i++){
			String[] array = indicies.get(i).split(" ");
			Long l = Long.parseLong(array[0])-1;	
			Long r = Long.parseLong(array[1])-1 ;
			Long num = Long.parseLong(number.substring(l.intValue(), r.intValue())+ number.charAt(r.intValue())) ;
			//System.out.println("Number before division:"+num);
			num = num%7;
			//System.out.println("Number after diving by 7:"+num);
			if(num == 0){
				System.out.println("YES");
			}
			else{
				System.out.println("NO");
			}
		}
	}

}
