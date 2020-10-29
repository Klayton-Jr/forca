# forca
Projeto final da disciplina sistemas distribuídos


Definição jogo forca – trabalho sd1 (clone de gartic)

Requisitos funcionais:
    • O sistema deve possibilitar usuários criarem salas de jogos 
    • O sistema deve ter uma opção para "descoberta" de salas de jogos utilizando comandos de broadcast para a rede local 
    • Cada jogador terá um contador que irá ser incrementado se errar uma letra ou errar o chute da palavra
    • O mestre da rodada tem o poder de kickar um jogador antes de começar a partida

Requisitos não funcionais:
    • O sistema deve poder ser executado em várias máquinas desde que estejam em uma mesma rede 
    • O sistema deve possibilitar que múltiplas instâncias (dentro da mesma rede) consigam conectar em uma mesma sala disponibilizada 
    • O sistema deve utilizar algum modelo de comunicação distribuída apresentado na disciplina (Sockets, Objetos Distribuídos, Comunicação Assíncrona, P2P, Webservers, etc) 
    • O sistema utilizará o(s) modelo(s) de comunicação:
        ◦ Socket TCP***
            ▪ Envio das palavras/letras
        ◦ Comunicação assíncrona
            ▪ Tempo da disponibilidade da jogada
        ◦ Webservices***
            ▪ Envio das tentativas e letras da palavra
    • Cada jogador terá o seu boneco da forca representando seu contador
    • Cada sala terá uma única thread, contendo o mestre, os jogadores e a palavra escolhida pelo mestre
    • A palavra escolhida pelo mestre estará salva na memória até definir o fim do jogo
    • O servidor terá um limite de 5 threads
    • As salas podem ter até 5 participantes, incluindo o mestre

Regras de negócio:
    • O sistema deve possibilitar que o dono da sala inicie a rodada (mestre da rodada)
    • Quando o jogador chegar com a 5 com o contador, ele perde
    • O intervalo para cada jogador poder efetuar uma jogada é de 90 segundos
    • Quando uma partida é iniciada, não pode entrar novos jogadores
    • O mestre da rodada (criador da sala) pode definir a quantidade de jogadores, sendo que o máximo é 2 (o mestre e outro jogador)
    • O mestre da rodada pode definir se a sala é privada (entrada somente com senha) ou pública
    • A quantidade de jogadores só pode ser definida ou alterada quando a rodada ainda não começou
    • Quando é listado as salas, mostrar se está em andamento o jogo ou pronto para entrar
    • Caso todos os jogadores da sala estourem o contador com 5, será considerado empate
    • Cada jogador, incluindo o mestre, poderá participar de somente uma sala









Personagens:
    • Jogadores normais → tentam adivinhar a palavra
    • Mestre da rodada (somente 1) → define a palavra pra forca

Fluxo:
	1. O jogador que criou a sala, mestre da rodada, escolhe a palavra
	2. O mestre da rodada inicia a partida quando todos estão prontos
		a. Se houver um jogador que não confirme estar pronto o jogo não inicia
			i. Os jogadores podem aguardar
			ii. O mestre da rodada tem a opção de kickar esse jogador
	3. O jogador do turno informa uma letra
		a. Se a letra existir na palavra, ela é revelada 
		b. Se a letra não existir, o turno do jogador se encerra 
	4. Após revelar a letra, o jogador deve:
		a. Escolher se deseja informar a palavra (caso ele saiba ou deseje chutar) 
			i. Se errar, o turno é encerrado 
			ii. Se acertar, ele ganha o jogo 
		b. Escolher se deseja informar outra letra. Neste caso, retorna para o primeiro passo. 






*** Dúvida, não tenho certeza se é a melhor forma
