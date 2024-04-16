package dao;

import connection.SQLServerConnection;
import model.dto.SkillDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO {
    public List<SkillDTO> getAllSKill() throws Exception {
        List<SkillDTO> skillDTOList = new ArrayList<>();
        String sql = "SELECT s.skill_id, s.skill_name FROM Skill s";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SkillDTO skillDTO = new SkillDTO();
                skillDTO.setSkillId(rs.getInt("skill_id"));
                skillDTO.setSkillName(rs.getString("skill_name"));
                skillDTOList.add(skillDTO);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return skillDTOList;
    }
}
