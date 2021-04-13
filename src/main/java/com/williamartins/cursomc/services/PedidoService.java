package com.williamartins.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EmailService emailService;


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
	@Transactional
	public Pedido insert(Pedido obj) throws ObjectNotFoundException {
		//setando os obj
		obj.setId(null);//id do pedido
		obj.setInstante(new Date());//cria uma nova data com o instante "data" atual 
		obj.setCliente(clienteService.find(obj.getCliente().getId()));//buscando no banco o nome do cliente e o id do pedido
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);//Um pedido q esta sendo inserindo o pagamento tem que ser pendente
		obj.getPagamento().setPedido(obj);//Associação de mão dupla, pagamento tem que conhecer o pedido dele.
		
		// criando uma data de vencimento ser for paga com boleto.
		// se o pagamento for pagamento com boleto, gera uma data pra ele.
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());// preencher no boleto a data do vencimento
		}
		obj = repo.save(obj);//salvou o pedido no banco.
		pagamentoRepository.save(obj.getPagamento());//salvando o pagamento 
		
		/*Apos inserir e gerar o pagamento, percorrer a lista dos pedido fazer a contagem e salvar no banco de dados.*/
		/*percorrendo toda lista de pedido associada ao objeto "obj" */
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));//Buscando no BD o produto para copiar o preço dele.
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());// apos percorrer a lista, salvar o pedido no BD
		emailService.sendOrderConfirmationHtmlEmail(obj);//Confirmação dos email.
		return obj;
	}
}
