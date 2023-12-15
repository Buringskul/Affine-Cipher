import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;
import java.awt.Container;
import javax.swing.*;

import javafx.scene.transform.Affine;

public class Affine_frontend extends JFrame implements ActionListener{
    private JTextArea output;
    private JLabel outputLabel;
    
    private JButton encryptButton;
    private JButton decryptButton;
   
    private JLabel textOption; 
    private JTextField textInput;

    private JLabel fileOption;
    private JLabel selectedFileLabel;
    private JTextField selectedFile;
    private JFileChooser fileChooser;
    private JButton openFileButton;

    private JLabel keyLabel;
    private JFormattedTextField keyA; 
    private JFormattedTextField keyB;

    private JFrame affineFrame;
    
    Affine_frontend() {
        // GridBagConstraints layout = null;
        // setTitle("Affine Cipher");

        affineFrame = new JFrame("Affine Cipher");

        Container container = affineFrame.getContentPane();
        container.setLayout(null);

        textOption = new JLabel("Enter text here:");

        textInput = new JTextField(15);
        textInput.setEditable(true);
        textInput.setText("");

        fileOption = new JLabel("Select .txt file");

        selectedFileLabel = new JLabel("Selected file:");
        selectedFile = new JTextField(20);
        selectedFile.setEditable(true);
        selectedFile.setText("...");

        openFileButton = new JButton("Open file");
        openFileButton.addActionListener(this);

        fileChooser = new JFileChooser();
        
        keyLabel = new JLabel("Keys");
        
        keyA = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyA.setEditable(true);
        keyA.setColumns(5);

        keyB = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyB.setEditable(true);
        keyB.setColumns(5);

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        
        output = new JTextArea(10, 15);
        output.setEditable(false);

        outputLabel = new JLabel("Encrypted/Decrypted Message");

        keyLabel.setBounds(130, 20, 150, 30);
        keyA.setBounds(90, 50, 50, 30);
        keyB.setBounds(150, 50, 50, 30);

        textOption.setBounds(110, 100, 150, 30);
        textInput.setBounds(75, 130, 150, 30);
        
        encryptButton.setBounds(30, 170, 100, 30);
        decryptButton.setBounds(160, 170, 100, 30);
        
        outputLabel.setBounds(320, 20, 200, 30);
        output.setBounds(320, 50, 300, 350);

        container.add(textInput);
        container.add(textOption);
        container.add(encryptButton);
        container.add(decryptButton);
        container.add(output);
        container.add(outputLabel);
        container.add(keyA);
        container.add(keyB);
        container.add(keyLabel);

        affineFrame.setSize(800, 600);
        affineFrame.setVisible(true);
        

        // setLayout(new GridBagLayout());

        // layout = new GridBagConstraints();
        // layout.insets = new Insets(10, 10, 5, 1);
        // layout.anchor = GridBagConstraints.LINE_END;
        // layout.gridx = 0;
        // layout.gridy = 0;
        // add(textOption, layout);

        // layout = new GridBagConstraints();
        // layout.insets = new Insets(10, 1, 5, 10);
        // layout.fill = GridBagConstraints.HORIZONTAL;
        // layout.gridx = 1;
        // layout.gridy = 0;
        // add(textInput, layout);

        // layout = new GridBagConstraints();
        // layout.insets = new Insets(0, 5, 0, 10);
        // layout.fill = GridBagConstraints.BOTH;
        // layout.gridx = 1;
        // layout.gridy = 1;
        // add(encryptButton, layout);

        // layout = new GridBagConstraints();
        // layout.insets = new Insets(0, 5, 0, 10);
        // layout.fill = GridBagConstraints.BOTH;
        // layout.gridx = 1;
        // layout.gridy = 3;
        // add(decryptButton, layout);
        
        // layout = new GridBagConstraints();
        // layout.insets = new Insets(10, 10, 1, 10);
        // layout.fill = GridBagConstraints.HORIZONTAL;
        // layout.gridx = 0;
        // layout.gridy = 5;
        // add(outputLabel, layout);
  
        // layout = new GridBagConstraints();
        // layout.insets = new Insets(1, 10, 10, 10);
        // layout.fill = GridBagConstraints.HORIZONTAL;
        // layout.gridx = 0;
        // layout.gridy = 6;
        // layout.gridwidth = 3; // 3 cells wide
        // add(output, layout);
    }

    

    // performs encryption
    @Override
    public void actionPerformed(ActionEvent event) {
        String msg = "";
        String encrypted = "";
        int a = 0;
        int b = 0;
        FileInputStream fileByteStream = null; 
        Scanner inFS = null;                  
        String readLine;                      
        File readFile = null;                  
        int fileChooserVal;                    

        JButton sourceEvent = (JButton)event.getSource();

        fileChooserVal = fileChooser.showOpenDialog(this);
        
        if (textInput.getText() != "") {
            msg = textInput.getText();            
        }
        else {
            if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
                readFile = fileChooser.getSelectedFile();

                selectedFile.setText(readFile.getName());

                if (readFile.canRead()) {
                    try {
                    fileByteStream = new FileInputStream(readFile);
                    inFS = new Scanner(fileByteStream);

                    output.setText(""); 

                    while (inFS.hasNext()) {
                        readLine = inFS.nextLine();
                        
                    }

                    } catch (IOException e) {
                    output.append("\n\nError occurred while creating file stream! " + e.getMessage());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Can't read file!");
                }
            }
        }

        a = ((Number) keyA.getValue()).intValue();
        b = ((Number) keyB.getValue()).intValue();

        encrypted = Affine_java.encrypt(msg, a, b);

        if (sourceEvent == encryptButton) {
            output.setText(Affine_java.encrypt(msg, a, b));
        }
        if (sourceEvent == decryptButton) {
            output.setText(Affine_java.decrypt(encrypted, a, b));
        }
        
        
    } 

    public static void main(String[] args) {
        Affine_frontend frontendFrame = new Affine_frontend();

        frontendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontendFrame.pack();
        frontendFrame.setVisible(true);

    }
}

