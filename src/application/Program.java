package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {
   public static void main(String[] args) {
      DB db = new DB();
      Connection connection = null;
      PreparedStatement st = null;

      try {
         connection = db.getConnection();
         st = connection.prepareStatement(
                 "DELETE FROM department "
                 + "WHERE "
                 + "Id = ?"
         );

         st.setInt(1, 2);

         int rowsAffected = st.executeUpdate();

         System.out.println("Done! Rows affected: " + rowsAffected);
      } catch (SQLException e) {
         throw new DbIntegrityException(e.getMessage());
      } finally {
         db.closeStatement(st);
         db.closeConnection();
         System.out.println("Closed!");
      }

   }
}
