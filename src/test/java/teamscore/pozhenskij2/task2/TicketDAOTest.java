package teamscore.pozhenskij2.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import teamscore.pozhenskij2.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TicketDAOTest {
    private Connection connection;
    private TicketDAO ticketDAO;

    @BeforeEach
    void setUp() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool("jdbc:postgresql://localhost:5433/demo", "postgres", "1234", 5);
        connection = connectionPool.getConnection();
        ticketDAO = new TicketDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void testUpdateTicketAndBookingPrices() throws SQLException {
        String flightNo = "0005432159776";
        String fareConditions = "Economy";
        double newAmount = 150000.0;

        assertDoesNotThrow(() -> {
            ticketDAO.updateTicketAndBookingPrices(flightNo, fareConditions, newAmount);
        });

        assertDoesNotThrow(() -> assertEquals(150000.0, ticketDAO.getAmount("0005432159776", "Economy")));
  }

}