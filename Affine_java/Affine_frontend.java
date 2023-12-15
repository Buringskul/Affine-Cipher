import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Affine_frontend extends JFrame implements ActionListener{
    private JTextArea output;
    
    private JLabel textOption; 
    private JFormattedTextField textInput; // formatted text field so we can only take in 
   
    // label for file field 
    // file input field
    
    Affine_frontend() {
        GridBagConstraints layout = null;
        setTitle("Repeat test");
        
        

        output = new JTextArea(10,15);
        output.setEditable(false);

        textOption = new JLabel("Enter text here:");
        textInput = new JFormattedTextField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    } 

    public static void main(String[] args) {
        Affine_frontend frontendFrame = new Affine_frontend();

        frontendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontendFrame.pack();
        frontendFrame.setVisible(true);
    }
}

