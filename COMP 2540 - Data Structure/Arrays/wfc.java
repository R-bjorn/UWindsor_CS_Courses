// Word Freq counter using 2 arrays


public class Main
{
	public static void main(String[] args) {
		String line = "My name is Xyz He is Abc Is he allright";
		String tokens[] = line.toUpperCase().split(" ");
		String token;
		String words[] = new String[100];
		int count[] = new int[100];
		
		
		for(int i = 0 ; i < tokens.length ; i++){
		    token = tokens[i];
		    
		    for(int j = 0 ; j < 100 ; j++){
		        if(words[j] == null){
    		        words[j] = token;
    		        count[j] = 1;
    		        break;
    		    }
    		    else if(words[j].equals(token)){
    		        count[j] += 1;
    		        break;
    		    }
		    }
		}
		
		int max = 0;
		String word = null;
		for(int i = 0 ; i < words.length ; i++){
		    if(words[i] == null){
		        break;
		    }
		    System.out.println(words[i] + "->"+count[i]);
		}
	}
}

