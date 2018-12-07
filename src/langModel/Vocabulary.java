package langModel;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.*;


/**
 * Class Vocabulary: class implementing the interface VocabularyInterface.
 * 
 * @author ... (2017)
 *
 */
public class Vocabulary implements VocabularyInterface {
	/**
	 * The set of words corresponding to the vocabulary.
	 */
	protected Set<String> vocabulary;

	
	/**
	 * Constructor.
	 */
	public Vocabulary(){
		this.vocabulary = new HashSet<String>();
	}
	
	
	@Override
	public int getSize() {
		return this.vocabulary.size();
	}

	@Override
	public Set<String> getWords() {
		return this.vocabulary;
	}

	@Override
	public boolean contains(String word) {
		return this.vocabulary.contains(word);
	}

	@Override
	public void addWord(String word) {
		this.vocabulary.add(word);
	}

	@Override
	public void removeWord(String word) {
		this.vocabulary.remove(word);
		
	}

	@Override
	public void scanNgramSet(Set<String> ngramSet) {
		// TODO Auto-generated method stub
		for(String ngram : ngramSet) {
            String[] motNgram = ngram.split(" ");
            for(String mot : motNgram) {
                System.out.println(mot);
                this.addWord(mot);
            }
        }

	}

	@Override
	public void readVocabularyFile(String filePath) {
		Scanner sc = new Scanner(filePath);
		String line;
		while(sc.hasNextLine()){
			vocabulary.add(sc.nextLine());
		}

		
	}

	@Override
	public void writeVocabularyFile(String filePath) {
		// TODO Auto-generated method stub
		 PrintWriter writer = new PrintWriter(filePath);
	        for(String mot : this.vocabulary) {
	            writer.println(mot);
	        }
	        writer.close();
	}

}
