package org.example;

import java.sql.*;

public class Main {
    private static final String getAllDrinksQuery = """
            SELECT "name_UA", "name_EN", "price"
            FROM public."Items" I
            WHERE I.type = 'напій'
            """;

    private static final String getAllDessertsQuery = """
            SELECT "name_UA", "name_EN", "price"
            FROM public."Items" I
            WHERE I.type = 'десерт'
            """;

    private static final String getWorkers = """
            SELECT * FROM public."ViewWorkers"
            ORDER BY id ASC
            """;

    private static final String averageOrderSumByDate = """
            SELECT
                "orderDate",
                AVG("totalAmount") AS avg_total,
                COUNT(*) AS orders_count
            FROM public."Orders"
            WHERE "orderDate" = ?
            GROUP BY "orderDate";
            """;

    private static final String biggestOrdersByDate = """
        SELECT 
            O.id,
            O."orderDate",
            O."totalAmount",
            C."fullname"
        FROM public."Orders" O
        JOIN public."Clients" C ON O."clientId" = C.id
        WHERE O."orderDate" = ?
          AND O."totalAmount" = (
              SELECT MAX("totalAmount")
              FROM public."Orders"
              WHERE "orderDate" = ?
          );
        """;

    private static final String getWorkScheduleTodayTomorrow = """
            SELECT
                    WS.id,
                    W.fullname,
                    D.day,
                    WS."startTime",
                    WS."endTime"
                FROM public."WorkSchedule" WS
                JOIN public."ViewWorkers" W ON WS."workerId" = W.id
                JOIN public."Days" D ON WS."dayId" = D.id
                WHERE D.day IN (
                    TO_CHAR(CURRENT_DATE, 'Day'),
                    TO_CHAR(CURRENT_DATE + INTERVAL '1 day', 'Day')
                )
                ORDER BY D.id, WS."startTime";
    """;

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:15432/Cafe", "selfcore", "secretpassword")) {
            Statement statement = connection.createStatement();

            System.out.println("\t\t\t\t\t\t\t ---Напої--- \t\t\t\t\t\t\t");

            ResultSet resultSet = statement.executeQuery(getAllDrinksQuery);

            while (resultSet.next()) {
                String name_UA = resultSet.getString("name_UA");
                String name_EN = resultSet.getString("name_EN");

                System.out.println(String.format("%s %s", name_UA, name_EN));
            }

            System.out.println("\t\t\t\t\t\t\t ---Десерти--- \t\t\t\t\t\t\t");

            resultSet = statement.executeQuery(getAllDessertsQuery);

            while (resultSet.next()) {
                String name_UA = resultSet.getString("name_UA");
                String name_EN = resultSet.getString("name_EN");
                double price = resultSet.getDouble("price");

                System.out.println(String.format("%s %s %.2f", name_UA, name_EN, price));
            }

            System.out.println("\t\t\t\t\t\t\t ---Робітники--- \t\t\t\t\t\t\t");

            resultSet = statement.executeQuery(getWorkers);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("fullname").trim();
                String position = resultSet.getString("title");

                System.out.println(String.format("%d | %s | %s", id, name, position));
            }

            PreparedStatement preparedStatement = connection.prepareStatement(averageOrderSumByDate);
            preparedStatement.setDate(1, Date.valueOf("2024-12-05"));

            ResultSet avgResult = preparedStatement.executeQuery();

            System.out.println("\t\t\t\t\t\t\t ---Середнє по замовленням за день--- \t\t\t\t\t\t\t");

            while (avgResult.next()) {
                Date orderDate = avgResult.getDate("orderDate");
                double avgTotal = avgResult.getDouble("avg_total");
                int ordersCount = avgResult.getInt("orders_count");

                System.out.printf("Дата: %s | Середня сума: %.2f | Кількість замовлень: %d%n",
                        orderDate, avgTotal, ordersCount);
            }

            preparedStatement = connection.prepareStatement(biggestOrdersByDate);

            Date date = Date.valueOf("2024-12-05");
            preparedStatement.setDate(1, date);
            preparedStatement.setDate(2, date);

            resultSet = preparedStatement.executeQuery();

            System.out.println("\t\t --- Найбільші замовлення за " + date + " ---");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date orderDate = resultSet.getDate("orderDate");
                double total = resultSet.getDouble("totalAmount");
                String fullname = resultSet.getString("fullname");

                System.out.printf("ID: %d | Дата: %s | Клієнт: %s | Сума: %.2f%n",
                        id, orderDate, fullname, total);
            }

            System.out.println("\t\t\t\t ---Розклад роботи на сьогодні та завтра--- \t\t\t\t");

            ResultSet scheduleSet = statement.executeQuery(getWorkScheduleTodayTomorrow);

            while (scheduleSet.next()) {
                int id = scheduleSet.getInt("id");
                String fullname = scheduleSet.getString("fullname").trim();
                String dayName = scheduleSet.getString("day").trim();
                String start = scheduleSet.getTime("startTime").toString();
                String end = scheduleSet.getTime("endTime").toString();

                System.out.printf("ID: %d | Працівник: %s | День: %s | Початок: %s | Кінець: %s%n",
                        id, fullname, dayName, start, end);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}