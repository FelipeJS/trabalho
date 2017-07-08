package br.com.meutrabalho.trabalho.solicitacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meutrabalho.trabalho.servico.Servico;
import br.com.meutrabalho.trabalho.servico.ServicoRepository;
import br.com.meutrabalho.trabalho.trabalhaPara.TrabalhaPara;
import br.com.meutrabalho.trabalho.trabalhaPara.TrabalhaParaRepository;
import br.com.meutrabalho.trabalho.usuario.UserLogado;

@Service("solicitacaoService")
public class SolicitacaoService {

	private static final int ABERTO = 1;
	private static final int ANALISE = 2;
	private static final int FECHADO = 3;
	private static final int RECUSADO = 4;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private TrabalhaParaRepository trabalhaParaRepository;

	@Autowired
	private UserLogado userLogado;
	
	public Solicitacao salvar(String descricao, Long cdServico) {
		Solicitacao solicitacao = new Solicitacao();

		solicitacao.setDescricao(descricao);
		solicitacao.setServico(servicoRepository.findOneByCdServico(cdServico));
		solicitacao.setUser(userLogado.getUsuarioLogado());
		solicitacao.setDhSolicitacao(new Date());
		solicitacao.setStatus(ABERTO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao aceitar(Solicitacao solicitacao) {
		solicitacao.setUserAnalise(userLogado.getUsuarioLogado());
		solicitacao.setStatus(ANALISE);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao finalizar(Solicitacao solicitacao) {
		solicitacao.setStatus(FECHADO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao recusar(Solicitacao solicitacao) {
		solicitacao.setUserAnalise(userLogado.getUsuarioLogado());
		solicitacao.setStatus(RECUSADO);

		return solicitacaoRepository.save(solicitacao);
	}

	public Iterable<Solicitacao> listarAbertos() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(userLogado.getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == ABERTO)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdAndStatus(userLogado.getUsuarioLogado().getId(), ABERTO);
		}
	}

	public Iterable<Solicitacao> listarAnalisados() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(userLogado.getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == ANALISE)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdAndStatus(userLogado.getUsuarioLogado().getId(), ANALISE);
		}
	}

	public Iterable<Solicitacao> listarFechados() {
		List<TrabalhaPara> empresasFuncionarios = (ArrayList<TrabalhaPara>) trabalhaParaRepository
				.findByUserFuncionario(userLogado.getUsuarioLogado());

		List<Solicitacao> solicitacoesParaFuncionario = new ArrayList<Solicitacao>();

		if (empresasFuncionarios.size() > 0) {
			for (TrabalhaPara trabalhaPara : empresasFuncionarios) {
				Iterable<Servico> servicosDaEmpresa = servicoRepository.findByUser(trabalhaPara.getUserEmpresa());
				Iterable<Solicitacao> solicitacoesDaEmpresa = solicitacaoRepository
						.findByServicoInOrderByCdSolicitacaoDesc(servicosDaEmpresa);

				for (Solicitacao solicitacao : solicitacoesDaEmpresa) {
					if (solicitacao.getStatus() == FECHADO)
						solicitacoesParaFuncionario.add(solicitacao);
				}
			}

			return solicitacoesParaFuncionario;
		} else {
			return solicitacaoRepository.findByUserIdAndStatus(userLogado.getUsuarioLogado().getId(), FECHADO);
		}
	}

	public Iterable<Solicitacao> listarMinhasSolicitacoes() {
		return solicitacaoRepository.findByUserOrderByCdSolicitacaoDesc(userLogado.getUsuarioLogado());
	}

	public Long contarSolicitacao(Long userId, int status) {
		return solicitacaoRepository.countByUserIdAndStatus(userId, status);
	}
}