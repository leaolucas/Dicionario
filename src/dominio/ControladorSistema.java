package dominio;

import java.util.ArrayList;
import java.util.List;

import servicosTecnicos.Persistencia;

public class ControladorSistema {
	private boolean logado;
	private Dicionario dicionario;
	private Administrador administrador;
	private Persistencia persistencia;
        //mudar path
	private String caminhoArquivo = "C:\\Users\\Notebook\\Desktop\\arquivo.txt";

	public ControladorSistema() {
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	/**
	 * Retorna lista com todas as express�es existentes no dicion�rio
	 * 
	 * @return Todas as express�es que existem no dicion�rio
	 */
	public ArrayList<String> getExpressoesDicionario() {
		ArrayList<String> expressoes = new ArrayList<String>();
		for (Expressao expressao : dicionario.getListaDeExpressoes()) {
			expressoes.add(expressao.getExpressao());
		}
		return expressoes;
	}

	/**
	 * Executa o login no sistema
	 * 
	 * @param login
	 *            O login do Administrador
	 * @param senha
	 *            A senha do Administrador
	 * @return True caso o Administrador tenha logado com sucesso, False caso
	 *         n�o tenha logado com sucesso
	 */
	public boolean loginAdmin(String login, String senha) {
		if (administrador.getLogin().equals(login)) {
			if (administrador.getSenha().equals(senha)) {
				logado = true;
			} else {
				logado = false;
			}
		} else {
			logado = false;
		}

		return logado;
	}

	/**
	 * Inicia o sistema
	 */
	public void iniciarSistema() {
		dicionario = new Dicionario();
		administrador = new Administrador();
		persistencia = new Persistencia();

		List<String> expressoes = persistencia.lerArquivo(caminhoArquivo);
		for (String exp : expressoes) {
			Expressao expressao = new Expressao(exp);
			dicionario.inserirExpressao(expressao);
		}

	}

	/**
	 * Busca as express�es de acordo com uma entrada e o tipo de busca
	 * 
	 * @param dadoEntrada
	 *            A String a ser buscada
	 * @param tipoBusca
	 *            O tipo de busca a ser feito 
	 *            0: procura a express�o pela palavra 
	 *            1: procura a express�o pelo caracter inicial 
	 *            2: procura a express�o de acordo com a quantidade de palavras
	 * @return Uma lista contendo as express�es que correspondem ao tipo de
	 *         busca e texto de entrada
	 */
	public ArrayList<String> buscarExpressoes(String dadoEntrada, int tipoBusca) {
		ArrayList<String> expressoes = new ArrayList<>();
                
                System.out.println(dadoEntrada);
                System.out.println(tipoBusca);
		switch (tipoBusca) {
		case 0:
			for (int i = 0; i < dicionario.tamanhoDicionario(); i++) {                            
				String expressao = dicionario.getExpressaoPalavra(dadoEntrada, i);
				if (expressao != null) {
					expressoes.add(expressao);
				}
			}
			break;
                case 1:
			for (int i = 0; i < dicionario.tamanhoDicionario(); i++) {
				String expressao = dicionario.getExpressaoCaracter(dadoEntrada, i);
				if (expressao != null) {
					expressoes.add(expressao);
				}
			}
			break;
		case 2:
			for (int i = 0; i < dicionario.tamanhoDicionario(); i++) {
				int expressaoInt = Integer.parseInt(dadoEntrada);
				String expressao = dicionario.getExpressaoQuantPalavra(expressaoInt, i);
				if (expressao != null) {
					expressoes.add(expressao);
				}
			}
			break;
		}
		return expressoes;
	}

	/**
	 * Altera o dicion�rio, substituindo a express�o antiga pela nova caso a
	 * antiga exista. Caso a antiga n�o exista, insere a nova no Dicion�rio
	 * 
	 * @param expressaoNova
	 *            A express��o a ser inserida
	 * @param expressaoAntiga
	 *            A express�o a ser substitu�da
	 * @return String dizendo se a express�o foi atualizada ou se deu erro
	 */
	public String alterarDicionario(String expressaoNova, String expressaoAntiga) {
		if (logado) {
			int posicaoExpressaoAntiga = dicionario.verificarExistenciaExpressao(expressaoAntiga);
			int posicaoExpressaoNova = dicionario.verificarExistenciaExpressao(expressaoNova);
			Expressao expressao = new Expressao(expressaoNova);
			if (posicaoExpressaoAntiga == -1 && posicaoExpressaoNova == -1) {				
				dicionario.inserirExpressao(expressao);
				persistencia.escreverArquivo(caminhoArquivo, expressao.getExpressao());
				return "Express�o inserida com sucesso";
			} else if (posicaoExpressaoNova == -1) {
				dicionario.substituirExpressao(expressao, posicaoExpressaoAntiga);
				persistencia.substituirLinhaArquivo(caminhoArquivo, expressaoNova, expressaoAntiga);
				return "Express�o substitu�da com sucesso";
			}
			return "A express�o nova j� existe no dicion�rio";
		}	
		return "O administrador n�o est� logado";
	}
        
       /**
	 * Altera o dicion�rio, substituindo a express�o antiga pela nova caso a
	 * antiga exista. Caso a antiga n�o exista, insere a nova no Dicion�rio
	 * 
	 * @param expressaoNova
	 *            A express��o a ser inserida
	 * @param expressaoAntiga
	 *            A express�o a ser substitu�da (caso tenha)
         * @param acao
	 *            Qual a a��o que ser� realizada (Inserir, Alterar ou Verificar)
         *            0: Inserir
	 *            1: Alterar
	 *            2: Verificar
	 * @return String dizendo a��o foi conclupida com �xito ou se ocorreu alguma falha
	 */ 
        
        public String gerenciarAcoes(String expressaoNova, String expressaoAntiga, int acao){
            if(acao == 2){
                return (verificarExistenciaDicionario(expressaoNova));
            }
            else{
                return (alterarDicionario(expressaoNova, expressaoAntiga));
            }
        }

	/**
	 * Verifica a exist�ncia de uma express�o no Dicion�rio
	 * 
	 * @param expressao
	 *            A express�o a ser buscada no Dicion�rio
	 * @return A posi��o da express�o caso ela exista, -1 caso n�o exista ou o
	 *         usu�rio n�o esteja logado
	 */

	public String verificarExistenciaDicionario(String expressao) {
		if (logado)
			if (dicionario.verificarExistenciaExpressao(expressao) != -1)
				return "A express�o existe no dicion�rio";
			else
				return "A express�o n�o existe no dicion�rio";
		else
			return "O administrador n�o est� logado";
	}
}
