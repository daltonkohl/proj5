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
