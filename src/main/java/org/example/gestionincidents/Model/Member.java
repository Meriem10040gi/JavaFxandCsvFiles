package org.example.gestionincidents.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    int identifiant;
    String nom;
    String prenom;
    String email;
    String phone;

    @Override
    public boolean equals(Object e ){
        Member e2=(Member)e;
        return e2.getIdentifiant()==this.identifiant;
    }
    @Override
    public int hashCode() {
        return Objects.hash(nom,phone,prenom,email);
    }
}
