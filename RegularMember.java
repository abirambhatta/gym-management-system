// Class for regular gym member
public class RegularMember extends GymMember {
    // Attendance limit for plan elevation
    private final int attendanceLimit = 30;
    // Referral source
    private String referralSource;
    // Reversion reason
    private String removalReason;
    // Plan elevation eligibility
    private boolean isEligibleForUpgrade;
    // Current plan
    private String plan;
    // Plan price
    private double price;

    // Constructor
    public RegularMember(int id, String name, String location, String phone, String email, 
                        String gender, String dob, String startDate, String referralSource) {
        super(id, name, location, phone, email, gender, dob, startDate);
        this.referralSource = referralSource;
        this.removalReason = "";
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500.0;
    }

    // Returns false for RegularMember
    public boolean isPremiumMember() {
        return false;
    }

    // Marks attendance
    public void markAttendance(boolean isPremiumMode) throws IllegalStateException {
        if (isPremiumMode) {
            throw new IllegalStateException("Member is not a premium member.");
        }
        if (getActiveStatus()) {
            attendance++;
            loyaltyPoints += 5.0;
            if (getAttendance() >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }

    // Gets attendance limit
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    // Gets elevation eligibility
    public boolean getEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    // Gets reversion reason
    public String getRemovalReason() {
        return removalReason;
    }

    // Gets referral source
    public String getReferralSource() {
        return referralSource;
    }

    // Gets current plan
    public String getPlan() {
        return plan;
    }

    // Gets plan price
    public double getPrice() {
        return price;
    }

    // Gets price for a plan
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic":
                return 6500.0;
            case "standard":
                return 12500.0;
            case "deluxe":
                return 18500.0;
            default:
                return -1.0;
        }
    }

    // Elevates plan if eligible
    public String elevatePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for upgrade. Attendance must be at least " + attendanceLimit + ".";
        }
        if (newPlan.toLowerCase().equals(plan)) {
            return "Member is already subscribed to the " + newPlan + " plan.";
        }
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1.0) {
            return "Invalid plan: " + newPlan + ". Available plans are: basic, standard, deluxe.";
        }
        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan upgraded to " + newPlan + " for " + price;
    }

    // Reverts membership
    public void revertRegularMember(String reason) {
        resetMember();
        this.removalReason = reason;
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500.0;
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
                        "Plan: " + getPlan() + "\n" +
                        "Price: " + getPrice() + "\n" +
                        "Referral Source: " + getReferralSource() + "\n";
        if (!getRemovalReason().isEmpty()) {
            result += "Removal Reason: " + getRemovalReason() + "\n";
        }
        return result;
    }
}