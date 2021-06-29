package businessLogic;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Download {
    public static void download(Window window, String fileContent, String fileName) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(window);
        try {
            File myObj = new File(chosenDir.getPath() + "Bill.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(chosenDir.getPath() + "/" + fileName);
            myWriter.write(fileContent);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
