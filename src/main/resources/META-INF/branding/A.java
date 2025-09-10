package META/*
 * TEMA HIGH-TECH PARA A AUTO API
 * Arquivo: smallrye-open-api-ui.css
 */

/* Variáveis de cor para ficar fácil de mudar depois */
:root {
    --cor-fundo: #1a1d24;      /* Cinza escuro de garagem */
    --cor-texto: #f0f0f0;      /* Texto quase branco, fácil de ler */
    --cor-azul-neon: #00a8ff;  /* Azul neon dos faróis do logo */
    --cor-verde-circuito: #39ff14; /* Verde do circuito do logo */
    --cor-borda: #3a3f4b;      /* Cor para as divisões */
}

/* Fundo geral da página */
body {
    background-color: var(--cor-fundo);
    color: var(--cor-texto);
}

/* ----- BARRA DO TOPO (HEADER) ----- */
.swagger-ui .topbar {
    background-color: #111317; /* Um pouco mais escuro para destacar */
    border-bottom: 1px solid var(--cor-azul-neon);
    padding: 10px 20px;
}

/* A Mágica! Trocando o logo do Swagger pelo nosso */
.swagger-ui .topbar .link img {
    content: url('/images/auto-api-logo.png'); /* Caminho para o nosso logo! */
    width: 180px;  /* Ajuste o tamanho como preferir */
    height: auto;
}

/* ----- ESTILO DOS "PODERES" DA API (ENDPOINTS) ----- */

/* A caixinha de cada grupo de poderes (GET, POST, etc) */
.swagger-ui .opblock {
    border-color: var(--cor-borda);
    background: #22262e;
}

.swagger-ui .opblock-summary-method {
    text-shadow: 0 0 5px rgba(0, 0, 0, 0.5); /* Sombra para destacar o texto */
}

/* Colorindo cada método para ficar fácil de ver */
.swagger-ui .opblock.opblock-get { border-left: 5px solid var(--cor-azul-neon); }
.swagger-ui .opblock.opblock-post { border-left: 5px solid var(--cor-verde-circuito); }
.swagger-ui .opblock.opblock-put { border-left: 5px solid #ffa500; } /* Laranja */
.swagger-ui .opblock.opblock-delete { border-left: 5px solid #ff4757; } /* Vermelho */

/* Título de cada grupo (ex: "Carro Controller") */
.swagger-ui .opblock-tag a {
    color: var(--cor-texto);
    font-size: 24px;
}

/* Botão "Execute" para testar a API */
.swagger-ui .btn.execute {
    background-color: var(--cor-verde-circuito);
    color: #111;
    border: none;
    font-weight: bold;
    transition: all 0.2s ease-in-out;
}

.swagger-ui .btn.execute:hover {
    background-color: #fff;
    box-shadow: 0 0 10px var(--cor-verde-circuito);
}