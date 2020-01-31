public class Booking{
    private String id = "";
    private String clientId = "";
    private String trainerId = "";
    private String date = "";
    private String timeStart = "";
    private String timeEnd = "";
    private String focus = "";

    public Booking(String id, String clientId, String trainerId, String date, String timeStart, String timeEnd, String focus){
	this.id = id;
	this.clientId = clientId;
	this.trainerId = trainerId;
	this.date = date;
	this.timeStart = timeStart;
	this.timeEnd = timeEnd;
	this.focus = focus;
    }

    public String getId(){
	return this.id;
    }
    public String getClientId(){
	return this.id;
    }
    public String getTrainerId(){
	return this.id;
    }
    public String getDate(){
	return this.id;
    }
    public String getTimeStart(){
	return this.timeStart;
    }
    public String getTimeEnd(){
	return this.timeEnd;
    }
    public String getFocus(){
	return this.focus;
    }

    public void setId(String id){
	this.id = id;
    }

    public void setClientId(String clientId){
	this.clientId = clientId;
    }

    public void setTrainerId(String trainerId){
	this.trainerId = trainerId;
    }

    public void setDate(String date){
	this.date = date;
    }

    public void setTimeStart(String timeStart){
	this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd){
	this.timeEnd = timeEnd;
    }

    public void setFocus(String focus){
	this.focus = focus;
    }  
}
