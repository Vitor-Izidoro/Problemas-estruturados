import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Classe para armazenar as palavras e suas frequências em diferentes arquivos
class WordNode {
    String word;
    Map<String, Integer> fileFrequencies; // Frequência da palavra por arquivo
    WordNode left, right;
    int height;

    public WordNode(String word) {
        this.word = word;
        this.fileFrequencies = new HashMap<>();
        this.height = 1;
    }

    // Adicionar ou atualizar a frequência da palavra em um arquivo específico
    public void addOrUpdateFrequency(String fileName) {
        fileFrequencies.put(fileName, fileFrequencies.getOrDefault(fileName, 0) + 1);
    }

    // Obter a frequência de uma palavra em um arquivo específico
    public int getFrequency(String fileName) {
        return fileFrequencies.getOrDefault(fileName, 0);
    }
}

class AVLTree {
    private WordNode root;

    // Função para verificar se uma palavra existe na árvore
    public boolean existeElemento(String word) {
        return existeElemento(root, word.toLowerCase().trim());
    }

    private boolean existeElemento(WordNode node, String word) {
        if (node == null) {
            return false;
        }

        int cmp = word.compareTo(node.word);
        if (cmp == 0) {
            return true; // Palavra encontrada
        } else if (cmp < 0) {
            return existeElemento(node.left, word); // Buscar na subárvore esquerda
        } else {
            return existeElemento(node.right, word); // Buscar na subárvore direita
        }
    }

    // Função para inserir uma nova palavra na árvore
    public void insertWord(String word, String fileName) {
        root = insertWord(root, word.toLowerCase().trim(), fileName);
    }

    private WordNode insertWord(WordNode node, String word, String fileName) {
        if (node == null) {
            WordNode newNode = new WordNode(word);
            newNode.addOrUpdateFrequency(fileName);
            return newNode;
        }

        int cmp = word.compareTo(node.word);
        if (cmp < 0) {
            node.left = insertWord(node.left, word, fileName);
        } else if (cmp > 0) {
            node.right = insertWord(node.right, word, fileName);
        } else {
            node.addOrUpdateFrequency(fileName); // Palavra já existe, atualiza a frequência
            return node;
        }

        // Atualizar a altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Balancear a árvore
        return balance(node, word);
    }

    // Método para balancear a árvore AVL
    private WordNode balance(WordNode node, String word) {
        int balance = getBalance(node);

        // Caso 1 - Rotação à direita
        if (balance > 1 && word.compareTo(node.left.word) < 0)
            return rightRotate(node);

        // Caso 2 - Rotação à esquerda
        if (balance < -1 && word.compareTo(node.right.word) > 0)
            return leftRotate(node);

        // Caso 3 - Rotação esquerda-direita
        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso 4 - Rotação direita-esquerda
        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Retorna o nó sem alterações se não precisar balancear
    }

    // Função para contar as ocorrências de uma palavra específica em todos os arquivos
    public void contarOcorrencias(String termo, String[] arquivos) {
        termo = termo.toLowerCase().trim();
        if (existeElemento(termo)) {
            System.out.println("O termo \"" + termo + "\" foi encontrado:");
            for (String arquivo : arquivos) {
                int freq = obterFrequencia(root, termo, arquivo);
                System.out.println(arquivo + ": " + freq + " vez(es).");
            }
        } else {
            System.out.println("O termo \"" + termo + "\" não foi encontrado.");
        }
    }

    private int obterFrequencia(WordNode node, String termo, String arquivo) {
        if (node == null) {
            return 0;
        }
        int cmp = termo.compareTo(node.word);
        if (cmp == 0) {
            return node.getFrequency(arquivo);
        } else if (cmp < 0) {
            return obterFrequencia(node.left, termo, arquivo);
        } else {
            return obterFrequencia(node.right, termo, arquivo);
        }
    }

    // Função para ler palavras de vários arquivos e inseri-las na árvore
    public void readWordsFromFiles(String[] filePaths) {
        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split("\\W+"); // Quebra a linha em palavras
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            insertWord(word, filePath); // Insere a palavra e associa ao arquivo
                        }
                    }
                }
                System.out.println("Palavras lidas do arquivo " + filePath + " e inseridas na árvore.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Métodos auxiliares para rotação e balanceamento
    private int height(WordNode node) { return (node == null) ? 0 : node.height; }

    private int getBalance(WordNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private WordNode rightRotate(WordNode y) {
        WordNode x = y.left;
        WordNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private WordNode leftRotate(WordNode x) {
        WordNode y = x.right;
        WordNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        String[] arquivos = {"src/arquivo.txt", "src/arquivo2.txt", "src/arquivo3.txt"};

        // Ler palavras de múltiplos arquivos
        tree.readWordsFromFiles(arquivos);

        // Contar ocorrências de uma palavra específica em cada arquivo
        String termoParaContar = "computador";
        tree.contarOcorrencias(termoParaContar, arquivos);
    }
}
