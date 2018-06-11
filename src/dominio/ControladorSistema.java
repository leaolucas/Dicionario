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
	 * Retorna lista com todas as expressões existentes no dicionário
	 * 
	 * @return Todas as expressões que existem no dicionário
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
	 *         não tenha logado com sucesso
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
	 * Busca as expressões de acordo com uma entrada e o tipo de busca
	 * 
	 * @param dadoEntrada
	 *            A String a ser buscada
	 * @param tipoBusca
	 *            O tipo de busca a ser feito 
	 *            0: procura a expressão pela palavra 
	 *            1: procura a expressão pelo caracter inicial 
	 *            2: procura a expressão de acordo com a quantidade de palavras
	 * @return Uma lista contendo as expressões que correspondem ao tipo de
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
	 * Altera o dicionário, substituindo a expressão antiga pela nova caso a
	 * antiga exista. Caso a antiga não exista, insere a nova no Dicionário
	 * 
	 * @param expressaoNova
	 *            A expressção a ser inserida
	 * @param expressaoAntiga
	 *            A expressão a ser substituída
	 * @return String dizendo se a expressão foi atualizada ou se deu erro
	 */
	public String alterarDicionario(String expressaoNova, String expressaoAntiga) {
		if (logado) {
			int posicaoExpressaoAntiga = dicionario.verificarExistenciaExpressao(expressaoAntiga);
			int posicaoExpressaoNova = dicionario.verificarExistenciaExpressao(expressaoNova);
			Expressao expressao = new Expressao(expressaoNova);
			if (posicaoExpressaoAntiga == -1 && posicaoExpressaoNova == -1) {				
				dicionario.inserirExpressao(expressao);
				persistencia.escreverArquivo(caminhoArquivo, expressao.getExpressao());
				return "Expressão inserida com sucesso";
			} else if (posicaoExpressaoNova == -1) {
				dicionario.substituirExpressao(expressao, posicaoExpressaoAntiga);
				persistencia.substituirLinhaArquivo(caminhoArquivo, expressaoNova, expressaoAntiga);
				return "Expressão substituída com sucesso";
			}
			return "A expressão nova já existe no dicionário";
		}	
		return "O administrador não está logado";
	}
        
       /**
	 * Altera o dicionário, substituindo a expressão antiga pela nova caso a
	 * antiga exista. Caso a antiga não exista, insere a nova no Dicionário
	 * 
	 * @param expressaoNova
	 *            A expressção a ser inserida
	 * @param expressaoAntiga
	 *            A expressão a ser substituída (caso tenha)
         * @param acao
	 *            Qual a ação que será realizada (Inserir, Alterar ou Verificar)
         *            0: Inserir
	 *            1: Alterar
	 *            2: Verificar
	 * @return String dizendo ação foi conclupida com êxito ou se ocorreu alguma falha
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
	 * Verifica a existï¿½ncia de uma expressï¿½o no Dicionï¿½rio
	 * 
	 * @param expressao
	 *            A expressï¿½o a ser buscada no Dicionï¿½rio
	 * @return A posiï¿½ï¿½o da expressï¿½o caso ela exista, -1 caso nï¿½o exista ou o
	 *         usuï¿½rio nï¿½o esteja logado
	 */

	public String verificarExistenciaDicionario(String expressao) {
		if (logado)
			if (dicionario.verificarExistenciaExpressao(expressao) != -1)
				return "A expressão existe no dicionário";
			else
				return "A expressão não existe no dicionário";
		else
			return "O administrador não está logado";
	}
}
