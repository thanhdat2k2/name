package dao;


import connection.SQLServerConnection;
import model.dto.CStatusDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CStatusDAO extends SQLServerConnection {

    public List<CStatusDTO> getListcStatus() throws Exception {
        List<CStatusDTO> cStatusList = new ArrayList<>();
        String sql = "SELECT cs.cstatus_id, cs.cstatus_name FROM CStatus cs";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CStatusDTO c = new CStatusDTO(
                        rs.getInt(1),
                        rs.getString(2)
                );
                cStatusList.add(c);
            }
            return cStatusList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
