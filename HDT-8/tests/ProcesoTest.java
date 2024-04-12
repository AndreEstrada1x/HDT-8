
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;

import org.junit.Test;

public class ProcesoTest {

    @Test
    public void testCargaDeDatosYInsercionEnPriorityQueue() {
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

        assertEquals(4, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void testCargaDeDatosYInsercionEnHeap() {
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

        assertEquals(4, heap.count());
        assertFalse(heap.isEmpty());
    }

    @Test
    public void testInsertAndRemove() {
        HeapUsingIterativeBinaryTree<Integer, Proceso> heap = new HeapUsingIterativeBinaryTree<>((p1, p2) -> p2 - p1);

        heap.Insert(30, new Proceso("Proceso1", "Usuario1", 10));
        heap.Insert(20, new Proceso("Proceso2", "Usuario2", 5));
        heap.Insert(25, new Proceso("Proceso3", "Usuario3", 15));

        Proceso proceso1 = heap.remove();
        Proceso proceso2 = heap.remove();
        Proceso proceso3 = heap.remove();

        assertTrue(proceso1.getNombre().equals("Proceso1") ||
                   proceso1.getNombre().equals("Proceso2") ||
                   proceso1.getNombre().equals("Proceso3"));
        assertTrue(proceso2.getNombre().equals("Proceso1") ||
                   proceso2.getNombre().equals("Proceso2") ||
                   proceso2.getNombre().equals("Proceso3"));
        assertTrue(proceso3.getNombre().equals("Proceso1") ||
                   proceso3.getNombre().equals("Proceso2") ||
                   proceso3.getNombre().equals("Proceso3"));
    }

    @Test
    public void testRemoveFromEmptyHeap() {
        HeapUsingIterativeBinaryTree<Integer, Proceso> heap = new HeapUsingIterativeBinaryTree<>((p1, p2) -> p2 - p1);

        assertNull(heap.remove());
    }
}
