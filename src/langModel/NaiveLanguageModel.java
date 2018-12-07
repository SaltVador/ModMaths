package langModel;


import java.util.List;

/**
 * Class NaiveLanguageModel: class implementing the interface LanguageModelInterface by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 * 
 * @author ... (2017)
 *
 */
public class NaiveLanguageModel implements LanguageModelInterface {
	/**
	 * The NgramCountsInterface corresponding to the language model.
	 */
	protected NgramCountsInterface ngramCounts;
	
	/**
	 * The vocabulary of the language model.
	 */
	protected VocabularyInterface vocabulary;
	
	
	/**
	 * Constructor.
	 */
	public NaiveLanguageModel(){
		this.ngramCounts = new NgramCounts();
		this.vocabulary = new Vocabulary();
	}
	

	@Override
	public int getLMOrder() {
		return ngramCounts.getMaximalOrder();
	}

	@Override
	public void setNgramCounts(NgramCountsInterface ngramCounts, VocabularyInterface vocab) {
		this.ngramCounts=ngramCounts;
		this.vocabulary=vocab;
	}

	@Override
	public Double getNgramProb(String ngram) {

		int res = ngramCounts.getCounts(ngram)/ngramCounts.getNgramCounterSize();
		return (double)res;
	}

	@Override
	public Double getSentenceProb(String sentence) {
		List<String> decomposeIntoNgrams = NgramUtils.decomposeIntoNgrams(sentence,ngramCounts.getMaximalOrder());
		Double res = (double)0;
		for (String var : decomposeIntoNgrams) {
			res=res+getNgramProb(var);
		}
		return res;
	}

}
