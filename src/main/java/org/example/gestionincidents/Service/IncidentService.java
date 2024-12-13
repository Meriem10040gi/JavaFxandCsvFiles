package org.example.gestionincidents.Service;

import org.example.gestionincidents.DAO.IncidentDAO;
import org.example.gestionincidents.DAO.IncidentDAOImpl;
import org.example.gestionincidents.DAO.MemberDAO;
import org.example.gestionincidents.DAO.MemberDAOImpl;
import org.example.gestionincidents.Model.Incident;
import org.example.gestionincidents.Model.Member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class IncidentService {

    private final IncidentDAO incidentDao = new IncidentDAOImpl();
    private final MemberDAO memberDao = new MemberDAOImpl();

    public void inser(Set<Incident> si) throws SQLException {
        for (Incident incident : si) {
            if(memberDao.isExist(incident.getSuperviseur().getIdentifiant())){
                 try {
                     incidentDao.inserer(incident);
                 } catch (SQLException e) {
                     System.err.println("Erreur lors de l'insertion de l'incident avec Ref " + incident.getReference() + ": " + e.getMessage());
            }}
        }
    }
    public  Set<Incident> chargerListeIncidents(String nomFichier) {
        Set<Incident> incidents = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 5) {
                    String ref = data[0].trim();
                    Long time = Long.parseLong(data[1].trim());
                    String status = data[2].trim();
                    int supervisor_id = Integer.parseInt(data[3].trim());
                    String description = data[4].trim();
                    Incident incident = new Incident(ref,status,time,description,null);
                    Member m = new Member(supervisor_id,null,null,null,null);
                    incident.setSuperviseur(m);
                    incidents.add(incident);
                }
            }
        } catch (IOException e) {
            System.out.println("une erruer est survenue "+e.getMessage());
        }
        return incidents;
    }
}
