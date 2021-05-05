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
			//String fromBinary = file.readUTF();
			//String readableString = fromBinary.replace("", " ").trim();
			//System.out.println(readableString);
			//StringTokenizer tokens = new StringTokenizer(readableString, " ");
			String outputString = "";
			String lastCode = "";
			String old = "";
		//	String newString = tokens.nextToken();
			//System.out.println("First Char: "+newString);
			//compress: String inputString = Character.toString((char)input.read());
		
			int putIndex = hashTable.numKeys;
			System.out.println(putIndex);
			int currentNum = file.readInt();
			int oldNum=0;
			while(file.available()>0){
				if(oldNum == 0)
				{
					outputString += hashTable.get(String.valueOf(currentNum));
				}
				else if(hashTable.get(String.valueOf(currentNum)) != null)
					{
						outputString += hashTable.get(String.valueOf(currentNum));
						hashTable.put(String.valueOf((int)putIndex+32), hashTable.get(String.valueOf(oldNum)) + hashTable.get(String.valueOf(currentNum)).substring(0,1));//hashTable.get(String.valueOf(oldNum)).substring(0,1));
						System.out.println("---IN DICT: output: "+ outputString);
						System.out.println("---IN DICT: PUT: " + String.valueOf((int)putIndex+32) +" VALUE: " + ( hashTable.get(String.valueOf(oldNum)) + hashTable.get(String.valueOf(currentNum)).substring(0,1))); 
					putIndex++;
					}	
				else
					{
						outputString += (hashTable.get(String.valueOf(oldNum)) + hashTable.get(String.valueOf(oldNum)).substring(0,1));
						hashTable.put(String.valueOf(currentNum), hashTable.get(String.valueOf(oldNum)) + hashTable.get(String.valueOf(oldNum)).substring(0,1));

					}
				oldNum = currentNum;
				currentNum = file.readInt();
				if(file.available() ==0)
					{
						outputString += hashTable.get(String.valueOf(currentNum));
						System.out.println("DONE!!!!!!!!!!!!!!!!!!!!!!");
					}	
				//oldNum = currentNum;
				//currentNum = file.readInt();	
				//while current string is in dictionary
				//	add next index to current string
				/*				while(currentNum <32 || hashTable.get(String.valueOf(currentNum)) != null)
				{
					oldNum = currentNum;


					old = newString;
					newString += tokens.nextToken();
					System.out.println("---- old: " + old);
					System.out.println("---- new: " + newString); 
					System.out.println("---- lastCode: " + lastCode);
					if (!tokens.hasMoreTokens())
					{
						old = newString;
						System.out.println("LAST STRING NO MORE TOKENS: " + old);
						break;
					}
				}*/
			/*	outputString += hashTable.get(old);
				System.out.println("Added :" + old + " to: " + outputString);
				if(!lastCode.equals(""))
				{
					hashTable.put(String.valueOf((int)putIndex+32), hashTable.get(lastCode) + hashTable.get(old).substring(0,1));
					System.out.println("PUTTING IN: Key= "+ String.valueOf((int)(putIndex+32)) + " Value =  " + hashTable.get(lastCode)+hashTable.get(old).substring(0,1));
					putIndex++;
				}*/
				//first pass through 
				/*else 
				{
					hashTable.put(String.valueOf((int)putIndex+32), hashTable.get(old) + hashTable.get(old).substring(0,1));
					System.out.println("Added double firstCHar in case: " + String.valueOf((int)(putIndex+32)) + " VALIE = " + hashTable.get(old) + hashTable.get(old).substring(0,1));
					putIndex++;
				}*/	
				//lastCode = old;
				//old="";
				//newString = newString.substring(newString.length()-1);
				//putIndex++;
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
		System.out.println("Final output string: " + outputString);	
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
		for(int i =32; i<128; i++)
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
