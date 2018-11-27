package puzzle;

import java.util.Scanner;



class TestClass2 {
    public static void main(String args[] ) throws Exception {
  
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" "); // Reading input from STDIN
        int a3 = Integer.parseInt(input[0]);
        int a4 = Integer.parseInt(input[1]);
        
        int a1 = a4-a3;
        int a2 = a3-a1;
        System.out.println(a1 + " "+a2);
    }
}
