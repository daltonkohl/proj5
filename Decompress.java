import java.io.*;
import java.util.*;

public class Decompress {
		
	public static void main(String args[])
	{
		double timeStart = 0;
		double timeFinish = 0;
		boolean choosing = true;
		HashTableChain<String, String> hashTable = new HashTableChain<>();
		String fileName = "";
		try{
		
		fileName = args[0];
		while(choosing){
		hashTable = new HashTableChain<>();
		File validateName =new File(fileName);
		while (!validateName.exists() || !fileName.substring(fileName.length()-4).equals(".zzz"))
			{
				Scanner kb = new Scanner (System.in);
				System.out.println("Please enter a valid file name that ends in '.zzz': ");
				fileName = kb.nextLine();
				validateName = new File(fileName);
			}	
		ObjectInputStream file  = new ObjectInputStream(new FileInputStream(fileName));		
		timeStart = System.nanoTime();
		hashTable = loadAscii(hashTable);
			String outputString = "";
			String lastCode = "";
			String old = "";
		
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
						//	System.out.println("---IN DICT: output: "+ outputString);
						//	System.out.println("---IN DICT: PUT: " + String.valueOf((int)putIndex+32) +" VALUE: " + ( hashTable.get(String.valueOf(oldNum)) + hashTable.get(String.valueOf(currentNum)).substring(0,1))); 
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
						//	System.out.println("DONE!!!!!!!!!!!!!!!!!!!!!!");
					}	

		}
		timeFinish = System.nanoTime();
		//	System.out.println("Final output string: " + outputString);

		PrintWriter decompOutput = new PrintWriter(new FileOutputStream(fileName.substring(0, (fileName.length() -8))));
		decompOutput.println(outputString);


		PrintWriter logOutput = new PrintWriter(new FileOutputStream(fileName.substring(0, (fileName.length()-4)) +".log"));
		
		double timeSeconds = (timeFinish - timeStart) / 1000000000;
		logOutput.println("Decompression for file "+ fileName);
		logOutput.println("Decompression took " + timeSeconds + " seconds");
		logOutput.println("The Table is doubled "+ hashTable.timesRehashed + " times");


		logOutput.close();
	
		Scanner kb = new Scanner(System.in);
		System.out.println("Would you like to Decompress another file (y/n)");
		String cont = kb.nextLine();
		if(cont.equals("y")){
			System.out.println("Enter the filename ending in .zzz");
			fileName = kb.nextLine();
		}
		else{
			choosing = false;
		}
		}
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
