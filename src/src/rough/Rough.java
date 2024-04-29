package rough;

public class Rough {
    public static void main(String[] args) {

        long a = 10;
        int b = (int) a;
        System.out.printf("%d %d", a, b);

        int x = 320;
        short y = (short) x;
        System.out.printf(" %d %d ", x, y);

        char z = 'a';
        int c = z;

        System.out.printf(" %s %d \n", z, c);

        int d = 'a' + 3;
        System.out.println(d);

        char dd = 'a' + 3;
        System.out.println(dd);

        Integer q = 10;
        modify(q);
        System.out.println(q);
    }

    public static void modify(Integer a){
        a = 20;
    }
}
