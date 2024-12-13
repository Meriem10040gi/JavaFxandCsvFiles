package org.example.gestionincidents.DAO;

import org.example.gestionincidents.Model.Incident;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IncidentDAO {
    void inserer(Incident e) throws SQLException;
    public List<Incident> chargerListIncidents() throws SQLException;
}
