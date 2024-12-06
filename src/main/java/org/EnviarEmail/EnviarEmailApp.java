package org.EnviarEmail;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class EnviarEmailApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VerView view = new VerView();
        Stage ver = new Stage();
        ver.setTitle("Enviar Email");
        ver.setScene(new Scene(view.getRootController()));
        ver.show();
    }


}
