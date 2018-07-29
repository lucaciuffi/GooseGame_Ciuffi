/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;

/**
 *
 * @author LUCA
 */
public class Dice {

    private int launch1;
    private int launch2;

    public Dice() {
        this.launch1 = launch1;
        this.launch2 = launch2;
    }

    public int getLaunch1() {
        return launch1;
    }

    public int getLaunch2() {
        return launch2;
    }

    public void diceLaunch() {
        Random random = new Random();
        int n = 5;
        int k = random.nextInt(n) + 1;
        launch1 = k;
        int j = random.nextInt(n) + 1;
        launch2 = j;
    }
}
