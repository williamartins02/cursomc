package com.williamartins.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamartins.cursomc.domain.ItemPedido;
import com.williamartins.cursomc.domain.PagamentoComBoleto;
import com.williamartins.cursomc.domain.Pedido;
import com.williamartins.cursomc.domain.enums.EstadoPagamento;
import com.williamartins.cursomc.repositories.ItemPedidoRepository;
import com.williamartins.cursomc.repositories.PagamentoRepository;
import com.williamartins.cursomc.repositories.PedidoRepository;


import javassist.tools.rmi.ObjectNotFoundException;

/*EndPoint Service*/
/*serviço que oferece operação de consulta de categorias com auxulio do repository.  */
@Service
public class PedidoService {

//instanciado o repository PedidoRepository usando a anotação @Autowired

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	/*
	 * Operação para fazer uma busca no banco de dados atraves do ID da Pedido e
	 * mostra uma execessão caso o ID não existir.. ObjectNotFoundException = lança
	 * um em todo de execessão casoo id não exista
	 */
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id); /* findById = faz operação de busca de dados, com base no ID. */
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	/* Metodo para inserir um novo pedido */
	public Pedido insert(Pedido obj) throws ObjectNotFoundException {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		// criando uma data de vencimento do boleto.
		if (obj.getPagamento() instanceof PagamentoComBoleto) {// se o pagamento for pagamento com boleto
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();// gerar uma data pra ele.
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);//salvou o pedido no banco.
		pagamentoRepository.save(obj.getPagamento());//salvando o pagamento 
		
		//percorrendo toda lista de pedido associada ao objeto "obj" 
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());//Buscando no BD o produto para copiar o preco dele.
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());// apos percorrer a lista, salvar o pedido no BD
		return obj;
	}
}
