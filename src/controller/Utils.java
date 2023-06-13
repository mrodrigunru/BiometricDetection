package controller;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * @author David Salguero Carrasco
 *
 */
public class Utils {

    /**
     * Carga una imagen según la ruta proporcionada.
     * @return imagensalida - Imagen cargada.
     * @throws Exception
     */
    public BufferedImage loadImage() throws Exception {
        System.out.println("Elija la imagen que quiere transformar (debe incluir la extensión de la misma y estar almacenada en la carpeta principal (src)):");
        String fichero;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        fichero = buffer.readLine();
        BufferedImage imagenentrada = ImageIO.read(new File("src/"+fichero));
        System.out.println("Imagen "+ fichero +" cargada");
        return imagenentrada;
    }

    /**
     * Almacena una imagen con el nombre indicado.
     * @param imagensalida - Imagen almacenada
     * @param nombre - Nombre de la imagen almacenada
     * @throws Exception
     */
    public void saveImage(BufferedImage imagensalida, String nombre) throws Exception {
        File outputfile = new File(nombre);
        ImageIO.write(imagensalida, "png", outputfile);
        System.out.println("Imagen "+ nombre +" almacenada");
    }
}
