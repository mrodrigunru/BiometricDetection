package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.FingerPrintImage;

public class ZhangSuen {
    final static int[][] nbrs = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 },
            { 0, -1 } };

    final static int[][][] nbrGroups = { { { 0, 2, 4 }, { 2, 4, 6 } }, { { 0, 2, 6 }, { 0, 4, 6 } } };

    static List<Point> toWhite = new ArrayList<>();

    public FingerPrintImage thinImage(FingerPrintImage imagenEntrada) {
        // FingerPrintImage imagenSalida = new
        // FingerPrintImage(imagenEntrada.getWidth(), imagenEntrada.getHeight());

        boolean firstStep = false;
        boolean hasChanged;

        do {
            hasChanged = false;
            firstStep = !firstStep;

            for (int r = 1; r < imagenEntrada.getWidth() - 1; r++) {
                for (int c = 1; c < imagenEntrada.getHeight() - 1; c++) {

                    if (imagenEntrada.getPixel(r, c) != 0)
                        continue;

                    int nn = numNeighbors(r, c, imagenEntrada);
                    if (nn < 2 || nn > 6)
                        continue;

                    if (numTransitions(r, c, imagenEntrada) != 1)
                        continue;

                    if (!atLeastOneIsWhite(r, c, firstStep ? 0 : 1, imagenEntrada))
                        continue;

                    toWhite.add(new Point(c, r));
                    hasChanged = true;
                }
            }

            for (Point p : toWhite)
                imagenEntrada.setPixel(p.y, p.x, (char) 1);
            toWhite.clear();

        } while (firstStep || hasChanged);

        return imagenEntrada;
    }

    static int numNeighbors(int r, int c, FingerPrintImage imagenEntrada) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (imagenEntrada.getPixel(r + nbrs[i][1], c + nbrs[i][0]) == 0)
                count++;
        return count;
    }

    static int numTransitions(int r, int c, FingerPrintImage imagenEntrada) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (imagenEntrada.getPixel(r + nbrs[i][1], c + nbrs[i][0]) == 1) {
                if (imagenEntrada.getPixel(r + nbrs[i + 1][1], c + nbrs[i + 1][0]) == 0)
                    count++;
            }
        return count;
    }

    static boolean atLeastOneIsWhite(int r, int c, int step, FingerPrintImage imagenEntrada) {
        int count = 0;
        int[][] group = nbrGroups[step];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < group[i].length; j++) {
                int[] nbr = nbrs[group[i][j]];
                if (imagenEntrada.getPixel(r + nbr[1], c + nbr[0]) == 1) {
                    count++;
                    break;
                }
            }
        return count > 1;
    }
}
