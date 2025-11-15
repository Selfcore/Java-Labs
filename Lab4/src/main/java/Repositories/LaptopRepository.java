package Repositories;

import Interfaces.RepositoryImp;
import Models.Laptop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LaptopRepository implements RepositoryImp<Laptop> {
    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:15432/LaptopShop";
    private static String user;
    private static String password;

    private static final String GET_ALL_QUERY = """
            SELECT id, name, description, image
            FROM public."Laptops"
            """;

    private static final String GET_BY_ID_QUERY = """
            SELECT id, name, description, image
            FROM public."Laptops"
            WHERE id = ?
            """;

    private static final String INSERT_QUERY = """
            INSERT INTO public."Laptops"(name, description, image)
            VALUES (?, ?, ?)
    """;

    private static final String REMOVE_QUERY = """
            DELETE FROM public."Laptops"
            WHERE id = ?
            """;

    public LaptopRepository() {
        Properties props = new Properties();

        Path currentDir = null;

        try {
            currentDir = Paths.get(
                    LaptopRepository.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            );
        }
        catch (Exception e) {

        }

        Path envFile = currentDir.resolve("../../../../.env").normalize();
        System.out.println("Looking for .env at: " + envFile.toAbsolutePath());

        try(var inputStream = Files.newInputStream(envFile)) {
            props.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        user = props.get("POSTGRES_USER").toString();
        password = props.get("POSTGRES_PASSWORD").toString();
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver не знайдено", e);
        }
        return DriverManager.getConnection(CONNECTION_STRING, user, password);
    }

    @Override
    public List<Laptop> getAll() {
        List<Laptop> laptops = new ArrayList<>();

        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_QUERY);

            while (rs.next()) {
                Laptop laptop = new Laptop();

                laptop.setId(rs.getInt("id"));
                laptop.setName(rs.getString("name").trim());
                laptop.setDescription(rs.getString("description").trim());
                laptop.setImage(rs.getString("image").trim());

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return laptops;
    }

    @Override
    public Laptop getById(int id) {
        try (Connection connection = getConnection()) {
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID_QUERY);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Laptop targetLaptop = new Laptop();

                targetLaptop.setId(rs.getInt("id"));
                targetLaptop.setName(rs.getString("name").trim());
                targetLaptop.setDescription(rs.getString("description").trim());
                targetLaptop.setImage(rs.getString("image").trim());

                return targetLaptop;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void add(Laptop entity) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getImage());

            statement.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY);
            statement.setInt(1, id);

            statement.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
