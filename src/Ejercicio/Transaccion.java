package Ejercicio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Clase para representar una transacción de venta
class Transaccion {
    private int idProducto;
    private String nombreProducto;
    private double precio;
    private int cantidad;
    private Persona comprador;
    private Persona vendedor;
    private Date fecha;
    private String hash;

    // Constructor de la transacción
    public Transaccion(int idProducto, String nombreProducto, double precio, int cantidad, Persona comprador, Persona vendedor) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha = new Date();
        this.hash = calcularHash();
    }

    // Método para calcular el hash de la transacción
    private String calcularHash() {
        // Formatear la fecha usando SimpleDateFormat para asegurar un formato específico
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String datosAHash = idProducto + nombreProducto + precio + cantidad + comprador.getId() + vendedor.getId() + dateFormat.format(fecha);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(datosAHash.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener el hash de la transacción
    public String getHash() {
        return hash;
    }

    // Método para obtener la fecha de la transacción
    public Date getFecha() {
        return fecha;
    }

    // Método para obtener la fecha de la transacción
    public Persona getComprador() {
        return comprador;
    }

    // Método para imprimir el resumen de la transacción
    public void imprimirResumen() {
        System.out.println("ID Producto: " + idProducto);
        System.out.println("Nombre Producto: " + nombreProducto);
        System.out.println("Precio: " + precio);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Comprador: " + comprador.getNombre() + " " + comprador.getApellido());
        System.out.println("Vendedor: " + vendedor.getNombre() + " " + vendedor.getApellido());
        System.out.println("Fecha: " + fecha);
        System.out.println("Hash: " + hash);
    }
}