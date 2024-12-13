package org.example.gestionincidents.Service;

import org.example.gestionincidents.DAO.IncidentDAO;
import org.example.gestionincidents.DAO.IncidentDAOImpl;
import org.example.gestionincidents.Model.Incident;

import java.sql.SQLException;
import java.util.Set;

public class IncidentService {
    private final IncidentDAO incidentDao = new IncidentDAOImpl();
    public void inser(Set<Incident> si) {
        for (Incident incident : si) {
            try {
                incidentDao.inserer(incident);
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'insertion de l'incident avec Ref " + incident.getReference() + ": " + e.getMessage());
            }
        }
    }
}
