package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    // Construtor padrão
    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    // Construtor para definir explicitamente o estado de `isEndOfWord`
    public TrieNode(boolean isEndOfWord) {
        this.children = new HashMap<>();
        this.isEndOfWord = isEndOfWord;
    }
	
}
