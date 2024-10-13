package org.EnviarEmail;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.apache.commons.mail.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class VerView implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public VerView(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VerView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    @FXML
    private TextField Asunto;

    @FXML
    private Button Cerrar;

    @FXML
    private PasswordField Contraseña;

    @FXML
    private TextField EmailDestinatario;

    @FXML
    private TextField EmailRemitente;

    @FXML
    private Button Enviar;

    @FXML
    private TextArea Mensaje;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Puerto;

    @FXML
    private CheckBox SSL;

    @FXML
    private Button Vaciar;

    @FXML
    private GridPane rootController;

    @FXML
    void onActionAsunto(ActionEvent event) {

    }

    @FXML
    void onActionCerrar(ActionEvent event) {
        Stage stage = (Stage) this.Cerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onActionContraseña(ActionEvent event) {

    }

    @FXML
    void onActionEmailDestinatario(ActionEvent event) {

    }

    @FXML
    void onActionEnviar(ActionEvent event) {
        if (!validaEmail(EmailRemitente.getText()) || !validaEmail(EmailDestinatario.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email erróneo");
            alert.setHeaderText("Formato de correo electrónico erróneo");
            alert.setContentText("Por favor introduce un correo electrónico válido");
            alert.show();
            return;
        }
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(Integer.parseInt(Puerto.getText()));
            email.setAuthenticator(new DefaultAuthenticator(EmailRemitente.getText(), Contraseña.getText()));
            email.setSSLOnConnect(SSL.isSelected());
            email.setFrom(EmailRemitente.getText(), Nombre.getText());
            email.addTo(EmailDestinatario.getText());
            email.setSubject(Asunto.getText());
            email.setMsg(Mensaje.getText());
            email.send();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje enviado");
            alert.setHeaderText(null);
            alert.setContentText("Email enviado con éxito a '" + EmailDestinatario.getText() + "'.");
            alert.show();
            onActionVaciar(event);
        } catch (EmailException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo enviar el email");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }


    @FXML
    void onActionMensaje(MouseEvent event) {

    }

    @FXML
    void onActionPuerto(ActionEvent event) {

    }

    @FXML
    void onActionSSL(ActionEvent event) {

    }

    @FXML
    void onActionVaciar(ActionEvent event) {
        Nombre.clear();
        Puerto.clear();
        SSL.setSelected(false);
        EmailRemitente.clear();
        EmailDestinatario.clear();
        Contraseña.clear();
        Mensaje.clear();
        Asunto.clear();
    }

    @FXML
    void onActionnombre(ActionEvent event) {

    }

    @FXML
    void onctionEmailRemitente(ActionEvent event) {

    }

    public GridPane getRootController() {
        return rootController;
    }

    public void setRootController(GridPane rootController) {
        this.rootController = rootController;
    }

    public Button getVaciar() {
        return Vaciar;
    }

    public void setVaciar(Button vaciar) {
        Vaciar = vaciar;
    }

    public CheckBox getSSL() {
        return SSL;
    }

    public void setSSL(CheckBox SSL) {
        this.SSL = SSL;
    }

    public TextField getPuerto() {
        return Puerto;
    }

    public void setPuerto(TextField puerto) {
        Puerto = puerto;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public TextArea getMensaje() {
        return Mensaje;
    }

    public void setMensaje(TextArea mensaje) {
        Mensaje = mensaje;
    }

    public Button getEnviar() {
        return Enviar;
    }

    public void setEnviar(Button enviar) {
        Enviar = enviar;
    }

    public TextField getEmailRemitente() {
        return EmailRemitente;
    }

    public void setEmailRemitente(TextField emailRemitente) {
        EmailRemitente = emailRemitente;
    }

    public TextField getEmailDestinatario() {
        return EmailDestinatario;
    }

    public void setEmailDestinatario(TextField emailDestinatario) {
        EmailDestinatario = emailDestinatario;
    }

    public PasswordField getContraseña() {
        return Contraseña;
    }

    public void setContraseña(PasswordField contraseña) {
        Contraseña = contraseña;
    }

    public Button getCerrar() {
        return Cerrar;
    }

    public void setCerrar(Button cerrar) {
        Cerrar = cerrar;
    }

    public TextField getAsunto() {
        return Asunto;
    }

    public void setAsunto(TextField asunto) {
        Asunto = asunto;
    }

    public static boolean validaEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();
    }
}
