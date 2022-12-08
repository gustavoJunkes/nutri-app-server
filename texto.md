app/site que calcula nutrição


ele deve me mostrar o seguinte:

- Gasto calórico
    - Quanto que gasto com meu cardio (formula minuto/km)
    - Quanto de cardio preciso fazer para queimar x
    - Gasto calórico basal (usando fórmula, apenas o necessário para viver)
    - Gasto calórico total dia
    - Quanto de físico devo fazer por dia pra queimar tanto
- Nutrição
    - Baseado em quanto gasto, mostrar quanto deveria ingerir de calorias totais (para crescimento, e deveria também ser configurável)
    - Quanto comer de gordura, carboidrato e proteína por dia
    - Como separar isso em refeições.
        - Deve ter uma lista de alimentos que o usuário aceita consumir durante o dia, e deve a partir disso montar um cardápio para as refeições do dia.


Deve ser uma api, calcula os valores e retorna para quem quer que tenha feito a chamada (busco fazer com um app).

Para começar:
- Entender como esses cálculos funcionam.
- Análise de sistemas, planejar a arquitetura do sistema.
- Criar api, inicialmente sem endpoints, apenas com os cálculos retornando um resultado.
- Criar interfaxe, consumindo a api (flutter/electron/web).

