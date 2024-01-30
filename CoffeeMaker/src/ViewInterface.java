//Egemen BALLI 26 Dec 2023
import javax.swing.*;
import java.awt.*;

public class ViewInterface extends JFrame implements Observer{
    private JButton FILLEDButton;
    private JButton STARTButton;
    private JButton TOTALCupsButton;
    private JButton resetButton;
    private JTextField textField1;
    private JPanel panel1;
    private JLabel warningMsg;
    private JLabel idleLabel;
    private JLabel brewingLabel;
    private JLabel doneLabel;
    private JPanel idlePanel;
    private JPanel brewingPanel;
    private JPanel donePanel;
    private JLabel totalCupsLabel;
    private Controller controller;
    private Model model;

    public ViewInterface(Controller controller, Model model) {
        add(panel1);
        setTitle("Coffee Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,400,400,400);
        warningMsg.setForeground(Color.RED);
        setResizable(false);
        setVisible(true);
        this.model = model;
        this.controller = controller;
        model.registerObserver(this);
    }

    public JPanel getIdlePanel() {
        return idlePanel;
    }

    public JPanel getBrewingPanel() {
        return brewingPanel;
    }

    public JPanel getDonePanel() {
        return donePanel;
    }

    public JLabel getWarningMsg() {
        return warningMsg;
    }

    public JLabel getTotalCupsLabel() {
        return totalCupsLabel;
    }

    public JButton getSTARTButton() {
        return STARTButton;
    }

    public JButton getFILLEDButton() {
        return FILLEDButton;
    }

    public JButton getTOTALCupsButton() {
        return TOTALCupsButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    @Override
    public void update(State state) {
        getIdlePanel().setBackground(new Color(238, 238, 238));
        getBrewingPanel().setBackground(new Color(238, 238, 238));
        getDonePanel().setBackground(new Color(238, 238, 238));
        if (state.equals(controller.getIdleState())) {
            getIdlePanel().setBackground(Color.YELLOW);
        } else if (state.equals(controller.getBrewingState())) {
            getBrewingPanel().setBackground(Color.YELLOW);
        } else if (state.equals(controller.getDoneState())) {
            getDonePanel().setBackground(Color.YELLOW);
        }

        if (model.getTotalNumberOfCups() != 0) totalCupsLabel.setText(String.valueOf(model.getTotalNumberOfCups()));
    }
}
