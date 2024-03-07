package teamscore.pozhenskij2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private String url;
    private String user;
    private String password;
    private List<Connection> connections;

    public ConnectionPool(String url, String user, String password, int poolSize) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connections = new ArrayList<>(poolSize);
        initializeConnections(poolSize);
    }

    private void initializeConnections(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            try {
                connections.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return connections.remove(connections.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notifyAll();
    }
}
