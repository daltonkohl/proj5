import java.io.*;
import java.util.*;

public class Decompress {
		
	public static void main(String args[])
	{
		boolean choosing = true;		
		try{
		
		String fileName = args[0];
		File validateName =new File(fileName);
		while (!validateName.exists() || !fileName.substring(fileName.length()-4).equals(".zzz"))
			{
				Scanner kb = new Scanner (System.in);
				System.out.println("Please enter a valid file name that ends in '.zzz': ");
				fileName = kb.nextLine();
				validateName = new File(fileName);
			}	
		ObjectInputStream file  = new ObjectInputStream(new FileInputStream(fileName));		
		
		HashTableChain<String, String> hashTable = new HashTableChain<>();
		hashTable = loadAscii(hashTable);
			//System.out.println(file.read());	
			String fromBinary = file.readUTF();
			StringTokenizer tokens = new StringTokenizer(fromBinary, "");
			String outputString = "";
			String lastCode = "";
			String old = "";
			String newString = tokens.nextToken();
			System.out.println("First Char: "+newString);
			//compress: String inputString = Character.toString((char)input.read());
		
			int putIndex = hashTable.numKeys;
			while(tokens.hasMoreTokens()){
				//while current string is in dictionary
				//	add next index to current string
				while(Integer.parseInt(newString) <32 || hashTable.get(newString) != null)
				{
					old = newString;
					newString += tokens.nextToken();
					if (!tokens.hasMoreTokens())
					{
						old = newString;
						break;
					}
					//compress: currentChar = input.read();
					//compress: inputString += Character.toString((char)currentChar);
				}
				outputString += hashTable.get(old);
				hashTable.put(String.valueOf(putIndex+33), lastCode + old.substring(0,1));
				lastCode = old;
				old="";
				newString = newString.substring(newString.length()-1);
				//if current string is not in dictionary 
				//add previous string to output string
				//add current string to dictionary 
			//	System.out.println("string in dict: " + oldString + " output added: " + hashTable.get(oldString));
			//	System.out.println("string not in dict: "+inputString + " added to dict: "+String.valueOf(putIndex+33));
				/*outputString += hashTable.get(oldString);
				hashTable.put(inputString, String.valueOf((putIndex)+34));
				putIndex++;	
				inputString = inputString.substring(inputString.length()-1);
				System.out.println("finished for loop");*/


		
		
		
		}
		System.out.println(outputString);	
		}
		
		catch (FileNotFoundException f)
		{
		//Scanner kb = new Scanner(System.in);
		System.out.println(f.getMessage());
		System.exit(1);
		}
		catch (IOException e) {
		System.out.println(e.getMessage());
		System.exit(1);
		}	
	}

	public static HashTableChain<String, String>  loadAscii(HashTableChain<String, String> hashTable)
	{
		for(int i =33; i<128; i++)
		{
			//
			//System.out.println("i: " +i);
			//System.out.println("Key: " + Character.toString((char)i));
			//System.out.println("string " + String.valueOf(i));
		//For this put statement, Ben did value "Integer.toBinaryString(i));
			hashTable.put(String.valueOf(i), Character.toString((char)i));
		}
		hashTable.put("128","\n");
		return hashTable;	
	}


}
