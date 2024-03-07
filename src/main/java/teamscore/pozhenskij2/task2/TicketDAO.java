package teamscore.pozhenskij2.task2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDAO {
    private final Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void updateTicketAndBookingPrices(String flightNo, String fareConditions, double newAmount) throws SQLException {
        String updateTicketQuery = "UPDATE ticket_flights SET amount = ? WHERE ticket_no = ? AND fare_conditions = ?";

        String selectBookingIdsQuery = "SELECT DISTINCT t.book_ref FROM ticket_flights tf JOIN tickets t ON tf.ticket_no = t.ticket_no" +
                " WHERE t.ticket_no = ?";

        String updateBookingQuery = "UPDATE bookings SET total_amount = ? WHERE book_ref = ?";

        try {
            connection.setAutoCommit(false);

            PreparedStatement updateTicketStatement = connection.prepareStatement(updateTicketQuery);
            updateTicketStatement.setDouble(1, newAmount);
            updateTicketStatement.setString(2, flightNo);
            updateTicketStatement.setString(3, fareConditions);
            updateTicketStatement.executeUpdate();

            PreparedStatement selectBookingIdsStatement = connection.prepareStatement(selectBookingIdsQuery);
            selectBookingIdsStatement.setString(1, flightNo);
            ResultSet bookingIdsResult = selectBookingIdsStatement.executeQuery();

            PreparedStatement updateBookingStatement = connection.prepareStatement(updateBookingQuery);
            while (bookingIdsResult.next()) {
                String bookRef = bookingIdsResult.getString("book_ref");
                double totalAmount = calculateTotalAmount(bookRef);
                updateBookingStatement.setDouble(1, totalAmount);
                updateBookingStatement.setString(2, bookRef);
                updateBookingStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private double calculateTotalAmount(String bookRef) throws SQLException {
        String selectTotalAmountQuery = "SELECT SUM(tf.amount) AS total_amount FROM ticket_flights tf JOIN tickets t ON tf.ticket_no = t.ticket_no" +
                " WHERE t.book_ref = ?";
        try (PreparedStatement selectTotalAmountStatement = connection.prepareStatement(selectTotalAmountQuery)) {
            selectTotalAmountStatement.setString(1, bookRef);
            ResultSet resultSet = selectTotalAmountStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_amount");
            } else {
                throw new SQLException("Failed to calculate total amount for booking " + bookRef);
            }
        }
    }

    public double getAmount(String flightNo, String fareConditions) {
        String query = "SELECT amount FROM ticket_flights WHERE ticket_no = ? AND fare_conditions = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightNo);
            statement.setString(2, fareConditions);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("amount");
                } else {
                    throw new SQLException("No ticket found for flightNo: " + flightNo + " and fareConditions: " + fareConditions);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
