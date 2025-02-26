package DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class BrowseImage{
   public File file;
    public FileInputStream fis;
    public void browseImage(ImageView imageV)
    {
        
        FileChooser obj=new FileChooser();
        obj.setTitle("Select Image");
        obj.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.JPG"));
        file=obj.showOpenDialog(new Stage());
        
        if(file!=null){
            try{
                Image img=new Image(new FileInputStream(file));
                imageV.setImage(img);
            }
            catch(Exception ex)
            {
                
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public InputStream insertImage(ImageView img) throws FileNotFoundException
    {
        try{
        InputStream is=new FileInputStream(file);
        return is;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    public FileInputStream loadImage(ImageView image)
    {
        try {
            fis=new FileInputStream(file);
            fis.read();
            return fis;
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
}