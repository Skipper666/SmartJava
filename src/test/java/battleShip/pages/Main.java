package battleShip.pages;

import battleShip.enums.Notifications;

public class Main {

    public static void main(String[] args) {

        int[][] firingOrder = new int[10][10];
        for (int i = 3; i <= 15; i += 4) {
            int x = 0, y = i;
            while (x < 10 && y >= 0) {
                if (x >= 0 && y >= 0 && x < 10 && y < 10) firingOrder[x][y] = 1;
                x++;
                y--;
            }
        }

        for (int i = 1; i <= 15; i += 4) {
            int x = 0, y = i;
            while (x < 10 && y >= 0) {
                if (x >= 0 && y >= 0 && x < 10 && y < 10) firingOrder[x][y] = 2;
                x++;
                y--;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(firingOrder[i][j] != 2 && firingOrder[i][j] !=1)
                    firingOrder[i][j]=3;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + firingOrder[i][j] + " ");
            }
            System.out.println();
        }


    }
}
