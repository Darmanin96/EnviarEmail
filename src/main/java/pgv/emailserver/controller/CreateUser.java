package pgv.emailserver.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pgv.emailserver.Conexion.DBManager;
import pgv.emailserver.CreacionUsuarios;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateUser implements Initializable {

    @FXML
    private Button Aceptar;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Limpiar;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField personal;

    @FXML
    private BorderPane root;

    @FXML
    private TextField usuario;

    private DBManager manager = new DBManager();

    private CreacionUsuarios creacionUsuarios = new CreacionUsuarios();

    private DBManager db = new DBManager();


    @FXML
    void onAceptarAction(ActionEvent event) {
        String usuario2 = usuario.getText().trim();
        String contraseña2 = contraseña.getText().trim();
        String personal2 = personal.getText().trim();
        if (usuario2.isEmpty() || contraseña2.isEmpty() || personal2.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Por favor, introducir todos los campos");
            return;
        }
        if(manager.existeUsuario(usuario2) || creacionUsuarios.existeUsuarioMercury(usuario2)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Usuario ya existe");
            alerta.show();
            Limpiar();
            return;
        }
        try {
            db.GuardarUsuario(usuario2,contraseña2);
            creacionUsuarios.crearContraseña(usuario2,contraseña2);
            creacionUsuarios.agregarUsuarioAlArchivo(usuario2,personal2);
            creacionUsuarios.reiniciarMercury();
            cerrar();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Usuario agregado");
            alerta.setHeaderText("Usuario agregado con éxito");
            alerta.show();
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo crear el usuario");
            alerta.show();
        }


    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    private void Limpiar(){
        personal.clear();
        usuario.clear();
        contraseña.clear();
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Confirmación");
        alerta.setContentText("¿Estás seguro?");
        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No");
        alerta.getButtonTypes().setAll(botonSi, botonNo);
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botonSi) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {

        }
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        contraseña.clear();
        personal.clear();
        usuario.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




    public PasswordField getContraseña() {
        return contraseña;
    }

    public void setContraseña(PasswordField contraseña) {
        this.contraseña = contraseña;
    }

    public TextField getPersonal() {
        return personal;
    }

    public void setPersonal(TextField personal) {
        this.personal = personal;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getUsuario() {
        return usuario;
    }

    public void setUsuario(TextField usuario) {
        this.usuario = usuario;
    }
}
