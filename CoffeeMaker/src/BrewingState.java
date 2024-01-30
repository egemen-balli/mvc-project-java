//Egemen BALLI 26 Dec 2023
public class BrewingState implements State {
    Controller controller;

    public BrewingState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void filled(ViewInterface view, int cupNumber) {
        view.getWarningMsg().setText("Please wait, brewing the coffee.");
    }

    @Override
    public void start(ViewInterface view) {
        view.getWarningMsg().setText("The machine is already started, please wait.");
    }

    @Override
    public void reset(ViewInterface view) {
        view.getWarningMsg().setText("The machine is running, please wait.");
    }
}
