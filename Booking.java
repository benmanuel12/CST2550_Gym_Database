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

    public void setId(id){
	this.id = id;
    }

    public void setClientId(clientId){
	this.clientId = clientId;
    }

    public void setTrainerId(trainerId){
	this.TrainerId = trainerId;
    }

    public void setDate(date){
	this.date = date;
    }

    public void setTimeStart(timeStart){
	this.timeStart = timeStart;
    }

    public void setTimeEnd(timeEnd){
	this.timeEnd = timeEnd;
    }

    public void setFocus(focus){
	this.focus = focus;
    }  
}
