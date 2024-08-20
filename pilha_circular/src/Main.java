public class Main {
    public static void main(String[] args) {
        // Criar instâncias das filas circulares automáticas
        FilaCircularAutomatica fila = new FilaCircularAutomatica(5);
        FilaCircularAutomatica fila2 = new FilaCircularAutomatica(5);
        //FilaCircular filaA = new FilaCircular(5);
        //FilaCircular filaB = new FilaCircular(5);
        // Testar as operações das filas
        fila.insere(10);
        fila.insere(20);
        fila.insere(320);
        fila.insere(40);

        System.out.println("Primeiro elemento: " + fila.primeiro());
        System.out.println("Último elemento: " + fila.ultimo());

        fila.insere(50);
        fila.insere(742);
        fila2.insere(60);
        fila2.insere(70);
        fila2.insere(80);
        fila2.insere(90);
        fila2.insere(100);

        System.out.println("Primeiro elemento após remoção: " + fila.primeiro());
        System.out.println("Último elemento após inserção: " + fila.ultimo());
        System.out.println("Primeiro elemento após remoção da fila 2: " + fila2.primeiro());
        System.out.println("Último elemento após inserção da fila 2: " + fila2.ultimo());
        FilaCircularAutomatica filaResultante = fila.merge(fila2);

        // Exibir os elementos da fila resultante
        while (!filaResultante.vazia()) {
            System.out.println(filaResultante.remove());
        }

        }
    }
