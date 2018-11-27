package puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestClass {

	private static List<InputStructure> inputs = new ArrayList<InputStructure>();
	public static void main(String args[] ) throws Exception {
	 
/**
	        //BufferedReader
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String name = br.readLine();                // Reading input from STDIN
	        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT*/

	        //Scanner
	        Scanner s = new Scanner(System.in);
	        TestClass ez = new TestClass();
	        int testCases = s.nextInt();                 // Reading input from STDIN
	        for(int i=1;i<=testCases;i++){
	        	int arraySize = s.nextInt();
	        	s.nextLine();
	        	String numbers = s.nextLine();
	        	String repeatedNumber = s.nextInt() + "";
	        	inputs.add(ez.new InputStructure(arraySize, numbers, repeatedNumber));
	        }
	        try{
	        new TestClass().replaceAndReplaceOccurrences();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	
	    }
	 
	 public void replaceAndReplaceOccurrences(){
		 
		 for(InputStructure input:inputs){
			 
			 int arraySize = input.getArraySize();
			 String[] listOfIntegers = input.getNumber().split(" ");
			 String repeatedNumber =  input.getRepeatedNumber();
			 input.getNumber().toString();
			 
			 
			 List<String> sortedNumbers = new LinkedList<String>();
			 List<String> repeatedNumbersList = new LinkedList<String>();
			 int count = 0;
			 for(int j=0;j<arraySize;j++){
				 if(listOfIntegers[j].equalsIgnoreCase(repeatedNumber)){
					 listOfIntegers[j] = "1";
					 sortedNumbers.add(count,listOfIntegers[j]);
					 count ++;
					 repeatedNumbersList.spliterator();
				 }
			 }
			 repeatedNumbersList.addAll(sortedNumbers);
			 printResult(repeatedNumbersList);
		 }
		 
	 }
	 
	 public void printResult(List<String> sortedList){
			for (String number : sortedList) {
				System.out.print(number + " ");
			}
		 System.out.println();
	 }
	 
	 class InputStructure{
		 private int arraySize;
		 private String number;
		 private String repeatedNumber;
		 
		 public InputStructure(int arraySize, String number,
				String repeatedNumber) {
			this.arraySize = arraySize;
			this.number = number;
			this.repeatedNumber = repeatedNumber;
		}

		public int getArraySize() {
			return arraySize;
		}

		public void setArraySize(int arraySize) {
			this.arraySize = arraySize;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getRepeatedNumber() {
			return repeatedNumber;
		}

		public void setRepeatedNumber(String repeatedNumber) {
			this.repeatedNumber = repeatedNumber;
		}

	 }

}
