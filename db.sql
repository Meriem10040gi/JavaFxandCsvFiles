-- Création de la base de données CommandeClient
CREATE DATABASE MemberIncidents;

-- Sélectionner la base de données CommandeClient
USE MemberIncidents;

-- Création de la table Client
CREATE TABLE Member (
                        id int PRIMARY KEY,      -- Identifiant unique pour chaque client
                        nom VARCHAR(255) NOT NULL,        -- Nom du client
                        prenom VARCHAR(255) NOT NULL,     -- Prénom du client
                        email VARCHAR(255) NOT NULL,      -- Email du client
                        phone VARCHAR(20) NOT NULL        -- Numéro de téléphone du client
);

-- Création de la table Commande
CREATE TABLE Commande (
                          ref VARCHAR(255) PRIMARY KEY,      -- Identifiant unique pour chaque commande
                          status VARCHAR(255),           -- Référence au client
                          description VARCHAR(255),           -- Référence au client
                          supervisor_id int ,               -- Statut de la commande (ex: 'En attente', 'Livrée', etc.)
                          time BIGINT NOT NULL,        -- Date de création de la commande (en timestamp Unix)

    -- Définition de la clé étrangère liant la commande au client
                          FOREIGN KEY (supervisor_id) REFERENCES member(id) ON DELETE CASCADE
);
