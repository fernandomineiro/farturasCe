package moduloFaturamento.email;

import java.util.Map;

public class Mail {

	private String from;
	private String to;
	private String subject;
	private Map<String, Object> model;

	public Mail() {
	}

	public Mail(String to, String subject) {
		this.to = to;
		this.subject = subject;

	}

	public Mail(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
