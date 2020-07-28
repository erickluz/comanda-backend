package com.erick.comanda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erick.comanda.domain.Comanda;
import com.erick.comanda.domain.ItensComanda;
import com.erick.comanda.domain.Produto;
import com.erick.comanda.dto.ComandaItensDTO;
import com.erick.comanda.repository.ComandaRepository;
import com.erick.comanda.service.exceptions.DataIntegrityException;
import com.erick.comanda.service.exceptions.ObjectNotFoundException;

@Service
public class ComandaService {

	static Logger log = Logger.getLogger(ComandaService.class.getName());

	@Autowired
	private ComandaRepository dao;
	@Autowired
	private ItensComandaService itensComandaService;
	@Autowired
	private ProdutoService produtoService;

	public Comanda buscar(Integer id) {
		return dao.findById(id).orElse(null);
	}

	public List<Comanda> listar() {
		return dao.findAll();
	}

	public ComandaItensDTO buscarComandaPorNumeroMesa(Integer numeroMesa) throws ObjectNotFoundException {
		Comanda comanda = dao.buscarComandaPorNumeroMesa(numeroMesa);
		if (comanda == null) {
			throw new ObjectNotFoundException("Comanda de número " + numeroMesa + " não encontrada");
		}
		List<ItensComanda> itensComanda = itensComandaService.buscarItensPorComanda(comanda.getId());
		ComandaItensDTO comandaCompleta = new ComandaItensDTO(comanda, itensComanda);
		return comandaCompleta;
	}

	public Comanda salvarComanda(ComandaItensDTO comanda) throws DataIntegrityException, ObjectNotFoundException {
		verificarNovaComanda(comanda);
		Comanda entidadeComanda = new Comanda(comanda.getNumero(), comanda.getNomeCliente(),
				comanda.getHoraFechamento(), comanda.getValorTotal());
		entidadeComanda.setHoraAbertura(comanda.getHoraAbertura());
		entidadeComanda.setValorTotal(calcularValorTotal(comanda.getItensComanda()));
		entidadeComanda = dao.save(entidadeComanda);
		List<ItensComanda> itensComanda = comanda.getItensComanda();
		for (ItensComanda item : itensComanda) {
			item.setComanda(entidadeComanda);
		}
		itensComandaService.salvarItensComanda(itensComanda);
		return entidadeComanda;
	}

	private void verificarNovaComanda(ComandaItensDTO comanda) throws DataIntegrityException {
		if (comanda == null) {
			throw new DataIntegrityException("Comanda inválida");
		}
		if (comanda.getNumero() == null || comanda.getNumero().compareTo(0) <= 0) {
			throw new DataIntegrityException("Número da comanda invalida");
		}
		if (dao.buscarComandaPorNumeroMesa(comanda.getNumero()) != null) {
			throw new DataIntegrityException("Numero de mesa ja em uso");
		}
	}

	public void editarComanda(ComandaItensDTO comanda) throws DataIntegrityException, ObjectNotFoundException {
		verificarComandaExistente(comanda);
		Comanda entidadeComanda = dao.buscarComandaPorNumeroMesa(comanda.getNumero());
		if (entidadeComanda == null) {
			throw new DataIntegrityException("Comanda não encontrada no sistema");
		}
		List<ItensComanda> itensComandas = new ArrayList<>();
		for (ItensComanda item : comanda.getItensComanda()) {
			ItensComanda entidadeItem = itensComandaService.buscarItemPorProdutoEComanda(item.getProduto().getId(),
					entidadeComanda.getId());
			if (entidadeItem == null) {
				entidadeItem = item;
			} else {
				entidadeItem.setQuantidade(item.getQuantidade());
			}
			entidadeItem.setComanda(entidadeComanda);
			itensComandas.add(entidadeItem);
		}
		entidadeComanda.setHoraFechamento(comanda.getHoraFechamento());
		entidadeComanda.setNomeCliente(comanda.getNomeCliente());
		entidadeComanda.setValorTotal(calcularValorTotal(comanda.getItensComanda()));
		dao.save(entidadeComanda);
		itensComandaService.excluirItensPorNumeroDeComanda(entidadeComanda.getId());
		itensComandaService.salvarItensComanda(itensComandas);
	}

	private BigDecimal calcularValorTotal(List<ItensComanda> itens) throws ObjectNotFoundException {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ItensComanda item : itens) {
			Produto produto = produtoService.buscar(item.getProduto().getId());
			if (produto == null) {
				throw new ObjectNotFoundException("Produto informado não encontrado" + item.getProduto().getId());
			}
			valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
		}
		return valorTotal;
	}

	private void verificarComandaExistente(ComandaItensDTO comanda) throws DataIntegrityException {
		if (comanda == null) {
			throw new DataIntegrityException("Comanda vazia!");
		}
		for (ItensComanda itemComanda : comanda.getItensComanda()) {
			if (itemComanda.getQuantidade().compareTo(0) < 0) {
				throw new DataIntegrityException("Item com quantidade inválida");
			}
		}
	}

	public void excluirComanda(Integer numeroComanda) throws ObjectNotFoundException {
		itensComandaService.excluirItensPorNumeroDeComanda(numeroComanda);
		dao.excluirComandaPorNumeroMesa(numeroComanda);
	}
}