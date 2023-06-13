package model;

import java.util.ArrayList;
import java.util.List;

public class FingerPrintImage {
    private int width;
    private int height;
    private char[][] img;
    private int minGray;
    private int maxGray;
    private int averageGray;
    private ArrayList<Minutia> listMinutia ;



    public char[][] getImg() {
        return img;
    }
    public void setImg(char[][] img) {
        this.img = img;
    }
    public int getMinGray() {
        return minGray;
    }
    public void setMinGray(int minGray) {
        this.minGray = minGray;
    }
    public int getMaxGray() {
        return maxGray;
    }
    public void setMaxGray(int maxGray) {
        this.maxGray = maxGray;
    }
    public int getAverageGray() {
        return averageGray;
    }
    public void setAverageGray(int averageGray) {
        this.averageGray = averageGray;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public FingerPrintImage(int width, int height) {
        this.height = height;
        this.width = width;
        img = new char[width][height];
        minGray = 0;
        maxGray = 0;
        averageGray = 0;
        listMinutia = new ArrayList<Minutia>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setPixel(int x, int y, int color) {
        img[x][y] = (char) color;
    }

    public char getPixel(int x, int y) {
        return img[x][y];
    }


    public ArrayList<Minutia> getMinutias() {
        return listMinutia;
    }

    public void setMinutias(ArrayList<Minutia> minutias) {
        this.listMinutia = minutias;
    }

    public void addMinutia (Minutia m) {
        listMinutia.add(m);
    }
}