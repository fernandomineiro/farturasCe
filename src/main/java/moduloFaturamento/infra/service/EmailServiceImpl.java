package moduloFaturamento.infra.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

//	private final String REDIRECIONAR_EMAIL_SITE_CESAN = "https://www.cesan.com.br/servicos/consulta-de-protocolos/consulta-de-status-da-solicitacao-de-servico/";
//	private final String REDIRECIONAR_EMAIL_AVALIACAO_CESAN = "https://www.cesan.com.br/servicos/consulta-de-protocolos/pesquisa-de-satisfacao/";
//	private final String REDIRECIONAR_EMAIL_SITE_YOUTUBE = "https://www.youtube.com/user/TVCesan";
//	private final String REDIRECIONAR_EMAIL_SITE_INSTA = "https://www.instagram.com/poupeagua";
//	private final String REDIRECIONAR_EMAIL_SITE_FACE = "https://pt-br.facebook.com/poupeagua";
//	private final String REDIRECIONAR_EMAIL_SITE_TWITTER = "https://twitter.com/poupeagua";
//	@Value("${rabbitmq.direct.produtorEmail.nomeExchange}")
//	private String exchangeEmail;
//	@Value("${rabbitmq.direct.produtorEmail.rota}")
//	private String rotaEmail;
//
//	@Autowired
//	private Produtor produtor;
//
//	private List<String> emailEnviado;

}
