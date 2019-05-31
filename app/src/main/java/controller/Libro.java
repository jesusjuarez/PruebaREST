package controller;

public class Libro {

    private String id;
    private String nombre;
    private float costo;
    private int numInventario;
    private String descripcion;


    public Libro(String id, String nombre, float costo, int numInventario, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.numInventario= numInventario;
        this.descripcion= descripcion;
    }

    public Libro(){
        this.nombre="No encontrado";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getNumInventario() {
        return numInventario;
    }

    public void setNumInventario(int numInventario) {
        this.numInventario = numInventario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
