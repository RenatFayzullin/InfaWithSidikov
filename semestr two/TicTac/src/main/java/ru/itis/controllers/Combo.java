package ru.itis.controllers;

public class Combo {
    public MainController.Tile[] tiles;
    public Combo (MainController.Tile...tiles){
        this.tiles = tiles;
    }
    public boolean isComplete(){
        if ((tiles[0].getValue().isEmpty())||(tiles[1].getValue().isEmpty())||(tiles[2].getValue().isEmpty())){
            return false;
        }
        return tiles[0].getValue().equals(tiles[1].getValue())
                &&tiles[0].getValue().equals(tiles[2].getValue());
    }
}
