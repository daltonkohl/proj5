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
		/*while((line = input.readLine()) != null){
			line += "\n";
			tokens = new StringTokenizer(line, "");
			String inputString = tokens.nextToken();
			String oldInput = inputString;
			loadAscii();**/
			//new Friday work 
		
			//need to figure out how to read entire file into one string
			String everything = input.readAllBytes();
			tokens = new StringTokenizer(line," ");
			String inputString = tokens.nextToken();
			String oldString = inputString;
			int putIndex = hashTable.numKeys;
			while(tokens.hasMoreTokens()){
				//while current string is in dictionary
				//	add next index to current string
				while(hashTable.get(inputString) != null)
				{
					oldString=inputString;
					inputString += tokens.nextToken();
				}
				//if current string is not in dictionary 
				//add previous string to output string
				//add current string to dictionary 
				outputString += oldString;
				hashTable.put(inputString, ((putIndex)+33));	
				
				//PREVIOUS TO FRIDAY WORK 	
				/*if(hashTable.get(getKey(inputString))  ==null){ //key is not in array
					outputString += getValue(inputString);
					hashTable.put(getKey(inputString), inputString);
					inputString = tokens.nextToken();  //MIGHT BE WRONG
				}
				else{ //key is in array
					output+= getValue(inputString);
					oldInput = inputString;
					inputString = tokens.nextToken();
					hashTable.put(oldInput+inputString,hashTable.numKeys+33);//(key,code)
				}**/
				//END OF PREVIOUS TO FRIDAY WORK 

				

	
		/*	HashTableChain<String, Integer> hash = new HashTableChain<>();
		hash.put("hello", 125);
		System.out.println(hash.get("hello"));*/
		}
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

	public static void loadAscii()
	{
		for(int i =32; i<128; i++)
		{
			hashTable.put(((String)(Character.toString((char)i))), i);
		}	
	}	
}



	
