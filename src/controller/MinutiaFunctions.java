package controller;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.FingerPrintImage;
import model.Minutia;

public class MinutiaFunctions {
    final static int[][] nbrs = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 },
            { 0, -1 } };
    final static int[][][] nbrGroups = { { { 0, 2, 4 }, { 2, 4, 6 } }, { { 0, 2, 6 }, { 0, 4, 6 } } };

    public void CN(FingerPrintImage imgE) {
        int[] vecinos = new int[9];
        int contador;
        int sum;
        int result = 0;
        contador = 0;
        for (int x = 1; x < imgE.getWidth() - 1; ++x) {
            for (int y = 1; y < imgE.getHeight() - 1; ++y) {
                sum = 0;
                if (imgE.getPixel(x, y) == 0) {
                    // obtener los vecinos del matriz de vecindad
                    vecinos[0] = imgE.getPixel(x + 1, y);
                    vecinos[1] = imgE.getPixel(x + 1, y - 1);
                    vecinos[2] = imgE.getPixel(x, y - 1);
                    vecinos[3] = imgE.getPixel(x - 1, y - 1);
                    vecinos[4] = imgE.getPixel(x - 1, y);
                    vecinos[5] = imgE.getPixel(x - 1, y + 1);
                    vecinos[6] = imgE.getPixel(x, y + 1);
                    vecinos[7] = imgE.getPixel(x + 1, y + 1);
                    vecinos[8] = imgE.getPixel(x + 1, y);

                    // formula del sumatorio de los vecinos
                    for (int k = 0; k < 8; k++) {
                        sum = sum + Math.abs(vecinos[k] - vecinos[k + 1]);
                    }
                    result = sum / 2;
                    if (result == 1 || result == 3) {
                        Minutia m = new Minutia(x, y, result);
                        imgE.addMinutia(m);
                        contador++;
                    }
                }
            }
        }
    }

    public void calcularGrad(Minutia m, int xi, int yi, int xf, int yf) {

        double gradX = xf - xi;
        double gradY = yf - yi;

        double grados = 0;
        if (gradX != 0) {
            double result = (-gradY / gradX);
            double radianes = Math.atan(result);
            grados = Math.toDegrees(radianes);
        }

        m.setAngulo(grados);
    }

    public void getVecinos( FingerPrintImage imagenEntrada, int x, int y, ArrayList<Point> vis, ArrayList<Point> aux) {
        int[] vecinos = new int[8];
        Point[] puntos = new Point[8];

        puntos[0] = new Point(x + 1, y);
        puntos[1] = new Point(x, y + 1);
        puntos[2] = new Point(x - 1, y);
        puntos[3] = new Point(x, y - 1);
        puntos[4] = new Point(x + 1, y - 1);
        puntos[5] = new Point(x - 1, y - 1);
        puntos[6] = new Point(x - 1, y + 1);
        puntos[7] = new Point(x + 1, y + 1);

        vecinos[0] = imagenEntrada.getPixel(x + 1, y);
        vecinos[1] = imagenEntrada.getPixel(x, y + 1);
        vecinos[2] = imagenEntrada.getPixel(x - 1, y);
        vecinos[3] = imagenEntrada.getPixel(x, y - 1);
        vecinos[4] = imagenEntrada.getPixel(x + 1, y - 1);
        vecinos[5] = imagenEntrada.getPixel(x - 1, y - 1);
        vecinos[6] = imagenEntrada.getPixel(x - 1, y + 1);
        vecinos[7] = imagenEntrada.getPixel(x + 1, y + 1);

        for (int i = 0; i < 8; i++) {
            if (vecinos[i] == 1 && !vis.contains(vecinos[i])) {
                aux.add(puntos[i]);
            }
        }

    }

    public void calcularAngulo(FingerPrintImage imagenEntrada) {

        ArrayList<Point> visitados = new ArrayList<Point>();
        ArrayList<Point> aux = new ArrayList<Point>();
        ArrayList<Point> vecinos = new ArrayList<Point>();
        int xf = 0;
        int yf = 0;

        for (Minutia m : imagenEntrada.getMinutias()) {
            int xi = m.getX();
            int yi = m.getY();
            Point p = new Point(xi, yi);
            visitados.clear();
            visitados.add(p);
            getVecinos( imagenEntrada,xi, yi, visitados, aux);
            while (!vecinos.isEmpty()) {
                xf = vecinos.get(0).x;
                yf = vecinos.get(0).y;
                vecinos.remove(0);
                for (int i = 0; i < 6; i++) {
                    Point nuevo = new Point(xf, yf);
                    visitados.add(nuevo);
                    aux.clear();
                    getVecinos(imagenEntrada,xf, yf, visitados, aux);
                }

            }
            calcularGrad(m, xi, yi, xf, yf);
        }
    }



}
