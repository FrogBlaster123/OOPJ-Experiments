package Expt_6;

abstract class MobileRechargePlan{
    String planName;
    double planAmount;
    int validityDays;

    public MobileRechargePlan(String planName, double planAmount, int validityDays) {
        this.planName = planName;
        this.planAmount = planAmount;
        this.validityDays = validityDays;
    }

    abstract void displayPlanDetails();

    abstract double calculateRechargeAmount(double talkTime);
}

class PrepaidRecharge extends MobileRechargePlan{
    double talkTimeRate;
    public PrepaidRecharge(String planName, double planAmount, int validityDays, double talkTimeRate){
        super(planName, planAmount, validityDays);
        this.talkTimeRate = talkTimeRate;
    }
    @Override
    void displayPlanDetails(){
        System.out.println("Prepaid Plan Details:");
        System.out.println("Plan Name: " + planName);
        System.out.println("Plan Amount: " + planAmount);
        System.out.println("Validity Days: " + validityDays);
        System.out.println("Talk Time Rate (per minute): " + talkTimeRate);
    }
    @Override
    double calculateRechargeAmount(double talkTime){
        return planAmount + (talkTime * talkTimeRate);
    }
}

class PostpaidRecharge extends MobileRechargePlan{
    double monthlyCharge;

    public PostpaidRecharge(String planName, double planAmount, int validityDays, double monthlyCharge){
        super(planName, planAmount, validityDays);
        this.monthlyCharge = monthlyCharge;
    }

    @Override
    void displayPlanDetails() {
        System.out.println("Postpaid Plan Details:");
        System.out.println("Plan Name: " + planName);
        System.out.println("Plan Amount: " + planAmount);
        System.out.println("Validity Days: " + validityDays);
        System.out.println("Monthly Charge: " + monthlyCharge);
    }

    @Override
    double calculateRechargeAmount(double talkTime) {
        //In postpaid plans, the talktime is usually included in the monthly charge.
        //This example adds the plan amount, and the monthly charge.
        return planAmount + monthlyCharge;
    }
}

public class Q2 {
    public static void main(String[] args) {
        PrepaidRecharge prepaid = new PrepaidRecharge("Prepaid 199", 199.0, 28, 1.5);
        PostpaidRecharge postpaid = new PostpaidRecharge("Postpaid 499", 499.0, 30, 200);

        prepaid.displayPlanDetails();
        double prepaidRechargeAmount = prepaid.calculateRechargeAmount(60); // 60 minutes of talk time
        System.out.println("Total Prepaid Recharge Amount: " + prepaidRechargeAmount);

        System.out.println("\n"); // Add a new line for better readability

        postpaid.displayPlanDetails();
        double postpaidRechargeAmount = postpaid.calculateRechargeAmount(100); // 100 minutes of talk time.
        System.out.println("Total Postpaid Recharge Amount: " + postpaidRechargeAmount);
    }
}
