package org.example.gestionincidents.DAO;

import org.example.gestionincidents.Model.Incident;
import org.example.gestionincidents.Model.Member;
import org.example.gestionincidents.Utils.GestionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IncidentDAOImpl implements IncidentDAO {
    public final GestionBD BD = new GestionBD();
    @Override
    public void inserer(Incident e) throws SQLException {
        BD.connecte("MemberIncidents","root","");
        String sql="insert into incident (ref,status,time,description,supervisor_id) values (?,?,?,?,?)";
        String checkid = "SELECT COUNT(*) FROM member WHERE ref LIKE ?";
        PreparedStatement checkId = BD.connexion.prepareStatement(checkid);
        checkId.setString(1, e.getReference());
        ResultSet rs = checkId.executeQuery();
        PreparedStatement stmt = BD.connexion.prepareStatement(sql);
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.setString(1,e.getReference());
            stmt.setString(2,e.getStatus());
            stmt.setLong(3,e.getTime());
            stmt.setString(4,e.getDescription());
            stmt.setInt(5,e.getSuperviseur().getIdentifiant());
            stmt.executeUpdate();

        }
        BD.close();
        checkId.close();
        stmt.close();
    }

    @Override
    public List<Incident> chargerListIncidents() throws SQLException {
        BD.connecte("MemberIncidents","root","");
        String sql = "SELECT * FROM incident";
        PreparedStatement stmt = BD.connexion.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Incident> list = new ArrayList<>();
        while (rs.next()){
            Incident i = new Incident(
                    rs.getString("ref"),
                    rs.getString("status"),
                    rs.getLong("time"),
                    rs.getString("description"),
                    null
            );
            String sql2 = "SELECT * FROM member Where id = ?";
            PreparedStatement stmt2 = BD.connexion.prepareStatement(sql2);
            stmt2.setInt(1,rs.getInt("supervisor_id"));
            ResultSet sup = stmt2.executeQuery();
            if(sup.next()){
            Member s = new Member(
                    sup.getInt("id"),
                    sup.getString("nom"),
                    sup.getString("prenom"),
                    sup.getString("email"),
                    sup.getString("phone")
            );
            i.setSuperviseur(s);}
            list.add(i);
        }
        stmt.close();
        BD.close();
        return list;
    }
}
