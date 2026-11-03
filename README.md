# Sistema de Restaurante

Projeto 1 do #ElasTech (PagBank + SoulCode Academy): sistema de console em Java para gerenciar o cardápio, os pedidos, a personalização dos lanches e o fechamento do dia de um restaurante.

🎥 **Vídeo de demonstração:** [assistir no Google Drive](https://drive.google.com/file/d/1JlW9hH-WJzWjuQ6AqD_AJy662Twt9kWy/view?usp=drive_link)

## Funcionalidades

- Registrar pedidos com lanches do cardápio (X-Burger, X-Salada e X-Bacon)
- Retirar ingredientes (queijo, alface, tomate e cebola) sem alterar o preço
- Acrescentar adicionais pagos (cheddar e bacon extra), inclusive repetidos
- Listar os pedidos do dia em formato de comanda
- Modificar pedidos: acrescentar item, remover item ou cancelar o pedido
- Fechamento do dia com o total vendido e as combinações diferentes pedidas
- Tratamento de entradas inválidas sem interromper o sistema

## Tecnologias

- Java
- Maven
- JUnit 5

## Como executar

```bash
git clone https://github.com/httpaline/sistema-restaurante.git
cd sistema-restaurante
```

Abra o projeto no IntelliJ IDEA e execute a classe `Main`, no pacote `app`.

Para rodar os testes:

```bash
mvn test
```

## Estrutura do projeto

```
src/main/java/io/github/httpaline/restaurante/
├── app/        Main, MenuConsole e RestauranteFacade
├── cardapio/   ItemCardapio, Lanche, LancheFactory e os adicionais
└── pedido/     Pedido e GerenciadorPedidos
```

- **app**: interação com a pessoa. O `MenuConsole` é a única classe que lê e exibe dados no console; a `RestauranteFacade` coordena as operações; o `Main` controla o fluxo principal.
- **cardapio**: tudo que é vendido. Os lanches e os adicionais implementam a interface `ItemCardapio`.
- **pedido**: o `Pedido` representa uma comanda; o `GerenciadorPedidos` guarda todos os pedidos e calcula o fechamento.

## Padrões de projeto

- **Decorator**: cada adicional (`ComCheddar`, `ComBaconExtra`) implementa `ItemCardapio`, guarda outro item e soma seu preço e sua descrição aos dele. Como um adicional também é um item, um pode embrulhar o outro. Sem o padrão, seria necessária uma classe para cada combinação; com ele, basta uma classe por adicional.
- **Factory**: a `LancheFactory` centraliza a criação dos lanches do cardápio e devolve uma nova instância a cada chamada, para que a personalização de um pedido não afete outro.
- **Facade**: a `RestauranteFacade` oferece ao `Main` operações simples e esconde as classes de negócio.

## Testes

- `LancheTest`: validação de preço negativo e de nome vazio, retirada de ingredientes alterando a descrição sem alterar o preço, e independência entre lanches criados pela factory.
- `AdicionaisTest`: preço de um lanche com adicional, descrição e preço da combinação completa, adicional repetido e o efeito da ordem dos adicionais.

## Decisões e limitações

- **Ordem dos adicionais:** "X-Bacon + cheddar + bacon extra" e "X-Bacon + bacon extra + cheddar" têm o mesmo preço, mas como o fechamento usa um `HashSet<String>`, contam como duas combinações. Para o negócio, a ordem não deveria importar. Uma solução simples seria ordenar os adicionais antes de aplicá-los; uma mais robusta seria representar a combinação como objeto, com `equals()` e `hashCode()` baseados no lanche e nos adicionais.
- **Escolhas por números:** ingredientes e adicionais são informados por números separados por vírgula, mantendo o mesmo padrão de uso de todos os menus. Os ingredientes usam um `Set` (repetir não faz sentido) e os adicionais uma `List` (repetição e ordem importam).
- **Comanda detalhada:** a descrição dos itens segue o formato exigido, mas a comanda exibe as retiradas e os adicionais em linhas separadas, abaixo do lanche, para facilitar a leitura.
- **Ingredientes como atributos:** os quatro ingredientes removíveis são atributos `boolean`, como pedido no enunciado. Num sistema real, seria mais escalável representá-los como uma coleção.
- **Valores em `double`:** usado por simplicidade neste projeto; em um sistema financeiro real, `BigDecimal` seria mais adequado.

## Autora

Aline Luiza Souza — [GitHub](https://github.com/httpaline)
