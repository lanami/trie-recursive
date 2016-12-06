package playground;

/**
 * @author lanami
 */
public interface ITrie {

    /**
     * Add a word to trie.
     * */
    void add(String value);

    /**
     * Remove a word from trie.
     * */
    void remove(String value);

    /**
     * @return  is the word in the trie?
     * */
    boolean contains(String value);

    /**
     * @return  are there any words in the trie that begin with the prefix?
     * */
    boolean beginsWith(String prefix);
}
