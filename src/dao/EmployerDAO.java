package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employer;
import enums.Role;
import enums.Poste;

public class EmployerDAO implements EmployerInterface {

    private Connection connection;

    public EmployerDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException connectionException) {
            connectionException.printStackTrace();
        }
    }


    @Override
    public boolean addEmployer(Employer employer) {
        try (PreparedStatement addStatement = connection.prepareStatement(
            "INSERT INTO employers (first_name, last_name, email, phone, salary, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            addStatement.setString(1, employer.getFirstName());
            addStatement.setString(2, employer.getLastName());
            addStatement.setString(3, employer.getEmail());
            addStatement.setInt(4, employer.getPhoneNumber());
            addStatement.setDouble(5, employer.getSalary());
            addStatement.setString(6, employer.getRole().name());
            addStatement.setString(7, employer.getPoste().name());

            return addStatement.executeUpdate() > 0;

        } catch (SQLException addException) {
            addException.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateEmployer(Employer employer) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE employers SET first_name = ?, last_name = ?, email = ?, phone = ?, salary = ?, role = ?, poste = ? WHERE id = ?")) {

            updateStatement.setString(1, employer.getFirstName());
            updateStatement.setString(2, employer.getLastName());
            updateStatement.setString(3, employer.getEmail());
            updateStatement.setInt(4, employer.getPhoneNumber());
            updateStatement.setDouble(5, employer.getSalary());
            updateStatement.setString(6, employer.getRole().name());
            updateStatement.setString(7, employer.getPoste().name());
            updateStatement.setInt(8, employer.getId());

            return updateStatement.executeUpdate() > 0;

        } catch (SQLException updateException) {
            updateException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployer(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM employers WHERE id = ?")) {

            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate() > 0;

        } catch (SQLException deleteException) {
            return false;
        }
    }

    @Override
    public List<Employer> getAllEmployers() {
        List<Employer> employers = new ArrayList<>();
        try (ResultSet getResult = connection.prepareStatement("SELECT * FROM employers").executeQuery()) {

            while (getResult.next()) {
                employers.add(new Employer(
                    getResult.getInt("id"), 
                    getResult.getString("first_name"), 
                    getResult.getString("last_name"), 
                    getResult.getString("email"), 
                    getResult.getInt("phone"), 
                    getResult.getDouble("salary"), 
                    Role.valueOf(getResult.getString("role")), 
                    Poste.valueOf(getResult.getString("poste"))
                ));
            }

        } catch (SQLException getException) {
            getException.printStackTrace();
        }
        return employers;
    }
}
