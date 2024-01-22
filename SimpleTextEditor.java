import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class SimpleTextEditor extends JFrame {

    JButton save, open;
    JPanel panel;
    JTextArea area;

    public SimpleTextEditor() {
        setTitle("Simple Text Editor");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        save = new JButton("Save");
        panel.add(save);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        open = new JButton("Open");
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openData();
            }
        });

        panel.add(open);

        area = new JTextArea();
        area.setEditable(true);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    private void saveData() {

        if (area.getText().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Write Something!");
        } else {
            JFileChooser fc = new JFileChooser();
            int userFile = fc.showSaveDialog(null);

            if (userFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fc.getSelectedFile();

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
                    writer.write(area.getText());
                    area.setText("");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Successfully Saved!");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving file");
                }
            }
        }

        // try {
        // BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        // writer.write("\n" + area.getText());
        // writer.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // area.setText("");
        // JOptionPane.showMessageDialog(null, "Save");
    }

    private void openData() {
        JFileChooser fileChooser = new JFileChooser();
        int userFile = fileChooser.showOpenDialog(this);

        if (userFile == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileToOpen));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                reader.close();
                area.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error opening file");
            }
        }

        // try {
        // BufferedReader read = new BufferedReader(new FileReader("output.txt"));
        // System.out.println(read.readLine());
        // read.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
    }

    public static void main(String[] args) {
        new SimpleTextEditor().setVisible(true);
    }

}
