public class Client{
    private String id = "C"; // + the length of the Client relation + 1 made up to 3 digits
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

     public void setId(id){
	this.id = id;
    }

     public void setId(firstName){
	 this.firstName = firstName;
    }

     public void setId(lastName){
	 this.lastName = lastName;
    }
    
    
}
