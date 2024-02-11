package moduloFaturamento.email;

import org.springframework.stereotype.Component;

@Component
public class Email {

//	@Autowired
//	private SpringTemplateEngine templateEngine;
//	@Autowired
//	private JavaMailSender emailSender;
//	@Value("${email.remetente}")
//	private String emailRemetente;
//
//	public void enviarEmail(Mail mail, Map<String, Object> model, String template) {
//		Context context = new Context();
//		context.setVariables(mail.getModel());
//		context.setVariable("logo", "logo");
//		context.setVariable("logocomnome", "logocomnome");
//		context.setVariable("face", "face");
//		context.setVariable("insta", "insta");
//		context.setVariable("twitter", "twitter");
//		context.setVariable("youtube", "youtube");
//		mail.setFrom(emailRemetente);
//		try {
//			MimeMessage message = emailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//			String html = templateEngine.process(template, context);
//			helper.setTo(mail.getTo());
//			helper.setText(html, true);
//			helper.addInline("logo", new ClassPathResource("/images/logo-cesan.png"), "image/png");
//			helper.addInline("logocomnome", new ClassPathResource("/images/logo-com-nome.png"), "image/png");
//			helper.addInline("youtube", new ClassPathResource("/images/youtube.png"), "image/png");
//			helper.addInline("insta", new ClassPathResource("/images/insta.png"), "image/png");
//			helper.addInline("face", new ClassPathResource("/images/face.png"), "image/png");
//			helper.addInline("twitter", new ClassPathResource("/images/twitter.png"), "image/png");
//			helper.setSubject(mail.getSubject());
//			helper.setFrom(mail.getFrom());
//			emailSender.send(message);
//
//		} catch (MessagingException e) {
//
//		}
//	}
}
