package ua.karatnyk.service.utilities;

public class MailManager {
	
	public static String createContentToSentPassword(String login, String password) {
		String content = "Доброго дня!!!\n"
				+ "На цю поштову скриньку був запит для відправлення паролю.\n"
				+ "Якщо ви не відправляли цей запит, то проігноруйте та видаліть це повідомлення.\n"
				+ "Логін: " + login + "\nПароль: " + password+"\nЗ повагою, адміністрація школи.";
		return content;
	}
	
	public static String createSubjectToSentPassword() {
		return "Відправлення паролю";
	}
	
	public static Mail createMailToSentPassword(String login, String password, String email) {
		Mail mail = new Mail();
		mail.setContent(MailManager.createContentToSentPassword(login, password));
		mail.setSubject(MailManager.createSubjectToSentPassword());
		mail.setTo(email);
		return mail;
	}

}
