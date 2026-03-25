# Comandos e evidencias TP3

## Comandos locais

```powershell
.\mvnw.cmd spotless:check
.\mvnw.cmd test
.\mvnw.cmd -DskipTests package
.\mvnw.cmd spring-boot:run
```

## Comandos Git

```powershell
git add .
git commit -m "Finaliza TP3"
git push origin dev
git checkout main
git merge dev
git push origin main
```

Se o Git reclamar de `dubious ownership`:

```powershell
git config --global --add safe.directory C:/Users/luizc/luiz_coser_dr4_tp3
```

## Evidencias para capturar no GitHub

1. Runner auto-hospedado executando o job `self-hosted-demo`.
2. Exibicao das variaveis `APP_MODE` e `SUPPORT_EMAIL` no job `ci`.
3. Etapa protegida acessando `PROD_TOKEN` sem expor o valor.
4. Workflow `env-context-demo` exibindo `github.actor`, `runner.os` e `STAGE=test`.
5. Issue criada automaticamente quando `SUPPORT_EMAIL` estiver ausente.
6. Environment `dev` configurado e deploy automatico na branch `dev`.
7. Environment `prod` configurado com aprovacao manual.
8. Deploy de producao aguardando aprovacao e depois concluido.
9. Testes passando com o endpoint `/api/calc/sqrt`.
10. Resposta do endpoint `sqrt` funcionando localmente.

## Guia completo

O roteiro detalhado de entrega, prints e montagem do PDF esta em:

- [PASSO_A_PASSO_TP3.md](/C:/Users/luizc/luiz_coser_dr4_tp3/evidencias/PASSO_A_PASSO_TP3.md)
