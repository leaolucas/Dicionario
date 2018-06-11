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
	 * Verifica a exist�ncia de uma express�o
	 * 
	 * @param expressao
	 *            A expressao a ser verificada
	 * @return A posi��o da String na lista de express�es do Dicion�rio, caso
	 *         ela exista. Retorna -1 caso a String n�o exista na lista do
	 *         Dicion�rio
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
	 * Insere uma nova express�o
	 * 
	 * @param novaExpressao
	 *            A express�o a ser inserida
	 */
	public void inserirExpressao(Expressao novaExpressao) {
		listaDeExpressoes.add(novaExpressao);
	}

	/**
	 * Substitui uma express�o
	 * 
	 * @param novaExpressao
	 *            A express�o a ser inserida
	 * @param posicao
	 *            A posi��o na qual a express�o deve ser inserida
	 */
	public void substituirExpressao(Expressao novaExpressao, int posicao) {
		listaDeExpressoes.set(posicao, novaExpressao);
	}

	/**
	 * O tamanho do dicion�rio
	 * 
	 * @return O tamanho do dicion�rio
	 */
	public int tamanhoDicionario() {
		return listaDeExpressoes.size();
	}

	/**
	 * Retorna a String da Express�o caso a posi��o tenha determinada palavra,
	 * caso n�o contenha, retorna null
	 * 
	 * @param palavra
	 *            A palavra a ser buscada
	 * @param posicao
	 *            A posi��o na qual a palavra deve ser buscada
	 * @return A String da express�o caso a posi��o tenha determinada palavra,
	 *         caso n�o contenha, retorna null
	 */
	public String getExpressaoPalavra(String palavra, int posicao) {
		if (listaDeExpressoes.get(posicao).getExpressao().toLowerCase().contains(palavra.toLowerCase())) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}

	/**
	 * Retorna a String da express�o caso seja iniciada por determinada letra,
	 * caso n�o seja, retorna null
	 * 
	 * @param caracter
	 *            O caracter inicial a ser buscado
	 * @param posicao
	 *            A posi��o na qual o caracter deve ser buscado
	 * @return A String da Express�o caso ela seja iniciada pelo caracter, e
	 *         null caso n�o seja iniciada pelo caracter
	 */
	public String getExpressaoCaracter(String caracter, int posicao) {
		if (listaDeExpressoes.get(posicao).getExpressao().toLowerCase().startsWith(caracter.toLowerCase())) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}

	/**
	 * Retorna a String da Express�o caso tenha a mesma quantidade de palavras
	 * passada como par�metro, e null caso n�o tenha a mesma quantidade de
	 * palavras passada como par�metro
	 * 
	 * @param quantPalavra
	 *            A quantidade de palavras a ser verificada
	 * @param posicao
	 *            A posi��o na qual a quantidade deve ser buscada
	 * @return A String da express�o caso ela tenha a mesma quantidade de
	 *         palavras, e null caso n�o tenha a mesma quantidade de palavras
	 */
	public String getExpressaoQuantPalavra(int quantPalavra, int posicao) {
		/*
		 * fun��o split quebra a String em partes e usa como par�metro o divisor
		 * split().length mostra em quantas partes a String foi quebrada
		 */
		if (listaDeExpressoes.get(posicao).getExpressao().split(" ").length == quantPalavra) {
			return listaDeExpressoes.get(posicao).getExpressao();
		}
		return null;
	}
}
