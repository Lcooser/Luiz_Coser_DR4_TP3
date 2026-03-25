# Luiz Coser DR4 TP3

Projeto DevCalc em Java com API REST e pipeline GitHub Actions preparado para demonstrar runner auto-hospedado, uso de variaveis e secrets, escopos de ambiente, permissoes do `GITHUB_TOKEN` e deploy separado para `dev` e `prod`.

## Endpoints

- `GET /api/calc/add?a=7&b=5`
- `GET /api/calc/subtract?a=7&b=5`
- `GET /api/calc/multiply?a=7&b=5`
- `GET /api/calc/divide?a=10&b=2`
- `GET /api/calc/sqrt?x=16`

## Como executar localmente

No Linux/macOS:

```bash
./mvnw spotless:check
./mvnw test
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
.\mvnw.cmd spotless:check
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

## TP3

### Etapa 1 Runner auto-hospedado

O workflow [`.github/workflows/luiz_coser_DR4_TP3.yml`](/C:/Users/luizc/luiz_coser_dr4_tp3/.github/workflows/luiz_coser_DR4_TP3.yml) contem o job `self-hosted-demo` com `runs-on: self-hosted`. Ele mostra o ator, o sistema operacional do runner, executa `java -version` e instala `jq` dinamicamente usando `choco`, `apt-get` ou `brew`, conforme o sistema operacional.

### Etapa 2 Variaveis e secrets

O pipeline usa as variaveis `APP_MODE` e `SUPPORT_EMAIL` via contexto `${{ vars.NOME }}`. O segredo `PROD_TOKEN` e consumido em etapas protegidas via `${{ secrets.PROD_TOKEN }}` para simular autenticacao e deploy sensivel.

### Etapa 3 Contextos e escopos

O workflow [`.github/workflows/env-context-demo.yml`](/C:/Users/luizc/luiz_coser_dr4_tp3/.github/workflows/env-context-demo.yml) mostra `github.actor`, `runner.os` e a variavel `STAGE=test`, alem da sobreposicao entre `env` de workflow, job e step.

### Etapa 4 Permissoes e GITHUB_TOKEN

O job `create-config-issue` define `permissions: issues: write` e usa `${{ secrets.GITHUB_TOKEN }}` para abrir automaticamente uma issue caso a variavel `SUPPORT_EMAIL` nao exista.

### Etapa 5 Environments dev e prod

O job `deploy-dev` usa `environment: dev` e dispara em `push` na branch `dev`. O job `deploy-prod` usa `environment: prod` e dispara em `push` na branch `main`, respeitando a protecao configurada no ambiente do GitHub.

Configure no GitHub:

- Repository variables: `APP_MODE`, `SUPPORT_EMAIL`
- Repository secret: `PROD_TOKEN`
- Environment `dev`: variavel `DEV_DEPLOY_URL` e liberacao automatica
- Environment `prod`: segredo `PROD_TOKEN` e regra de aprovacao manual

### Etapa 6 Nova funcionalidade

A API ganhou o endpoint `GET /api/calc/sqrt?x=16`, implementado em [CalculatorService.java](/C:/Users/luizc/luiz_coser_dr4_tp3/src/main/java/br/com/infnet/devcalc/service/CalculatorService.java) e exposto em [CalculatorController.java](/C:/Users/luizc/luiz_coser_dr4_tp3/src/main/java/br/com/infnet/devcalc/controller/CalculatorController.java). Os testes automatizados cobrem o novo endpoint e o caso de erro para numero negativo em [CalculatorControllerTest.java](/C:/Users/luizc/luiz_coser_dr4_tp3/src/test/java/br/com/infnet/devcalc/controller/CalculatorControllerTest.java).

## Reexecutando os workflows

1. Faça push para `dev` para validar CI e deploy em `dev`.
2. Faça push para `main` para validar CI e deploy em `prod`.
3. Use `Run workflow` em `luiz_coser_DR4_TP3` para disparar o job do runner auto-hospedado.
4. Use `Run workflow` em `env-context-demo` para registrar a evidencia dos escopos de ambiente.
