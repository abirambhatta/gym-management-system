import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

// GUI class to manage gym members interactively
public class GymGUI extends JFrame {
    // List of all gym members
    private ArrayList<GymMember> members = new ArrayList<>();
    // Input fields for member details
    private JTextField idField, nameField, locationField, phoneField, emailField,
            referralSourceField, paidAmountField, removalReasonField, trainerField;
    // Display fields for prices and points
    private JTextField regularPriceField, premiumChargeField, discountField, loyaltyPointsField;
    // Field for entering ID in action section
    private JTextField actionIdField;
    // Gender selection radio buttons
    private JRadioButton maleRadio, femaleRadio;
    // Combo boxes for date and plan selection
    private JComboBox<String> planCombo, dayCombo, monthCombo, yearCombo,
            startDayCombo, startMonthCombo, startYearCombo;
    // Buttons for various actions
    private JButton regularModeButton, premiumModeButton, addMemberButton, activateButton, deactivateButton,
            markAttendanceButton, upgradePlanButton, calcDiscountButton, revertRegularButton, revertPremiumButton,
            payDueButton, displayButton, clearButton, saveButton, readButton;
    // Labels for input fields
    private JLabel referralSourceLabel, planLabel, regularPriceLabel, trainerLabel, paidAmountLabel,
            premiumChargeLabel, discountLabel, loyaltyPointsLabel;
    // Tracks current mode (regular or premium)
    private boolean isPremiumMode = false;
    // Temporary object for plan pricing
    private RegularMember tempRegularMember;
    // Panels for layout
    private JPanel mainPanel, formPanel, actionPanel;

