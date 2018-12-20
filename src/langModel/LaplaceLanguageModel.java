package langModel;


/**
 * Class LaplaceLanguageModel: class inheriting the class NaiveLanguageModel by creating 
 * a n-gram language model using a Laplace smoothing.
 * 
 * @author ... (2017)
 *
 */
public class LaplaceLanguageModel extends NaiveLanguageModel {

	@Override
	public Double getNgramProb(String ngram) {

		int res = (ngramCounts.getCounts(ngram)+1)/(ngramCounts.getNgramCounterSize()+ngramCounts.getTotalWordNumber()-1);
		return (double)res;

	}
}
