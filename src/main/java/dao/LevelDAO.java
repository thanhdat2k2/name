package dao;

import connection.SQLServerConnection;
import model.dto.LevelDTO;
import model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LevelDAO {
    public List<LevelDTO> getAllLevel() throws Exception {
        List<LevelDTO> levelList = new ArrayList<>();
        String sql = "SELECT l.level_id, l.level_name FROM Level l";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LevelDTO levelDTO = new LevelDTO();
                levelDTO.setLevelId(rs.getInt("level_id"));
                levelDTO.setLevelName(rs.getString("level_name"));
                levelList.add(levelDTO);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return levelList;
    }
}
