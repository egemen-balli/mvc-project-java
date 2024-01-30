//Egemen BALLI 26 Dec 2023
public class DoneState implements State{
    Controller controller;

    public DoneState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void filled(ViewInterface view, int cupNumber) {
        view.getWarningMsg().setText("You need to reset the machine before using it again.");
    }

    @Override
    public void start(ViewInterface view) {
        view.getWarningMsg().setText("You need to reset the machine before using it again.");
    }

    @Override
    public void reset(ViewInterface view) {
        controller.setState(controller.getEmptyState());
        view.getWarningMsg().setText("");
        view.getTextField1().setText("");
        view.getTotalCupsLabel().setText("");
    }
}
