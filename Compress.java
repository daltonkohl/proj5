//This is an implementation of our text compression that takes in a text file
//and compresses it. This also creates a log with data about the compression
//and a file that contains the compressed data.
//Bo Kulbacki
//Dalton Kohl
//Date modified: 5/4/21

import java.util.*;
import java.io.*;

public class Compress{
	public static void main(String[] args){
	try{
		String fileName = args[0];
		boolean choosing = true;
		while(choosing){
		//get valid filename
		HashTableChain<String, String> hashTable = new HashTableChain<>();
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
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName + ".zzz"));

			//creating variables
			int currentChar;
			int writeInt;
			String inputString = Character.toString((char)input.read());
			oldString = inputString;
			int putIndex = hashTable.numKeys;
			//compression algorithim
			while(input.ready()){
				//while current string is in dictionary
				//	add next index to current string
				while(hashTable.get(inputString) != null)
				{
					oldString=inputString;
					currentChar = input.read();
					inputString += Character.toString((char)currentChar);
				}
							
				outputString = hashTable.get(oldString);

				hashTable.put(inputString, String.valueOf((putIndex)+32));
				putIndex++;	
				inputString = inputString.substring(inputString.length()-1);
				//	System.out.println("----WRITING INT: "+ hashTable.get(oldString));
				
				//NEW
				writeInt = Integer.parseInt(hashTable.get(oldString));
				output.writeInt(writeInt);
				

	
		
		}

		output.close();
	
		//write the log file
		PrintWriter logOutput = new PrintWriter(new FileOutputStream(fileName +".zzz.log"));
		logOutput.println("Compression of "+ fileName);
		File f = new File(fileName);
		File compressed = new File(fileName + ".zzz");
		logOutput.println("Compressed from "+ f.length()/1000 +" Kilobytes to "+ compressed.length()/1000 + " Kilobytes");
		logOutput.println("The dictionary contains "+ hashTable.numKeys+ " total entires");
		logOutput.println("The table was reshashed " + hashTable.timesRehashed + " times");
		logOutput.close();
		
		//ask user if they would like to compress another file
		Scanner kb = new Scanner(System.in);
		System.out.println("Would you like to Compress another file? (y/n)");
		String choice = kb.nextLine();
		if(choice.equals("y")){
			System.out.println("Enter the filename :");
			fileName = kb.nextLine();
		}
		else{
			choosing = false;
		}
		}
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



	
