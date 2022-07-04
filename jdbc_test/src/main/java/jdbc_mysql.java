import java.sql.*;

public class jdbc_mysql {
    String url = "jdbc:mysql://localhost:3306/mysql_db";
    String user = "root";
    String password = "anhvan09";

    public Connection connect() {
        Connection conn = null;
        try {
            // connect to mysql using drivermanager
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Success connecting to MySQL Database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        jdbc_mysql app = new jdbc_mysql();
        String name = "ADAMS";
        app.selectQuery(name);
        String department_name = "IT";
        String location = "TEXAS";
        app.InsertQuery(department_name, location);
    }
    void selectQuery(String name) {
        String sql = "SELECT * FROM employee WHERE EMP_NAME = ?";
        try {
            Connection conn = this.connect();
            // Recieve blackholder is "?" in query
            PreparedStatement selectStatement = conn.prepareStatement(sql);
            // Set it with name
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                String empName = rs.getString("EMP_NAME");
                String job = rs.getString("JOB");
                System.out.println("EmployeeName = " + empName + "," + "JOB= " + job);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void InsertQuery(String name, String location) {
        String sql = "INSERT INTO department (DEPT_NAME, LOCATION) VALUES (?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, location);
            int insertedRows = statement.executeUpdate();
            System.out.println("Inserted in " + insertedRows + " department");
        }
        catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
    }
    void UpdateQuery(int dept_id) {
        String sql = "UPDATE users SET DEPT_NAME = \"unknown\" WHERE dept_id = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setInt(1, dept_id);
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("Updated " + updatedRows + " department");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void DeleteQuery (int dept_id) {
        String sql = "DELETE FROM department WHERE dept_id = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setInt(1, dept_id);
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("Deleted " + updatedRows + " department");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


