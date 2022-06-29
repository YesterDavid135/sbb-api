package dto;

import java.util.ArrayList;

public class JourneyDTO {

        private String name;
        private String category; //todo make enum
        private  int number;
        private  String operator; //todo make enum
        private StopDTO departure;
        private String to; //todo make location object
        private  ArrayList<StopDTO> stops;

        public JourneyDTO() {
        }

        public JourneyDTO(String name, String category, int number, String operator, StopDTO departure, String to, ArrayList<StopDTO> stops) {
                this.name = name;
                this.category = category;
                this.number = number;
                this.operator = operator;
                this.departure = departure;
                this.to = to;
                this.stops = stops;
        }

        public StopDTO getDeparture() {
                return departure;
        }

        public void setDeparture(StopDTO departure) {
                this.departure = departure;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public int getNumber() {
                return number;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public String getOperator() {
                return operator;
        }

        public void setOperator(String operator) {
                this.operator = operator;
        }

        public String getTo() {
                return to;
        }

        public void setTo(String to) {
                this.to = to;
        }

        public ArrayList<StopDTO> getStops() {
                return stops;
        }

        public void setStops(ArrayList<StopDTO> stops) {
                this.stops = stops;
        }
}
