package me.kamyx.stock;

import java.util.ArrayList;
import java.util.Random;

public class Maths {


    public static double mean(ArrayList<Double> list) {
        double sum = 0;
        for (double num : list) {
            sum += num;
        }
        return sum / list.size();
    }
    public static double simulatePrice(double currentPrice, double meanReturn, double stdReturn, double timeStep) {
        Random random = new Random();
        double z = random.nextGaussian(); // Zmienna losowa o rozkładzie normalnym standardowym
        double exponent = (meanReturn - 0.5 * Math.pow(stdReturn, 2)) * timeStep + stdReturn * Math.sqrt(timeStep) * z;
        return currentPrice * Math.exp(exponent);
    }

    // Metoda do obliczania odchylenia standardowego z listy liczb
    public static double standardDeviation(ArrayList<Double> list) {
        double mean = mean(list);
        double sum = 0;
        for (double num : list) {
            sum += Math.pow(num - mean, 2);
        }
        return Math.sqrt(sum / list.size());
    }

//    public static double meanReturn(ArrayList<Double> list) {
//        ArrayList<Double> returns = new ArrayList<>();
//        for (int i = 1; i < list.size(); i++) {
//            double currentPrice = list.get(i);
//            double previousPrice = list.get(i - 1);
//            double returnRate = (currentPrice - previousPrice) / previousPrice;
//            returns.add(returnRate);
//        }
//        return mean(returns);
//    }
    public static double meanReturn(ArrayList<Double> list) {
        ArrayList<Double> returns = new ArrayList<>(); // Lista stóp zwrotu
        for (int i = 1; i < list.size(); i++) {
        double returnRate = Math.log(list.get(i) / list.get(i - 1)); // Oblicz stopę zwrotu
        returns.add(returnRate); // Dodaj stopę zwrotu do listy
        }
    return mean(returns); // Oblicz średnią z listy stóp zwrotu
    }






}
