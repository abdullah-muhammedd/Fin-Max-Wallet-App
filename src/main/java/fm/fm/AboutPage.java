package fm.fm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutPage {
    //FX Components
    @FXML
    Hyperlink linkedin_link;
    @FXML
    Hyperlink github_link;
    @FXML
    Hyperlink facebook_link;

    //Handling exceptions
    public void accountBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildMainPage());
        HelloApplication.stage.show();
    }

    public void depositBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildDepositPage());
        HelloApplication.stage.show();
    }

    public void withdrawBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildWithdrawPage());
        HelloApplication.stage.show();
    }

    public void aboutBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildAboutPage());
        HelloApplication.stage.show();
    }

    public void linkedinLinkOnAction() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(linkedin_link.getText()));
    }

    public void facebookLinkOnAction() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(facebook_link.getText()));
    }

    public void githubLinkOnAction() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(github_link.getText()));
    }
}
