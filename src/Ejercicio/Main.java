package Ejercicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

// Clase principal del programa
public class Main {
    public static void main(String[] args) {
        // Solicitar datos del vendedor al usuario
        int idVendedor = obtenerEntero("Ingrese el ID del vendedor:");
        String nombreVendedor = obtenerCadena("Ingrese el nombre del vendedor:");
        String apellidoVendedor = obtenerCadena("Ingrese el apellido del vendedor:");
        String emailVendedor = obtenerCadena("Ingrese el email del vendedor:");

        // Crear el objeto del vendedor
        Persona vendedor = new Persona(idVendedor, nombreVendedor, apellidoVendedor, emailVendedor);

        // Crear la cadena de bloques para almacenar las transacciones
        Blockchain blockchain = new Blockchain();

        // Variable para controlar el bucle del menú
        boolean ejecutando = true;

        // Bucle del menú
        while (ejecutando) {
            // Mostrar opciones del menú
            String opcionStr = JOptionPane.showInputDialog(null,
                    "Menú de opciones:\n" +
                            "1. Agregar transacción\n" +
                            "2. Buscar transacción por fecha\n" +
                            "3. Modificar transacción\n" +
                            "4. Salir\n" +
                            "Seleccione una opción:");

            // Verificar si se ingresó la palabra "salir"
            if (opcionStr.equalsIgnoreCase("salir")) {
                ejecutando = false;
                continue;
            }

            // Convertir la opción a entero
            int opcion = obtenerEntero(opcionStr);

            // Realizar la acción correspondiente según la opción seleccionada
            switch (opcion) {
                case 1:
                    agregarTransaccion(vendedor, blockchain);
                    break;
                case 2:
                    buscarTransaccionPorFecha(blockchain);
                    break;
                case 3:
                    modificarTransaccion(vendedor, blockchain);
                    break;
                case 4:
                    ejecutando = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida del menú.");
            }
        }
    }

    // Método para solicitar un entero al usuario mediante una ventana emergente
    private static int obtenerEntero(String mensaje) {
        int numero;
        do {
            String input = JOptionPane.showInputDialog(null, mensaje);
            // Verificar si se ingresó la palabra "salir"
            if (input.equalsIgnoreCase("salir")) {
                return -1; // Salir del bucle
            }
            try {
                numero = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                numero = -1;
            }
            if (numero == -1) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido o 'salir'.");
            }
        } while (numero == -1);
        return numero;
    }

    // Método para solicitar una cadena al usuario mediante una ventana emergente
    private static String obtenerCadena(String mensaje) {
        String cadena;
        do {
            cadena = JOptionPane.showInputDialog(null, mensaje);
            // Verificar si se ingresó la palabra "salir"
            if (cadena.equalsIgnoreCase("salir")) {
                return null; // Salir del bucle
            }
            if (cadena == null || cadena.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido o 'salir'.");
            }
        } while (cadena == null || cadena.trim().isEmpty());
        return cadena;
    }

    // Método para agregar una transacción
    private static void agregarTransaccion(Persona vendedor, Blockchain blockchain) {
        // Solicitar datos del producto al usuario
        int idProducto = obtenerEntero("Ingrese el ID del producto:");
        if (idProducto == -1) return; // Salir si se ingresa "salir"

        String nombreProducto = obtenerCadena("Ingrese el nombre del producto:");
        if (nombreProducto == null) return; // Salir si se ingresa "salir"

        double precioProducto = Double.parseDouble(obtenerCadena("Ingrese el precio del producto:"));
        if (precioProducto == -1) return; // Salir si se ingresa "salir"

        int cantidadProducto = obtenerEntero("Ingrese la cantidad del producto:");
        if (cantidadProducto == -1) return; // Salir si se ingresa "salir"

        // Solicitar datos del comprador al usuario
        int idComprador = obtenerEntero("Ingrese el ID del comprador:");
        if (idComprador == -1) return; // Salir si se ingresa "salir"

        String nombreComprador = obtenerCadena("Ingrese el nombre del comprador:");
        if (nombreComprador == null) return; // Salir si se ingresa "salir"

        String apellidoComprador = obtenerCadena("Ingrese el apellido del comprador:");
        if (apellidoComprador == null) return; // Salir si se ingresa "salir"

        String emailComprador = obtenerCadena("Ingrese el email del comprador:");
        if (emailComprador == null) return; // Salir si se ingresa "salir"

        // Crear el objeto del comprador
        Persona comprador = new Persona(idComprador, nombreComprador, apellidoComprador, emailComprador);

        // Crear la transacción
        Transaccion transaccion = new Transaccion(idProducto, nombreProducto, precioProducto, cantidadProducto, comprador, vendedor);

        // Agregar la transacción a la cadena de bloques
        blockchain.agregarTransaccion(transaccion);

        JOptionPane.showMessageDialog(null, "Transacción agregada correctamente.");
    }

    // Método para buscar una transacción por fecha
    private static void buscarTransaccionPorFecha(Blockchain blockchain) {
        // Solicitar al usuario una fecha para filtrar las transacciones
        String fechaString = JOptionPane.showInputDialog(null, "Ingrese la fecha para filtrar las transacciones (formato: dd/MM/yyyy):");
        Date fechaFiltro = new Date();
        try {
            // Parsear la fecha usando SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fechaFiltro = dateFormat.parse(fechaString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Se mostrarán todas las transacciones.");
            return;
        }

        // Mostrar resumen de las transacciones filtradas por fecha
        blockchain.resumenTransaccionesPorFecha(fechaFiltro);
    }

    // Método para modificar una transacción existente
    private static void modificarTransaccion(Persona vendedor, Blockchain blockchain) {
        // Solicitar al usuario el índice de la transacción a modificar
        int indiceModificar = obtenerEntero("Ingrese el índice de la transacción a modificar:");
        if (indiceModificar == -1) return; // Salir si se ingresa "salir"

        // Verificar si el índice proporcionado es válido
        if (indiceModificar >= 0 && indiceModificar < blockchain.transacciones.size()) {
            // Solicitar al usuario los nuevos datos para la transacción
            agregarTransaccion(vendedor, blockchain);
            // Eliminar la transacción antigua
            blockchain.transacciones.remove(indiceModificar);
            JOptionPane.showMessageDialog(null, "Transacción modificada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El índice de la transacción no es válido.");
        }
    }
}