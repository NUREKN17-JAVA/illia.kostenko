package main.java.ua.nure.kn.kostenko.gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;



public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 800 ;
    private static final int FRAME_HEIGHT = 600;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;

    public MainFrame(){
        super();
        initialize();
    }

    private void initialize() {//nastroika okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Управление пользователями");
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null){
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }
public void showAddPanel() {
        showPanel(getAddPanel());
}
public void showBrowsePanel() {
        showPanel(getBrowsePanel());
}
private void showPanel(JPanel panel){
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
}

    private AddPanel getAddPanel() {
        if (addPanel == null){
            addPanel = new AddPanel(this);

        }
        return addPanel;
    }

    private JPanel getBrowsePanel() {
        if(browsePanel == null){
            browsePanel = new BrowsePanel(this);
        }
        return browsePanel;
    }
}
