package dominio;

public class Expressao {
	private String expressao;

	public Expressao(String expressao) {
		this.expressao = expressao;
	}

	public String getExpressao() {
		return expressao;
	}

	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}

	@Override
	public String toString() {
		return expressao;
	}

}
