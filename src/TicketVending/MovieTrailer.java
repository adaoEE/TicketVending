package TicketVending;

//import java.net.URL;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
//import javax.swing.ImageIcon;

public class MovieTrailer extends Application  {
    public MovieInfor movieInfo = new MovieInfor();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(830);
        stage.setHeight(650);
        Scene scene = new Scene(new Group());

//        String link = String.valueOf(movieInfo.VideoUrl);
//         URL url = new URL(link);
//         System.out.println(link + url);
//         System.out.println(movieInfo.VideoUrl);
         
         String link = "https://www.youtube.com/embed/S5CjKEFb-sM";
//         System.out.println(link);
//            ImageIcon imgThisImg = new ImageIcon(url);
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);

        webEngine.getLoadWorker().stateProperty()
                .addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

                        if (newState == Worker.State.SUCCEEDED) {
                            stage.setTitle(webEngine.getLocation());
                        }

                    }
                });
        webEngine.load(link); //"https://www.youtube.com/embed/S5CjKEFb-sM"  https://www.youtube.com/embed/S5CjKEFb-sM

        scene.setRoot(scrollPane);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
        
    }
}
