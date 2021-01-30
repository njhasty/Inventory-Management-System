package Model;


/** This class defines and manages in house parts and inherits from part class */
public class InHousePart extends Part {
    public InHousePart(int partID, String name, double price, int stock, int min, int max, int machineID) {
        super(partID, name, price, stock, min, max);
        this.machineID = machineID;
    }

    private int machineID;

    /** Method to return machineID.
     * @return machineID. */
    public int getMachineID() {
        return machineID;
    }

    /** Method to set machineID.
     * @param machineID  Passes machineID.
     * @return void. */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
