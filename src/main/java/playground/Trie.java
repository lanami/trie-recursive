package playground;

import java.util.Optional;

/**
 * @author lanami
 */
public class Trie implements ITrie {
    private Node root = Node.createRoot();

    //--- interface ---//

    @Override
    public void add(String value) {
        if (value == null) throw new IllegalArgumentException("Please supply a non-null value.");

        this.addRecursive(root, value);
    }

    @Override
    public void remove(String value) {
        if (value == null) throw new IllegalArgumentException("Please supply a non-null value.");

        this.removeRecursive(root, value);
    }

    @Override
    public boolean contains(String value) {
        if (value == null) throw new IllegalArgumentException("Please supply a non-null value.");

        return this.containsRecursive(root, value, true);
    }

    @Override
    public boolean beginsWith(String value) {
        if (value == null) throw new IllegalArgumentException("Please supply a non-null value.");

        return this.containsRecursive(root, value, false);
    }



    //--- implementation ---//

    private void addRecursive(Node root, CharSequence s) {
        if (s.length() == 0) return;

        Character ch = s.charAt(0);

        Node next;
        Optional<Node> nextNodeOptional = root.find(ch);
        if (nextNodeOptional.isPresent()) { //node already exists, use it
            next = nextNodeOptional.get();
        } else {                            //create and add new node
            next = Node.of(ch);
            root.add(ch, next);
        }

        //reached the end, set terminal marker
        if (isTerminalChar(s)) {
            next.setTerminal(true);
        }

        this.addRecursive(next, s.subSequence(1, s.length()));
    }

    private void removeRecursive(Node root, CharSequence s) {
        if (s.length() == 0) return;

        Character ch = s.charAt(0);

        //check if the value is present before attempting to remove
        Optional<Node> nextNodeOptional = root.find(ch);
        if (!nextNodeOptional.isPresent()) throw new IllegalArgumentException("Value not found. Please supply a valid value.");

        Node next = nextNodeOptional.get();

        if (isTerminalChar(s)) {
            next.setTerminal(false); //reached the end, now unmark the char as terminal...
        } else {
            this.removeRecursive(next, s.subSequence(1, s.length()));
        }

        //...and cleanup orphans on the way back
        if (next.getChildren().isEmpty() && !next.isTerminal()) {
            root.remove(ch);
        }
    }

    private boolean containsRecursive(Node root, CharSequence s, boolean searchFullWords) {
        Character ch = s.charAt(0);

        Optional<Node> nextNodeOptional = root.find(ch);
        if (!nextNodeOptional.isPresent()) return false;

        Node next = nextNodeOptional.get();

        if (isTerminalChar(s)) {
            return searchFullWords? next.isTerminal() : true;
        }

        return this.containsRecursive(next, s.subSequence(1, s.length()), searchFullWords);
    }


    private static boolean isTerminalChar(CharSequence s) {
        return s.length() == 1;
    }

}