public class Trainer{
    private String id = "T"; // + the length of the Trainer relation + 1 made up to 3 digits
    private String firstName = "";
    private String lastName = "";

    public Trainer(String id, String firstName, String lastName){
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
