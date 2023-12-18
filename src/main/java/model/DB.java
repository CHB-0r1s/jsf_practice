package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
    private static Connection conn;
    private static DB instance;

    private DB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://host.docker.internal:5433/web3", "postgres", "12345");
            // Path for Docker
            PreparedStatement ps = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS \"hits\" (" +
                            "id SERIAL PRIMARY KEY," +
                            "x DOUBLE PRECISION NOT NULL," +
                            "y DOUBLE PRECISION NOT NULL,"  +
                            "r DOUBLE PRECISION NOT NULL," +
                            "status VARCHAR(40) NOT NULL" +
                            ");"
            );
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка инициализации БД");
        }
    }

    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }

    public List<Point> getAll(Double r) {
        List<Point> hits = new ArrayList<Point>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM hits WHERE r = ?");
            ps.setDouble(1, r);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Point hit = new Point();
                hit.setX(rs.getDouble("x"));
                hit.setY(rs.getDouble("y"));
                hit.setR(rs.getDouble("r"));
                hit.setStatus(rs.getBoolean("status"));
                hits.add(hit);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Ошибка получения");
        }
        return hits;
    }

    public boolean add(Point hit) {
        try {
            System.out.print("1 ");
            System.out.println(hit);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO hits(x, y, r, status) VALUES (?, ?, ?, ?)");
            ps.setDouble(1, hit.getX());
            ps.setDouble(2, hit.getY());
            ps.setDouble(3, hit.getR());
            ps.setBoolean(4, hit.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Ошибка добавления");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getClass().getName());
            return false;
        }
    }

    public boolean clear() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM hits");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}