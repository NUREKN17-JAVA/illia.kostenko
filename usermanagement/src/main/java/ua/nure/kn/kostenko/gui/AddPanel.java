package ua.nure.kn.kostenko.gui;

import ua.nure.kn.kostenko.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddPanel extends JPanel implements ActionListener {
    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    public static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    public static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final Date DATE_OF_BIRTH = new Date();
    public static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
    private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
    private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
    private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
    private static final String OK_COMMAND = "ok";
    private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
    private static final String CANCEL_COMMAND = "cancel";
    private MainFrame parent;
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel fieldPanel;
    private JTextField firstNameField;
    private JTextField dayOfBirthField;
    private JTextField lastNameField;

    public AddPanel(MainFrame mainFrame) {
        parent = mainFrame;
        initialize();
    }

    private void initialize() {
        this.setName(ADD_PANEL_COMPONENT_NAME);
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.CENTER);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        if(buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText("Отмена");
            cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME);
            cancelButton.setActionCommand(CANCEL_COMMAND);
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText("ok");
            okButton.setName(OK_BUTTON_COMPONENT_NAME);
            okButton.setActionCommand(OK_COMMAND);
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JPanel getFieldPanel() {
        String language = "ru";
        String country = "RU";


         Locale locale = new Locale(language, country);


        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3,2));
            //     addLabeledField(fieldPanel,  Messages.getString("AddPanel.first_name"), getFirstNameField());
            addLabeledField(fieldPanel, "Имя", getFirstNameField());
            addLabeledField(fieldPanel, "Фамилия", getLastNameField());
            addLabeledField(fieldPanel, "Дата рождения", getDayOfBirthField());
        }
        return fieldPanel;

    }

    private JTextField getDayOfBirthField() {
        if (dayOfBirthField == null) {
            dayOfBirthField = new JTextField();
            dayOfBirthField.setName(DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
        }

        return dayOfBirthField;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName(LAST_NAME_FIELD_COMPONENT_NAME);
        }

        return lastNameField;
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName(FIRST_NAME_FIELD_COMPONENT_NAME);
            addLabeledField(fieldPanel, "Имя", getFirstNameField());
        }
        return firstNameField;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textfield) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textfield);
        panel.add(label);
        panel.add(textfield);

    }

    public void actionPerformed(ActionEvent e) {
        //parse
        //dao create

    }
}
