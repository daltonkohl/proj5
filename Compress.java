import java.util.*;
import java.io.*;

public class Compress{
	public static void main(String[] args){
	try{
		String inputFile = args[0];
		HashTableChain<String, String> hashTable = new HashTableChain<>();


		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		String line;
		StringTokenizer tokens;
		String outputString="";
		/*while((line = input.readLine()) != null){
			line += "\n";
			tokens = new StringTokenizer(line, "");
			String inputString = tokens.nextToken();
			String oldInput = inputString;**/
			hashTable = loadAscii(hashTable);
			String oldString;
			//new Friday work 
		
			//need to figure out how to read entire file into one string
			int currentChar;
			String inputString = Character.toString((char)input.read());
			oldString = inputString;
			int putIndex = hashTable.numKeys;
			while(input.ready()){
				//while current string is in dictionary
				//	add next index to current string
				while(hashTable.get(inputString) != null)
				{
					oldString=inputString;
					currentChar = input.read();
					inputString += Character.toString((char)currentChar);
				}
				//if current string is not in dictionary 
				//add previous string to output string
				//add current string to dictionary 
				outputString += hashTable.get(oldString);
				hashTable.put(inputString,String.valueOf((putIndex)+33));
				putIndex++;	
				inputString = inputString.substring(inputString.length()-1);
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
		System.out.println(outputString);
		}
		catch (FileNotFoundException f)
		{
			System.out.println(f.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
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

	public static HashTableChain<String, String>  loadAscii(HashTableChain<String, String> hashTable)
	{
		for(int i =32; i<128; i++)
		{
			//
			//System.out.println("i: " +i);
			//System.out.println("Key: " + Character.toString((char)i));
		//For this put statement, Ben did value "Integer.toBinaryString(i));
			hashTable.put(((Character.toString((char)i))),String.valueOf( i));
		}
		return hashTable;	
	}	
}



	
