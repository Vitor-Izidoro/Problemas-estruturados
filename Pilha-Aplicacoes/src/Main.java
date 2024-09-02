public class Main {
    public static void main(String[] args) {
        class PilhaChar {
            private int topo;
            private final char[] dado;
            private final int MAX;

            // Construtor
            public PilhaChar(int n) {
                this.MAX = n;
                this.dado = new char[MAX];
                this.topo = -1;
            }

            // a) Retorna o elemento no topo da pilha
            public char topo() {
                if (vazia() == 1) {
                    throw new RuntimeException("Pilha vazia");
                }
                return dado[topo];
            }

            // b) Retorna 1 se a pilha está vazia e 0 caso contrário
            public int vazia() {
                return (topo == -1) ? 1 : 0;
            }

            // c) Retorna 1 se a pilha está cheia e 0 caso contrário
            public int cheia() {
                return (topo == MAX - 1) ? 1 : 0;
            }

            // d) Coloca o elemento na pilha
            public void empilha(char elemento) {
                if (cheia() == 1) {
                    throw new RuntimeException("Pilha cheia");
                }
                dado[++topo] = elemento;
            }

            // e) Retira e retorna o elemento do topo da pilha
            public void desempilha() {
                if (vazia() == 1) {
                    throw new RuntimeException("Pilha vazia");
                }
                topo--;
            }
        }

        class Expressao {
            public static int verificaDelimitadores(String expressao) {
                PilhaChar pilha = new PilhaChar(expressao.length());
                for (char ch : expressao.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        pilha.empilha(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (pilha.vazia() == 1) {
                            return 0; // delimitador de fechamento sem correspondência
                        }
                        char topo = pilha.topo();
                        if ((ch == ')' && topo == '(') ||
                                (ch == '}' && topo == '{') ||
                                (ch == ']' && topo == '[')) {
                            pilha.desempilha();  // Só desempilha se corresponder
                        } else {
                            return 0; // delimitadores não correspondem
                        }
                    }
                }
                return pilha.vazia() == 1 ? 1 : 0; // se a pilha estiver vazia, a expressão é válida
            }
        }
    }
}

// Exemplo de uso
public class validacao {
    public static void main(String[] args) {
        String exp1 = "(1+5)*(56+12)";
        String exp2 = "(1+5)*)56+12)";
        String exp3 =  "(1+5)*[56+12)";
        String exp4 = "(((1+2) - 3 )* 6)";
        String exp5 = "((((1+2) -3)* 6)";
        String exp6 = "(1+1*[-1-1/{2^2}])";

        System.out.println("Expressão 1: " + Main.Expressao.verificaDelimitadores(exp1)); // 1
        System.out.println("Expressão 2: " + Main.Expressao.verificaDelimitadores(exp2)); // 0
        System.out.println("Expressão 3: " + Main.Expressao.verificaDelimitadores(exp3)); // 0
        System.out.println("Expressão 4: " + Main.Expressao.verificaDelimitadores(exp4)); // 1
        System.out.println("Expressão 5: " + Main.Expressao.verificaDelimitadores(exp5)); // 0
        System.out.println("Expressão 6: " + Main.Expressao.verificaDelimitadores(exp6)); // 1
    }
}
