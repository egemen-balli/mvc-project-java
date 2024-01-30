//Egemen BALLI 26 Dec 2023
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Controller {
    Model model;
    ViewInterface view;

    State emptyState;
    State idleState;
    State brewingState;
    State doneState;

    State state;

    public Controller(Model model) {
        this.model = model;
        this.view = new ViewInterface(this, model);

        emptyState = new EmptyState(this);
        idleState = new IdleState(this);
        brewingState = new BrewingState(this);
        doneState = new DoneState(this);

        state = emptyState;
        model.setState(emptyState);

        view.getFILLEDButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filled();
            }
        });

        view.getSTARTButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        view.getTOTALCupsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCups();
            }
        });

        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    public void filled() {
        try {
            state.filled(view, Integer.parseInt(view.getTextField1().getText()));
        } catch (Exception e) {
            view.getWarningMsg().setText("Please enter a valid number.");
        }
    }

    public void start() {
        state.start(view);
    }

    public void totalCups() {
        model.setTotalCupsFromDB();
        model.notifyObservers();
    }

    public void saveCups() throws SQLException {
        model.saveCups();
    }

    public void reset() {
        state.reset(view);
    }

    void setState(State state) {
        this.state = state;
        model.setState(state);
        model.notifyObservers();
    }

    public State getEmptyState() {
        return emptyState;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getBrewingState() {
        return brewingState;
    }

    public State getDoneState() {
        return doneState;
    }
}
