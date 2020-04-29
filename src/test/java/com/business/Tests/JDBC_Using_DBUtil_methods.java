package com.business.Tests;

import com.business.Utilities.DBUtils;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBC_Using_DBUtil_methods {

    @Test
    public void metadataTesting() throws SQLException {
        DBUtils.createConnectionToHrDB();
        String query = "select * from employees;";
        // Using DBUtil get ROWSCOUNT in the result
        int rowsCountInResult = DBUtils.executeQueryAndGetRowsCount(query);
        System.out.println("total row count is :" + rowsCountInResult);
        // Using DBUtil get total COLUMNSCOUNT in the result
        int columnsCount = DBUtils.executeQueryAndGetColumnsCount(query);
        System.out.println("total columns count is :" + columnsCount);
        // Using DBUtil class get COLUMNSNAMES in the result
        List<String> columnNames = DBUtils.executeQueryAndGetColumnsNames(query);
        for (String columnName : columnNames) {
            System.out.println(columnName);
        }
        // Using DBUtil class get ALL VALUES IN GIVEN COLUMN in result
        List<String> names = DBUtils.executeQueryAndGetColumnValues(query, "last_name");
        for (String name : names) {
            System.out.println(name);
        }
        // Using DBUtil get RESULT AS LIST OF LISTS (each row is one list)
        List<List<Object>> resultAsList = DBUtils.executeQueryAndGetResultAsList(query);
        for (int i = 0; i < resultAsList.size(); i++) {
            System.out.println(resultAsList.get(i).toString());
        }
        // Using DBUtil get RESULT AS LIST OF MAPS (each row is one list)
        List<Map<String, Object>> resultAsListOfMaps = DBUtils.executeQueryAndGetResultMap(query);
        for (int i = 0; i < resultAsListOfMaps.size(); i++) {
            System.out.println(resultAsListOfMaps.get(i).get("first_name") + " - "
                    + resultAsListOfMaps.get(i).get("last_name"));
        }
        DBUtils.destroy();


    }


}