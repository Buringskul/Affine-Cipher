import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Affine_frontend extends JFrame implements ActionListener{
    private JTextArea output;
    private JLabel outputLabel;
    
    private JButton encryptButton;
    private JButton decryptButton;
    private JLabel textOption; 
    private JTextField textInput; // formatted text field so we can only take in 
    
    Affine_frontend() {
        GridBagConstraints layout = null;
        setTitle("Affine Cipher");

        textOption = new JLabel("Enter text here:");

        textInput = new JTextField(15);
        textInput.setEditable(true);
        textInput.setText("");
        
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        
        output = new JTextArea(10, 15);
        output.setEditable(false);

        outputLabel = new JLabel("Encrypted/Decrypted Message");

        setLayout(new GridBagLayout());

        layout = new GridBagConstraints();
        layout.insets = new Insets(10, 10, 5, 1);
        layout.anchor = GridBagConstraints.LINE_END;
        layout.gridx = 0;
        layout.gridy = 0;
        add(textOption, layout);

        layout = new GridBagConstraints();
        layout.insets = new Insets(10, 1, 5, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 0;
        add(textInput, layout);

        layout = new GridBagConstraints();
        layout.insets = new Insets(0, 5, 0, 10);
        layout.fill = GridBagConstraints.BOTH;
        layout.gridx = 5;
        layout.gridy = 1;
        add(encryptButton, layout);

        layout = new GridBagConstraints();
        layout.insets = new Insets(0, 5, 0, 10);
        layout.fill = GridBagConstraints.BOTH;
        layout.gridx = 5;
        layout.gridy = 3;
        add(decryptButton, layout);
        
        layout = new GridBagConstraints();
        layout.insets = new Insets(10, 10, 1, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        add(outputLabel, layout);
  
        layout = new GridBagConstraints();
        layout.insets = new Insets(1, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 4;
        layout.gridwidth = 3; // 3 cells wide
        add(output, layout);
    }

    // performs encryption
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "";
        String encrypted = "";

        JButton sourceEvent = (JButton)e.getSource();

        msg = textInput.getText();
        encrypted = Affine_java.encrypt(msg);

        if (sourceEvent == encryptButton) {
            output.setText(Affine_java.encrypt(msg));
        }
        if (sourceEvent == decryptButton) {
            output.setText(Affine_java.decrypt(encrypted));
        }
    } 

    public static void main(String[] args) {
        Affine_frontend frontendFrame = new Affine_frontend();

        frontendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontendFrame.pack();
        frontendFrame.setVisible(true);
    }
}

