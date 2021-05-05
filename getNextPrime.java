//This is a helper class that we use to get the next prime number for the size
//of our hash table.
//Dalton Kohl
//Bo Kulbacki
//Date modified: 5/4/21

public class getNextPrime{	
	public static int getNextPrime(int currentValue){
		int next = currentValue *2;
		if(isPrime(next)){
			return next;
		}
		else{
			boolean isPrime = false;
			while(!isPrime){
				next ++;
				if(isPrime(next)){
					isPrime = true;
				}
			}
		}
		return next;

	}



	public static boolean isPrime(int value){
		boolean prime = true;
		for(int i = 2; i <= value / 2; i++){
			if(value % i == 0){
				prime = false;
				break;
			}
		}
		return prime;
	}

	public static void main(String[] args){
		int num = 293;
		System.out.println(getNextPrime(num));
		}
}
