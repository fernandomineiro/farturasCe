package moduloFaturamento.infra.config;

import moduloFaturamento.excecoes.MsgGenericaParametrizadaEnum;
import moduloFaturamento.excecoes.MsgGenericaParametrizadaException;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.infra.dto.ExcecaoAgenciaDTO;
import moduloFaturamento.infra.dto.ExcessoesRetornoApiDTO;
import moduloFaturamento.infra.util.LogErro;
import moduloFaturamento.util.FormatarMensagemExceptionValidation;
import org.json.JSONException;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerAdviceExceptionConfig extends ResponseEntityExceptionHandler {
	
	@Autowired
	private LogErro logErro;
	
	/**
	 * @author Ivan Alves
	 * <b>Toda vez que a aplicação gerar a excessão MsgGenericaPersonalizadaException a função é chamada</b>
	 * A classe instancia a classe que constroi a mensagem para o front end
	 */
	@ExceptionHandler(MsgGenericaPersonalizadaException.class)
	public ResponseEntity<?> handleMsgGenericaPersonalizada(MsgGenericaPersonalizadaException e, HttpServletRequest request, HandlerMethod handlerMethod) {

		if (request.getRequestURL().toString().contains("agenciaVirtual")) {
			ExcecaoAgenciaDTO excecaoAgenciaDTO = new ExcecaoAgenciaDTO(
					e.getClienteCodeAgencia(),
					e.getMensagemParaAgencia(), Integer.valueOf(e.getHttpStatus().toString()), request.getRequestURI());
			return new ResponseEntity<ExcecaoAgenciaDTO>(excecaoAgenciaDTO, e.getHttpStatus());

			/*ExcecaoAgenciaDTO excecaoAgenciaDTO = new ExcecaoAgenciaDTO(
					MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getClientCode(),
					MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getMessage(), 503, request.getRequestURI());
					MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getMessage(), 503, request.getRequestURI());
			return new ResponseEntity<ExcecaoAgenciaDTO>(excecaoAgenciaDTO, HttpStatus.valueOf(503));*/
		}else {
			ExcessoesRetornoApiDTO excessoesMensagemRetorno = new ExcessoesRetornoApiDTO(e.getHttpStatus().value(), e.getHttpStatus().toString(), e.getMessage());
			return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno, e.getHttpStatus());
		}
	}
	
	/**
	 * @author Ivan Alves
	 * <b>Toda vez que a aplicação gerar a excessão NullArgumentException a função é chamada</b>
	 * A classe instancia a classe que constroi a mensagem para o front end
	 */
	@ExceptionHandler(NullArgumentException.class)	
	public ResponseEntity<ExcessoesRetornoApiDTO> handleDadoObrigatorioNaoInformado(NullArgumentException e, HttpServletRequest request, HandlerMethod handlerMethod) {
		ExcessoesRetornoApiDTO excessoesMensagemRetorno = 
				new ExcessoesRetornoApiDTO(400, HttpStatus.BAD_REQUEST.toString(), e.getMessage());
		logErro.logErroGeral(e, "NullArgumentException.class");
		return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno,  HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @author Ivan Alves
	 * <b>Execessão gerada quando os dados de entidade recebida esta fora do escopo informado</b>
	 * EX: @Size, @NotNull especificado na entidade
	 * O código abaixo chama a classe responsável por  
	 * captura a mensagem. Formata para remover informações complementares, deixando somente o texto configurado
	 * no arquivo 'ValidationMenssage.properties'
	 * Depois o metodo chama a DTO responsável por construir um objeto de mensagens legível ao cliente
	 * retorna o objeto ao cliente com o codigo de status adequado
	 *
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExcessoesRetornoApiDTO> handleRetornaDadoInvalidoSpringValidation(ConstraintViolationException e,
			HttpServletRequest request, HandlerMethod handlerMethod) throws JSONException {

		FormatarMensagemExceptionValidation formatarMensagem = new FormatarMensagemExceptionValidation();
		String mensagemExcessaoFormatada = formatarMensagem.executarProcedimentoObterMensagemPersonalizadaUsuario(e);

		ExcessoesRetornoApiDTO excessoesMensagemRetorno = 
						new ExcessoesRetornoApiDTO(400, HttpStatus.BAD_REQUEST.toString(), mensagemExcessaoFormatada);
		return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno,  HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MsgGenericaParametrizadaException.class)
	public ResponseEntity<ExcecaoAgenciaDTO> handleParametrizada(MsgGenericaParametrizadaException e,
			HttpServletRequest request) throws JSONException {	
	
		ExcecaoAgenciaDTO excessoesMensagemRetorno = 
				new ExcecaoAgenciaDTO(e.getClientCode(), e.getMessage(), e.getStatusCode(), request.getRequestURI());
		
		return new ResponseEntity<ExcecaoAgenciaDTO>(excessoesMensagemRetorno, HttpStatus.valueOf(e.getStatusCode()));

 
	}
	
	@ExceptionHandler(ResourceAccessException.class)	
	public ResponseEntity<?> handleResourceAccessException(ResourceAccessException e, HttpServletRequest request, HandlerMethod handlerMethod) {
		if (request.getRequestURL().toString().contains("agenciaVirtual")) {
			ExcecaoAgenciaDTO excecaoAgenciaDTO =
					new ExcecaoAgenciaDTO("servico_indisponivel", "indisponivel", 503, request.getRequestURI());
			logErro.logErroGeral(e, "ResourceAccessException.class");
			return new ResponseEntity<ExcecaoAgenciaDTO>(excecaoAgenciaDTO, HttpStatus.valueOf(503));
		}else{
			ExcessoesRetornoApiDTO excessoesMensagemRetorno = new ExcessoesRetornoApiDTO(503, HttpStatus.SERVICE_UNAVAILABLE.toString(), e.getMessage());
			logErro.logErroGeral(e, "ResourceAccessException.class");
			return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno,  HttpStatus.BAD_REQUEST);
		}
	}
	

	@ExceptionHandler(Exception.class)	
	public ResponseEntity<?> handleGenericException(Exception e, HttpServletRequest request, HandlerMethod handlerMethod) {
		logErro.logErroGeral(e, "Exception.class");

		if (request.getRequestURL().toString().contains("agenciaVirtual")) {
			ExcecaoAgenciaDTO excecaoAgenciaDTO = new ExcecaoAgenciaDTO(
					MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getClientCode(),
					MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getMessage(), 503, request.getRequestURI());
			return new ResponseEntity<ExcecaoAgenciaDTO>(excecaoAgenciaDTO, HttpStatus.valueOf(503));

		}else {
			ExcessoesRetornoApiDTO excessoesMensagemRetorno = 
					new ExcessoesRetornoApiDTO(400, HttpStatus.BAD_REQUEST.toString(), e.getMessage());
		return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno,  HttpStatus.BAD_REQUEST);
		}
	}
}