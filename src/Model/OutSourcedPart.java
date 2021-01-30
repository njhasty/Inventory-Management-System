package Model;


/** This class defines and manages outsourced parts and inherits from part class */
public class OutSourcedPart extends Part {
    public OutSourcedPart(int partID, String name, double price, int stock, int min, int max, String companyName) {
        super(partID, name, price, stock, min, max);
        this.companyName = companyName;
    }


    private String companyName;

    /** Method to return companyName.
     * @return companyName. */
    public String getCompanyName() {
        return companyName;
    }

    /** Method to set companyName.
     * @param companyName  Passes companyName.
     * @return void. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
