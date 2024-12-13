package org.example.gestionincidents.Controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.gestionincidents.DAO.IncidentDAO;
import org.example.gestionincidents.DAO.IncidentDAOImpl;
import org.example.gestionincidents.Model.Incident;
import org.example.gestionincidents.Model.Member;
import org.example.gestionincidents.DAO.MemberDAO;
import org.example.gestionincidents.DAO.MemberDAOImpl;
import org.example.gestionincidents.Service.MembreService;
import org.example.gestionincidents.Utils.Browser;
import org.example.gestionincidents.Utils.TimeConverteser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MainController {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TableView<Incident> incidentTable;

    @FXML
    private TableColumn<Incident, String> refColumn;
    @FXML
    private TableColumn<Incident, Long> timeColumn;
    @FXML
    private TableColumn<Incident, String> statusColumn;
    @FXML
    private TableColumn<Incident, String> supervisorColumn;
    @FXML
    private TableColumn<Incident, String> descriptionColumn;
    @FXML
    private TextField file;
    @FXML
    private TextField file2;

    IncidentDAO incidentDAO = new IncidentDAOImpl();
    MembreService membreService = new MembreService();

    public void initialize() throws SQLException {
        List<Incident> Incidents = incidentDAO.chargerListIncidents();
        ObservableList<Incident> IncidentList = FXCollections.observableArrayList(Incidents);
        refColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReference()));
        refColumn.setCellFactory(column -> {
            TableCell<Incident, String> cell = new TableCell<Incident, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        timeColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getTime()).asObject());
        timeColumn.setCellFactory(column -> {
            TableCell<Incident, Long> cell = new TableCell<Incident, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        TimeConverteser t = new TimeConverteser();
                        setText(t.convert(item));
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        statusColumn.setCellFactory(column -> {
            TableCell<Incident, String> cell = new TableCell<Incident, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        descriptionColumn.setCellFactory(column -> {
            TableCell<Incident, String> cell = new TableCell<Incident, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        supervisorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuperviseur().getEmail()));
        supervisorColumn.setCellFactory(column -> {
            TableCell<Incident, String> cell = new TableCell<Incident, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        incidentTable.setItems(IncidentList);
    }

    public void InsererDataMembers(){
        if(file2!=null && !file2.getText().equals("")){
            String path = file2.getText();
            Set<Member> members = membreService.chargerListeMembre(path);
            membreService.inser(members);
            file2.clear();
        }
    }
    public void BrowsFileMembers(ActionEvent actionEvent) {
        Browser b = new Browser();
        file2.setText(b.Find("csv"));
    }

    public void BrowsFileIncidents(ActionEvent actionEvent) {
        Browser b = new Browser();
        file.setText(b.Find("csv"));
    }

    public void InsertData(ActionEvent actionEvent) throws IOException, SQLException {

        initialize();
    }

    public void SignOut(ActionEvent actionEvent) {

    }

    public void AddMember(){
        String Snom = nom.getText();
        String Sprenom = prenom.getText();
        String Sphone = phone.getText();
        String Semail = email.getText();
        System.out.println("depui le controlleur : "+100+Snom+Sprenom+Sphone+Semail);
        Member m = new Member(
                0,
                Snom,
                Sprenom,
                Semail,
                Sphone
        );
        m.setIdentifiant(m.hashCode());
        MemberDAO md = new MemberDAOImpl();
        try{
            md.insere(m);
        }
        catch(SQLException e){
            System.out.println("Erreur lors d'ajout de l'utilisateur"+e.getMessage());
        }
    }

    public void MemberView(ActionEvent actionEvent) {
    }
}