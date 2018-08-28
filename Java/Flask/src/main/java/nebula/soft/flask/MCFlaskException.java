package nebula.soft.flask;

public class MCFlaskException extends Exception {
	public MCFlaskException(MCFlask f_, String message_, Throwable cause_) {
		super("["+f_.fullName()+"]["+f_.typeAsString()+"] "+message_, cause_);
	}
	
	public MCFlaskException(MCFlask f_, String message_) {
		super("["+f_.fullName()+"]["+f_.typeAsString()+"] "+message_);
	}
	
	public MCFlaskException(String message_, Throwable cause_) {
		super(message_, cause_);
	}
	
	public MCFlaskException(String message_) {
		super(message_);
	}
	
	private static final long serialVersionUID = 4970041940207835763L;
}
