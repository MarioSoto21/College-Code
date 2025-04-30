package controllers;

import databases.LayoutDatabase;
import objects.Room;

public class LayoutController {
    private LayoutDatabase layout;

    public LayoutController(LayoutDatabase layout) {
        this.layout = layout;
    }

    public int getRoomNumber(int floor, int row, int col) {
        return layout.getRoomNumber(floor, row, col);
    }

    public void setRoomNumber(int floor, int row, int col, int roomNumber) {
        layout.setRoomNumber(floor, row, col, roomNumber);
    }

    public void removeRoomNumber(int roomNumber){
        int[][][] tempLayout = layout.getRoomLayout();
        for (int floor = 0; floor < tempLayout.length; floor++) {
            for (int row = 0; row < tempLayout[floor].length; row++) {
                for (int col = 0; col < tempLayout[floor][row].length; col++) {
                    if (tempLayout[floor][row][col] == roomNumber) {
                        setRoomNumber(floor, row, col, 0);
                    }
                }
            }
        }
    }

    public void addFloor(){
        RoomController roomController = Controller.getInstance().getRoomController();
        int[][][] preLayout = layout.getRoomLayout();
        int[][][] postLayout = new int[preLayout.length+1][preLayout[0].length][preLayout[0][0].length];
        for (int floor = 0; floor < preLayout.length; floor++) {
            for (int row = 0; row < preLayout[floor].length; row++) {
                for (int col = 0; col < preLayout[floor][row].length; col++) {
                    postLayout[floor][row][col] = preLayout[floor][row][col];
                }
            }
        }
        int floor = preLayout.length;
        for (int row = 0; row < postLayout[floor].length; row++) {
            for (int col = 0; col < postLayout[floor][row].length; col++) {
                if (postLayout[floor-1][row][col] != 0){
                    postLayout[floor][row][col] = postLayout[floor-1][row][col] + 100;
                    if(roomController.getRoom(postLayout[floor][row][col])==null){
                        Room room = new Room(0, postLayout[floor][row][col], 0, true, false, 0);
                        roomController.addRoom(room);
                    }
                } else {
                    postLayout[floor][row][col] = 0;
                }
            }
        }
        this.layout.setRoomLayout(postLayout);
    }

    public void removeFloor(){
        RoomController roomController = Controller.getInstance().getRoomController();
        int[][][] preLayout = layout.getRoomLayout();
        int[][][] postLayout = new int[preLayout.length-1][preLayout[0].length][preLayout[0][0].length];
        for (int floor = 0; floor < postLayout.length; floor++) {
            for (int row = 0; row < postLayout[floor].length; row++) {
                for (int col = 0; col < postLayout[floor][row].length; col++) {
                    postLayout[floor][row][col] = preLayout[floor][row][col];
                }
            }
        }
        for (int row = 0; row < preLayout[0].length; row++) {
            for (int col = 0; col < preLayout[0][row].length; col++) {
                roomController.removeRoom(preLayout[preLayout.length-1][row][col]);
            }
        }
        this.layout.setRoomLayout(postLayout);
    }
}
