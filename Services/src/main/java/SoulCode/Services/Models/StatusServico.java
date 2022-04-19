package SoulCode.Services.Models;

public enum StatusServico {
	
	RECEBIDO("recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido");
	
	
	private String descricao;

	private StatusServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
}
