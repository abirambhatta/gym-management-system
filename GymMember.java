public abstract class GymMember {
    // Member ID
    protected int id;
    // Member name
    protected String name;
    // Member location
    protected String location;
    // Member phone
    protected String phone;
    // Member email
    protected String email;
    // Member gender
    protected String gender;
    // Member date of birth
    protected String dob;
    // Membership start date
    protected String startDate;
    // Active status
    protected boolean activeStatus;
    // Gym visits
    protected int attendance;
    // Loyalty points
    protected double loyaltyPoints;

    // Constructor
    public GymMember(int id, String name, String location, String phone, String email, 
                     String gender, String dob, String startDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.startDate = startDate;
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
    }

    // Gets ID
    public int getId() {
        return id;
    }

    // Gets name
    public String getName() {
        return name;
    }

    // Gets location
    public String getLocation() {
        return location;
    }

    // Gets phone
    public String getPhone() {
        return phone;
    }

    // Gets email
    public String getEmail() {
        return email;
    }

    // Gets gender
    public String getGender() {
        return gender;
    }

    // Gets date of birth
    public String getDob() {
        return dob;
    }

    // Gets start date
    public String getMembershipStartDate() {
        return startDate;
    }

    // Gets active status
    public boolean getActiveStatus() {
        return activeStatus;
    }

    // Gets attendance
    public int getAttendance() {
        return attendance;
    }

    // Gets loyalty points
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // Checks if PremiumMember
    public abstract boolean isPremiumMember();

    // Activates membership
    public void activateMembership(boolean isPremiumMode) throws IllegalStateException {
        if (isPremiumMode && !isPremiumMember()) {
            throw new IllegalStateException("Member is not a premium member.");
        }
        if (!isPremiumMode && isPremiumMember()) {
            throw new IllegalStateException("Member is not a regular member.");
        }
        activeStatus = true;
    }

    // Deactivates membership
    public void deactivateMembership(boolean isPremiumMode) throws IllegalStateException {
        if (isPremiumMode && !isPremiumMember()) {
            throw new IllegalStateException("Member is not a premium member.");
        }
        if (!isPremiumMode && isPremiumMember()) {
            throw new IllegalStateException("Member is not a regular member.");
        }
        if (activeStatus) {
            activeStatus = false;
        }
    }

    // Marks attendance
    public abstract void markAttendance(boolean isPremiumMode);

    // Resets member
    public void resetMember() {
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0.0;
    }

    // Displays details 
    public String display() {
        return "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Location: " + location + "\n" +
               "Phone: " + phone + "\n" +
               "Email: " + email + "\n" +
               "Gender: " + gender + "\n" +
               "DOB: " + dob + "\n" +
               "Start Date: " + startDate + "\n" +
               "Active: " + activeStatus + "\n" +
               "Attendance: " + attendance + "\n" +
               "Loyalty Points: " + loyaltyPoints + "\n";
    }
}