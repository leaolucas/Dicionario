package dominio;

import java.util.ArrayList;

public class Dicionario {

	private ArrayList<Expressao> listaDeExpressoes;

	public Dicionario() {
		this.listaDeExpressoes = new ArrayList<Expressao>();
	}

	public ArrayList<Expressao> getListaDeExpressoes() {
		return listaDeExpressoes;
	}

	@Override
	public String toString() {
		String expressoes = new String();

		for (Expressao expressao : listaDeExpressoes) {
			expressoes += expressao.toString() + "\n";
		}

		return expressoes;
	}

	/**
	 * Verifica a existência de uma expressão
	 * 
	 * @param expressao
	 *            A expressao a ser verificada
	 * @return A posição da String na lista de expressões do Dicionário, caso
	 *         ela exista. Retorna -1 caso a String não exista na lista do
	 *         Dicionário
	 */
	public int verificarExistenciaExpressao(String expressao) {
		Expressao check = new Expressao(expressao);
		for (Expressao ex : listaDeExpressoes) {
			if (ex.toString().toLowerCase().equals(check.toString().toLowerCase()))
				return listaDeExpressoes.indexOf(ex);
		}
		return -1;
	}

	/**
	 * Insere uma nova expressão
	 * 
	 * @param novaExpressao
	 *            A expressão a ser inserida
	 */
	public void inserirExpressao(Expressao novaExpressao) {
		listaDeExpressoes.add(novaExpressao);
	}

	/**
	 * Substitui uma expressão
	 * 
	 * @param novaExpressao
	 *            A expressão a ser inserida
	 * @param posicao
	 *            A posição na qual a expressão deve ser inserida
	 */
	public void substituirExpressao(Expressao novaExpressao, int posicao) {
		listaDeExpressoes.set(posicao, novaExpressao);
	}

	/**
	 * O tamanho do dicionário
	 * 
	 * @return O tamanho do dicionário
	 */
	public int tamanhoDicionario() {
		return listaDeExpressoes.size();
	}

	/**
	 * Retorna a String da Expressão caso a posição tenha determinada palavra,
	 * caso não contenha, retorna null
	 * 
	 * @param palavra
	 *            A palavra a ser buscada
	 * @param posicao
	 *            A posição na qual a palavra deve ser buscada
	 * @return A String da expressão caso a posição tenha determinada palavra,
	 *         caso não contenha, retorna null
	 */
	public String getExpressaoPalavra(String palavra, int posicao) {
		if (listaDeExpressoes.get(posicao).getExpressao().toLowerCase().contains(palavra.toLowerCase())) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}

	/**
	 * Retorna a String da expressão caso seja iniciada por determinada letra,
	 * caso não seja, retorna null
	 * 
	 * @param caracter
	 *            O caracter inicial a ser buscado
	 * @param posicao
	 *            A posição na qual o caracter deve ser buscado
	 * @return A String da Expressão caso ela seja iniciada pelo caracter, e
	 *         null caso não seja iniciada pelo caracter
	 */
	public String getExpressaoCaracter(String caracter, int posicao) {
		if (listaDeExpressoes.get(posicao).getExpressao().toLowerCase().startsWith(caracter.toLowerCase())) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}

	/**
	 * Retorna a String da Expressão caso tenha a mesma quantidade de palavras
	 * passada como parâmetro, e null caso não tenha a mesma quantidade de
	 * palavras passada como parâmetro
	 * 
	 * @param quantPalavra
	 *            A quantidade de palavras a ser verificada
	 * @param posicao
	 *            A posição na qual a quantidade deve ser buscada
	 * @return A String da expressão caso ela tenha a mesma quantidade de
	 *         palavras, e null caso não tenha a mesma quantidade de palavras
	 */
	public String getExpressaoQuantPalavra(int quantPalavra, int posicao) {
		/*
		 * função split quebra a String em partes e usa como parâmetro o divisor
		 * split().length mostra em quantas partes a String foi quebrada
		 */
		if (listaDeExpressoes.get(posicao).getExpressao().split(" ").length == quantPalavra) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}
}
