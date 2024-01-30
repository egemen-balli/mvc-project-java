//Egemen BALLI 26 Dec 2023
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class IdleState implements State {
    Controller controller;

    public IdleState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void filled(ViewInterface view, int cupNumber) {
        view.getWarningMsg().setText("The machine is already filled!");
    }

    @Override
    public void start(ViewInterface view) {
        controller.setState(controller.getBrewingState());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                controller.setState(controller.getDoneState());
                try {
                    controller.saveCups();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 10000);
    }

    @Override
    public void reset(ViewInterface view) {
        controller.setState(controller.getEmptyState());
        view.getWarningMsg().setText("");
        view.getTextField1().setText("");
        view.getTotalCupsLabel().setText("");
    }
}
