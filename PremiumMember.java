// Class for premium gym member
public class PremiumMember extends GymMember {
    // Personal trainer
    private String personalTrainer;
    // Paid amount
    private double paidAmount;
    // Full payment status
    private boolean isFullPayment;
    // Discount amount
    private double discountAmount;
    // Premium charge
    private final double premiumCharge = 50000.0;

    // Constructor
    public PremiumMember(int id, String name, String location, String phone, String email, 
                        String gender, String dob, String startDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, dob, startDate);
        this.personalTrainer = personalTrainer;
        this.paidAmount = 0.0;
        this.isFullPayment = false;
        this.discountAmount = 0.0;
        
        //create a button, add action Listener to it, on click display I am Messi in JOptionPane DialogBox
     
    }
    // Returns true for PremiumMember
    public boolean isPremiumMember() {
        return true;
    }

    // Marks attendance
    public void markAttendance(boolean isPremiumMode) throws IllegalStateException {
        if (!isPremiumMode) {
            throw new IllegalStateException("Member is not a regular member.");
        }
        if (getActiveStatus()) {
            attendance++;
            loyaltyPoints += 10.0;
        }
    }

    // Gets trainer
    public String getPersonalTrainer() {
        return personalTrainer;
    }

    // Gets premium charge
    public double getPremiumCharge() {
        return premiumCharge;
    }

    // Gets paid amount
    public double getPaidAmount() {
        return paidAmount;
    }

    // Gets full payment status
    public boolean getFullPayment() {
        return isFullPayment;
    }

    // Gets discount amount
    public double getDiscountAmount() {
        return discountAmount;
    }

    // Processes payment
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment already completed.";
        }
        double remaining = premiumCharge - paidAmount;
        if (amount > remaining) {
            return "Payment exceeds remaining amount. Please pay " + remaining + " or less.";
        }
        paidAmount += amount;
        if (paidAmount >= premiumCharge) {
            isFullPayment = true;
            calculateDiscount();
        }
        return isFullPayment ? "Payment successful. Full amount paid." : "Payment successful. Remaining: " + (premiumCharge - paidAmount);
    }

    // Calculates discount
    public String calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.1;
            return "Discount calculated: " + discountAmount;
        }
        discountAmount = 0.0;
        return "No discount applied. Full payment required.";
    }

    // Reverts membership
    public void revertPremiumMember() {
        resetMember();
        personalTrainer = "";
        paidAmount = 0.0;
        isFullPayment = false;
        discountAmount = 0.0;
    }

    // Displays details
    public String display() {
        String result = "ID: " + getId() + "\n" +
                        "Name: " + getName() + "\n" +
                        "Location: " + getLocation() + "\n" +
                        "Phone: " + getPhone() + "\n" +
                        "Email: " + getEmail() + "\n" +
                        "Gender: " + getGender() + "\n" +
                        "DOB: " + getDob() + "\n" +
                        "Start Date: " + getMembershipStartDate() + "\n" +
                        "Active: " + getActiveStatus() + "\n" +
                        "Attendance: " + getAttendance() + "\n" +
                        "Loyalty Points: " + getLoyaltyPoints() + "\n" +
                        "Personal Trainer: " + getPersonalTrainer() + "\n" +
                        "Premium Charge: " + getPremiumCharge() + "\n" +
                        "Paid Amount: " + getPaidAmount() + "\n" +
                        "Full Payment: " + getFullPayment() + "\n";
        if (getFullPayment()) {
            result += "Discount Amount: " + getDiscountAmount() + "\n";
        }
        return result;
    }
}