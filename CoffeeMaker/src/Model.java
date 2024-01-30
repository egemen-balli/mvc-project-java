//Egemen BALLI 26 Dec 2023
import java.sql.*;
import java.util.ArrayList;

public class Model {
    private State state;
    private int numberOfCups;
    private int totalNumberOfCups = 0;
    private Connection connection = SqlConnect();
    private ArrayList<Observer> observers = new ArrayList<>();

    void notifyObservers() {
        for(int i = 0; i < observers.size(); i++){
            (observers.get(i)).update(state);
        }
    }

    void registerObserver(Observer o) {
        observers.add(o);
    }

    void setState(State state) {
        this.state = state;
    }

    public void setNumberOfCups(int numberOfCups) {
        this.numberOfCups = numberOfCups;
    }

    public int getNumberOfCups() {
        return numberOfCups;
    }

    public void saveCups() throws SQLException {
        for (int i = 0; i < numberOfCups; i++){
            connection.prepareStatement("insert into totalcups (date) values ('"+new Date(new java.util.Date().getTime())+"')").executeUpdate();
        }
    }

    public void setTotalCupsFromDB() {
        try {
            PreparedStatement statement = connection.prepareStatement("select count(*) as total from totalcups where month(date) = month(curdate()) and year(date) = year(curdate());");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            totalNumberOfCups = resultSet.getInt("total");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection SqlConnect(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeemaker_20070006005", "root", "admin");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalNumberOfCups() {
        return totalNumberOfCups;
    }
}
