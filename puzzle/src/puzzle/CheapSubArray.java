package puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CheapSubArray {

	private static Long number;
	private static List<Input> indicies = new ArrayList<Input>();
	private static List<Integer> result = new ArrayList();

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		// System.out.println("Enter an integer");
		try {
			number = Long.parseLong(reader.readLine());
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println("Questions ");

		for (int i = 0; i < number; i++) {
			Long arraySize = null;
			try {
				arraySize = Long.parseLong(reader.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("Enter the indices");
			String readString = "";
			try {
				readString = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(readString);
			indicies.add(new Input(arraySize, readString));
		}
		try {
			findCostOfArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void findCostOfArray(){
		
	    for(int i = 0; i< number;i++){
	    	Input input = indicies.get(i);
	    	String[] array = input.getArray().split(" ");
	    	int min = Integer.parseInt(array[0]);
	    	int max = Integer.parseInt(array[1]);
	    	int cost = min + max;
	    	for(int j = 0;j<array.length-1;j++){
	    		int	newCost = Integer.parseInt(array[j]) + Integer.parseInt(array[j+1]);
	    		cost = newCost < cost ? newCost : cost;
	    	    
	    	}
	    	result.add(cost);
	    }
	    for (int minMax : result) {
	    	 System.out.println(minMax);
		}
	   
		
	}
}

class Input {

	private Long arraySize;
	private String array;

	public Input(Long arraySize, String array) {
		this.arraySize = arraySize;
		this.array = array;
	}

	public Long getArraySize() {
		return arraySize;
	}

	public void setArraySize(Long arraySize) {
		this.arraySize = arraySize;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

}