    // Constructor to set up the GUI
    public GymGUI() {
        // Set window properties
        setTitle("Gym Management");
        setSize(800, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize main panel with GridBagLayout
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(5, 5, 5, 5);
        mainGbc.fill = GridBagConstraints.BOTH;
        mainGbc.weighty = 1.0;

        // Initialize form panel for left side
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize temporary regular member for plan pricing
        tempRegularMember = new RegularMember(0, "", "", "", "", "", "", "", "");

        // Row 0: Mode selection buttons
        regularModeButton = new JButton("Regular Member");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(regularModeButton, gbc);

        premiumModeButton = new JButton("Premium Member");
        gbc.gridx = 1;
        formPanel.add(premiumModeButton, gbc);

        // Row 1: ID input
        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(idLabel, gbc);

        idField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        // Row 2: Name input
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Row 3: Location input
        JLabel locationLabel = new JLabel("Location:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(locationLabel, gbc);

        locationField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(locationField, gbc);

        // Row 4: Phone input
        JLabel phoneLabel = new JLabel("Phone:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(phoneLabel, gbc);

        phoneField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Row 5: Email input
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Row 6: Gender selection
        JLabel genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(genderLabel, gbc);

        maleRadio = new JRadioButton("Male");
        gbc.gridx = 1;
        formPanel.add(maleRadio, gbc);

        femaleRadio = new JRadioButton("Female");
        gbc.gridx = 2;
        formPanel.add(femaleRadio, gbc);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Row 7: Date of birth input
        JLabel dobLabel = new JLabel("DOB:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(dobLabel, gbc);

        dayCombo = new JComboBox<String>();
        for (int i = 1; i <= 31; i++) dayCombo.addItem(String.valueOf(i));
        gbc.gridx = 1;
        formPanel.add(dayCombo, gbc);

        monthCombo = new JComboBox<String>();
        for (int i = 1; i <= 12; i++) monthCombo.addItem(String.valueOf(i));
        gbc.gridx = 2;
        formPanel.add(monthCombo, gbc);

        yearCombo = new JComboBox<String>();
        for (int i = 1980; i <= 2025; i++) yearCombo.addItem(String.valueOf(i));
        gbc.gridx = 3;
        formPanel.add(yearCombo, gbc);

        // Row 8: Membership start date input
        JLabel startDateLabel = new JLabel("Start Date:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(startDateLabel, gbc);

        startDayCombo = new JComboBox<String>();
        for (int i = 1; i <= 31; i++) startDayCombo.addItem(String.valueOf(i));
        gbc.gridx = 1;
        formPanel.add(startDayCombo, gbc);

        startMonthCombo = new JComboBox<String>();
        for (int i = 1; i <= 12; i++) startMonthCombo.addItem(String.valueOf(i));
        gbc.gridx = 2;
        formPanel.add(startMonthCombo, gbc);

        startYearCombo = new JComboBox<String>();
        for (int i = 1980; i <= 2025; i++) startYearCombo.addItem(String.valueOf(i));
        gbc.gridx = 3;
        formPanel.add(startYearCombo, gbc);

        // Row 9: Referral source input (regular mode)
        referralSourceLabel = new JLabel("Referral Source:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(referralSourceLabel, gbc);
        referralSourceField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(referralSourceField, gbc);

        // Row 10: Plan selection (regular mode)
        planLabel = new JLabel("Plan:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(planLabel, gbc);
        planCombo = new JComboBox<String>(new String[]{"basic", "standard", "deluxe"});
        gbc.gridx = 1;
        formPanel.add(planCombo, gbc);

        // Row 11: Regular price display (regular mode)
        regularPriceLabel = new JLabel("Regular Price:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        formPanel.add(regularPriceLabel, gbc);
        regularPriceField = new JTextField("6500.0");
        regularPriceField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(regularPriceField, gbc);

        // Row 12: Trainer input (premium mode)
        trainerLabel = new JLabel("Trainer:");
        gbc.gridx = 0;
        gbc.gridy = 12;
        formPanel.add(trainerLabel, gbc);
        trainerField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(trainerField, gbc);

        // Row 13: Paid amount input (premium mode)
        paidAmountLabel = new JLabel("Paid Amount:");
        gbc.gridx = 0;
        gbc.gridy = 13;
        formPanel.add(paidAmountLabel, gbc);
        paidAmountField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(paidAmountField, gbc);

        // Row 14: Premium charge display (premium mode)
        premiumChargeLabel = new JLabel("Premium Charge:");
        gbc.gridx = 0;
        gbc.gridy = 14;
        formPanel.add(premiumChargeLabel, gbc);
        premiumChargeField = new JTextField("50000.0");
        premiumChargeField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(premiumChargeField, gbc);

        // Row 15: Discount display (premium mode)
        discountLabel = new JLabel("Discount:");
        gbc.gridx = 0;
        gbc.gridy = 15;
        formPanel.add(discountLabel, gbc);
        discountField = new JTextField("0.0");
        discountField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(discountField, gbc);

        // Row 16: Removal reason input
        JLabel removalReasonLabel = new JLabel("Removal Reason:");
        gbc.gridx = 0;
        gbc.gridy = 16;
        formPanel.add(removalReasonLabel, gbc);
        removalReasonField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(removalReasonField, gbc);

        // Row 17: Loyalty points display
        loyaltyPointsLabel = new JLabel("Loyalty Points:");
        gbc.gridx = 0;
        gbc.gridy = 17;
        formPanel.add(loyaltyPointsLabel, gbc);
        loyaltyPointsField = new JTextField("0.0");
        loyaltyPointsField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(loyaltyPointsField, gbc);

        // Row 18: Add member button
        addMemberButton = new JButton("Add Regular");
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        formPanel.add(addMemberButton, gbc);

        // Initialize action panel for right side
        actionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints actionGbc = new GridBagConstraints();
        actionGbc.insets = new Insets(5, 5, 5, 5);
        actionGbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Action section label
        JLabel actionLabel = new JLabel("Action");
        actionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        actionGbc.gridx = 0;
        actionGbc.gridy = 0;
        actionGbc.gridwidth = 2;
        actionPanel.add(actionLabel, actionGbc);

        // Row 1: Action ID input
        JLabel actionIdLabel = new JLabel("Enter ID:");
        actionGbc.gridx = 0;
        actionGbc.gridy = 1;
        actionGbc.gridwidth = 1;
        actionPanel.add(actionIdLabel, actionGbc);

        actionIdField = new JTextField(15);
        actionGbc.gridx = 1;
        actionPanel.add(actionIdField, actionGbc);

        // Row 2: Activate and deactivate buttons
        activateButton = new JButton("Activate");
        actionGbc.gridx = 0;
        actionGbc.gridy = 2;
        actionPanel.add(activateButton, actionGbc);

        deactivateButton = new JButton("Deactivate");
        actionGbc.gridx = 1;
        actionPanel.add(deactivateButton, actionGbc);

        // Row 3: Mark attendance and upgrade plan buttons
        markAttendanceButton = new JButton("Mark Attendance");
        actionGbc.gridx = 0;
        actionGbc.gridy = 3;
        actionPanel.add(markAttendanceButton, actionGbc);

        upgradePlanButton = new JButton("Upgrade Plan");
        actionGbc.gridx = 1;
        actionPanel.add(upgradePlanButton, actionGbc);

        // Row 4: Calculate discount and revert regular buttons
        calcDiscountButton = new JButton("Calc Discount");
        actionGbc.gridx = 0;
        actionGbc.gridy = 4;
        actionPanel.add(calcDiscountButton, actionGbc);

        revertRegularButton = new JButton("Revert Regular");
        actionGbc.gridx = 1;
        actionPanel.add(revertRegularButton, actionGbc);

        // Row 5: Revert premium and pay due buttons
        revertPremiumButton = new JButton("Revert Premium");
        actionGbc.gridx = 0;
        actionGbc.gridy = 5;
        actionPanel.add(revertPremiumButton, actionGbc);

        payDueButton = new JButton("Pay Due");
        actionGbc.gridx = 1;
        actionPanel.add(payDueButton, actionGbc);

        // Row 6: Display and clear buttons
        displayButton = new JButton("Display");
        actionGbc.gridx = 0;
        actionGbc.gridy = 6;
        actionPanel.add(displayButton, actionGbc);

        clearButton = new JButton("Clear");
        actionGbc.gridx = 1;
        actionPanel.add(clearButton, actionGbc);

        // Row 7: Save and read file buttons
        saveButton = new JButton("Save to File");
        actionGbc.gridx = 0;
        actionGbc.gridy = 7;
        actionPanel.add(saveButton, actionGbc);

        readButton = new JButton("Read from File");
        actionGbc.gridx = 1;
        actionPanel.add(readButton, actionGbc);

        // Add panels to main panel
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 0.5;
        mainPanel.add(new JScrollPane(formPanel), mainGbc);

        mainGbc.gridx = 1;
        mainGbc.weightx = 0.5;
        mainPanel.add(new JScrollPane(actionPanel), mainGbc);

        add(mainPanel);

        // Add action listeners
        // Updates price display when plan is selected
        planCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPlan = (String) planCombo.getSelectedItem();
                double price = tempRegularMember.getPlanPrice(selectedPlan);
                regularPriceField.setText(String.valueOf(price));
            }
        });

        // Switches to regular mode
        regularModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPremiumMode = false;
                updateUIMode();
            }
        });

        // Switches to premium mode
        premiumModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPremiumMode = true;
                updateUIMode();
            }
        });

