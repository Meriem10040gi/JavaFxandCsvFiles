package org.example.gestionincidents.DAO;

import org.example.gestionincidents.Model.Member;
import org.example.gestionincidents.Utils.GestionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    GestionBD BD = new GestionBD();
    @Override
    public void insere(Member e) throws SQLException {
          BD.connecte("MemberIncidents","root","");
              String sql="insert into member (id,nom,prenom,email,phone) values (?,?,?,?,?)";
              String checkid = "SELECT COUNT(*) FROM member WHERE id = ?";
              PreparedStatement checkId = BD.connexion.prepareStatement(checkid);
              checkId.setInt(1, e.getIdentifiant());
              ResultSet rs = checkId.executeQuery();
              PreparedStatement stmt = BD.connexion.prepareStatement(sql);
              if (rs.next() && rs.getInt(1) == 0) {
                  stmt.setInt(1, e.getIdentifiant());
                  stmt.setString(2, e.getNom());
                  stmt.setString(3, e.getPrenom());
                  stmt.setString(4, e.getEmail());
                  stmt.setString(5, e.getPhone());
                  stmt.execute();
              }
              stmt.close();
              checkId.close();
              BD.close();
    }

    @Override
    public List<Member> chargerListMembers() throws SQLException {
        BD.connecte("MemberIncidents","root","");
        String sql = "SELECT * FROM member";
        PreparedStatement stmt = BD.connexion.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Member> list = new ArrayList<>();
        while (rs.next()){
         Member s = new Member(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("phone")
        );
        list.add(s);
    }
        stmt.close();
        BD.close();
        return list;
    }

    @Override
    public boolean isExist(int id) throws SQLException {
        BD.connecte("MemberIncidents","root","");
        String checkid = "SELECT COUNT(*) FROM member WHERE id = ?";
        PreparedStatement checkId = BD.connexion.prepareStatement(checkid);
        checkId.setInt(1, id);
        ResultSet rs = checkId.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return true;
        }
        return false;
    }


}
