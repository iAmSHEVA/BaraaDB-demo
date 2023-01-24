package fcit.cpit251;

import java.sql.*;
import java.util.Scanner;

public class Task {
    private String name;
    private boolean isComplete;

    private int id;

    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void insertTask() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, done) VALUES (?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, (this.isComplete));
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, task, done FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //Display values
                String row = "ID: " + rs.getInt("id") +
                        " Task: " + rs.getString("task") +
                        " Done: " + rs.getBoolean("done") + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTask(String name, boolean done, int id) {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement updateStmt =
                    dbConnection.prepareStatement("UPDATE todo set task=?,done=? where id=?");
            updateStmt.setString(1, name);
            updateStmt.setBoolean(2, done);
            updateStmt.setInt(3, id);
            int rows = updateStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTask(int id) {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement deleteStmt =
                    dbConnection.prepareStatement("DELETE from todo where id=?");
            deleteStmt.setInt(1, id);
            int rows = deleteStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "Task: " + this.name + "\nDone: " + (this.isComplete ? "1" : "0");
    }
}
