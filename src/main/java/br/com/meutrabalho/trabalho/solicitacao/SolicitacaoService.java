package br.com.meutrabalho.trabalho.solicitacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.meutrabalho.trabalho.servico.Servico;
import br.com.meutrabalho.trabalho.servico.ServicoRepository;
import br.com.meutrabalho.trabalho.trabalhaPara.TrabalhaPara;
import br.com.meutrabalho.trabalho.trabalhaPara.TrabalhaParaRepository;
import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserService;

@Service("solicitacaoService")
public class SolicitacaoService {

	private static final int ABERTO = 1;
	private static final int ANALISE = 2;
	private static final int FECHADO = 3;
	private static final int RECUSADO = 4;

	@Autowired
	private UserService userService;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private TrabalhaParaRepository trabalhaParaRepository;

	public User getUsuarioLogado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByEmail(auth.getName());
	}

	public Solicitacao salvar(String descricao, Long cdServico) {
		Solicitacao solicitacao = new Solicitacao();

		solicitacao.setDescricao(descricao);
		solicitacao.setServico(servicoRepository.findOneByCdServico(cdServico));
		solicitacao.setUser(getUsuarioLogado());
		solicitacao.setDhSolicitacao(new Date());
		solicitacao.setStatus(ABERTO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao aceitar(Solicitacao solicitacao) {
		solicitacao.setUserAnalise(getUsuarioLogado());
		solicitacao.setStatus(ANALISE);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao finalizar(Solicitacao solicitacao) {
		solicitacao.setStatus(FECHADO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao recusar(Solicitacao solicitacao) {
		solicitacao.setUserAnalise(getUsuarioLogado());
		solicitacao.setStatus(RECUSADO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Iterable<Solicitacao> listarAbertos() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == 1)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdAberto(getUsuarioLogado().getId());
		}
	}

	public Iterable<Solicitacao> listarAnalisados() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == 2)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdAnalise(getUsuarioLogado().getId());
		}
	}

	public Iterable<Solicitacao> listarFechados() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == 3)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdFechado(getUsuarioLogado().getId());
		}
	}

	public Iterable<Solicitacao> listarMinhasSolicitacoes() {
		return solicitacaoRepository.findByUserOrderByCdSolicitacaoDesc(getUsuarioLogado());
	}
	
	public Long contarSolicitacao(int status){
		return solicitacaoRepository.countByStatus(status);
	}
}