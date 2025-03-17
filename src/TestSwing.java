package Swing;

import javax.swing.*;
import java.awt.FlowLayout;

public class TestSwing {
    public static void main(String[] args){
        JFrame f = new JFrame("User From");
        f.setVisible(true);
        f.setSize(480, 240);
        f.setLayout(new FlowLayout());

        //Label
        JLabel l1 = new JLabel("Username: ");
        f.add(l1);

        //TextBox
        JTextField t1 = new JTextField(20);
        f.add(t1);

        JLabel l2 = new JLabel("Password: ");
        f.add(l2);

        JTextField t2 = new JTextField(20);
        f.add(t2);

        //Button
        JButton b = new JButton("Submit");
        f.add(b);
    }
}
