package langModel;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.*;

import org.junit.Test;

public class VocabularyTest {

    Vocabulary vocab = new Vocabulary();

    @Test
    public void testGetSize1() {
        assertEquals(0,vocab.getSize());
    }


    @Test
    public void testGetSize2() {
        vocab.addWord("un");
        vocab.addWord("deux");
        vocab.addWord("trois");
        vocab.addWord("quatre");
        assertEquals(4,vocab.getSize());
    }

    @Test
    public void testGetSize3() {
        vocab.addWord("un");
        vocab.addWord("deux");
        vocab.addWord("trois");
        vocab.addWord("quatre");
        assertFalse(vocab.getSize()==5);
    }

    @Test
    public void testGetSize4() {
        assertTrue(vocab.getSize()!=5);
    }

    @Test
    public void testGetWords1() {
        vocab.addWord("un");
        vocab.addWord("deux");
        Set<String> test = new HashSet<String>();
        test.add("un");
        test.add("deux");
        assertEquals(test,vocab.getWords());
    }

    @Test
    public void testGetWords2() {
        Set<String> test = new HashSet<String>();
        assertEquals(test,vocab.getWords());
    }

    @Test
    public void testGetWords3() {
        vocab.addWord("un");
        vocab.addWord("deux");
        Set<String> test = new HashSet<String>();
        test.add("un");
        test.add("deux");
        test.add("trois");
        assertFalse(test==vocab.getWords());
    }

    @Test
    public void testContains1() {
        vocab.addWord("test");
        assertTrue(vocab.contains("test"));
    }

    @Test
    public void testContains2() {
        assertFalse(vocab.contains("test"));
    }

    @Test
    public void testContains3() {
        vocab.addWord("test");
        assertFalse(vocab.contains("tests"));
    }

    @Test
    public void testContains4() {
        vocab.addWord("Morgan");
        assertFalse(vocab.contains("morgan"));
    }

    @Test
    public void testContains5() {
        vocab.addWord("mus");
        vocab.addWord("Morgan");
        assertFalse(vocab.contains("mus Morgan"));
    }

    @Test
    public void testRemove1() {
        vocab.addWord("mus");
        vocab.addWord("Morgan");

        vocab.removeWord("mus");

        assertFalse(vocab.contains("mus"));
        assertTrue(vocab.contains("Morgan"));
    }

    @Test
    public void testReadVocabularyFile() throws FileNotFoundException {
        vocab.addWord("lol");
        vocab.addWord("lul");
        vocab.addWord("lel");

        Vocabulary vocab2 = new Vocabulary();
        vocab2.readVocabularyFile("/home/e174688d/Bureau/mod_maths/data.txt");
        System.out.println("vocab : "+vocab.getWords());
        System.out.println("vocab2 : "+vocab2.getWords());

        for(String s: vocab.vocabulary){
            assertTrue(vocab2.contains(s));
        }

    }


}

