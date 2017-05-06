package TD1;

public class Main {

    public static void main(String[] args) {
        Client c1 = new Client(1);
        Client c2 = new Client(2);
        Client c3 = new Client(3);
        Client c4 = new Client(4);
        Client c5 = new Client(5);
        Client c6 = new Client(6);
        Client c7 = new Client(7);
        Client c8 = new Client(8);
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
    }
}
