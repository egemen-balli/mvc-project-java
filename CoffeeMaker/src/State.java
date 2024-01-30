//Egemen BALLI 26 Dec 2023
public interface State {
    void filled(ViewInterface view, int cupNumber);
    void start(ViewInterface view);
    void reset(ViewInterface view);
}
