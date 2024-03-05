package shared;
public class TaxPayer implements Comparable<TaxPayer>{
    private Integer code;
    private String name;
    private double income;
    private double deduct;
    private double tax;

    public TaxPayer() {
    }
    public TaxPayer(Integer code, String name, double income, double deduct, double tax) {
        this.code = code;
        this.name = name;
        this.income = income;
        this.deduct = deduct;
        this.tax = tax;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        if (income > 0) {
            this.income = income;
        }
    }

    public double getDeduct() {
        return deduct;
    }

    public void setDeduct(double deduct) {
        this.deduct = deduct;
    }

    public double getTax() {
        double remain = income - deduct;
        if (remain <= 5000) {
            tax = remain * 5 / 100;
        } else if (remain <= 10000) {
            tax = 5000*0.05 +  (remain-5000)*0.1;
        } else {
            tax = 5000*0.05 + 5000*0.1 + (remain-10000)*0.15;
        }
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public int compareTo(TaxPayer o) {
        return this.getCode().compareTo(o.getCode());
    }

    @Override
    public String toString() {
        return "{ "+code+", "+name+", "+income+", "+deduct+", "+tax+" }";
    }
}
