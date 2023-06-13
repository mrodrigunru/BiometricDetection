package controller;
import java.awt.image.BufferedImage;

import model.FingerPrintImage;
import model.Minutia;

public class ColorUtility {


    public FingerPrintImage convert2GrayB(BufferedImage imagenentrada) {
        FingerPrintImage imagensalida = new FingerPrintImage(imagenentrada.getWidth(), imagenentrada.getHeight());
        {
            for (int x = 0; x < imagenentrada.getWidth(); ++x) {
                for (int y = 0; y < imagenentrada.getHeight(); ++y) {
                    int rgb = imagenentrada.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb & 0xFF);
                    int nivelGris =  (int) (0.2126*r + 0.7152*g + 0.0722*b);
                    imagensalida.setPixel(x, y, (char) nivelGris);
                }
            }
            return imagensalida;
        }
    }
    public FingerPrintImage convert2GrayA(BufferedImage imagenentrada) {
        FingerPrintImage imagensalida = new FingerPrintImage(imagenentrada.getWidth(), imagenentrada.getHeight());
        {
            for (int x = 0; x < imagenentrada.getWidth(); ++x) {
                for (int y = 0; y < imagenentrada.getHeight(); ++y) {
                    int rgb = imagenentrada.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb & 0xFF);
                    int nivelGris = (r + g + b) / 3;
                    imagensalida.setPixel(x, y, (char) nivelGris);
                }
            }
            return imagensalida;
        }
    }

    public BufferedImage convert2RGB(FingerPrintImage imagenentrada, int modo) {
        BufferedImage imagensalida = new BufferedImage(imagenentrada.getWidth(), imagenentrada.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < imagenentrada.getWidth(); ++x) {
            for (int y = 0; y < imagenentrada.getHeight(); ++y) {
                int valor = imagenentrada.getPixel(x, y);
                if (modo == 0) {
                    valor = valor * 255;
                }
                int pixelRGB = (255 << 24 | valor << 16 | valor << 8 | valor);
                imagensalida.setRGB(x, y, pixelRGB);
            }
        }
        return imagensalida;
    }

    public BufferedImage colorearMinutias(BufferedImage imagenEntrada, FingerPrintImage minutias) {
        for (Minutia m : minutias.getMinutias()) {
            if (m.getTipo() == 1)
            {
                int red = (255 << 24 | 255 << 16 | 0 << 8 | 0);
                imagenEntrada.setRGB(m.getX(), m.getY(), red);
            }
            if (m.getTipo() == 3) {
                int blue = (255 << 24 | 0 << 16 | 0 << 8 | 255);
                imagenEntrada.setRGB(m.getX(), m.getY(), blue);
            }
        }
        return imagenEntrada;
    }

}
