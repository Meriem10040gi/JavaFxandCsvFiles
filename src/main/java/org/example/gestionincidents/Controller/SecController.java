package org.example.gestionincidents.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestionincidents.DAO.MemberDAO;
import org.example.gestionincidents.DAO.MemberDAOImpl;
import org.example.gestionincidents.HelloApplication;
import org.example.gestionincidents.Model.Member;
import org.example.gestionincidents.Service.MembreService;
import org.example.gestionincidents.Utils.Browser;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class SecController {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TableView<Member> MemberTable;

    @FXML
    private TableColumn<Member, Integer> idColumn;
    @FXML
    private TableColumn<Member, String> nomColumn;
    @FXML
    private TableColumn<Member, String> prenomColumn;
    @FXML
    private TableColumn<Member, String> phoneColumn;
    @FXML
    private TableColumn<Member, String> emailColumn;
    @FXML
    private TextField file;
    @FXML
    private Button incidentbtn;

    MemberDAO memberDAO = new MemberDAOImpl();
    MembreService membreService = new MembreService();

    public void initialize() throws SQLException {
        List<Member> Members = memberDAO.chargerListMembers();
        ObservableList<Member> MemberList = FXCollections.observableArrayList(Members);
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdentifiant()).asObject());
        idColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });

        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        prenomColumn.setCellFactory(column -> {
            TableCell<Member, String> cell = new TableCell<Member, String>() {
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
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        nomColumn.setCellFactory(column -> {
            TableCell<Member, String> cell = new TableCell<Member, String>() {
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
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailColumn.setCellFactory(column -> {
            TableCell<Member, String> cell = new TableCell<Member, String>() {
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
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        phoneColumn.setCellFactory(column -> {
            TableCell<Member, String> cell = new TableCell<Member, String>() {
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
        MemberTable.setItems(MemberList);
    }

    public void InsertDataMembers() throws SQLException {
        if(file!=null && !file.getText().equals("")){
            String path = file.getText();
            Set<Member> members = membreService.chargerListeMembre(path);
            membreService.inser(members);
            initialize();
        }
    }
    public void BrowsFileMembers(ActionEvent actionEvent) {
        Browser b = new Browser();
        file.setText(b.Find("csv"));
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
        int h =m.hashCode();
        if(h<0) h=-1*h;
        m.setIdentifiant(h);
        MemberDAO md = new MemberDAOImpl();
        try{
            md.insere(m);
            nom.clear();
            prenom.clear();
            email.clear();
            phone.clear();
            initialize();
        }
        catch(SQLException e){
            System.out.println("Erreur lors d'ajout de l'utilisateur"+e.getMessage());
        }
    }

    public void IncidentView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Views/HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) incidentbtn.getScene().getWindow();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

