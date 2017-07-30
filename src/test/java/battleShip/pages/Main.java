package battleShip.pages;

import battleShip.enums.Notifications;

public class Main {

    public static void main(String[] args) {

        int[][] mass = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mass[i][j] = 0;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + mass[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------");


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + mass[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(Notifications.INIT.notification());
        System.out.println(Notifications.INIT);
        System.out.println(test());
    }

    public static boolean test() {
        return 1 > 2 ||
                1 > 3 ||
                1 > 3 ||
                1 == 2;
    }
}
