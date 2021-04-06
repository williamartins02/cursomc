package com.williamartins.cursomc.domain.enums;

/*implemtanção de um tipo enumero para ter controle total do codigo atribuido a cada valor da numeração */

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
      return cod;
    }
    public String getDescricao() {
    	return descricao;
    }
    
    /*criando um metodo static que recebe um codigo e retorna um objeto do tipo cliente 
     * conforme o codigo que for passar "Pessoa fisica/juridica."*/
    public static TipoCliente toEnum(Integer cod) {
    	
    	if(cod == null) {
    		return null;
    	}
    	/*Fazendo uma busca dos objeto pra percorrer Pessoa Fisica/juridica , 
    	 * retorna o codico solicitado pessoa fisica ou juririca*/
    	for (TipoCliente x : TipoCliente.values()) {
    		if(cod.equals(x.getCod())) {
    			return x;
    		}
    	}
    	/*Percorrer o for e não for ninguem, da a exceção "id invalido"*/
    	throw new IllegalArgumentException("Id inválido: " + cod);
    }
}