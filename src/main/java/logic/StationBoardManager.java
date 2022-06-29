package logic;

import apiHandler.StationBoardHandler;
import dto.JourneyDTO;

import java.util.ArrayList;

public class StationBoardManager {

    StationBoardHandler api = new StationBoardHandler();

    public ArrayList<JourneyDTO> getStationboard(String station){

       return api.getStationboard(station);

    }

}
