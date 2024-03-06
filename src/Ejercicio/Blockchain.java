package Ejercicio;

import java.util.ArrayList;
import java.text.SimpleDateFormat; // Importar SimpleDateFormat para formatear la fecha
import java.util.Date;

// Clase para representar la cadena de bloques
class Blockchain {
    public ArrayList<Transaccion> transacciones;

    // Constructor de la cadena de bloques
    public Blockchain() {
        this.transacciones = new ArrayList<>();
    }

    // Método para agregar una transacción a la cadena de bloques
    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    // Método para obtener todas las transacciones realizadas en una fecha específica
    public void resumenTransaccionesPorFecha(Date fecha) {
        // Formatear la fecha para comparación
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Transacciones realizadas el " + dateFormat.format(fecha) + ":");
        for (Transaccion transaccion : transacciones) {
            // Comparar solo las fechas sin la hora
            if (dateFormat.format(transaccion.getFecha()).equals(dateFormat.format(fecha))) {
                transaccion.imprimirResumen();
                System.out.println("-------------------------");
            }
        }
    }

    // Método para intentar modificar una transacción existente
    public void modificarTransaccion(int indice, Transaccion nuevaTransaccion) {
        if (indice >= 0 && indice < transacciones.size()) {
            transacciones.set(indice, nuevaTransaccion);
            System.out.println("Transacción modificada correctamente.");
        } else {
            System.out.println("El índice de la transacción no es válido.");
        }
    }
}