import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseOperation {
    private String userId;
    private String userPassword;
    private String userRequest;
    private String jdbcURL = "jdbc:postgresql:src/database/my_sch_stats.db";

    public DatabaseOperation(String userId, String userPassword, String userRequest) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userRequest = userRequest;
        this.jdbcURL = jdbcURL;
    }

    private void writeLine(String line, Writer writer) throws IOException {
        writer.append(line);
        writer.append("\"n");
    }

    private String sqlToString(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Writer strWriter = new StringWriter()) {
            String line = null;
            while ((line = br.readLine()) != null) {
                writeLine(line, strWriter);
                return strWriter.toString();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public void csvToTable(DatabaseOperation operation) {
        try {
            File csvFolder = new File("./src/inputcsv");
            String[] presentCsvFiles = csvFolder.list();
            if (!(presentCsvFiles.length == 0)) {
                for (String filename : presentCsvFiles) {
                    if ((filename.toLowerCase().endsWith(".csv")) && (new File(filename).isFile())) {
                        File csvFile = new File(filename);
                        String csvQuery = sqlToString("./src/database/TEMP.sql") +
                                " COPY TEMP FROM " + filename + " CSV HEADER";
                        operation.getQueryResult(operation, csvQuery);
                        csvFile.delete();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDatabase(DatabaseOperation operation) {
        try {
            String updateQuery = sqlToString("./src/database/updateQuery.sql");
            operation.getQueryResult(operation, updateQuery);
            String tempTableDropQuery = "DROP TABLE TEMP";
            operation.getQueryResult(operation, tempTableDropQuery);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printUserQueryResultSet(List<Map<String, Object>> userResultArraylist) {
        for (Map<String, Object> row : userResultArraylist) {
            row.forEach((columnName, columnValue) -> System.out.print(columnName + "=" + columnValue + "  "));
            System.out.println();
        }
    }

    public List<Map<String, Object>> getQueryResult(DatabaseOperation operation, String sqlQuery) {
        List<Map<String, Object>> userResultArraylist = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(operation.getJdbcURL(), operation.getUserId(), operation.getUserPassword());
             PreparedStatement prst = con.prepareStatement(sqlQuery)) {
            ResultSet rs = prst.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                userResultArraylist.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userResultArraylist;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}
