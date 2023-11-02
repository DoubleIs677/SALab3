package com.mycode.threeCS;

import java.sql.*;

public class DataServer {
    private Connection connection;

    public DataServer(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public boolean addContact(Contact contact) {
        String query = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhone());
            statement.setString(3, contact.getEmail());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContact(Contact contact) {
        String query = "UPDATE contacts SET phone = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getPhone());
            statement.setString(2, contact.getEmail());
            statement.setInt(3, contact.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContact(int id) {
        String query = "DELETE FROM contacts WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet searchContacts(String keyword) {
        String query = "SELECT * FROM contacts WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?";

        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
