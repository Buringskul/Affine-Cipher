import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Scanner;
import javax.swing.*;

// Class definition for the Affine frontend interface, extending JFrame and implementing ActionListener
public class Affine_frontend extends JFrame implements ActionListener{
    // Declaration of GUI components
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

    // Handle file reading
    private void readFile(File file) {
        try (FileInputStream fileByteStream = new FileInputStream(file);
             Scanner inFS = new Scanner(fileByteStream)) {

            StringBuilder buffer = new StringBuilder();

            // Reading file content line by line
            while (inFS.hasNext()) {
                String readLine = inFS.nextLine();
                buffer.append(readLine).append(System.lineSeparator());
            }

            // Setting read content to the text area and displaying success message
            textPad.setText(buffer.toString());
            output.setText("File loaded successfully.");
        } catch (IOException e) {
            // Displaying error message in case of file reading error
            output.setText("Error occurred while reading file: " + e.getMessage());
        }
    }

    // Handle file writing
    private void writeFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            String result = output.getText();
            // Writing content to the file and displaying success message
            writer.write(result);
            output.setText("File written successfully.");
        } catch (IOException e) {
            // Displaying error message in case of file writing error
            output.setText("Error occurred while writing file: " + e.getMessage());
        }
    }

    // Constructor for the Affine frontend class
    Affine_frontend() {
        // Initializing and setting up the main frame for the Affine Cipher application
        affineFrame = new JFrame("Affine Cipher");

        // Getting the content pane of the frame to manage components
        Container container = affineFrame.getContentPane();
        container.setLayout(null); // Using a null layout for manual component placement

        // Initializing scroll panes for input and output text areas (inScrollPane and outScrollPane)
        JScrollPane inScrollPane = null;
        JScrollPane outScrollPane = null;

        // Creating a label for entering text
        textOption = new JLabel("Enter text here:");

        // Creating a text area for user input (textPad)
        textPad = new JTextArea(10, HEIGHT); // Setting initial rows and columns
        textPad.setEditable(true); // Allowing user input
        textPad.setLineWrap(true); // Enabling line wrapping
        inScrollPane = new JScrollPane(textPad); // Placing the text area in a scroll pane for overflow handling
        textPad.setText(""); // Setting initial text to an empty string

        // Creating a label for file selection
        fileOption = new JLabel("Select .txt file");

        // Creating a label to display the selected file path
        selectedFileLabel = new JLabel("Selected file:");
        selectedFile = new JTextField(20); // Text field to display the selected file path
        selectedFile.setEditable(false); // Disabling editing
        selectedFile.setText("..."); // Setting initial text to ellipsis

        fileChooser = new JFileChooser(); // Initializing the file chooser for file selection

        keyLabel = new JLabel("Keys"); // Creating a label for 'a' and 'b' keys

        // Creating formatted text fields for 'a' and 'b' keys input
        keyA = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyA.setEditable(true); // Allowing user input
        keyA.setColumns(5); // Setting column width

        keyB = new JFormattedTextField(NumberFormat.getNumberInstance());
        keyB.setEditable(true); // Allowing user input
        keyB.setColumns(5); // Setting column width

        // Creating a text area for displaying output (encrypted/decrypted text)
        output = new JTextArea(10, 15); // Setting initial rows and columns
        output.setLineWrap(true); // Enabling line wrapping
        outScrollPane = new JScrollPane(output); // Placing the text area in a scroll pane for overflow handling
        output.setEditable(false); // Making output area read-only

       // Creating a label for displaying the result of encryption or decryption
        outputLabel = new JLabel("Encrypted/Decrypted Message");

        // Creating a button to trigger encryption process
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener((event) -> {
            // Getting text from the input area
            String contents = textPad.getText();
            // Retrieving 'a' and 'b' keys as BigIntegers from formatted text fields
            BigInteger a = BigInteger.valueOf(((Number) keyA.getValue()).intValue());
            BigInteger b = BigInteger.valueOf(((Number) keyB.getValue()).intValue());
            // Encrypting the input text using the Affine cipher algorithm
            String encrypted = Affine_java.encrypt(contents, a, b);
            // Displaying the encrypted text in the output area
            output.setText(encrypted);
        });

        // Creating a button to trigger decryption process
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener((event) -> {
            // Getting encrypted text from the output area
            String contents = output.getText();
            // Retrieving 'a' and 'b' keys as BigIntegers from formatted text fields
            BigInteger a = BigInteger.valueOf(((Number) keyA.getValue()).intValue());
            BigInteger b = BigInteger.valueOf(((Number) keyB.getValue()).intValue());
            // Decrypting the input text using the Affine cipher algorithm
            String decrypted = Affine_java.decrypt(contents, a, b);
            // Displaying the decrypted text in the output area
            output.setText(decrypted);
        });

        // Creating a button to open a file
        openFileButton = new JButton("Open file");
        openFileButton.addActionListener((event) -> {
            // Displaying a file chooser dialog to select a file to open
            int fileChooserVal = fileChooser.showOpenDialog(this);
            if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
                // Retrieving the selected file
                File readFile = fileChooser.getSelectedFile();
                // Displaying the path of the selected file
                selectedFile.setText(readFile.getPath());
                if (readFile.canRead()) {
                    // Reading the selected file and displaying its content in the text area
                    readFile(readFile);
                } else {
                    // Showing an error message if the file cannot be read
                    JOptionPane.showMessageDialog(this, "Can't read file!");
                }
            }
        });

        // Creating a button to write content to a file
        writeFileButton = new JButton("Write to file");
        writeFileButton.addActionListener((event) -> {
            // Displaying a file chooser dialog to select a file to write to
            int fileChooserVal = fileChooser.showSaveDialog(this);
            if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
                // Retrieving the selected file
                File writeFile = fileChooser.getSelectedFile();
                // Displaying the path of the selected file
                selectedFile.setText(writeFile.getPath());
                if (writeFile.exists()) {
                    // Asking for confirmation if the file already exists
                    int result = JOptionPane.showConfirmDialog(this, "File already exists. Overwrite?", "Warning",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // Writing the content from the output area to the selected file
                        writeFile(writeFile);
                    }
                } else {
                    // Writing the content from the output area to the selected file directly
                    writeFile(writeFile);
                }
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

        affineFrame.setSize(650, 450);
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

