package controller;

import view.*;
import dao.*;
import model.*;
import enums.*;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployerController {

    private FormFrame frame;
    private EmployerModel employerModel;

    public EmployerController(FormFrame frame, EmployerModel employerModel) {
        this.frame = frame;
        this.employerModel = employerModel;

        frame.getBtnPanel().getAddBtn().addActionListener(addEvent -> addEmployer());
        frame.getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateEmployer());
        frame.getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteEmployer());
        loadEmployers();
    }

    private void addEmployer() {
        try {

            if (employerModel.addEmployer(
                    1,
                    frame.getInPanel().getFirstNameField().getText(),
                    frame.getInPanel().getLastNameField().getText(),
                    frame.getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
            ))
            {
                JOptionPane.showMessageDialog(frame, "Employer added successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }

    private void updateEmployer() {
        try {
            if (employerModel.updateEmployer(
                    frame.getListPanel().getSelectedRowId(),
                    frame.getInPanel().getFirstNameField().getText(),
                    frame.getInPanel().getLastNameField().getText(),
                    frame.getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
            ))
            {
                JOptionPane.showMessageDialog(frame, "Employer updated successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }


    private void deleteEmployer() {
        try {
            if (employerModel.deleteEmployer(frame.getListPanel().getSelectedRowId())) {
                JOptionPane.showMessageDialog(frame, "Employer deleted successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to delete employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }

    private void loadEmployers() {
        frame.getListPanel().updateEmployerList(employerModel.getAllEmployers());
    }
}