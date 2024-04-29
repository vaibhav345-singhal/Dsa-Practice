package Reflection;

public class Eagle {

    private Eagle() {
    }

    public String breed;
    private boolean canSwim;

    public void fly() {
        System.out.println("fly");
    }

    public void eat(String insect) {
        System.out.println("eating " + insect);
    }

    private void sleep() {
        System.out.println("sleep");
    }

    public void doSome(String breed, boolean canSwim, int height) {
        System.out.println(" breed is " + breed + " can swim " + canSwim + " fly height " + height);
    }
}
