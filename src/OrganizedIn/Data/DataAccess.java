/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Data;

//import com.jolbox.bonecp.BoneCP;
//import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DataAccess {

    private Connection connection = null;
    private static Logger logger = LogManager.getLogger(DataAccess.class.getName());

//    public static BoneCP createConnectionPool() {
//        try {
//            BoneCPConfig config = new BoneCPConfig();
//            config.setJdbcUrl(Config.getDbDatasource()); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
//            config.setUsername(Config.getDbUsername());
//            config.setPassword(Config.getDbPassword());
//            config.setMinConnectionsPerPartition(1);
//            config.setMaxConnectionsPerPartition(2);
//            config.setPartitionCount(1);
//            connectionPool = new BoneCP(config);
//        } catch (SQLException ex) {
//            logger.error(ex + "Could not create connection pool something wend worng.");
//        }
//        return connectionPool;
//    
//    }
//        public static void shutdownPool(){
//            connectionPool.shutdown();
//        }
    public static Connection getConnection() {
        Connection conn = null;
//        connectionPool = DataAccess.createConnectionPool();
//        try {
        try {
            //Create the connection object from driver
            conn = DriverManager.getConnection(Config.getDbDatasource(), Config.getDbUsername(), Config.getDbPassword());
        } catch (SQLException ex) {
            logger.error(ex.getMessage() + "Function: GetConnection in Class: DataAccess\n Cannot establish the connection");
        }
//            if (connectionPool != null) {
//                conn = connectionPool.getConnection();
//            } else {
//                logger.error("createConnectionPool() Connection pool returned empty");
//            }
//        } catch (SQLException ex) {
//            logger.error(ex + "cannot create new connection form pool");
//        }
        return conn;
    }

    public static boolean testConnection() {
        if (DataAccess.getConnection() == null) {
            return false;
        }
        return true;
    }

    public String getDbType() {
        try {
            return connection.getMetaData().getDatabaseProductName();
        } catch (Exception ex) {
            logger.error(ex.getMessage() + "Couldn't identifiy the database type");
        }
        return "";
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage() + "Connection close faild");
        }
    }
}
