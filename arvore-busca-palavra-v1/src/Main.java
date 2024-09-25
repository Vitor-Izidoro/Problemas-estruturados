import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Classe para armazenar as palavras e suas frequências
class WordNode {
    String word;
    int frequency;
    WordNode left, right;
    int height;

    public WordNode(String word) {
        this.word = word;
        this.frequency = 1; // Primeira ocorrência da palavra
        this.height = 1;
    }
}

class AVLTree {
    private WordNode root;

    // Método auxiliar para obter a altura a partir de um nó
    private int height(WordNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Função para obter o máximo entre dois inteiros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Rotação à direita
    private WordNode rightRotate(WordNode y) {
        WordNode x = y.left;
        WordNode T2 = x.right;

        // Realizar a rotação
        x.right = y;
        y.left = T2;

        // Atualizar as alturas
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotação à esquerda
    private WordNode leftRotate(WordNode x) {
        WordNode y = x.right;
        WordNode T2 = y.left;

        // Realizar a rotação
        y.left = x;
        x.right = T2;

        // Atualizar as alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Obter o fator de balanceamento de um nó
    private int getBalance(WordNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Função para inserir uma nova palavra na árvore
    public void insertWord(String word) {
        root = insertWord(root, word.toLowerCase().trim());
    }

    private WordNode insertWord(WordNode node, String word) {
        if (node == null) {
            return new WordNode(word); // Inserir novo nó se o espaço estiver livre
        }

        int cmp = word.compareTo(node.word);
        if (cmp < 0) {
            node.left = insertWord(node.left, word);
        } else if (cmp > 0) {
            node.right = insertWord(node.right, word);
        } else {
            node.frequency++; // Palavra já existe, apenas incrementa a frequência
            return node;
        }

        // Atualiza a altura e balanceia, como no seu código original
        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Balanceamento da árvore AVL
        if (balance > 1 && word.compareTo(node.left.word) < 0)
            return rightRotate(node);
        if (balance < -1 && word.compareTo(node.right.word) > 0)
            return leftRotate(node);
        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

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

    // Função para ler palavras de um arquivo e inseri-las na árvore
    public void readWordsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+"); // Quebra a linha em palavras
                for (String word : words) {
                    if (!word.isEmpty()) {
                        insertWord(word); // Insere a palavra na árvore
                    }
                }
            }
            System.out.println("Palavras lidas do arquivo e inseridas na árvore.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para imprimir as palavras e suas frequências (em ordem)
    public void printWordFrequencies() {
        printWordFrequencies(root);
    }

    private void printWordFrequencies(WordNode node) {
        if (node != null) {
            printWordFrequencies(node.left);
            System.out.println(node.word + ": " + node.frequency);
            printWordFrequencies(node.right);
        }
    }

    // Função para contar as ocorrências de uma palavra específica em arquivos
    public void contarOcorrencias(String termo) {
        termo = termo.toLowerCase().trim();
        if (existeElemento(termo)) {
            System.out.println("O termo \"" + termo + "\" foi encontrado " + obterFrequencia(root, termo) + " vez(es).");
        } else {
            System.out.println("O termo \"" + termo + "\" não foi encontrado.");
        }
    }

    private int obterFrequencia(WordNode node, String termo) {
        if (node == null) {
            return 0;
        }
        int cmp = termo.compareTo(node.word);
        if (cmp == 0) {
            return node.frequency; // Frequência da palavra
        } else if (cmp < 0) {
            return obterFrequencia(node.left, termo); // Busca à esquerda
        } else {
            return obterFrequencia(node.right, termo); // Busca à direita
        }
    }

    // Função para desenhar a árvore
    public void draw() {
        drawTree(root, "", true);
    }

    private void drawTree(WordNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.print(prefix + (isLeft ? "├── " : "└── ") + node.word + "\n");
            drawTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            drawTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Inserir palavras manualmente ou a partir de arquivo
        tree.readWordsFromFile("src/arquivo.txt");

        // Imprimir frequências das palavras na árvore
        tree.printWordFrequencies();

        // Contar ocorrências de uma palavra específica
        String termoParaContar = "computador";
        tree.contarOcorrencias(termoParaContar);

        // Desenhar a árvore (visualização)
        tree.draw();
    }
}
