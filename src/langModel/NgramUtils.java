package langModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class NgramUtils: class containing useful functions to deal with n-grams.
 * 
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class NgramUtils {

	/**
	 * Method counting the number of words in a given sequence 
	 * (the sequence can be a n-gram or a sentence).
	 * 
	 * @param sequence the sequence to consider.
	 * @return the number of words of the given sequence.
	 */
	public static int getSequenceSize (String sequence) {
		 int nbmot = 0;
	        
	        String[] warray = sequence.split(" ");
	        for(String s : warray) {
	            nbmot++;
	        }
	        return nbmot;
	}

	
	/**
	 * Method parsing a n-gram and returning its history, i.e. the n-1 preceding words.
	 * 
	 * Example: 
	 *   let the ngram be "l' historique de cette phrase";
	 *   the history will be given for the last word of the ngram, here "phrase":
	 *   if the order is 2 then the history will be "cette"; 
	 *   if the order is 3 then it will be "de cette".
	 * 
	 * @param ngram the n-gram to consider.
	 * @param order the order to consider for the n-gram.
	 * @return history of the given n-gram (the length of the history is order-1).  
	 */
	public static String getHistory (String ngram, int order) {
		//TODO
		 ArrayList historyList = new ArrayList();
	        String[] warray = ngram.split(" ");
	        
	        int i = warray.length-order-1;
	        while(i!=warray.length-1) {
	            historyList.add(warray[i]);
	            i++;
	        }
	        String history = Arrays.asList(historyList).toString();
	        return history;

	}


	/**
	 * Method decomposing the given sentence into n-grams of the given order.
	 * 
	 * This method will be used in the LanguageModelInterface class for computing 
	 * the probability of a sentence as the product of the probabilities of its n-grams. 
	 * 
	 * Example
	 * given the sentence "a b c d e f g", with order=3,
	 * it will result in the following list:
	 * [a, a b, a b c, b c d, c d e, d e f, e f g] 
	 * 
	 * @param sentence the sentence to consider.
	 * @param order the maximal order for the n-grams to create from the sentence.
	 * @return the list of n-grams constructed from the sentence.
	 */
    public static List<String> decomposeIntoNgrams (String sentence, int order) {
        List<String> ngrams = new ArrayList<String>();
        String[] mots = sentence.split(" ");
        for (int i = 0; i<mots.length; i++) {
            String ngram="";
            if (i<order){
                for (int j = 0; j<=i;j++){
                    ngram = ngram + mots[j] + " ";
                }
            } else {
                for (int j = i-order; j<=i; j++){
                    ngram = ngram + mots[j] + " ";
                }
            }
            ngram = ngram.substring(0,ngram.length()-1);
            ngrams.add(ngram);
        }
        return ngrams;
  }

	
	
	/**
	 * Method parsing the given sentence and generate all the combinations of ngrams,
	 * by varying the order n between the given minOrder and maxOrder.
	 * 
	 * This method will be used in the NgramCount class for counting the ngrams 
	 * occurring in a corpus.
	 * 
	 * Algorithm (one possible algo...)
	 * initialize list of ngrams
	 * for n = minOrder to maxOrder (for each order)
	 * 	 for i = 0 to sentence.length-n (parse the whole sentence)
	 *     initialize ngram string parsedSentence
	 *     for j = i to i+n-1 (create a ngram made of the following sequence of words starting from i to i + the order size)
	 *       ngram = ngram + " " + sentence[j]
	 *     add ngramm to list ngrams 
	 * return list ngrams
	 * 
	 * Example
	 * given the sentence "a b c d e f g", with minOrder=1 and maxOrder=3, it will result in the following list:
	 * [a, b, c, d, e, f, g, a b, b c, c d, d e, e f, f g, a b c, b c d, c d e, d e f, e f g]
	 * 
	 * @param sentence the sentence from which to generate n-grams.
	 * @param minOrder the minimal order of the n-grams to create.
	 * @param maxOrder the maximal order of the n-grams to create.
	 * @return a list of generated n-grams from the sentence.
	 */
    public static List<String> generateNgrams (String sentence, int minOrder, int maxOrder) {
    	  List<String> ngrams = new ArrayList<String>();
    	  String[] mots = sentence.split(" ");
    	  for    (int i=minOrder; i<=maxOrder; i++){
    	     for(int j = i-1;j<mots.length;j++) {
    	        String ngram = "";
    	        for(int y = i-1; y>=0; y--) {
    	           ngram = ngram + mots[j-y] + " ";
    	        }
    	              ngram = ngram.substring(0,ngram.length()-1);
    	        ngrams.add(ngram);
    	     }
    	  }
    	  return ngrams;
    	}

	
	/**
	 * Method parsing a sequence of words and returning the modified string where
	 * out-of-vocabulary words are replaced with the OOV tag.
	 * 
	 * @param s the string to consider.
	 * @param vocab the vocabulary to consider.
	 * @return the sequence of words with OOV tags according to the vocabulary. 
	 */
	public static String getStringOOV(String s, VocabularyInterface vocab) {
		String[] warray = s.split(" ");
        for(int i = 0;i<warray.length;i++) {
            if(!vocab.contains(warray[i])) {
                warray[i]="<unk>";
            }
        }
        String modifiedwArray = Arrays.asList(warray).toString();
        return modifiedwArray;
	}

}
