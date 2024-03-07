package teamscore.pozhenskij2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private Connection connection;

    public FlightDAO(Connection connection) {
        this.connection = connection;
    }

    public List<String> getArrivals(String airportCode, String arrivalDate) throws SQLException {
        return getFlights(airportCode, arrivalDate, true);
    }

    public List<String> getDepartures(String airportCode, String departureDate) throws SQLException {
        return getFlights(airportCode, departureDate, false);
    }

    private List<String> getFlights(String airportCode, String date, boolean isArrival) throws SQLException {
        List<String> flights = new ArrayList<>();
        String direction = isArrival ? "f.scheduled_arrival" : "f.scheduled_departure";
        String query =
                "SELECT f.scheduled_departure, f.scheduled_arrival, f.departure_airport, " +
                        "f.arrival_airport, f.status, da.airport_name, " +
                        "aa.airport_name, ad.model " +
                        "FROM flights f " +
                        "JOIN airports_data da ON f.departure_airport = da.airport_code " +
                        "JOIN airports_data aa ON f.arrival_airport = aa.airport_code " +
                        "JOIN aircrafts_data ad ON f.aircraft_code = ad.aircraft_code " +
                        "WHERE " + direction + " = to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS') AND " +
                        (isArrival ? "f.arrival_airport" : "f.departure_airport") + " = ?";

        try (PreparedStatement prepareSt = connection.prepareStatement(query)) {
            prepareSt.setString(1, date);
            prepareSt.setString(2, airportCode);
            ResultSet resultSet = prepareSt.executeQuery();

            while (resultSet.next()) {
                String scheduledDeparture = resultSet.getString(1);
                String scheduledArrival = resultSet.getString(2);
                String departureAirport = resultSet.getString(3);
                String arrivalAirport = resultSet.getString(4);
                String status = resultSet.getString(5);
                String model = resultSet.getString(8);

                String formattedFlight = String.format("From: %s (%s) | To: %s (%s) | Aircraft: %s | Status: %s",
                        scheduledDeparture, departureAirport, scheduledArrival, arrivalAirport, model, status);
                flights.add(formattedFlight);
            }
        }

        return flights;
    }
}
