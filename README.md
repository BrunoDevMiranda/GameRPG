# API Rest de RPG em Java SpringBoot

## Visão Geral
Bem-vindo à nossa API Rest de RPG baseada em Java SpringBoot! Este jogo épico segue as tradições do Advanced Dungeons & Dragons (AD&D) e permite que você participe de batalhas em turnos emocionantes. Escolha seu personagem favorito, herói ou monstro, e lute até que um deles seja derrotado. Este README fornecerá uma visão geral do jogo e da API.

## Requisitos
- Java SpringBoot
- Banco de Dados Postgres
- Maven

## Iniciando o Jogo
1. Clone este repositório.
2. Configure um banco de dados Postgres e atualize as configurações de conexão no arquivo `application.properties`.
3. Execute o aplicativo SpringBoot.
4. Acesse a API para criar, atualizar, ler e excluir personagens.
5. Inicie uma batalha usando os endpoints relevantes.

## Personagens
Existem vários heróis e monstros que você pode escolher para a batalha. Cada um deles tem atributos exclusivos, incluindo Vida, Força, Defesa, Agilidade e os detalhes dos dados que eles jogam durante a batalha.

| Personagem  | Vida | Força | Defesa | Agilidade | Quantidade de Dados | Faces do Dado |
|-------------|------|-------|--------|-----------|----------------------|----------------|
| Guerreiro   | 20   | 7     | 5      | 6         | 1                    | 12             |
| Bárbaro     | 21   | 10    | 2      | 5         | 2                    | 8              |
| Cavaleiro   | 26   | 6     | 8      | 3         | 2                    | 6              |
| Orc         | 42   | 7     | 1      | 2         | 3                    | 4              |
| Gigante     | 34   | 10    | 4      | 4         | 2                    | 6              |
| Lobisomen   | 34   | 7     | 4      | 7         | 2                    | 4              |

## Fluxo do Jogo
1. **Iniciativa**: Determine quem começa a batalha jogando um dado de 20 faces.
2. **Turno**: Cada turno consiste em um ataque e uma fase de defesa.
   - **Ataque**: Jogue um dado de 12 faces, some à Força e à Agilidade.
   - **Defesa**: Jogue um dado de 12 faces, some à Defesa e à Agilidade.
3. **Dano**: Se o ataque for maior que a defesa, calcule o dano.
4. **Pontos de Vida**: Subtraia o dano dos Pontos de Vida do personagem.
5. **Fim do Turno**: Se um personagem tiver 0 ou menos Pontos de Vida, o jogo termina.
6. **Histórico**: Todos os detalhes das batalhas são registrados em um log.

## Endpoints da API
- `/personagens`: CRUD para gerenciar personagens.
- `/batalha/ataque`: Inicia um ataque.
- `/batalha/defesa`: Inicia uma defesa.
- `/batalha/calculo-dano`: Calcula o dano.
- `/historico`: Obtém o histórico de batalhas.

## Contribuindo
Sinta-se à vontade para contribuir para este projeto! Crie uma branch, faça suas alterações e envie um Pull Request.

## Autores
- [Seu Nome] - [Seu Email]

## Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

## Agradecimentos
Agradecemos a todos que contribuíram para tornar este jogo e API possíveis!

