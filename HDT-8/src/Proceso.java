/**
 * The Proceso class represents a process in a computer system.
 * It implements the Comparable interface to allow comparison based on the 'nice' value.
 */
public class Proceso implements Comparable<Proceso> {
    private String nombre;
    private String usuario;
    private int nice;

    /**
     * Constructs a Proceso object with the specified nombre, usuario, and nice values.
     *
     * @param nombre  the name of the process
     * @param usuario the user associated with the process
     * @param nice    the nice value of the process
     */
    public Proceso(String nombre, String usuario, int nice) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.nice = nice;
    }

    /**
     * Returns the nombre of the process.
     *
     * @return the nombre of the process
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Returns the usuario associated with the process.
     *
     * @return the usuario associated with the process
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Returns the nice value of the process.
     *
     * @return the nice value of the process
     */
    public int getNice() {
        return nice;
    }

    /**
     * Compares this Proceso object with another Proceso object based on their nice values.
     *
     * @param otro the other Proceso object to compare with
     * @return a negative integer, zero, or a positive integer if this Proceso is less than, equal to, or greater than the specified Proceso object
     */
    @Override
    public int compareTo(Proceso otro) {
        // Comparamos los procesos según su valor nice
        return Integer.compare(this.nice, otro.nice);
    }
}
