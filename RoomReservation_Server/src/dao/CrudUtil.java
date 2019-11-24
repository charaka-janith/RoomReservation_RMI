package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute (String sql, Connection connection, Object ... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++){
            preparedStatement.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }
        return (T) ((Boolean) (preparedStatement.executeUpdate()>0));
    }
}