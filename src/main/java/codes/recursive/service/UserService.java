package codes.recursive.service;

import io.micronaut.transaction.jdbc.DelegatingDataSource;
import oracle.ucp.jdbc.JDBCConnectionPoolStatistics;
import oracle.ucp.jdbc.PoolDataSourceImpl;

import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
public class UserService {

    private final DataSource dataSource;

    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<HashMap<String,Object>> getUsers() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        return convertResultSetToList(resultSet);
    }

    public JDBCConnectionPoolStatistics getStats() {
        DelegatingDataSource ds = (DelegatingDataSource) dataSource;
        PoolDataSourceImpl targetDataSource = (PoolDataSourceImpl) ds.getTargetDataSource();
        return targetDataSource.getStatistics();
    }

    private List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        while (rs.next()) {
            HashMap<String,Object> row = new HashMap<String, Object>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }
}
