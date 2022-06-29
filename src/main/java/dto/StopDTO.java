package dto;


import java.sql.Time;

public class StopDTO {

        private StationDTO station;
        private Time arrival;
        private Time departure;
        private  int delay;
        private  String platform;

        public StopDTO() {
        }

        public StopDTO(StationDTO station, Time arrival, Time departure, int delay, String platform) {
                this.station = station;
                this.arrival = arrival;
                this.departure = departure;
                this.delay = delay;
                this.platform = platform;
        }

        public StationDTO getStation() {
                return station;
        }

        public void setStation(StationDTO station) {
                this.station = station;
        }

        public Time getArrival() {
                return arrival;
        }

        public void setArrival(Time arrival) {
                this.arrival = arrival;
        }

        public Time getDeparture() {
                return departure;
        }

        public void setDeparture(Time departure) {
                this.departure = departure;
        }

        public int getDelay() {
                return delay;
        }

        public void setDelay(int delay) {
                this.delay = delay;
        }

        public String getPlatform() {
                return platform;
        }

        public void setPlatform(String platform) {
                this.platform = platform;
        }
}
