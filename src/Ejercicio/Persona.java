package Ejercicio;

// Clase para representar una persona
class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    // Constructor de la persona
    public Persona(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Getters de los atributos de la persona
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
}