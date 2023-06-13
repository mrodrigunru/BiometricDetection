package controller;

import java.awt.image.BufferedImage;

import model.FingerPrintImage;

public class main {

    static Utils utils = new Utils();
    static ColorUtility cu = new ColorUtility();
    static ImageFunctions imf = new ImageFunctions();
    static ZhangSuen zs = new ZhangSuen();
    static MinutiaFunctions mn = new MinutiaFunctions();

    public static void main(String[] args) throws Exception {

        // Cargar imagen
        BufferedImage imagenentrada = null;
        imagenentrada = utils.loadImage();
        System.out.println("La imagen se ha cargado correctamente");



        // Convertir la imagen de RGB a matriz de grises
        FingerPrintImage img = cu.convert2GrayB(imagenentrada);

        // Histograma de la imagen en gris
        FingerPrintImage imgHistograma ;
        imgHistograma = imf.histogram( img);


        // Calcular valores minimos, maximos y medios de grises
        int[] values;

        values = imf.calculoNivelesGris(imgHistograma);

        imgHistograma.setAverageGray(values[2]);
        imgHistograma.setMaxGray(values[2]);
        imgHistograma.setMinGray(values[1]);

        // Umbralizacion de la imagen
        FingerPrintImage salidaUmbral = imf.ecualizado(imgHistograma);


        // Aplicar filtrados
        FingerPrintImage filteredImage;
        filteredImage = imf.BinaryFilter1(salidaUmbral);
        filteredImage = imf.BinaryFilter2(salidaUmbral);

        // aplicar juan zen

        FingerPrintImage zsImg;
        zsImg = zs.thinImage(filteredImage);




        mn.CN(zsImg);
        mn.calcularAngulo(zsImg);

        // convertir la imgaen en ByN a RGB y pintar las minutias
        BufferedImage imagenFinal = cu.convert2RGB(zsImg, 0);
        BufferedImage imagenFinalColor = cu.colorearMinutias(imagenFinal, zsImg);



        utils.saveImage(imagenFinalColor, "imagen_Coloreada.png");

    }

}
