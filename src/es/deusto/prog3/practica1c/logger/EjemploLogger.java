/**
 * This code has been adapted from this github repo:
 * https://github.com/unaguil/prog3-ejemplos-codigo/tree/master/tema1/tema1C
 * 
 * Thanks to: Unai Aguilera (https://github.com/unaguil) 
 */
package es.deusto.prog3.practica1c.logger;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// En este ejemplo se muestra la utilización de las classes
// de logging de Java para sacar información de traza. Se recomienda
// utilizar esta opción para proporcionar información del programa en vez
// de utilizar la salida directa por consola con System.out.

public class EjemploLogger {

    // Creamos el logger pasando como parámetro el nombre de la clase actual
    private static Logger logger = Logger.getLogger(EjemploLogger.class.getName());

    
    
    
    // Niveles de importancia para el log:
	// FINEST / FINER / FINE / CONFIG / INFO / WARNING / SEVERE
	// Por defecto se muestran en consola de error sólo INFO-WARNING-SEVERE
    
    public static void main(String[] args) {
        // Cambios el nivel de salida del log para sacar los mensajes
        // FINE-CONFIG-INFO-WARNING-SEVERE
        // Aunque se puede configurar por código el nivel del logger, lo usual es 
        // hacerlo en un fichero externo 
        // Logger.getLogger("").setLevel(Level.SEVERE);
        // Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);

        try (FileInputStream fis = new FileInputStream("config/logger.properties")) {
            LogManager.getLogManager().readConfiguration(fis);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se pudo leer el fichero de configuración del logger");
        }

        // Empieza el programa y se añaden un mensaje de nivel INFO.
        logger.info("Programa comenzado");

        // Desde el bucle se añaden al log mensajes de nivel FINE
        for (int i = 0; i < 10; i++) {
            logger.log(Level.FINE, String.format("Voy por la iteración %d", i));
        }

        // Se intenta abrir un fichero que no existe para provocar un error
        try (FileInputStream fis = new FileInputStream("noexiste.txt")) {

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "No se ha encontrado el fichero 'noexiste.txt");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se ha encontrado el fichero 'noexiste.txt");
        }

        logger.info("Programa finalizado");
    }
}
