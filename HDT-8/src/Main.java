import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione la versión del programa:");
        System.out.println("1. Utilizar Version1.0 con HeapUsingIterativeBinaryTree");
        System.out.println("2. Utilizar Version2.0 con PriorityQueue del Java Collection Framework");
        System.out.print("Ingrese su elección: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (opcion == 1) {
            usarHeap();
        } else if (opcion == 2) {
            usarPriorityQueue();
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    private static void usarHeap() {
        HeapUsingIterativeBinaryTree<Integer, Proceso> heap = new HeapUsingIterativeBinaryTree<>((p1, p2) -> p2 - p1);

        try (BufferedReader br = new BufferedReader(new FileReader("resources/procesos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String nombre = fields[0];
                String usuario = fields[1];
                int nice = Integer.parseInt(fields[2]);
                Proceso proceso = new Proceso(nombre, usuario, nice);
                heap.Insert(20 + nice, proceso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!heap.isEmpty()) {
            Proceso siguienteProceso = heap.remove();
            imprimirProceso(siguienteProceso);
        }
    }

    private static void usarPriorityQueue() {
        PriorityQueue<Proceso> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Proceso::getNice));

        try (BufferedReader br = new BufferedReader(new FileReader("resources/procesos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String nombre = fields[0];
                String usuario = fields[1];
                int nice = Integer.parseInt(fields[2]);
                Proceso proceso = new Proceso(nombre, usuario, nice);
                priorityQueue.offer(proceso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!priorityQueue.isEmpty()) {
            Proceso siguienteProceso = priorityQueue.poll();
            imprimirProceso(siguienteProceso);
        }
    }

    private static void imprimirProceso(Proceso proceso) {
        System.out.println("Proceso a ser atendido:");
        System.out.println("Nombre: " + proceso.getNombre());
        System.out.println("Usuario: " + proceso.getUsuario());
        System.out.println("Prioridad (PR): " + (120 + proceso.getNice()));
        System.out.println("Valor nice: " + proceso.getNice());
        System.out.println();
    }
}
