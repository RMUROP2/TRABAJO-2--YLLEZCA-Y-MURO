import java.util.*;

public class OptimizarRuta {

    static Map<String, List<Conexion>> grafo = new HashMap<>();

    static class Conexion {
        String destino;
        int peso;

        Conexion(String destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void optimizarRuta(String nodoInicio, String nodoDestino, List<String> rutaActual, int distanciaActual) {
        if (nodoInicio.equals(nodoDestino)) {
            System.out.println("Ruta: " + rutaActual + " | Distancia: " + distanciaActual);
            return;
        }

        if (!grafo.containsKey(nodoInicio)) return;

        for (Conexion conexion : grafo.get(nodoInicio)) {
            if (!rutaActual.contains(conexion.destino)) {
                List<String> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(conexion.destino);
                optimizarRuta(conexion.destino, nodoDestino, nuevaRuta, distanciaActual + conexion.peso);
            }
        }
    }

    public static void agregarConexion(String origen, String destino, int peso) {
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.get(origen).add(new Conexion(destino, peso));
    }

    public static void dibujarGrafo() {
        for (String nodo : grafo.keySet()) {
            for (Conexion conexion : grafo.get(nodo)) {
                System.out.println(nodo + " -> " + conexion.destino + " (Peso: " + conexion.peso + ")");
            }
        }
    }

    public static void main(String[] args) {
        agregarConexion("A", "B", 10);
        agregarConexion("A", "C", 7);
        agregarConexion("B", "D", 5);
        agregarConexion("C", "D", 2);

        System.out.println("Grafo:");
        dibujarGrafo();

        List<String> rutaInicial = new ArrayList<>();
        rutaInicial.add("A");
        optimizarRuta("A", "D", rutaInicial, 0);
    }
}
