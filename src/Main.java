import java.sql.*;

public class Main {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args) {
        connect();

        try {
            createTable();
            insert ("101", "milk", 60);
            insert ("10101", "sugar", 80);
            select(1);
            deleteRow(1);
            clearTable();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
    private static void createTable() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS productsTable (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    prodid  TEXT UNIQUE,\n" +
                "    title TEXT,\n" +
                "    cost INTEGER\n" +
                ");");
    }
    private static void insert (String prodid, String title, int cost) throws SQLException {
        ps = connection.prepareStatement("INSERT INTO productsTable (prodid, title, cost) VALUES (?, ?, ?);");
        ps.setString(1, prodid);
        ps.setString(2, title);
        ps.setInt(3, cost);
        ps.executeUpdate();
    }
    private static void select(int id) throws SQLException {
        stmt.executeQuery("SELECT * FROM productsTable WHERE id = " + id + ";");
    }
    private static void deleteRow(int id) throws SQLException {
        stmt.executeUpdate("DELETE FROM productsTable WHERE id = " + id + ";");
    }
    private static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM productsTable;");
    }

}
