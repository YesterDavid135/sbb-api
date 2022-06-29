package apiHandler;

import dto.JourneyDTO;
import dto.StationDTO;
import dto.StopDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class StationBoardHandler {


    private ArrayList<JourneyDTO> parseStationboard(String responseBody) {
        JSONArray stationBoard =  new JSONObject(responseBody).getJSONArray("stationboard");

        ArrayList<JourneyDTO> journeyDTOS= new ArrayList<>();

        for (int i = 0 ; i < stationBoard.length(); i++) {
            JSONObject journey = stationBoard.getJSONObject(i);
            JourneyDTO journeyDTO = new JourneyDTO();

            journeyDTO.setName(journey.getString("name"));
            journeyDTO.setTo(journey.getString("to"));
            journeyDTO.setCategory(journey.getString("category"));
            journeyDTO.setNumber(journey.getInt("number"));
            journeyDTO.setOperator(journey.getString("operator"));

            journeyDTO.setDeparture(convertStop(journey.getJSONObject("stop")));

            ArrayList<StopDTO> stops = new ArrayList<>();
            for (int j = 0; j < journey.getJSONArray("passList").length(); j++){
                stops.add(convertStop(journey.getJSONArray("passList").getJSONObject(j)));
            }

            journeyDTO.setStops(stops);

            journeyDTOS.add(journeyDTO);
            }

        return journeyDTOS;
    }

    private StationDTO convertStation (JSONObject station){
        return new StationDTO(
                station.getString("id"),
                station.isNull("name") ? "" : station.getString("name")
        );
    }

    private StopDTO convertStop(JSONObject json){
        return new StopDTO(
                convertStation(json.getJSONObject("station")),
                json.isNull("arrivalTimestamp")? null : new Time(json.getLong("arrivalTimestamp")*1000),
                json.isNull("departureTimestamp")? null : new Time(json.getLong("departureTimestamp")*1000),
                json.isNull("delay") ? 0 : json.getInt("delay"),
                json.isNull("platform") ? "" :
                json.getString("platform")
        );
    }
    public ArrayList<JourneyDTO> getStationboard(String station) {

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();


        try {
            URL url = new URL("https://transport.opendata.ch/v1/stationboard?station=" + station + "&limit=5");
            System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
           /* con.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            con.setReadTimeout(5000);*/

            // Test if the response from the server is successful
            int status = con.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
           return parseStationboard(responseContent.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
