package controller;

import java.util.ArrayList;
import java.util.List;

import model.FingerPrintImage;

public class ImageFunctions {

    public FingerPrintImage histogram(FingerPrintImage imagenEntrada) {
        FingerPrintImage imagenSalida = new FingerPrintImage(imagenEntrada.getWidth(), imagenEntrada.getHeight());

        int width = imagenEntrada.getWidth();
        int height = imagenEntrada.getHeight();
        int tampixel = width * height;
        int[] histograma = new int[256];
        int i = 0;
        // Calculamos frecuencia relativa de ocurrencia
        // de los distintos niveles de gris en la imagen
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int valor = imagenEntrada.getPixel(x, y);
                histograma[valor]++;
            }
        }

        int sum = 0;
        // Construimos la Lookup table LUT
        float[] lut = new float[256];
        for (i = 0; i < 256; ++i) {
            sum += histograma[i];
            lut[i] = sum * 255 / tampixel;
        }
        // Se transforma la imagen utilizando la tabla LUT
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int valor = imagenEntrada.getPixel(x, y);
                int valorNuevo = (int) lut[valor];
                imagenSalida.setPixel(x, y, (char) valorNuevo);
            }
        }

        return imagenSalida;

    }

    public int[] calculoNivelesGris(FingerPrintImage imagenEntrada) {
        int[] grises = new int[3];

        int max = 0, min = 255, medium = 0, sum = 0;
        for (int y = 0; y < imagenEntrada.getHeight(); y++) {
            for (int x = 0; x < imagenEntrada.getWidth(); x++) {
                int level = imagenEntrada.getPixel(x, y);
                if (level > max) {
                    max = level;
                }
                if (min > level) {
                    min = level;
                }
                sum = sum + level;
            }
        }

        medium = (sum / (imagenEntrada.getHeight() * imagenEntrada.getWidth()));
        grises[0] = max;
        grises[1] = min;
        grises[2] = medium;

        return grises;
    }

    public FingerPrintImage ecualizado(FingerPrintImage imagenEntrada) {
        FingerPrintImage imagenSalida = new FingerPrintImage(imagenEntrada.getWidth(), imagenEntrada.getHeight());

        for (int x = 0; x < imagenEntrada.getWidth(); ++x) {
            for (int y = 0; y < imagenEntrada.getHeight(); ++y) {
                if (imagenEntrada.getPixel(x, y) >= imagenEntrada.getAverageGray()) {
                    imagenSalida.setPixel(x, y, (char) 1);
                } else {
                    imagenSalida.setPixel(x, y, (char) 0);
                }
            }
        }

        return imagenSalida;
    }

    public FingerPrintImage BinaryFilter1(FingerPrintImage imagenentrada) {
        FingerPrintImage imagenSalida = new FingerPrintImage(imagenentrada.getWidth(), imagenentrada.getHeight());
        int punto = 0, p = 0, b = 0, d = 0, e = 0, g = 0;
        for (int x = 1; x < imagenentrada.getWidth() - 1; x++) {
            for (int y = 1; y < imagenentrada.getHeight() - 1; y++) {
                p = imagenentrada.getPixel(x, y);
                b = imagenentrada.getPixel(x, y - 1);
                d = imagenentrada.getPixel(x - 1, y);
                e = imagenentrada.getPixel(x + 1, y);
                g = imagenentrada.getPixel(x, y + 1);

                punto = (char) (p | b & g & (d | e) | d & e & (b | g));
                imagenSalida.setPixel(x, y, punto);
            }
        }
        return imagenSalida;
    }

    public FingerPrintImage BinaryFilter2(FingerPrintImage imagenentrada) {
        FingerPrintImage imagenSalida = new FingerPrintImage(imagenentrada.getWidth(), imagenentrada.getHeight());
        int punto = 0, p = 0, b = 0, d = 0, e = 0, g = 0, a = 0, h = 0, c = 0, f = 0;
        for (int x = 1; x < imagenentrada.getWidth() - 1; x++) {
            for (int y = 1; y < imagenentrada.getHeight() - 1; y++) {
                p = imagenentrada.getPixel(x, y);

                a = imagenentrada.getPixel(x-1, y-1);
                b = imagenentrada.getPixel(x, y - 1);
                c = imagenentrada.getPixel(x + 1, y - 1);
                d = imagenentrada.getPixel(x - 1, y);
                e = imagenentrada.getPixel(x + 1, y);
                f = imagenentrada.getPixel(x - 1, y + 1);
                g = imagenentrada.getPixel(x, y + 1);
                h = imagenentrada.getPixel(x + 1, y + 1);

                punto = (p & ((a | b | d) & (e | g | h) | (b | c | e) & (d | f | g)));

                imagenSalida.setPixel(x, y, (char) punto);
            }
        }
        return imagenSalida;
    }

}
