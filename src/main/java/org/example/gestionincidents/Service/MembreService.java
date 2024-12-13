package org.example.gestionincidents.Service;

import org.example.gestionincidents.DAO.MemberDAO;
import org.example.gestionincidents.DAO.MemberDAOImpl;
import org.example.gestionincidents.Model.Member;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MembreService {

    private final MemberDAO memberDao = new MemberDAOImpl();

    public void inser(Set<Member> si) {
        for (Member membre : si) {
            try {
                memberDao.insere(membre);
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'insertion du membre avec ID " + membre.getIdentifiant() + ": " + e.getMessage());
            }
        }
    }

    public  Set<Member> chargerListeMembre(String nomFichier) {
        Set<Member> members = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 4) {
                    String nom = data[0].trim();
                    String prenom = data[1].trim();
                    String email = data[2].trim();
                    String phone = data[3].trim();
                    Member member = new Member(0, nom, prenom, email, phone);
                    int h =member.hashCode();
                    if(h<0) h=-1*h;
                    member.setIdentifiant(h);
                    members.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("une erruer est survenue "+e.getMessage());
        }
        return members;
    }
}

