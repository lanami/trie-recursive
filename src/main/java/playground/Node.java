package playground;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lanami
 */
@RequiredArgsConstructor(staticName = "of")
public @Data class Node {
    private final Character value;
    private final Map<Character, Node> children = new HashMap<>();
    private boolean isTerminal;

    public void add(Character value, Node node) {
        if (!this.children.containsKey(value)) {
            this.children.put(value, node);
        }
    }

    public void remove(Character value) {
        this.children.remove(value);
    }

    public Optional<Node> find(Character value) {
        return Optional.ofNullable(this.children.get(value));
    }

    public static Node createRoot() {
        return of(null);
    }
}
