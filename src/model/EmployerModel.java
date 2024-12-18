package model;

import enums.*;
import dao.EmployerDAO;
import java.util.List;

// Classe principale qui sert de modèle pour la gestion des employeurs
public class EmployerModel {

    private EmployerDAO dao; // Interface DAO pour l'accès aux données
    // Constructeur de la classe
    public EmployerModel (EmployerDAO dao) {
        this.dao = dao;
    }

    public boolean addEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {

        if ( isValidEmail(email) ) {
            return dao.addEmployer( new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste
            ));
        }

        return false;
    }


    public boolean updateEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {


        if ( isValidEmail(email) ) {
            
            Employer employer = new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste
            );

            return dao.updateEmployer(employer);
        }

        return false;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@gmail.com") ? true : false;
    }


    public boolean deleteEmployer(int id) {
        return dao.deleteEmployer(id);
    }

    public List<Employer> getAllEmployers() {
        return dao.getAllEmployers();
    }
    
}