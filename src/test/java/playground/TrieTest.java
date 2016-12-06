package playground;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author lanami
 */
public class TrieTest {

    @Test
    public void emptyTrie_containsNothing() {
        ITrie sut = new Trie();
        assertThat(sut.contains("."), is(false));
    }

    @Test
    public void addSingleElement_trieContainsIt() {
        ITrie sut = new Trie();
        sut.add("Jim");

        assertThat(sut.contains("Jim"), is(true));
    }

    @Test
    public void addMultipleElements_trieContainsIt() {
        ITrie sut = new Trie();
        sut.add("Jim");
        sut.add("Johnny");

        assertThat(sut.contains("John"), is(false));
    }

    @Test
    public void addElement_trieDoesNotContainASubstring() {
        ITrie sut = new Trie();
        sut.add("Jimmy");

        assertThat(sut.contains("Jim"), is(false));
    }

    @Test
    public void addElements_trieHasBeginsWith() {
        ITrie sut = new Trie();
        sut.add("Jim");
        sut.add("Johnny");
        sut.add("John");

        assertThat(sut.beginsWith("Jo"), is(true));
    }

    @Test
    public void addElements_trieDoesNotHaveBeginsWith() {
        ITrie sut = new Trie();
        sut.add("Jim");
        sut.add("Johnny");
        sut.add("John");

        assertThat(sut.beginsWith("Job"), is(false));
    }

    @Test
    public void removeLeafElement_trieContainsInner() {
        ITrie sut = new Trie();
        sut.add("Jim");
        sut.add("John");
        sut.add("Johnny");

        sut.remove("Johnny");

        assertThat(sut.contains("Johnny"), is(false));
        assertThat(sut.contains("John"), is(true));
    }

    @Test
    public void removeInnerElement_trieContainsLeaf() {
        ITrie sut = new Trie();
        sut.add("Jim");
        sut.add("John");
        sut.add("Johnny");

        sut.remove("John");

        assertThat(sut.contains("John"), is(false));
        assertThat(sut.contains("Johnny"), is(true));
    }

}
