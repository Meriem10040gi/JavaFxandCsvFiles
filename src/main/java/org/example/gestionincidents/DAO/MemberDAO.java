package org.example.gestionincidents.DAO;
import org.example.gestionincidents.Model.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    void insere(Member e) throws SQLException;
    List<Member> chargerListMembers() throws SQLException;
}
