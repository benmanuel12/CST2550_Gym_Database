public class Client{
    private String id = "";
    private String firstName = "";
    private String lastName = "";

    public Client(String id, String firstName, String lastName){
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public String getId(){
	return this.id;
    }

    public String getFirstName(){
	return this.firstName;
    }

    public String getLastName(){
	return this.lastName;
    }

    public void setId(String id){
	this.id = id;
    }

    public void setFirstName(String firstName){
	 this.firstName = firstName;
    }

    public void setLastName(String lastName){
	 this.lastName = lastName;
    } 
}
