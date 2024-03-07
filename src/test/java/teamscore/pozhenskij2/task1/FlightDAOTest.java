package teamscore.pozhenskij2.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import teamscore.pozhenskij2.ConnectionPool;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    private ConnectionPool connectionPool;
    private FlightDAO flightDAO;
    private Connection con;

    @BeforeEach
    void setUp() {
        String url = "jdbc:postgresql://localhost:5433/demo";
        String user = "postgres";
        String password = "1234";
        int poolSize = 5;
        connectionPool = new ConnectionPool(url, user, password, poolSize);
        con = connectionPool.getConnection();
        flightDAO = new FlightDAO(con);
    }

    @AfterEach
    void tearDown() {
        connectionPool.releaseConnection(con);
    }

    @Test
    void testGetArrivals() {
        String airportCode = "AER";
        String arrivalDate = "2017-09-05 15:15:00";
        assertDoesNotThrow(() -> {
            List<String> arrivals = flightDAO.getArrivals(airportCode, arrivalDate);
            assertNotNull(arrivals);
            assertFalse(arrivals.isEmpty());
            assertEquals(1, arrivals.size());
            for (String arrival : arrivals) {
                System.out.println(arrival);
            }
        });
    }

    @Test
    void testGetDepartures() {
        String airportCode = "SVO";
        String departureDate = "2017-09-12 10:50:00";
        assertDoesNotThrow(() -> {
            List<String> departures = flightDAO.getDepartures(airportCode, departureDate);
            assertNotNull(departures);
            assertEquals(2, departures.size());
            assertFalse(departures.isEmpty());
            for (String departure : departures) {
                System.out.println(departure);
            }
        });
    }
}