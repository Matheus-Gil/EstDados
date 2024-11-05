package hashing;

import java.util.LinkedList;

public class HashTable<K, V> {
	private LinkedList<HashEntry<K, V>> [] hashTable; // Tabela hash
	private int size; // Tamanho da tabela hash 
	private boolean alternateHash; // Boolean para determinar se a função hashDJB2 será utilizada ou não
	
	public HashTable(int size) {
		hashTable = new LinkedList[size]; // 
		this.size = size;
		this.alternateHash = false; // Falso por default
	}

	/***********
	 * HASHING *
	 ***********/

    // Função de hash de divisão
    public int hashDivisao (String texto , int M) {
        int soma = 0;
        for (char c : texto.toCharArray()) {
            soma += (int) c;
        }
        return soma % M;
    }

    // Função de hashDJB2
	public int hashDJB2 (String texto) {
		long hash = 5381;
		for ( char c : texto . toCharArray () ) {
			hash = (( hash << 5) + hash ) + c ; // hash * 33 + c
		}
		return ( int ) ( hash % Integer . MAX_VALUE ) ;
	}

	/* TODO: Atualizar a função get(K key) para funcionar com texto. Rascunho da nova função getPosition() abaixo
	Função getPosition alterada para melhor acomodar as duas funções de hashing
    public int getPosition(String key) {
        if (alternateHash) {
            return hashDJB2(key);
        } else {
            return hashDivisao(key);
        }
    }*/

	public int getPosition(int value) {
		return value % size;
	}

	/*********
	 * LISTA *
	 *********/
	
	// Retorna o valor associado à chave na tabela hash
	public LinkedList<V> get(K key) {
		int position;
		LinkedList<V> valuesFound = new LinkedList<V>();
		
		// Não pode inserir valores com chave nula
		if(key == null)
			return null;
		
		position = getPosition(Math.abs(key.hashCode()));
		
		// Se a posição do hash for nulo, então nenhum elemento com aquela chave foi inserido ainda.
		// Nesse caso, retorna null
		if(hashTable[position] == null) {
			return null;
		}
		
		// Se existir uma lista na posição
		else {
			LinkedList<HashEntry<K, V>> currentList = hashTable[position];
			//for(HashEntry<K, V> currentEntry : currentList) 
			for(int i = 0; i < currentList.size(); i++) {
				HashEntry<K, V> currentEntry = currentList.get(i);
				// Se a chave que procuramos é igual à chave guardada nessa posição
				// Guarda o valor encontrado na lista de valores 
				if(key.equals(currentEntry.key))
					valuesFound.add(currentEntry.value);
			}
			return valuesFound;
		}
	}

	/************
	 * INSERÇÃO *
	 ************/
	
	public boolean put(K key, V value) {
		
		int position;
		
		// Não pode inserir valores com chave nula
		if(key == null)
			return false;
		
		// Antes de inserir, precisamos checar se já não existe na coleção uma chave com 
		// valor idêntico ao que estamos tentando inserir. Caso exista, não deve inserir e
		// retorna null
		LinkedList<V> currentValuesForKey = get(key);
		if(currentValuesForKey != null && currentValuesForKey.contains(value))
			return false;
		
		// Caso não encontrou chaves e valores iguais, insere na coleção
		position  = getPosition(Math.abs(key.hashCode()));
		
		// Pegando a lista da posição em que precisamos inserir
		LinkedList<HashEntry<K, V>> currentList = hashTable[position];
		
		// Testa se a posição é nula. Se for, precisamos criar a lista para inserir o valor
		if (currentList == null) 
			currentList = new LinkedList<HashEntry<K, V>>(); // Cria a lista
		
		currentList.add(new HashEntry<K, V>(key, value)); // Inserir o valor na lista
		hashTable[position] = currentList; // Guardo a lista na posição
		
		return true; // Inserido com sucesso
		
	}
	
	public void print() {
		for(int i = 0; i < hashTable.length; i++) {
			System.out.println("---------------");
			System.out.println("Position " + i + ":");
			if(hashTable[i] == null) {
				System.out.println("Empty position");
			}
			else {
				LinkedList<HashEntry<K, V>> currentList = hashTable[i];
				for (int j = 0; j < currentList.size(); j++) {
					System.out.print(currentList.get(j).toString() + "  -  ");
				}
				System.out.println();
			}
		}
	}	
}
