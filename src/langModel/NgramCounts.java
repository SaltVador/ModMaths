package langModel;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Class NgramCounts: class implementing the interface NgramCountsInterface.
 *
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class NgramCounts implements NgramCountsInterface {
	/**
	 * The maximal order of the n-gram counts.
	 */
	protected int order;

	/**
	 * The map containing the counts of each n-gram.
	 */
	protected Map<String,Integer> ngramCounts;

	/**
	 * The total number of words in the corpus.
	 * In practice, the total number of words will be increased when parsing a corpus
	 * or when parsing a NgramCountsInterface file (only if the ngram encountered is a unigram one).
	 */
	protected int nbWordsTotal;


	/**
	 * Constructor.
	 */
	public NgramCounts(){
		//TODO
		ngramCounts = new HashMap<String,Integer>();
		nbWordsTotal = 0;

	}


	/**
	 * Setter of the maximal order of the ngrams considered.
	 *
	 * In practice, the method will be called when parsing the training corpus,
	 * or when parsing the NgramCountsInterface file (using the maximal n-gram length encountered).
	 *
	 * @param order the maximal order of n-grams considered.
	 */
	private void setMaximalOrder (int order) {
		// TODO Auto-generated method stub
		this.order = order;
	}


	@Override
	public int getMaximalOrder() {
		// TODO Auto-generated method stub
		return this.order;
	}


	@Override
	public int getNgramCounterSize() {
		// TODO Auto-generated method stub
		return this.ngramCounts.size();
	}


	@Override
	public int getTotalWordNumber(){
		// TODO Auto-generated method stub
		return this.nbWordsTotal;
	}


	@Override
	public Set<String> getNgrams() {
		// TODO Auto-generated method stub
		Set<String> ngrams = new HashSet<String>();

		Iterator iterator = ngramCounts.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			ngrams.add((String)mapentry.getKey());
		}
		return ngrams;
	}


	@Override
	public int getCounts(String ngram) {
		// TODO Auto-generated method stub
		return ngramCounts.get(ngram);
	}


	@Override
	public void incCounts(String ngram) {
		// TODO Auto-generated method stub
		if(!ngramCounts.containsKey(ngram)) {
			ngramCounts.put(ngram, 1);
		}
		else {
			ngramCounts.computeIfPresent(ngram, (k, v) -> v + 1);
		}
	}


	@Override
	public void setCounts(String ngram, int counts) {
		ngramCounts.put(ngram, counts);
	}


	@Override
	public void scanTextFile(String filePath, VocabularyInterface vocab, int maximalOrder) {
		// TODO Auto-generated method stub
	}


	@Override
	public void writeNgramCountFile(String filePath) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PrintWriter writer = new PrintWriter(filePath);

		Iterator iterator = ngramCounts.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			writer.println(mapentry.getKey() + "    " + mapentry.getValue());
		}
		writer.close();
	}


	@Override
	public void readNgramCountsFile(String filePath) {
		// TODO Auto-generated method stub
	}

}
