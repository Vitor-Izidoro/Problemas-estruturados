public class CalcularTempo {
    public long quaseOrdenado(long[] keys, ordenarFunction ordenarFunction){
        System.out.println("Calculando tempo para ordenar array quase ordenado usando algoritmo " + ordenarFunction);
        long start = System.currentTimeMillis();
        ordenarFunction.ordenar(keys);
        return System.currentTimeMillis() - start;
    }

    public long desordenado(long[] keys, desordenarFunction desordenarFunction){
        System.out.println("Calculando tempo para ordenar array desordenado usando algoritmo " + desordenarFunction);
        long start = System.currentTimeMillis();
        desordenarFunction.desordenar(keys);
        return System.currentTimeMillis() - start;
    }

    public long ordemDecrescente(long[] keys, decrescenteFunction decrescenteFunction){
        System.out.println("Calculando tempo para ordenar array decrescente usando algoritmo " + decrescenteFunction);
        long start = System.currentTimeMillis();
        decrescenteFunction.decrescente(keys);
        return System.currentTimeMillis() - start;
    }

    @FunctionalInterface
    public interface ordenarFunction{
        void ordenar(long[] keys);
    }

    @FunctionalInterface
    public interface desordenarFunction{
        void desordenar(long[] keys);
    }

    @FunctionalInterface
    public interface decrescenteFunction{
        void decrescente(long[] keys);
    }
}
