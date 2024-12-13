package org.example.gestionincidents.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    String reference;
    String status;
    Long time;
    String description;
    Member superviseur;
}
