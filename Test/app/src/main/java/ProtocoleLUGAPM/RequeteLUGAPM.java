package ProtocoleLUGAPM;

/**
 * Created by Vince on 13-12-17.
 */

public class RequeteLUGAPM {
    public static int REQUEST_ = 1;

    private int type;
    private String chargeUtile;

    public RequeteLUGAPM(int t, String cu) {
        setType(t);
        cu = getChargeUtile();
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChargeUtile() {
        return chargeUtile;
    }

    public void setChargeUtile(String chargeUtile) {
        this.chargeUtile = chargeUtile;
    }
}
