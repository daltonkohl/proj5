import java.util.*;
import java.io.*;

public class Compress{
	public static void main(String[] args){
		String inputFile = args[0];
		HashTableChain<String, String> hashTable = new HashTableChain<>();


		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		String line;
		StringTokenizer tokens;
		String outputString;
		while((line = input.readLine()) != null){
			line += "\n";
			tokens = new StringTokenizer(line, "");
			String inputString = tokens.nextToken();
			String oldInput = inputString;
			while(tokens.hasMoreTokens()){
				if(hashTable.get(getKey(inputString)) == null){ //key is not in array
					outputString += getKey(inputString);
					hashTable.put(getKey(inputString), inputString);
					inputString = nextToken();  //MIGHT BE WRONG
				}
				else{ //key is in array
					oldInput = inputString;
					inputString += tokens.nextToken();
				}

	
		/*	HashTableChain<String, Integer> hash = new HashTableChain<>();
		hash.put("hello", 125);
		System.out.println(hash.get("hello"));*/
	}
	public static String getKey(String value){
		String outputKey = "";
		for(int i = 0; i<value.length(); i++){
			int output =  value.charAt(i);
			String keyValue = String.valueOf(output);
			outputKey += keyValue;
		}
		return outputKey;
	}
}



	
