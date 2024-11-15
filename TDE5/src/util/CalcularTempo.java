package util;

public class CalcularTempo {
    public static long quaseOrdenado(long[] keys, sortFunction sortFunction){
        System.out.println("Calculando tempo para ordenar array quase ordenado usando algoritmo " + sortFunction);
        long start = System.currentTimeMillis();
        sortFunction.sort(keys);
        return System.currentTimeMillis() - start;
    }

    public static long desordenado(long[] keys, sortFunction sortFunction){
        System.out.println("Calculando tempo para ordenar array desordenado usando algoritmo " + sortFunction);
        long start = System.currentTimeMillis();
        sortFunction.sort(keys);
        return System.currentTimeMillis() - start;
    }

    public static long ordemDecrescente(long[] keys, sortFunction sortFunction){
        System.out.println("Calculando tempo para ordenar array decrescente usando algoritmo " + sortFunction);
        long start = System.currentTimeMillis();
        sortFunction.sort(keys);
        return System.currentTimeMillis() - start;
    }

    @FunctionalInterface
    public interface sortFunction{
        void sort(long[] keys);
    }
}