        // Adds a new member based on mode
        addMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPremiumMode) addPremium();
                else addRegular();
            }
        });

        // Activates membership
        activateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activate();
            }
        });

        // Deactivates membership
        deactivateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deactivate();
            }
        });

        // Marks attendance
        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        // Upgrades regular member plan
        upgradePlanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradePlan();
            }
        });

        // Calculates discount for premium member
        calcDiscountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcDiscount();
            }
        });

        // Reverts regular member
        revertRegularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revertRegular();
            }
        });

        // Reverts premium member
        revertPremiumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revertPremium();
            }
        });

        // Processes payment for premium member
        payDueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payDue();
            }
        });

        // Displays member details
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display();
            }
        });

        // Clears all input fields
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        // Saves members to file
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        // Reads members from file
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readFromFile();
            }
        });

        // Set initial UI mode
        updateUIMode();
        setVisible(true);
    }

    // Updates UI visibility based on mode
    private void updateUIMode() {
        referralSourceLabel.setVisible(!isPremiumMode);
        referralSourceField.setVisible(!isPremiumMode);
        planLabel.setVisible(!isPremiumMode);
        planCombo.setVisible(!isPremiumMode);
        regularPriceLabel.setVisible(!isPremiumMode);
        regularPriceField.setVisible(!isPremiumMode);
        upgradePlanButton.setVisible(!isPremiumMode);
        revertRegularButton.setVisible(!isPremiumMode);

        trainerLabel.setVisible(isPremiumMode);
        trainerField.setVisible(isPremiumMode);
        paidAmountLabel.setVisible(isPremiumMode);
        paidAmountField.setVisible(isPremiumMode);
        premiumChargeLabel.setVisible(isPremiumMode);
        premiumChargeField.setVisible(isPremiumMode);
        discountLabel.setVisible(isPremiumMode);
        discountField.setVisible(isPremiumMode);
        calcDiscountButton.setVisible(isPremiumMode);
        payDueButton.setVisible(isPremiumMode);
        revertPremiumButton.setVisible(isPremiumMode);

        addMemberButton.setText(isPremiumMode ? "Add Premium" : "Add Regular");
    }

    // Adds a regular member
    private void addRegular() {
        try {
            // Validate input fields
            if (idField.getText().isEmpty() || nameField.getText().isEmpty() || locationField.getText().isEmpty() ||
                    phoneField.getText().isEmpty() || emailField.getText().isEmpty() || referralSourceField.getText().isEmpty() ||
                    (!maleRadio.isSelected() && !femaleRadio.isSelected())) {
                JOptionPane.showMessageDialog(this, "Field is empty.");
                return;
            }
            int id = Integer.parseInt(idField.getText());
            if (isIdTaken(id)) {
                JOptionPane.showMessageDialog(this, "Duplicate member found.");
                return;
            }
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String dob = dayCombo.getSelectedItem() + "/" + monthCombo.getSelectedItem() + "/" + yearCombo.getSelectedItem();
            String startDate = startDayCombo.getSelectedItem() + "/" + startMonthCombo.getSelectedItem() + "/" + startYearCombo.getSelectedItem();
            String referralSource = referralSourceField.getText();
            String plan = (String) planCombo.getSelectedItem();

            // Create and add regular member
            RegularMember member = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referralSource);
            member.elevatePlan(plan);
            members.add(member);
            JOptionPane.showMessageDialog(this, "Regular member added successfully.");
            clear();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Adds a premium member
    private void addPremium() {
        try {
            // Validate input fields
            if (idField.getText().isEmpty() || nameField.getText().isEmpty() || locationField.getText().isEmpty() ||
                    phoneField.getText().isEmpty() || emailField.getText().isEmpty() || trainerField.getText().isEmpty() ||
                    (!maleRadio.isSelected() && !femaleRadio.isSelected())) {
                JOptionPane.showMessageDialog(this, "Field is empty.");
                return;
            }
            int id = Integer.parseInt(idField.getText());
            if (isIdTaken(id)) {
                JOptionPane.showMessageDialog(this, "Duplicate member found.");
                return;
            }
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String dob = dayCombo.getSelectedItem() + "/" + monthCombo.getSelectedItem() + "/" + yearCombo.getSelectedItem();
            String startDate = startDayCombo.getSelectedItem() + "/" + startMonthCombo.getSelectedItem() + "/" + startYearCombo.getSelectedItem();
            String trainer = trainerField.getText();

            // Create and add premium member
            PremiumMember member = new PremiumMember(id, name, location, phone, email, gender, dob, startDate, trainer);
            members.add(member);
            JOptionPane.showMessageDialog(this, "Premium member added successfully.");
            clear();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Activates a member's membership
    private void activate() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            // Check mode compatibility
            if (isPremiumMode && !member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a premium member.");
                return;
            }
            if (!isPremiumMode && member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a regular member.");
                return;
            }
            if (member.getActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Membership already activated.");
            } else {
                member.activateMembership(isPremiumMode);
                JOptionPane.showMessageDialog(this, "Membership activated successfully.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // Deactivates a member's membership
    private void deactivate() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            // Check mode compatibility
            if (isPremiumMode && !member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a premium member.");
                return;
            }
            if (!isPremiumMode && member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a regular member.");
                return;
            }
            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Membership already deactivated.");
            } else {
                member.deactivateMembership(isPremiumMode);
                JOptionPane.showMessageDialog(this, "Membership deactivated successfully.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // Marks attendance for a member
    private void markAttendance() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            // Check mode compatibility
            if (isPremiumMode && !member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a premium member.");
                return;
            }
            if (!isPremiumMode && member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a regular member.");
                return;
            }
            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Member is not active.");
                return;
            }
            member.markAttendance(isPremiumMode);
            loyaltyPointsField.setText(String.valueOf(member.getLoyaltyPoints()));
            JOptionPane.showMessageDialog(this, "Attendance marked successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // Upgrades a regular member's plan
    private void upgradePlan() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            if (!(member instanceof RegularMember)) {
                JOptionPane.showMessageDialog(this, "Not a regular member.");
                return;
            }
            String plan = (String) planCombo.getSelectedItem();
            String result = ((RegularMember) member).elevatePlan(plan);
            regularPriceField.setText(String.valueOf(((RegularMember) member).getPrice()));
            JOptionPane.showMessageDialog(this, result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Calculates discount for a premium member
    private void calcDiscount() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(this, "Not a premium member.");
                return;
            }
            String result = ((PremiumMember) member).calculateDiscount();
            discountField.setText(String.valueOf(((PremiumMember) member).getDiscountAmount()));
            JOptionPane.showMessageDialog(this, result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Reverts a regular member
    private void revertRegular() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            if (!(member instanceof RegularMember)) {
                JOptionPane.showMessageDialog(this, "Not a regular member.");
                return;
            }
            ((RegularMember) member).revertRegularMember(removalReasonField.getText());
            regularPriceField.setText("6500.0");
            loyaltyPointsField.setText("0.0");
            JOptionPane.showMessageDialog(this, "Regular member reverted successfully.");
            clear();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Reverts a premium member
    private void revertPremium() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(this, "Not a premium member.");
                return;
            }
            ((PremiumMember) member).revertPremiumMember();
            discountField.setText("0.0");
            loyaltyPointsField.setText("0.0");
            JOptionPane.showMessageDialog(this, "Premium member reverted successfully.");
            clear();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Processes payment for a premium member
    private void payDue() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(this, "Not a premium member.");
                return;
            }
            double amount = Double.parseDouble(paidAmountField.getText());
            String result = ((PremiumMember) member).payDueAmount(amount);
            discountField.setText(String.valueOf(((PremiumMember) member).getDiscountAmount()));
            JOptionPane.showMessageDialog(this, result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID or amount.");
        }
    }

    // Displays member details
    private void display() {
        try {
            int id = Integer.parseInt(actionIdField.getText());
            GymMember member = findMember(id);
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found.");
                return;
            }
            // Check mode compatibility
            if (isPremiumMode && !member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a premium member.");
                return;
            }
            if (!isPremiumMode && member.isPremiumMember()) {
                JOptionPane.showMessageDialog(this, "Member is not a regular member.");
                return;
            }
            // Create display window
            JFrame displayFrame = new JFrame("Member Details - ID: " + id);
            displayFrame.setSize(400, 200);
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.append(member.display());
            displayFrame.add(new JScrollPane(area));
            displayFrame.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    // Clears all input fields
    private void clear() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralSourceField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        actionIdField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        startDayCombo.setSelectedIndex(0);
        startMonthCombo.setSelectedIndex(0);
        startYearCombo.setSelectedIndex(0);
        planCombo.setSelectedIndex(0);
        regularPriceField.setText("6500.0");
        discountField.setText("0.0");
        loyaltyPointsField.setText("0.0");
    }

    // Saves member details to file
    private void saveToFile() {
        try {
            FileWriter writer = new FileWriter("MemberDetails.txt");
            // Write header
            writer.write(String.format("%-6s%-16s%-16s%-16s%-26s%-21s%-11s%-11s%-11s%-16s%-11s%-16s%-16s%-16s\n",
                    "ID", "Name", "Location", "Phone", "Email", "Start Date", "Plan", "Price", "Attendance", "Loyalty Points",
                    "Active", "Full Payment", "Discount", "Paid Amount"));
            // Write member details
            for (GymMember m : members) {
                if (m instanceof RegularMember) {
                    RegularMember rm = (RegularMember) m;
                    writer.write(String.format("%-6d%-16s%-16s%-16s%-26s%-21s%-11s%-11.2f%-11d%-16.2f%-11b%-16s%-16.1f%-16.1f\n",
                            rm.getId(), rm.getName(), rm.getLocation(), rm.getPhone(), rm.getEmail(), rm.getMembershipStartDate(),
                            rm.getPlan(), rm.getPrice(), rm.getAttendance(), rm.getLoyaltyPoints(), rm.getActiveStatus(),
                            "N/A", 0.0, 0.0));
                } else if (m instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) m;
                    writer.write(String.format("%-6d%-16s%-16s%-16s%-26s%-21s%-11s%-11s%-11d%-16.2f%-11b%-16b%-16.1f%-16.1f\n",
                            pm.getId(), pm.getName(), pm.getLocation(), pm.getPhone(), pm.getEmail(), pm.getMembershipStartDate(),
                            "N/A", "N/A", pm.getAttendance(), pm.getLoyaltyPoints(), pm.getActiveStatus(),
                            pm.getFullPayment(), pm.getDiscountAmount(), pm.getPaidAmount()));
                }
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Member details saved to MemberDetails.txt successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred");
        }
    }

    // Reads and displays member details from file
    private void readFromFile() {
        JFrame readFrame = new JFrame("Member Details from File");
        readFrame.setSize(800, 400);
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set monospaced font for proper alignment
        try {
            FileReader reader = new FileReader("MemberDetails.txt");
            int charRead;
            String content = "";
            while ((charRead = reader.read()) != -1) {
                content = content + (char) charRead;
            }
            area.append(content);
            reader.close();
        } catch (IOException ex) {
            area.setText("Error occurred");
        }
        readFrame.add(new JScrollPane(area));
        readFrame.setVisible(true);
    }

    // Checks if ID is already taken
    private boolean isIdTaken(int id) {
        for (GymMember m : members) {
            if (m.getId() == id) return true;
        }
        return false;
    }

    // Finds member by ID
    private GymMember findMember(int id) {
        for (GymMember m : members) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    // Launches the application
    public static void main(String[] args) {
        new GymGUI();
    }
}