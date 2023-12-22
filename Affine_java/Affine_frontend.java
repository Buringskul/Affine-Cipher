import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;
import java.awt.Container;
import javax.swing.*;


public class Affine_frontend extends JFrame implements ActionListener{
    private JTextArea output;
    private JLabel outputLabel;
    
    private JButton encryptButton;
    private JButton decryptButton;

    private JLabel textOption; 
    private JTextArea textPad;

    private JLabel fileOption;
    private JLabel selectedFileLabel;
    private JTextField selectedFile;
    private JFileChooser fileChooser;
    private JButton openFileButton;
    private JButton writeFileButton;

    private JLabel keyLabel;
    private JFormattedTextField keyA; 
    private JFormattedTextField keyB;

    private JFrame affineFrame;
    
    Affine_frontend() {
        affineFrame = new JFrame("Affine Cipher");

        Container container = affineFrame.getContentPane();
        container.setLayout(null);
        JScrollPane inScrollPane = null;
        JScrollPane outScrollPane = null;

        textOption = new JLabel("Enter text here:");

        textPad = new JTextArea(10, HEIGHT);
        textPad.setEditable(true);
        textPad.setLineWrap(true);
        inScrollPane = new JScrollPane(textPad);
        textPad.setText("");

        fileOption = new JLabel("Select .txt file");

        selectedFileLabel = new JLabel("Selected file:");
        selectedFile = new JTextField(20);
        selectedFile.setEditable(false);
        selectedFile.setText("...");

        fileChooser = new JFileChooser();
        
        keyLabel = new JLabel("Keys");
        
        keyA = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyA.setEditable(true);
        keyA.setColumns(5);

        keyB = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyB.setEditable(true);
        keyB.setColumns(5);
      
        output = new JTextArea(10, 15);
        output.setLineWrap(true);
        outScrollPane = new JScrollPane(output);
        output.setEditable(false);

        outputLabel = new JLabel("Encrypted/Decrypted Message");

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener((event) -> {
            String contents = textPad.getText();
            int a = ((Number) keyA.getValue()).intValue();
            int b = ((Number) keyB.getValue()).intValue();
            String encrypted = Affine_java.encrypt(contents, a, b);
            output.setText(encrypted);
        });

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener((event) -> {
            String contents = textPad.getText();
            int a = ((Number) keyA.getValue()).intValue();
            int b = ((Number) keyB.getValue()).intValue();
            String decrypted = Affine_java.decrypt(Affine_java.encrypt(contents, a, b), a, b);
            output.setText(decrypted);
        });

        openFileButton = new JButton("Open file");
        openFileButton.addActionListener((event) -> {
            FileInputStream fileByteStream = null; 
            Scanner inFS = null;                  
            String readLine;                      
            File readFile = null;                  
            int fileChooserVal;        
            StringBuffer buffer = new StringBuffer(); 

            fileChooserVal = fileChooser.showOpenDialog(this);
            if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
                readFile = fileChooser.getSelectedFile();
                selectedFile.setText(readFile.getPath());
                if (readFile.canRead()) {
                    try {
                    fileByteStream = new FileInputStream(readFile);
                    inFS = new Scanner(fileByteStream);
                   
                    output.setText(""); 

                    while (inFS.hasNext()) {
                        readLine = inFS.nextLine();
                        buffer.append(readLine + System.lineSeparator());   
                    }

                    } catch (IOException e) {
                    output.append("\n\nError occurred while creating file stream! " + e.getMessage());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Can't read file!");
                }
            }
            
            textPad.setText(buffer.toString());
            
        });

        writeFileButton = new JButton("Write to file");
        writeFileButton.addActionListener((event) -> {
            File readFile = new File(selectedFile.getText());
            FileInputStream fileByteStream = null; 
            Scanner inFS = null;                  
            String readLine;                                            
            StringBuffer buffer = new StringBuffer();
            FileWriter writer; 

                try {
                    fileByteStream = new FileInputStream(readFile);
                    inFS = new Scanner(fileByteStream);

                    while (inFS.hasNext()) {
                        readLine = inFS.nextLine();
                        buffer.append(readLine + System.lineSeparator());  
                    }

                    writer = new FileWriter(readFile.getAbsolutePath());
                    String result = output.getText();
                    System.out.println(result);
                    String fileContents = buffer.toString();
                    fileContents = fileContents.replaceAll(fileContents, result);

                    writer.append(fileContents);
                    writer.flush();

                    } 
                catch (IOException e) {
                    output.append("\n\nError occurred while creating file stream! " + e.getMessage());
            }               
        });

        keyLabel.setBounds(130, 20, 150, 30);
        keyA.setBounds(90, 50, 50, 30);
        keyB.setBounds(150, 50, 50, 30);

        textOption.setBounds(100, 100, 150, 30);
        textPad.setBounds(75, 130, 150, 30);
        
        encryptButton.setBounds(30, 350, 100, 30);
        decryptButton.setBounds(160, 350, 100, 30);
        
        outputLabel.setBounds(320, 20, 200, 30);
        output.setBounds(320, 50, 300, 350);

        fileOption.setBounds(30, 170, 100, 30);
        selectedFile.setBounds(30, 200, 244, 30);
        openFileButton.setBounds(30, 240, 100, 30);
        writeFileButton.setBounds(160, 240, 100, 30);

        container.add(textPad);
        container.add(textOption);
        container.add(encryptButton);
        container.add(decryptButton);
        container.add(output);
        container.add(outputLabel);
        container.add(keyA);
        container.add(keyB);
        container.add(keyLabel);
        container.add(fileOption);
        container.add(selectedFile);
        container.add(openFileButton);
        container.add(writeFileButton);

        affineFrame.setSize(800, 600);
        affineFrame.setVisible(true);
    }

    // performs encryption
    @Override
    public void actionPerformed(ActionEvent event) {
       
    } 

    public static void main(String[] args) {
        Affine_frontend frontendFrame = new Affine_frontend();

    }
}

