import bank.*;
import bank.exceptions.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hauptklasse
 *
 * @author fatih
 */
public class Main  {


    /**
     * Hauptmethode
     *
     * @param args "Die Elemente des Arrays verweisen auf Strings, die die Argumente der Kommandozeile enthalten, mit denen das Programm gestartet wurde" -gailer-net.de
     */
    public static void main(String[] args) throws AccountAlreadyExistsException, TransactionAlreadyExistException, AccountDoesNotExistException, TransactionDoesNotExistException, IOException{
        PrivateBank pb = new PrivateBank("",0.0,0.0,"C:/Users/Benutzer01/IdeaProjects/OOS/src/main/java/transactions");

        System.out.println(pb.getAccountBalance("Hannes"));
    }


}
