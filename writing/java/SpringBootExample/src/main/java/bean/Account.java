package bean;

import java.io.Serializable;

public class Account implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3303690594565168870L;
	private String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}