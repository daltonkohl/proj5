import java.io.*;
import java.util.*;

public class fileMaker{
	public static void main(String[] args){
		try{
			PrintWriter output = new PrintWriter(new FileOutputStream("longfile.txt"));
			for (int i = 0; i < 1000; i++){
				output.println("I am so happy to be done with this class! Dr. Jiang is so great!" + i);
			}
			output.close();
		}
		catch (IOException e){
			System.exit(1);
		}
	}
}

