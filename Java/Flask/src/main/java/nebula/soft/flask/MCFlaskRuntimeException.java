package nebula.soft.flask;

public class MCFlaskRuntimeException extends RuntimeException {
	public MCFlaskRuntimeException(MCFlask f_, String message_, Throwable cause_) {
		super("["+f_.fullName()+"]["+f_.typeAsString()+"] "+message_, cause_);
	}
	
	public MCFlaskRuntimeException(MCFlask f_, String message_) {
		super("["+f_.fullName()+"]["+f_.typeAsString()+"] "+message_);
	}
	
	public MCFlaskRuntimeException(String message_, Throwable cause_) {
		super(message_, cause_);
	}
	
	public MCFlaskRuntimeException(String message_) {
		super(message_);
	}
	
	private static final long serialVersionUID = 4977447940207835763L;

}
