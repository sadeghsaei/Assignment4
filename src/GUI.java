//public class Main {
//    public static void main(String[] args) {
//        GUI frm = new GUI();
//        frm.setTitle("Subscribers Registration");
//        frm.setDefaultCloseOperation(3);
//        frm.setSize( new Dimension(600, 300));
//        frm.setVisible(true);
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class GUI extends JFrame {

    private JLabel labelTitle, labelEmail, labelMobile, labelCountry, labelContact, labelTerms, labelError;
    private JTextField fieldEmail, fieldMobile;
    private JComboBox comboCountry;
    private JRadioButton radioText, radioEmail, radioDont;
    private JCheckBox checkTerms;
    private JButton buttonRegister, buttonReset;
    ButtonGroup btgroupContact;
    String email, mobile, country, contact;

    public GUI() {
        createView();
        setTitle("Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 250));
        setVisible(true);
    }

    private void createView() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());

        labelTitle = new JLabel("Register for an Account", SwingConstants.LEFT);
        labelTitle.setPreferredSize(new Dimension(200, 30));
        labelTitle.setHorizontalAlignment(10);
        labelTitle.setForeground(Color.blue);
        panelMain.add(labelTitle, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.ipadx = 10;
        // c.gridheight = 2;

        labelEmail = new JLabel("E-Mail:");
        panel.add(labelEmail, c);
        c.gridy++;

        labelMobile = new JLabel("Mobile Phone:");
        panel.add(labelMobile, c);
        c.gridy++;

        labelCountry = new JLabel("Country");
        panel.add(labelCountry, c);
        c.gridy++;

        labelContact = new JLabel("Contact me by:");
        panel.add(labelContact, c);
        c.gridy++;

        labelTerms = new JLabel("Terms of Service:");
        panel.add(labelTerms, c);
        c.gridy++;

        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;

        fieldEmail = new JTextField(20);
        panel.add(fieldEmail, c);
        c.gridy++;

        fieldMobile = new JTextField(20);
        panel.add(fieldMobile, c);
        c.gridy++;

        String countries[] = {"Select an option", "Canada", "USA", "UK", "UAE", "India", "Japan", "China"};
        comboCountry = new JComboBox(countries);
        panel.add(comboCountry, c);
        c.gridy++;

        radioText = new JRadioButton("Text");
        radioEmail = new JRadioButton("Email");
        radioDont = new JRadioButton("Don't contact me");
        btgroupContact = new ButtonGroup();
        btgroupContact.add(radioText);
        btgroupContact.add(radioEmail);
        btgroupContact.add(radioDont);
        panel.add(radioText, c);
        c.gridx++;
        panel.add(radioEmail, c);
        c.gridx++;
        panel.add(radioDont, c);

        c.gridy++;
        c.gridx = 1;
        checkTerms = new JCheckBox("I accept");
        panel.add(checkTerms, c);


        panelMain.add(panel, BorderLayout.CENTER);
        buttonRegister = new JButton("Register");
        buttonReset = new JButton("Reset");
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButton.add(buttonRegister);
        panelButton.add(buttonReset);
        labelError = new JLabel();
        panelButton.add(labelError);

        panelMain.add(panelButton, BorderLayout.SOUTH);


        buttonRegister.addActionListener(e -> {
            fieldEmail.setBackground(Color.white);
            fieldMobile.setBackground(Color.white);
            comboCountry.setBackground(Color.getColor("eeeeee"));
            checkTerms.setBackground(Color.getColor("eeeeee"));
            radioText.setBackground(Color.getColor("eeeeee"));
            radioEmail.setBackground(Color.getColor("eeeeee"));
            radioDont.setBackground(Color.getColor("eeeeee"));
            labelError.setText("");

            int sentinel = 0;
            if (fieldEmail.getText().equals("")) {
                fieldEmail.setBackground(Color.orange);
                sentinel++;
            }
            if (fieldMobile.getText().equals("")) {
                fieldMobile.setBackground(Color.orange);
                sentinel++;
            }
            if (comboCountry.getSelectedItem().equals("Select an option")) {
                comboCountry.setBackground(Color.orange);
                sentinel++;
            }
            if (!checkTerms.isSelected()) {
                checkTerms.setBackground(Color.orange);
                sentinel++;
            }
            if (!radioDont.isSelected() && !radioEmail.isSelected() && !radioText.isSelected()) {
                radioText.setBackground(Color.orange);
                radioEmail.setBackground(Color.orange);
                radioDont.setBackground(Color.orange);
                sentinel++;
            }
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!fieldEmail.getText().matches(emailPattern)) {
                fieldEmail.setBackground(Color.orange);
                JOptionPane.showMessageDialog(this, "Email validation failed", "Alert", JOptionPane.WARNING_MESSAGE);
            }


            if (sentinel == 1) {
                labelError.setText("This field is required. (" + sentinel + " error)");
                labelError.setForeground(Color.red);
            } else if (sentinel > 1) {
                labelError.setText("These fields are required. (" + sentinel + " errors)");
                labelError.setForeground(Color.red);
            } else {
                email = fieldEmail.getText();
                mobile = fieldMobile.getText();
                country = comboCountry.getSelectedItem().toString();
                for (Enumeration<AbstractButton> buttons = btgroupContact.getElements(); buttons.hasMoreElements(); ) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected())
                        contact = button.getText();
                }
                JOptionPane.showMessageDialog(this, "Email: " + email + "    Mobile: " + mobile
                        + "    Country: " + country + "    Contact type: " + contact);
            }


        });


        buttonReset.addActionListener(e -> {
            fieldEmail.setText("");
            fieldMobile.setText("");
            comboCountry.setSelectedItem("Select an option");
            checkTerms.setSelected(false);
            fieldEmail.setBackground(Color.white);
            fieldMobile.setBackground(Color.white);
            comboCountry.setBackground(Color.getColor("eeeeee"));
            checkTerms.setBackground(Color.getColor("eeeeee"));
            radioText.setBackground(Color.getColor("eeeeee"));
            radioEmail.setBackground(Color.getColor("eeeeee"));
            radioDont.setBackground(Color.getColor("eeeeee"));
            labelError.setText("");
        });

        add(panelMain);


    }


}