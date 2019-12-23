package ua.nure.kn.kostenko.agent;



import ua.nure.kn.kostenko.db.DatabaseException;
import ua.nure.kn.kostenko.gui.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;


/**
 * @author mak
 */
public class SearchGui extends JFrame {

    private SearchAgent agent;
    private JPanel contentPanel;
    private JPanel tablePanel;
    private JTable table;


    public SearchGui(SearchAgent agent) {
        this.agent = agent;
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setTitle("Searcher");
        this.setContentPane(getContentPanel());
    }


    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getSearchPanel(), BorderLayout.NORTH);
            contentPanel.add(new JScrollPane(getTablePanel()), BorderLayout.CENTER);
        }
        return contentPanel;
    }
    
    private JPanel getSearchPanel() {
        return new SearchPanel(agent);
    }
    
    /**
     * @author mak
     */
    class SearchPanel extends JPanel implements ActionListener {
        //        protected JFrame parent;
        SearchAgent agent;
//        private JPanel buttonPanel;
        private JPanel fieldPanel;
        private JButton cancelButton;
        private JButton searchButton;
        private JTextField firstNameField;
//        private JTextField dateOfBirthField;
        private JTextField lastNameField;

        public SearchPanel(SearchAgent agent) {
            this.agent = agent;
            initialize();
        }

        private void initialize() {
            this.setName("searchPanel"); 
            this.setLayout(new BorderLayout());
            this.add(getFieldPanel(), BorderLayout.NORTH);
        }
        
        private JPanel getFieldPanel() {
            if (fieldPanel == null) {
                fieldPanel = new JPanel();
                fieldPanel.setLayout(new GridLayout(2, 3));
                addLabeledField(fieldPanel, "FirstName", getFirstNameField()); 
                fieldPanel.add(getSearchButton());
                addLabeledField(fieldPanel, "LastName", getLastNameField());
                fieldPanel.add(getCancelButton());
            }
            return fieldPanel;
        }


        private JButton getSearchButton() {
            if (searchButton == null) {
                searchButton = new JButton();
                searchButton.setText("Search"); 
                searchButton.setName("okButton"); 
                searchButton.setActionCommand("ok"); 
                searchButton.addActionListener(this);
            }
            return searchButton;
        }
        
        private JButton getCancelButton() {
            if (cancelButton == null) {
                cancelButton = new JButton();
                cancelButton.setText("Cancel"); 
                cancelButton.setName("cancelButton"); 
                cancelButton.setActionCommand("cancel"); 
                cancelButton.addActionListener(this);
            }
            return cancelButton;
        }

        protected JTextField getFirstNameField() {
            if (firstNameField == null) {
                firstNameField = new JTextField();
                firstNameField.setName("firstNameField"); 
            }
            return firstNameField;
        }
        
        protected JTextField getLastNameField() {
            if (lastNameField == null) {
                lastNameField = new JTextField();
                lastNameField.setName("lastNameField"); 
            }
            return lastNameField;
        }

        private void addLabeledField(JPanel panel, String labelText,
                JTextField textField) {
            JLabel label = new JLabel(labelText);
            label.setLabelFor(textField);
            panel.add(label);
            panel.add(textField);
        }

        protected void doAction(ActionEvent e) throws ParseException {
            if ("ok".equalsIgnoreCase(e.getActionCommand())) {
                String firstName = getFirstNameField().getText();
                String lastName = getLastNameField().getText();
                try {
                    clearUsers();
                    agent.search(firstName, lastName);
                } catch (SearchException | DatabaseException e1) {
                    throw new RuntimeException(e1);
                }
            }
            clearFields();
        }
        
        private void clearFields() {
            getFirstNameField().setText(";");
            getLastNameField().setText(";");
        }

        public void actionPerformed(ActionEvent e) {
            try {
                doAction(e);
            } catch (ParseException e1) {
                return;
            }
        }
    }


    /**
     * @return
     */
    private JPanel getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(getTable(), BorderLayout.CENTER);
        }
        return tablePanel;
    }

    /**
     * @return
     */
    private JTable getTable() {
        if (table == null) {
            table = new JTable(new UserTableModel(new LinkedList()));
        }
        return table;
    }

    /**
     * @param users
     */
    public void addUsers(Collection users) {
        System.out.println("addUsers : " + users);
        UserTableModel model = (UserTableModel) getTable().getModel();
        model.addUsers(users);
        this.repaint();
    }

    private void clearUsers() {
        System.out.println("clearUsers : ");
        UserTableModel model = (UserTableModel) getTable().getModel();
        model.clearUsers();
        this.repaint();
    }
    
    
    public static void main(String[] args) {
        SearchGui gui = new SearchGui(null);
        gui.setVisible(true);
    }
}