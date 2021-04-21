import java.util.*;
import java.io.*;

public class Compress{
	public static void main(String[] args){
		String inputFile = args[0];
		HashTableChain<String, String> hashTable = new HashTableChain<>();


		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		String line;
		StringTokenizer tokens;
		while((line = input.readLine()) != null){
			line += "\n";
			tokens = new StringTokenizer(line, "");
			String inputString = tokens.nextToken();
			while(tokens.hasMoreTokens()){
				if(
	
		/*	HashTableChain<String, Integer> hash = new HashTableChain<>();
		hash.put("hello", 125);
		System.out.println(hash.get("hello"));*/
	}
}



	
