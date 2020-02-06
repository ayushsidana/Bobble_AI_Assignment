import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


 class Permutation{
    public static void main(String args[])throws Exception 
    {
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(args[0])); 
        // BufferedReader br = new BufferedReader(new FileReader("Input.csv"));
        String line = br.readLine();
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        while(line!=null){
            ArrayList<String> temp = new ArrayList<String>();
             String[] b = line.split(splitBy);
             for(int i=0;i<b.length;i++)
             {
                 temp.add(b[i]);
             }
             arr.add(temp);
             line = br.readLine();
        }   
        br.close();
        PrintPermutations(arr);
    }
    public static  void PrintPermutations( ArrayList<ArrayList<String>> arr)
    {
        int n = arr.size(); 
  
        int []indices = new int[n]; 
      
        for (int i = 0; i < n; i++) 
            indices[i] = 0; 
      
        while (true) { 
            // print current combination 
            for (int i = 0; i < n; i++) 
                System.out.print(arr.get(i).get(indices[i])) ; 
           
            // find the rightmost array that has more 
            // elements left after the current element in that array 
            int next = n - 1; 
            while (next >= 0 &&  
                  (indices[next] + 1 >= arr.get(next).size())) 
                next--; 

            // no such array is found so no more combinations left
            if (next < 0) 
                return; 
            System.out.print(", ");
      
            // if found move to next element in that array
            indices[next]++; 
      
        // for all arrays to the right of this  
        // array current index again points to first element
            for (int i = next + 1; i < n; i++) 
                indices[i] = 0; 
        } 
    }
}