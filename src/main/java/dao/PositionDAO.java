package dao;

import connection.SQLServerConnection;
import model.Gender;
import model.Position;
import model.dto.PositionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO extends SQLServerConnection {
    public Position getPositionById(int positionId) throws SQLException {
        Position position = null;
        String query = "SELECT * FROM position WHERE position_id = ?";

        try ( Connection connection = SQLServerConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, positionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String positionName = resultSet.getString("position_name");
                    position = new Position(positionId, positionName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return position;
    }

    public List<PositionDTO> getAllPosition() throws Exception {
        List<PositionDTO> positionDTOList = new ArrayList<>();
        String sql = "SELECT p.position_id, p.position_name FROM Position p";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PositionDTO positionDTO = new PositionDTO();
                positionDTO.setPositionId(rs.getInt("position_id"));
                positionDTO.setPositionName(rs.getString("position_name"));
                positionDTOList.add(positionDTO);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return positionDTOList;
    }

    public static void main(String[] args) {
        PositionDAO positionDAO = new PositionDAO();
        try {
            System.out.println(positionDAO.getPositionById(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
