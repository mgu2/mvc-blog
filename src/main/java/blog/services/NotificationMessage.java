package blog.services;

public class NotificationMessage {
	NotificationMessageType type;
	String text;
	
	public NotificationMessage(NotificationMessageType type, String text) {
        this.type = type;
        this.text = text;
    }
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public NotificationMessageType getType() {
		return type;
	}
	
	public void setType(NotificationMessageType type) {
		this.type = type;
	}
}
