# SATTRA

Sistema de cadastro de pessoas

As funcionalidades disponíveis:

  Cadastrar, Editar, Buscar, Listar, Remover Pessoa.
  Validação de cpf.
  Consulta a API do viaCEP.
  Exportar registros do banco de dados em forma de txt.


Os padrões utilizados foram:

  Facade: foi criado a class FacadeFuntionalities para servir de faxada para a interface do usuário, ela disponibiliza o acesso a todas funcinalidades do sistem, expondo somente a assinatura dos métodos.Facilita bastante o acesso a todos os métodos por parte da interface do usuário.
  
  Singleton e Factory: no class ConnerctionFactory qeu fornece a conexão com o banco de dados.


Para executar a aplicação, basta abrir o projeto em alguma IDE (Eclipse, IntelliJ) e executar a classe run, a interface do usuário é realizado via linha de comando, no terminal da propria IDE.
