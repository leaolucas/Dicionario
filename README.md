# Dicionario
1. Como utilizar o programa
- Altere o caminho do arquivo na classe ControladorSistema,
atributo caminhoArquivo
- Instancie ControladorSistema, e logo após chame o método
iniciarSistema, da mesma classe
- Logue-se no sistema chamando o método loginAdmin de
ControladorSistema. O usuário e senha corretos são ambos ADM
- Chame o método de seu interesse passando os argumentos
necessários
2. Descrição dos Requisitos Funcionais
- A aplicação deve possibilitar que o administrador inclua uma nova
expressão
- A aplicação deve possibilitar que o administrador verifique a
existência de uma determinada expressão
- A aplicação deve possibilitar que o administrador altere uma
determinada expressão
- A aplicação deve possibilitar que qualquer pessoa possa realizar
consultas
3. Descrição dos Requisitos Não Funcionais
- A aplicação deve aplicar o princípio de separação modelo-visão
- A aplicação deve ser implementada em Java
4. Diagrama de classes com uma perspectiva de implementação
![classes](https://user-images.githubusercontent.com/40123806/41257632-e8218336-6da3-11e8-8b20-18029605893b.png)
5. Diagrama de pacotes que apresente as diferentes camadas e as classes que
fazem parte de cada camada
![pacotes](https://user-images.githubusercontent.com/40123806/41257661-1004a306-6da4-11e8-8b08-4d677eb80c83.jpg)
6. Padrões GRASP ( General Responsability Assignment Software Patterns ).
Anotar quais foram usados e como foram aplicados.
- Invenção pura: utilização de Persistência na camada de Serviços
Técnicos
- Indireção: introdução de um controlador para mediação entre dados
(modelo) e sua representação (visão)
- Controlador: A classe ControladorDicionário é a classe que
coordena as atividades de outras classes, sendo responsável por
coordenar as iterações do usuário com o sistema
- Criador: O padrão é utilizado na inicialização do sistema. A classe
de Interface com o usuário ViewDicionário instancia a classe
ControladorDicionário . Esta, por sua vez, instancia a classe
Dicionário e lê os dados em arquivos e, caso existam dados,
ControladorDicionário chama o método inserirExpressão até que
todas as expressões do arquivo passado estejam carregadas, e
Dicionário em inserirExpressão cria instâncias do tipo Expressão .
A relação mais forte de Criador é observada da classe Dicionário
para com a classe Expressão , pois para cada inserção ela vai criar
uma instância de Expressão.
- Alta coesão: as responsabilidades de um elemento estão fortemente
relacionadas à sua classe. Torna-se fácil reusar, manter e alterar os
elementos do sistema.
- Especialista da informação: No modelo, cada classe possui
métodos que satisfazem restritamente as responsabilidades dela e
que gerenciam apenas as informações que cabem àquela classe.
- Baixo acoplamento: As classes estão relacionadas de tal forma que
uma mudança em uma classe tem baixo impacto em outras classes,
e elas também tem alto potencial de reuso. Os métodos só utilizam
as classes que realmente precisam e os atributos são privados.
- Variação protegida: Não houve necessidade de implementação
- Polimorfismo: Não houve necessidade de implementação
7. Diagrama de casos de uso
![casodeuso](https://user-images.githubusercontent.com/40123806/41257703-3b16da1e-6da4-11e8-997c-999005cc0954.jpg)
