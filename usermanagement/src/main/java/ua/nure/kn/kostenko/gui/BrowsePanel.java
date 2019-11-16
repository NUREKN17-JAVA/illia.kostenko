package main.java.ua.nure.kn.kostenko.gui;

import main.java.ua.nure.kn.kostenko.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BrowsePanel extends JPanel implements ActionListener {
    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
    private static final String DETAILS_COMMAND = "details";
    private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    private static final String DELETE_COMMAND = "delete";

    private MainFrame parent;
    public static final String USER_TABLE_COMPONENT_NAME = "browsePanel";
    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    private JScrollPane tablePanel;
    private JTable userTable;
    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton detailsButton;
    private JButton deleteButton;


    public BrowsePanel(MainFrame mainFrame) {
        parent = mainFrame;
        initialize();
    }

    private void initialize() {
        this.setName(BROWSE_PANEL_COMPONENT_NAME);
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }


    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName(USER_TABLE_COMPONENT_NAME);
        }
        return userTable;
    }

    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel();
            buttonsPanel.add(getAddButton(), null);
            buttonsPanel.add(getEditButton(), null);
            buttonsPanel.add(getDetailsButton(), null);
            buttonsPanel.add(getDeleteButton(), null);
        }
        return buttonsPanel;
    }

    //TODO ADD DELETE BUTTON
    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText("Редактировать");
            editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
            editButton.setActionCommand(EDIT_COMMAND);
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText("Удалить");
            deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
            deleteButton.setActionCommand(DELETE_COMMAND);
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText("Детали");
            detailsButton.setName(DETAILS_BUTTON_COMPONENT_NAME);
            detailsButton.setActionCommand(DETAILS_COMMAND);
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText("Добавить");
            addButton.setName(ADD_BUTTON_COMPONENT_NAME);
            addButton.setActionCommand(ADD_COMMAND);
            addButton.addActionListener(this);
        }
        return addButton;
    }


    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        if ("add".equalsIgnoreCase(actionCommand)) {
            this.setVisible(false);
            parent.showAddPanel();
        }
    }
}
