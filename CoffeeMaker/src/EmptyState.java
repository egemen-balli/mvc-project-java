//Egemen BALLI 26 Dec 2023
public class EmptyState implements State {
    Controller controller;

    public EmptyState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void filled(ViewInterface view, int cupNumber) {
        controller.model.setNumberOfCups(cupNumber);
        view.getWarningMsg().setText("The machine is filled.");
        controller.setState(controller.getIdleState());
    }

    @Override
    public void start(ViewInterface view) {
        view.getWarningMsg().setText("The machine is empty!");
    }

    @Override
    public void reset(ViewInterface view) {
        controller.setState(controller.getEmptyState());
        view.getWarningMsg().setText("");
        view.getTextField1().setText("");
        view.getTotalCupsLabel().setText("");
    }
}
