package dao;

import connection.SQLServerConnection;
import model.Candidate;
import model.dto.CandidateDTO;
import model.dto.CandidateSkillDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidateDAO extends SQLServerConnection {
    public List<CandidateDTO> getListCandidate() throws Exception {
        List<CandidateDTO> candidateList = new ArrayList<>();
        String sql = "SELECT c.candidate_id, c.candidate_name, c.email, c.phone, c.position_id, p.position_name, " +
                "c.owner_HR, c.cstatus_id, cs.cstatus_name " +
                "FROM Candidate c INNER JOIN Position p ON c.position_id = p.position_id " +
                "INNER JOIN CStatus cs ON c.cstatus_id = cs.cstatus_id";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)
                );
                candidateList.add(c);
            }
            return candidateList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<CandidateDTO> getListCandidate(String name, int status) throws Exception {
        List<CandidateDTO> candidateList = new ArrayList<>();
        String sql = "SELECT c.candidate_id, c.candidate_name, c.email, c.phone, c.position_id, p.position_name, " +
                "c.owner_HR, c.cstatus_id, cs.cstatus_name " +
                "FROM Candidate c INNER JOIN Position p ON c.position_id = p.position_id " +
                "INNER JOIN CStatus cs ON c.cstatus_id = cs.cstatus_id " +
                "WHERE c.candidate_name LIKE ? " +
                "AND c.cstatus_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)
                );
                candidateList.add(c);
            }
            return candidateList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CandidateDTO> getListCandidate(String name) throws Exception {
        List<CandidateDTO> candidateList = new ArrayList<>();
        String sql = "SELECT c.candidate_id, c.candidate_name, c.email, c.phone, c.position_id, p.position_name, " +
                "c.owner_HR, c.cstatus_id, cs.cstatus_name " +
                "FROM Candidate c INNER JOIN Position p ON c.position_id = p.position_id " +
                "INNER JOIN CStatus cs ON c.cstatus_id = cs.cstatus_id " +
                "WHERE c.candidate_name LIKE ? ";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)
                );
                candidateList.add(c);
            }
            return candidateList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CandidateDTO> getListCandidate(int status) throws Exception {
        List<CandidateDTO> candidateList = new ArrayList<>();
        String sql = "SELECT c.candidate_id, c.candidate_name, c.email, c.phone, c.position_id, p.position_name, " +
                "c.owner_HR, c.cstatus_id, cs.cstatus_name " +
                "FROM Candidate c INNER JOIN Position p ON c.position_id = p.position_id " +
                "INNER JOIN CStatus cs ON c.cstatus_id = cs.cstatus_id " +
                "WHERE c.cstatus_id = ? ";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)
                );
                candidateList.add(c);
            }
            return candidateList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int createCandidate(String name, Date dob, String phone, String email, String address, int gender_id,
                               String cv_att, String note, int position_id, int status, int yoe, int recruiter_id,
                               int level_id, String ownerHR) throws Exception {
        String sql = "INSERT INTO Candidate VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLServerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setDate(2, (java.sql.Date) dob);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.setInt(6, gender_id);
            ps.setString(7, cv_att);
            ps.setInt(8, position_id);
            ps.setInt(9, recruiter_id);
            ps.setString(10, note);
            ps.setInt(11, status);
            ps.setInt(12, yoe);
            ps.setInt(13, level_id);
            ps.setString(14, ownerHR);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("Failed to create candidate, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new Exception("Failed to retrieve candidate_id after insertion.");
                }
            }
        }
    }

    public void createCandidateSkill(int candidate_id, List<Integer> skill_ids) throws Exception {
        String sql = "INSERT INTO Candidate_Skill VALUES (?, ?)";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            for (int skill_id : skill_ids) {
                ps.setInt(1, candidate_id);
                ps.setInt(2, skill_id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CandidateDTO viewCandidateInfo(int candidate_id) throws Exception {
        String sql = "SELECT c.candidate_id, c.candidate_name, c.email, c.dob, c.address, c.phone, c.gender_id, " +
                "g.gender_name, c.CV_att, c.cstatus_id, cs.cstatus_name, c.position_id, p.position_name, c.yoe, c.level_id, " +
                "l.level_name, c.recruiter_id, u.fullname, c.note " +
                "FROM Candidate c INNER JOIN Gender g ON c.gender_id = g.gender_id " +
                "INNER JOIN CStatus cs ON c.cstatus_id = cs.cstatus_id " +
                "INNER JOIN Position p on c.position_id = p.position_id " +
                "INNER JOIN Level l on c.level_id = l.level_id " +
                "INNER JOIN [User] u on c.recruiter_id = u.user_id " +
                "WHERE c.candidate_id IS NULL OR c.candidate_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, candidate_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CandidateDTO c = new CandidateDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17),
                        rs.getString(18),
                        rs.getString(19)
                );
                return c;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public List<CandidateSkillDTO> viewSKillOfCandidate(int candidate_id) throws Exception {
        List<CandidateSkillDTO> skillList = new ArrayList<>();
        String sql = "SELECT cs.candidate_id, cs.skill_id, c.candidate_name, s.skill_name " +
                "FROM Candidate_Skill cs INNER JOIN Candidate c ON cs.candidate_id = c.candidate_id " +
                "INNER JOIN Skill s ON cs.skill_id = s.skill_id " +
                "WHERE c.candidate_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, candidate_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CandidateSkillDTO cs = new CandidateSkillDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                skillList.add(cs);
            }
            return skillList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int deleteCandidate(int candidate_id) throws Exception {
        String sqlDeleteCanDidateSkill = "DELETE FROM Candidate_Skill WHERE candidate_id = ?";
        String sqlDeleteCandidate = "DELETE FROM Candidate WHERE candidate_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement psDeleteCandidateSkill = connection.prepareStatement(sqlDeleteCanDidateSkill);
                PreparedStatement psDeleteCandidate = connection.prepareStatement(sqlDeleteCandidate)
        ) {
            psDeleteCandidateSkill.setInt(1, candidate_id);
            psDeleteCandidate.setInt(1, candidate_id);
            int updateCheck = psDeleteCandidate.executeUpdate();
            if (updateCheck > 0) {
                return candidate_id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int banCandidate(int candidate_id) throws Exception {
        String sql = "Update Candidate " +
                "SET candidate_status = 2 " +
                "WHERE candidate_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, candidate_id);
            int updateCheck = ps.executeUpdate();
            if (updateCheck > 0) {
                return candidate_id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int updateCandidate(CandidateDTO c) throws Exception {
        String sql = "UPDATE Candidate " +
                "SET candidate_name = ?, DOB = ?, phone = ?, email = ?, address = ?, gender_id = ?, CV_att = ?, " +
                "position_id = ?, recruiter_id = ?, note = ?, YoE = ?, level_id = ? " +
                "WHERE candidate_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, c.getCandidateName());
            ps.setDate(2, (java.sql.Date) c.getDob());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());
            ps.setInt(6, c.getGenderId());
            ps.setString(7, c.getCvAtt());
            ps.setInt(8, c.getPositionId());
            ps.setInt(9, c.getRecruiterId());
            ps.setString(10, c.getNote());
            ps.setInt(11, c.getYoe());
            ps.setInt(12, c.getLevelId());
            ps.setInt(13, c.getCandidateId());
            int updateCheck = ps.executeUpdate();
            if (updateCheck > 0) {
                return c.getCandidateId();
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int updateSKillForCandidate(int candidate_id, List<Integer> skill_ids) throws Exception {
        if (skill_ids.isEmpty()) {
            return -1;
        } else {
            String sqlDeleteFK = "DELETE FROM Candidate_Skill WHERE candidate_id = ?";
            String sqlInsertFKAgain = "INSERT INTO Candidate_Skill VALUES (?, ?)";
            try (
                    Connection connection = SQLServerConnection.getConnection();
                    PreparedStatement psDelete = connection.prepareStatement(sqlDeleteFK);
                    PreparedStatement psInsert = connection.prepareStatement(sqlInsertFKAgain);
            ) {
                psDelete.setInt(1, candidate_id);
                psDelete.executeUpdate();
                for (int skill_id : skill_ids) {
                    psInsert.setInt(1, candidate_id);
                    psInsert.setInt(2, skill_id);
                    psInsert.executeUpdate();
                }
                return candidate_id;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CandidateDAO c = new CandidateDAO();
        System.out.println(c.viewSKillOfCandidate(3));
    }
}
