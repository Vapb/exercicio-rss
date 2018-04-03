@ -1,38 +0,0 @@
?# Tarefa #1 - RSS 

Esta tarefa envolve os conceitos de UI widgets, AsyncTasks, Custom Adapters, Intents, Permissions, e SharedPreferences. 
Fa�a um clone ou fork deste projeto, siga os passos na ordem sugerida e marque mais abaixo, na sua resposta, quais os passos completados. 
Para entregar o exerc�cio, responda o [formul�rio de entrega](https://docs.google.com/forms/d/e/1FAIpQLSdQksHsbYqlciFS5JHAGlODKsXAY0MUUlA33I_Aur3DkbmMDg/viewform) at� 02/04/2018, �s 23h59.

  1. Teste a aplica��o e certifique-se de que est� tudo funcionando.
  2. Altere a aplica��o RSS de forma que passe a processar o arquivo XML usando a classe `ParserRSS`.
  3. Uma vez processado o XML por meio do parser, � retornado um objeto do tipo `List<ItemRSS>`. 
  4. Use este objeto para popular um `ListView` por meio de um `Adapter` --- o widget deve manter o mesmo id do TextView (`conteudoRSS`).
  5. Inicialmente, use um `ArrayAdapter<ItemRSS>` para confirmar que est� funcionando. 
  6. Altere o `ListView`, para mostrar t�tulo e data para cada item RSS, por meio de um Adapter personalizado, usando o layout em `res/layout/itemlista.xml` como base. Este layout n�o deve ser alterado.
  7. Fa�a com que, ao clicar em um t�tulo, o usu�rio seja direcionado para o navegador. Opcionalmente, pode abrir em uma nova activity com `WebView`.
  8. Modifique a aplica��o para que passe a carregar o endere�o do feed a partir de uma `SharedPreferences` com a chave `rssfeed`. O endere�o padr�o para o feed est� dispon�vel em res/values/strings.xml.
  9. Inclua a possibilidade de alterar a `SharedPreference` (`rssfeed`) incluindo um bot�o na `ActionBar` da aplica��o. Ao clicar no bot�o, abra `PreferenciasActivity`, que deve exibir uma `PreferenceScreen` gerada automaticamente por meio de um `Fragment` que estende a classe `PreferenceFragment`, como visto em sala. Use o arquivo em `res/xml/preferencias.xml` para definir a tela.

---

# Orienta��es

  - Comente o c�digo que voc� desenvolver, explicando o que cada parte faz.
  - Entregue o exerc�cio *mesmo que n�o tenha completado todos os itens* listados. Marque abaixo apenas o que completou.

----

# Status

| Passo | Completou? |
| ------ | ------ |
| 1 | **Sim** |
| 2 | **Sim** |
| 3 | **Sim** |
| 4 | **Sim** |
| 5 | **Sim** |
| 6 | **Sim** |
| 7 | **Sim** |
| 8 | **Sim** |
| 9 | **Sim** |