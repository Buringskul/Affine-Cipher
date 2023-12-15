import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.FlowLayout;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Absolute Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(null); // Use null layout

        JButton button = new JButton("Click me!");
        JLabel label = new JLabel("Hello, Swing!");

        // Set the bounds (x, y, width, height) for JButton and JLabel
        button.setBounds(100, 70, 100, 30);
        label.setBounds(110, 20, 150, 30);

        container.add(button);
        container.add(label);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}