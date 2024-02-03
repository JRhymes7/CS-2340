package com.example.myapplication;

public class Map {

    //private int currRound;
    private final Tile[][] map = new Tile[21][19];
    private final int[][] intMap =
            // 0: pellet, 1: wall, 2: power-up, 3: empty 4: pellet turn point
            // 5: empty turn point, 6: speed pellet
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} //0, 0 pellets
            ,{1, 0, 0, 0, 4, 0, 7, 0, 4, 1, 0, 0, 7, 0, 4, 0, 0, 0, 1} //1, 16 pellets
            ,{1, 2, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 2, 1} //2, 4 pellets
            ,{1, 4, 0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 4, 1} //3, 17 pellets
            ,{1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1} //4, 6 pellets
            ,{1, 0, 0, 0, 4, 1, 0, 0, 0, 1, 0, 0, 0, 1, 4, 0, 0, 0, 1} //5, 14 pellets
            ,{1, 1, 1, 1, 0, 1, 1, 1, 3, 1, 3, 1, 1, 1, 0, 1, 1, 1, 1} //6, 2 pellets
            ,{1, 1, 1, 1, 6, 1, 3, 3, 5, 3, 5, 3, 3, 1, 6, 1, 1, 1, 1} //7, 2 pellets
            ,{1, 1, 1, 1, 0, 1, 3, 1, 1, 1, 1, 1, 3, 1, 0, 1, 1, 1, 1} //8, 2 pellets
            ,{1, 1, 1, 1, 4, 3, 5, 1, 3, 5, 3, 1, 5, 3, 4, 1, 1, 1, 1} //9, 4 pellets
            ,{1, 1, 1, 1, 0, 1, 3, 1, 1, 1, 1, 1, 3, 1, 0, 1, 1, 1, 1} //10, 2 pellets
            ,{1, 1, 1, 1, 6, 1, 5, 3, 3, 3, 3, 3, 5, 1, 6, 1, 1, 1, 1} //11, 2 pellets
            ,{1, 1, 1, 1, 0, 1, 3, 1, 1, 1, 1, 1, 3, 1, 0, 1, 1, 1, 1} //12, 2 pellets
            ,{1, 0, 0, 0, 4, 0, 4, 0, 0, 1, 0, 0, 4, 0, 4, 0, 0, 0, 1} //13, 16 pellets
            ,{1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1} //14, 6 pellets
            ,{1, 2, 0, 1, 4, 0, 4, 0, 4, 3, 4, 0, 4, 0, 4, 1, 0, 2, 1} //15, 12 pellets
            ,{1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1} //16, 6 pellets
            ,{1, 0, 4, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 4, 0, 1} //17, 14 pellets
            ,{1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1} //18, 4 pellets
            ,{1, 0, 0, 0, 0, 7, 0, 0, 4, 0, 4, 0, 0, 7, 0, 0, 0, 0, 1} //19, 17 pellets
            ,{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}; //20
            //0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18
    public Map() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 19; j++) {
                map[i][j] = createNode(intMap[i][j]);
            }
        }
        System.out.println(intMap[9][1]);
    }

    public Tile createNode(int type) {
        switch (type) {
            default:
                //Pellet
                return new Tile(false, true);
            case 1:
                //Wall
                return new Tile(true, false, false);
            case 2:
                //Power-up
                return new Tile(false, true, false, Pellet.POWER);
            case 3:
                //Empty
                return new Tile(false, false);
            case 4:
                //Pellet turn
                return new Tile(false, true, true);
            case 5:
                //Empty turn
                return new Tile(false, false, true);
            case 6:
                //Speed pellet
                return new Tile(false, true, false, Pellet.SPEED);
            case 7:
                //Double points pellet
                return new Tile(false, true, false, Pellet.DOUBLE);
        }
    }

    public Tile[][] getMap() {
        return map;
    }
}
