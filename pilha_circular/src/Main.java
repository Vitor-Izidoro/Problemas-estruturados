public class Main {
    public static void main(String[] args) {
        // Criar uma instância da FilaCircular
        FilaCircularAutomatica fila = new FilaCircularAutomatica(5);

        // Testar as operações da fila
        fila.insere(10);
        fila.insere(20);
        fila.insere(30);
        fila.insere(40);

        System.out.println("Primeiro elemento: " + fila.primeiro());
        System.out.println("Último elemento: " + fila.ultimo());

        //fila.remove();
        fila.insere(50);
        fila.insere(60);
        //fila.remove();
        fila.insere(70);
        fila.insere(80);
        fila.insere(90);
        fila.insere(100);
        System.out.println("Primeiro elemento após remoção: " + fila.primeiro());
        System.out.println("Último elemento após inserção: " + fila.ultimo());
    }
}
