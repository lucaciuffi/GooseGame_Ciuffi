/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author LUCA
 */
public class Billboard {

    private static final int dimension = 63;
    private static final int bridge = 6;
    private static final int[] goose = {5, 9, 14, 18, 23, 27};
    private static final ArrayList<Integer> gooseArray = new ArrayList<>();
    private TreeMap<Integer, Box> billboard = new TreeMap<>();
    private static Billboard theInstance;

    public TreeMap<Integer, Box> getBillboard() {
        return billboard;
    }

    public ArrayList<Integer> getGooseArray() {
        return gooseArray;
    }

    public static Billboard getInstance() {
        if (theInstance == null) {
            theInstance = new Billboard();
        }
        return theInstance;
    }

    public int getDimension() {
        return dimension;
    }

    public int getBridge() {
        return bridge;
    }

//5, 9, 14, 18, 23, 27
    public boolean checkBox(int n) {
        boolean busy = billboard.get(n).isBusy();
        return busy;
    }

    public void initBillboard() {

        for (int i = 0; i < goose.length; i++) {
            gooseArray.add(goose[i]);

        }

        this.billboard = new TreeMap<>();
        for (int i = 0; i < dimension; i++) {
            Box box = new Box(i);
            billboard.put(i, box);
        }
    }

    public void showbill() {
        System.out.println("" + gooseArray.size());
        for (Iterator<Integer> iterator = gooseArray.iterator(); iterator.hasNext();) {
            System.out.println("" + iterator.toString());
            Integer next = iterator.next();
            System.out.println("" + next.toString());
        }
        System.out.println("" + billboard.size());

    }
}
