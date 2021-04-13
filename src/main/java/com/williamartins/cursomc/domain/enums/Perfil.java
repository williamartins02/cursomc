package com.williamartins.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIETE(2, "ROLE_CLIENTE");
	
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
      return cod;
    }
    public String getDescricao() {
    	return descricao;
    }
    
    /*criando um metodo static que recebe um codigo e retorna um objeto do tipo pagamento 
     * conforme o codigo que for passar "Pagamento pendente/quitado/cancelado."*/
    public static Perfil toEnum(Integer cod) {
    	
    	if(cod == null) {
    		return null;
    	}
    	/*Fazendo uma busca dos objeto pra percorrer Pagamento pendente/quitado/cancelado , 
    	 * retorna o codico solicitado pessoa fisica ou juririca*/
    	for (Perfil x : Perfil.values()) {
    		if(cod.equals(x.getCod())) {
    			return x;
    		}
    	}
    	/*Percorrer o for e não for nenhum tipo, da a exceção "id invalido"*/
    	throw new IllegalArgumentException("Id inválido: " + cod);
    }
}

