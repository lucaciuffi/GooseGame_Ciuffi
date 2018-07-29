/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenario;

/**
 *
 * @author LUCA
 */
public class Box {

    private int n;
    private boolean busy;

    public Box(int n) {
        this.n = n;
        this.busy = false;
    }

    public int getN() {
        return n;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "Box{" + "n=" + n + ", busy=" + busy + '}';
    }

}
