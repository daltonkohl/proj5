import java.util.*;
import java.io.*;

public class Compress{
	public static void main(String[] args){
	try{
		HashTableChain<String, String> hashTable = new HashTableChain<>();

		String fileName = args[0];
		File validateName =new File(fileName);
		while (!validateName.exists() )
			{
				Scanner kb = new Scanner (System.in);
				System.out.println("Please enter a valid file name: ");
				fileName = kb.nextLine();
				validateName = new File(fileName);
			}	
	
		BufferedReader input = new BufferedReader(new FileReader(fileName));
		String line;
		String outputString="";
			hashTable = loadAscii(hashTable);
			String oldString;
			//new Friday work 
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName + ".zzz"));

			//need to figure out how to read entire file into one string
			int currentChar;
			int writeInt;
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
				System.out.println("string in dict: " + oldString + " output added: " + hashTable.get(oldString));
				System.out.println("string not in dict: "+inputString + " added to dict: "+String.valueOf(putIndex+32));
				//UTF WAYoutputString += hashTable.get(oldString);
				//NEW
				
				outputString = hashTable.get(oldString);

				hashTable.put(inputString, String.valueOf((putIndex)+32));
				putIndex++;	
				inputString = inputString.substring(inputString.length()-1);
				System.out.println("----WRITING INT: "+ hashTable.get(oldString));
				
				//NEW
				writeInt = Integer.parseInt(hashTable.get(oldString));
				output.writeInt(writeInt);
				

	
		
		}

		//ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName +".zzz"));
		//output.writeUTF(outputString);
		output.close();

		PrintWriter logOutput = new PrintWriter(new FileOutputStream(fileName +".zzz.log"));
		logOutput.println("Compression of "+ fileName);
		logOutput.println("Compressed from ????"+ "to ???");
		logOutput.println("The dictionary contains "+ hashTable.numKeys+ "total entires");
		logOutput.println("The table was reshashed xyz(calculate this) times");
		logOutput.close();
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
			//System.out.println("string " + String.valueOf(i));
		//For this put statement, Ben did value "Integer.toBinaryString(i));
			hashTable.put(((Character.toString((char)i))), String.valueOf(i));
		}
		hashTable.put("\n","128");
		return hashTable;	
	}	
}



	
