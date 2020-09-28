/***  
===================================================================================================================================
==================================================================================================================================
                  
            TABELAS/VIEWS/PROCS DO SISTEMA  
-----------------------------------------------------------------------------------------------------------  
-----------------------------------------------------------------------------------------------------------    

TALBLES:  TB_ALUNO			||        VIEWS:						||  
      TB_ATRIBTURMA			||            TABELAALUNO				||  
      TB_ATRIBUICAO			||            TABELAATRIBTURMA          ||  
      TB_AVALIACAO			||            TABELAATRIBUICAO          ||  
      TB_CURSO				||            TABELADISCIPLINA          ||  
      TB_DISCIPLINA			||            TABELAMENSAGEM            ||  
      TB_EVENTO				||            TABELASUGESTAO            ||  
      TB_FOTO				||            TABELATURMA				||  
      TB_LOGIN				||            VW_ATRIBUICAO				||    
      TB_MENSAGEM			||            VW_GRIDNOTAS				||  
      TB_NIVELHIERARQUICO   ||            VW_GRIDNOTASGERAL         ||      
      TB_NOTA				||            VW_LISTAUSUAIOS           ||          
      TB_PROFESSOR			||            VW_PROFESSORES            ||  
      TB_SUGESTAO			||            VW_QUANTIDADEALUNOS       ||  
      TB_TURMA				||            VW_RANKING				||  
      TB_HORARIO			||										||  
      TB_ATRIBALUNO			||										||  
							||										||  
-----------------------------------------------------------------------------------------------------------  
-----------------------------------------------------------------------------------------------------------    
PRODEDURES:  
            PROC_APROVA_ALUNOS        
      -- DOIS PARÂMETROS: TURMA, E RM DOS ALUNOS REPROVADOS  
        
            PROC_BUSCAMENSAGEM        
      -- DOIS PARÂMETROS: TIPO DE BUSCA, RM/ID_PROFESSOR  
        
            PROC_CRUDALUNO          
      -- DEZ PARÂMETOS:  COMANDO(I - INSERT, S - SELECT), RM, NOME, SOBRENOME,CPF,   
                DT_NASCIMENTO,EMAIL, DT_EXCLUSÃO, CAMINHO FOTO, ID_TURMA  
        
            PROC_CRUDNOTA          
      -- SEIS PARÂMETROS:  COMANDO(I - INSERT, S - SELECT), RM,ATRIB_TURMA, AVALIACAO,TRIMESTRE,NOTA  
        
            RETORNAAVALIACOESMATERIA    
      -- DOIS PARÂMETROS: RM E TRIMESTRE  
              
            RETORNAMEDIAPORMATERIA      
      -- DOIS PARÂMETROS: RM E TRIMESTRE  

USE MASTER USE PORTALZINHO_V5  

SELECT * FROM TB_LOGIN
INSERT INTO TB_EVENTO
===================================================================================================================================
===================================================================================================================================
***/ 

USE MASTER; 

GO 

DROP DATABASE PORTALZINHO_V7; 

GO 

CREATE DATABASE PORTALZINHO_V7; 

GO 

USE PORTALZINHO_V7; 

GO 

/*  
========================================================================================================
========================================================================================================
                CRIAÇÃO DE TABELAS - REGRA GERAL  
========================================================================================================
========================================================================================================
*/ 
GO 

CREATE TABLE [DBO].[TB_EVENTO] 
  ( 
     [ID_EVENTO]        INT PRIMARY KEY IDENTITY(1, 1), 
     [TITULO_EVENTO]    VARCHAR(20), 
     [DESCRICAO_EVENTO] VARCHAR(500), 
     [DATA_EVENTO]      DATETIME, 
     [ANOLETIVO_EVENTO] DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR 
     ( 
     GETDATE()), 103), 103), 
     [STATUS_EVENTO]    CHAR(1) DEFAULT '1' 
  ); 

GO 

CREATE TABLE [DBO].[TB_NIVELHIERARQUICO] 
  ( 
     [ID_NH]   INT PRIMARY KEY IDENTITY(1, 1) NOT NULL, 
     [NOME_NH] VARCHAR(20) 
  ); 

GO 

INSERT INTO TB_NIVELHIERARQUICO 
            (NOME_NH) 
VALUES      ('ADMINISTRADXR'); 

INSERT INTO TB_NIVELHIERARQUICO 
            (NOME_NH) 
VALUES      ('PROFESSXR'); 

INSERT INTO TB_NIVELHIERARQUICO 
            (NOME_NH) 
VALUES      ('ALUNX'); 

GO 

CREATE TABLE [DBO].[TB_LOGIN] 
  ( 
     [ID_LOGIN]   INT PRIMARY KEY IDENTITY(1, 1) NOT NULL, 
     [USERLOGIN]  VARCHAR(20) NOT NULL UNIQUE, 
     [SENHALOGIN] VARCHAR(20) NOT NULL, 
     [ID_NH]      INT NOT NULL 
     FOREIGN KEY ([ID_NH]) REFERENCES [TB_NIVELHIERARQUICO]([ID_NH]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_CURSO] 
  ( 
     [ID_CURSO]   INT PRIMARY KEY IDENTITY(1, 1), 
     [NOMECURSO]  VARCHAR(50) NOT NULL, 
     [ABREVCURSO] VARCHAR(5) NOT NULL, 
     [AICURSO]    CHAR(1) DEFAULT '1' 
  /*,[DTEXCCURSO]  DATE ??????? */ 
  ); 

GO 

CREATE TABLE [DBO].[TB_PROFESSOR] 
  ( 
     [ID_PROF]    INT PRIMARY KEY, 
     [NOME_PROF]  VARCHAR(60), 
     [SOBRENPROF] VARCHAR(60), 
     [CPFPROF]    CHAR(14), 
     [EMAILPROF]  VARCHAR(50), 
     [STATUSPROF] CHAR(1) DEFAULT '1', 
     [DTCADPROF]  DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE()) 
     , 103), 103), 
     [ID_LOGIN]   INT 
     FOREIGN KEY ([ID_LOGIN]) REFERENCES [TB_LOGIN]([ID_LOGIN]) 
  ); 

--SELECT * FROM TB_PROFESSOR;  
GO 

CREATE TABLE [DBO].[TB_TURMA] 
  ( 
     [ID_TURMA]        INT PRIMARY KEY IDENTITY(1, 1), 
     [ID_CURSO]        INT, 
     [ANO_TURMA]       CHAR(1), 
     [NOME_TURMA]      CHAR(1), 
     [PERIODO_TURMA]   CHAR(1), 
     [ANOLETIVO_TURMA] DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE()), 103), 103), 
     [COMPLETO_TURMA]  CHAR(8), 
     [STATUS_TURMA]    CHAR(1) DEFAULT '1' 
     FOREIGN KEY ([ID_CURSO]) REFERENCES [TB_CURSO]([ID_CURSO]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_HORARIO] 
  ( 
     [ID_HORARIO]      [INT] IDENTITY(1, 1) NOT NULL, 
     [ID_TURMA]        [INT] NULL, 
     [CAMINHO_HORARIO] [VARCHAR](1000) NULL, 
     [STATUS_HORARIO]  [CHAR](1) DEFAULT ('1') 
     FOREIGN KEY ([ID_TURMA]) REFERENCES [DBO].[TB_TURMA]([ID_TURMA]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_DISCIPLINA] 
  ( 
     [ID_DISC]     INT PRIMARY KEY IDENTITY(1, 1), 
     [ANO]         DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE()), 103) 
     , 103), 
     [NOME_DISC]   VARCHAR(50), 
     [SERIE]       INT, 
     [ID_CURSO]    INT, 
     [STATUS_DISC] CHAR(1) DEFAULT '1' 
  ); 

GO 

CREATE TABLE [DBO].[TB_ATRIBUICAO] 
  ( 
     [ID_ATRIB]    INT PRIMARY KEY IDENTITY(1, 1), 
     [ID_PROF]     INT NOT NULL, 
     [ID_DISC]     INT NOT NULL, 
     [NOME]        VARCHAR(100) NULL, 
     [ANO]         DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, GETDATE() 
     ), 103), 
     [STATUSATRIB] CHAR(1) DEFAULT '1' 
     FOREIGN KEY ([ID_PROF]) REFERENCES [TB_PROFESSOR]([ID_PROF]), 
     FOREIGN KEY ([ID_DISC]) REFERENCES [TB_DISCIPLINA]([ID_DISC]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_AVALIACAO] 
  ( 
     [ID_AVAL]     INT PRIMARY KEY IDENTITY(1, 1), 
     [ANO]         DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE()), 103) 
     , 103), 
     [DESC_AVAL]   VARCHAR(20) NOT NULL, 
     [PESO_AVAL]   NUMERIC(4, 2) NOT NULL, 
     [STATUS_AVAL] CHAR(1) DEFAULT '1' 
  ); 

GO 

CREATE TABLE [DBO].[TB_ALUNO] 
  ( 
     [RM_ALUNO]    INT PRIMARY KEY, 
     [NOMEALUNO]   VARCHAR(60), 
     [SOBRENALUNO] VARCHAR(60), 
     [CPFALUNO]    CHAR(14), 
     [NASCALUNO]   DATE, 
     [EMAILALUNO]  VARCHAR(50), 
     [ANOCADALUNO] DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE( 
     ) ), 103), 103), 
     [STATUSALUNO] CHAR(1) DEFAULT '1', 
     [DTEXCALUNO]  DATETIME, 
     [CAMINHOFOTO] VARCHAR(500), 
     [ID_LOGIN]    INT 
     /* ERRO DE COMMIT É NÚMERO MAIOR DE CARACTERES QUE O PERMITIDO */ 
     FOREIGN KEY ([ID_LOGIN]) REFERENCES [TB_LOGIN]([ID_LOGIN]) 
  ); 

INSERT INTO TB_LOGIN 
            (USERLOGIN, 
             SENHALOGIN, 
             ID_NH) 
VALUES      ('ADM', 
             '7', 
             3); 

GO 

CREATE TABLE [DBO].[TB_MENSAGEM] 
  ( 
     [ID_MENSAGEM]         INT PRIMARY KEY IDENTITY(1, 1), 
     [ENVMSG_ALUNO]        INTEGER, 
     [ENVMSG_PROFESSOR]    INTEGER, 
     [RECEBEMSG_ALUNO]     INTEGER, 
     [RECEBEMSG_TURMA]     INTEGER, 
     [RECEBEMSG_PROFESSOR] INTEGER, 
     [DTMSG]               DATETIME DEFAULT GETDATE(), 
     [ASSMSG]              VARCHAR(50), 
     [DESCMSG]             VARCHAR(1000), 
     [STATUSMSG]           CHAR(1) DEFAULT '0' 
  ); 

GO 

CREATE TABLE [DBO].[TB_SUGESTAO] 
  ( 
     [ID_SUGESTAO] INT PRIMARY KEY IDENTITY(1, 1), 
     [FKSGT]       INTEGER, 
     [DTENVSGT]    DATETIME DEFAULT GETDATE(), 
     [MSGSGT]      VARCHAR(1000) NOT NULL 
     FOREIGN KEY ([FKSGT]) REFERENCES [TB_ALUNO]([RM_ALUNO]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_ATRIBTURMA] 
  ( 
     [ID_ATRIBTURMA]   INT PRIMARY KEY IDENTITY(1, 1), 
     [ID_ATRIB]        INT NOT NULL, 
     [ID_TURMA]        INT NOT NULL, 
     [STATUS_ATRTURMA] CHAR(1) DEFAULT '1' 
     FOREIGN KEY ([ID_ATRIB]) REFERENCES [TB_ATRIBUICAO]([ID_ATRIB]), 
     FOREIGN KEY ([ID_TURMA]) REFERENCES [TB_TURMA]([ID_TURMA]) 
  ); 

GO 

CREATE TABLE [DBO].[TB_ATRIBALUNO] 
  ( 
     [ID_ATRIBALUNO]         INT PRIMARY KEY IDENTITY(1, 1), 
     [RM_ALUNO]              INT, 
     [ID_TURMA]              INT, 
     [ANO_ATRIB]             VARCHAR(4) DEFAULT CAST(YEAR(GETDATE()) AS VARCHAR( 
     4)), 
     [STATUS_RELACIONAMENTO] CHAR(1) DEFAULT '1' 
     FOREIGN KEY ([RM_ALUNO]) REFERENCES [TB_ALUNO]([RM_ALUNO]), 
     FOREIGN KEY ([ID_TURMA]) REFERENCES [TB_TURMA]([ID_TURMA]), 
  ); 

GO 

CREATE TABLE [DBO].[TB_NOTA] 
  ( 
     [ID_NOTA]     INT PRIMARY KEY IDENTITY(1, 1), 
     [ID_ALUNO]    INT NOT NULL, 
     [ID_ATRIBT]   INT NOT NULL, 
     [ID_AVAL]     INT NOT NULL, 
     [TRIM]        INT NOT NULL, 
     [ANOLETIVO]   DATETIME DEFAULT CONVERT(DATETIME, CONVERT(VARCHAR, YEAR( 
     GETDATE()) 
     , 103), 103), 
     [NOTA]        NUMERIC(4, 2) NOT NULL, 
     [STATUS_NOTA] CHAR(1) DEFAULT '1' 
     FOREIGN KEY ([ID_ALUNO]) REFERENCES [TB_ATRIBALUNO]([ID_ATRIBALUNO]), 
     FOREIGN KEY ([ID_ATRIBT]) REFERENCES [TB_ATRIBTURMA]([ID_ATRIBTURMA]), 
     FOREIGN KEY ([ID_AVAL]) REFERENCES [TB_AVALIACAO]([ID_AVAL]) 
  ); 

GO 

/*  
========================================================================================================
========================================================================================================
                SISTEMAS DE VIEW PARA ALUNOS  
========================================================================================================
========================================================================================================
*/ 
CREATE VIEW VW_QUANTIDADEALUNOS 
AS 
  SELECT TB_ATRIBALUNO.ANO_ATRIB       AS ANO, 
         TB_ATRIBALUNO.ID_TURMA        AS 'CÓDIGO DA TURMA', 
         TB_TURMA.COMPLETO_TURMA       AS TURMA, 
         COUNT(TB_ATRIBALUNO.RM_ALUNO) AS QUANTIDADE 
  FROM   TB_ATRIBALUNO 
         INNER JOIN TB_TURMA 
                 ON TB_TURMA.ID_TURMA = TB_ATRIBALUNO.ID_TURMA 
         INNER JOIN TB_ALUNO 
                 ON TB_ALUNO.RM_ALUNO = TB_ATRIBALUNO.RM_ALUNO 
  GROUP  BY TB_ATRIBALUNO.ID_TURMA, 
            TB_TURMA.COMPLETO_TURMA, 
            TB_ATRIBALUNO.ANO_ATRIB 

GO 

CREATE PROC PROC_CRUDALUNO @TP_OPERACAO CHAR(1), 
                           @RM_ALUNO    INT, 
                           @NOMEALUNO   VARCHAR(60), 
                           @SOBRENALUNO VARCHAR(60), 
                           @CPFALUNO    CHAR(14), 
                           @NASCALUNO   DATE, 
						   @DT_EXCLUSAO DATE,
                           @EMAILALUNO  VARCHAR(50),   
                           @CAMINHOFOTO VARCHAR(500), 
                           @ID_TURMA    INT 
AS 
  BEGIN 
      IF( @TP_OPERACAO = 'I' ) 
        BEGIN 
            INSERT INTO TB_ALUNO 
                        (RM_ALUNO, 
                         NOMEALUNO, 
                         SOBRENALUNO, 
                         CPFALUNO, 
                         NASCALUNO, 
                         EMAILALUNO,  
                         CAMINHOFOTO) 
            VALUES      ( @RM_ALUNO, 
                          @NOMEALUNO, 
                          @SOBRENALUNO, 
                          @CPFALUNO, 
                          @NASCALUNO, 
                          @EMAILALUNO, 
                          @CAMINHOFOTO ); 

            INSERT INTO TB_ATRIBALUNO 
                        (ID_TURMA, 
                         RM_ALUNO) 
            VALUES      (@ID_TURMA, 
                         @RM_ALUNO); 
        END 

      IF( @TP_OPERACAO = 'U' ) 
        BEGIN 
            UPDATE TB_ALUNO 
            SET    NOMEALUNO = @NOMEALUNO, 
                   SOBRENALUNO = @SOBRENALUNO, 
                   CPFALUNO = @CPFALUNO, 
                   NASCALUNO = @NASCALUNO, 
                   EMAILALUNO = @EMAILALUNO,  
                   CAMINHOFOTO = @CAMINHOFOTO 
            WHERE  RM_ALUNO = @RM_ALUNO 

            UPDATE TB_ATRIBALUNO 
            SET    STATUS_RELACIONAMENTO = 0 
            WHERE  RM_ALUNO = @RM_ALUNO 

            INSERT INTO TB_ATRIBALUNO 
                        (ID_TURMA, 
                         RM_ALUNO) 
            VALUES      (@ID_TURMA, 
                         @RM_ALUNO); 
        END 

      IF( @TP_OPERACAO = 'S' ) 
        BEGIN 
            SELECT * 
            FROM   TB_ALUNO 
            WHERE  RM_ALUNO = @RM_ALUNO 
        END 

      IF( @TP_OPERACAO = 'D' ) 
        BEGIN 
            UPDATE TB_ALUNO 
            SET    STATUSALUNO = 0, DTEXCALUNO = CAST(GETDATE() AS DATE)
            WHERE  RM_ALUNO = @RM_ALUNO 
        END 

      IF( @TP_OPERACAO = 'R' ) 
        BEGIN 
            UPDATE TB_ALUNO 
            SET    STATUSALUNO = 1 
            WHERE  RM_ALUNO = @RM_ALUNO 
        END 
  END 

GO 

/*  
========================================================================================================
========================================================================================================
                SISTEMAS DE VIEW PARA PROFESSORES  
========================================================================================================
========================================================================================================
*/ 
CREATE VIEW VW_ATRIBUICAO 
AS 
  SELECT DISTINCT P.ID_PROF    AS CODIGO, 
                  P.NOME_PROF  AS PROFESSOR, 
                  ATT.ID_ATRIB AS 'CODIGOATRIBUICAO', 
                  AT.NOME      AS 'ATRIBUIÇÃO', 
                  D.ID_DISC    AS 'CÓDIGO DA DISCIPLINA', 
                  CASE 
                    WHEN T.COMPLETO_TURMA IS NULL THEN 'SEM TURMA' 
                    ELSE T.COMPLETO_TURMA 
                  END          AS TURMA, 
                  T.ID_TURMA   AS 'CÓDIGO DA TURMA' 
  FROM   TB_ATRIBTURMA ATT 
         LEFT JOIN TB_ATRIBUICAO AT 
                ON ATT.ID_ATRIB = AT.ID_ATRIB 
         LEFT JOIN TB_PROFESSOR P 
                ON AT.ID_PROF = P.ID_PROF 
         LEFT JOIN TB_DISCIPLINA D 
                ON AT.ID_DISC = D.ID_DISC 
         LEFT JOIN TB_TURMA T 
                ON ATT.ID_TURMA = T.ID_TURMA; 

GO 

CREATE VIEW VW_PROFESSORES 
AS 
  SELECT P.ID_PROF   AS CODIGO, 
         P.NOME_PROF AS PROFESSOR, 
         CASE 
           WHEN D.NOME_DISC IS NULL THEN 'SEM MATÉRIA' 
           ELSE CAST(D.NOME_DISC AS VARCHAR) + ' - ' 
                + CAST(D.SERIE AS VARCHAR) + 'º ANO' 
         END         AS DISCIPLINA, 
         CASE 
           WHEN P.STATUSPROF = '2' 
                 OR D.NOME_DISC IS NULL THEN 'SUSPENSO' 
           WHEN P.STATUSPROF = '1' THEN 'ATIVO' 
           WHEN P.STATUSPROF IS NULL THEN 'FORA DO SISTEMA' 
         END         AS 'STATUS DO PROFESSOR', 
         CASE 
           WHEN D.NOME_DISC IS NULL THEN 'SEM MATÉRIAS' 
           ELSE D.NOME_DISC 
         END         AS MATÉRIA, 
         CASE 
           WHEN T.NOME_TURMA IS NULL THEN 'SEM TURMA' 
           ELSE T.COMPLETO_TURMA 
         END         AS TURMA 
  FROM   TB_PROFESSOR P 
         LEFT JOIN TB_ATRIBUICAO AT 
                ON AT.ID_PROF = P.ID_PROF 
         LEFT JOIN TB_DISCIPLINA D 
                ON D.ID_DISC = AT.ID_DISC 
         LEFT JOIN TB_ATRIBTURMA ATT 
                ON ATT.ID_ATRIB = AT.ID_ATRIB 
         LEFT JOIN TB_TURMA T 
                ON T.ID_TURMA = ATT.ID_TURMA; 

GO 

/*  
========================================================================================================
========================================================================================================
                SISTEMAS DE TRIGGER PARA TURMAS  
========================================================================================================
========================================================================================================
*/ 
CREATE TRIGGER TRGGNOMETURMA 
ON TB_TURMA 
AFTER INSERT 
AS 
  BEGIN 
      DECLARE @ID_TURMA        INT, 
              @ID_CURSO        INT, 
              @ANO_TURMA       CHAR(1), 
              @NOME_TURMA      CHAR(1), 
              @PERIODO_TURMA   CHAR(1), 
              @ANOLETIVO_TURMA CHAR(4) 

      SELECT @ID_TURMA = ID_TURMA, 
             @ID_CURSO = ID_CURSO, 
             @ANO_TURMA = ANO_TURMA, 
             @NOME_TURMA = NOME_TURMA, 
             @PERIODO_TURMA = PERIODO_TURMA, 
             @ANOLETIVO_TURMA = ANOLETIVO_TURMA 
      FROM   INSERTED; 

      UPDATE TB_TURMA 
      SET    COMPLETO_TURMA = CAST((SELECT DISTINCT ABREVCURSO 
                                    FROM   TB_CURSO 
                                    WHERE  ID_CURSO = @ID_CURSO) 
                                   + @ANO_TURMA + UPPER(@NOME_TURMA) 
                                   + @PERIODO_TURMA AS VARCHAR) 
      WHERE  ID_TURMA = @ID_TURMA; 
  END; 

GO 

/*  
========================================================================================================
========================================================================================================
                SISTEMAS DE TRIGGER PARA LOGIN  
========================================================================================================
========================================================================================================
*/ 
CREATE TRIGGER TRGGUSERLOGIN_ALUNO 
ON TB_ALUNO 
AFTER INSERT 
AS 
  BEGIN 
      DECLARE @NOMEALUNO VARCHAR(60), 
              @RM_ALUNO  INT 

      SELECT @NOMEALUNO = NOMEALUNO, 
             @RM_ALUNO = RM_ALUNO 
      FROM   INSERTED; 

      INSERT INTO TB_LOGIN 
                  (USERLOGIN, 
                   SENHALOGIN, 
                   ID_NH) 
      VALUES      ( (SELECT LOWER(NOMEALUNO) + '_' 
                            + CAST(RM_ALUNO AS VARCHAR) 
                     FROM   TB_ALUNO 
                     WHERE  RM_ALUNO = @RM_ALUNO 
                            AND NOMEALUNO = @NOMEALUNO), 
                    '123', 
                    1 ); 

      UPDATE TB_ALUNO 
      SET    ID_LOGIN = (SELECT MAX(ID_LOGIN) 
                         FROM   TB_LOGIN) 
      WHERE  RM_ALUNO = @RM_ALUNO; 
  END; 

GO 

CREATE TRIGGER TRGGUSERLOGIN_PROFESSOR 
ON TB_PROFESSOR 
AFTER INSERT 
AS 
  BEGIN 
      DECLARE @NOME_PROF VARCHAR(60), 
              @ID_PROF   INT 

      SELECT @NOME_PROF = NOME_PROF, 
             @ID_PROF = ID_PROF 
      FROM   INSERTED; 

      INSERT INTO TB_LOGIN 
                  (USERLOGIN, 
                   SENHALOGIN, 
                   ID_NH) 
      VALUES      ( (SELECT LOWER(NOME_PROF) + '_' 
                            + CAST(ID_PROF AS VARCHAR) 
                     FROM   TB_PROFESSOR 
                     WHERE  ID_PROF = @ID_PROF 
                            AND NOME_PROF = @NOME_PROF), 
                    '123', 
                    2 ); 

      UPDATE TB_PROFESSOR 
      SET    ID_LOGIN = (SELECT MAX(ID_LOGIN) 
                         FROM   TB_LOGIN) 
      WHERE  ID_PROF = @ID_PROF; 
  END; 

GO 

CREATE VIEW VW_LISTAUSUAIOS 
AS 
  SELECT 
  /*CASE   
    WHEN NH.ID_NH = 1 THEN 'ALUNO'  
    WHEN NH.ID_NH = 2 THEN 'PROFESSOR'  
  END  
  AS PESSOA, */ L.USERLOGIN  AS USUARIO, 
                  L.SENHALOGIN AS SENHA, 
                  CASE 
                    WHEN NH.ID_NH = 1 THEN A.RM_ALUNO 
                    WHEN NH.ID_NH = 2 THEN P.ID_PROF 
                  END          AS CÓDIGO, 
                  CASE 
                    WHEN NH.ID_NH = 1 THEN 3 
                    WHEN NH.ID_NH = 2 THEN 2 
                    WHEN NH.ID_NH = 3 THEN 1 
                  END          AS NH 
  FROM   TB_LOGIN L 
         INNER JOIN TB_NIVELHIERARQUICO NH 
                 ON NH.ID_NH = L.ID_NH 
         LEFT JOIN TB_PROFESSOR P 
                ON P.ID_LOGIN = L.ID_LOGIN 
         LEFT JOIN TB_ALUNO A 
                ON A.ID_LOGIN = L.ID_LOGIN; 

GO 

/*  
========================================================================================================
========================================================================================================
                GRIDS E PROCEDURES DE NOTA  
========================================================================================================
========================================================================================================
*/ 

CREATE VIEW VW_GRIDNOTASGERAL_HISTORICO
AS
  SELECT YEAR([TB_NOTA].ANOLETIVO)                 AS ANO, 
         STR([TB_NOTA].TRIM)                       AS TRIM, 
         ( STR([TB_NOTA].TRIM) + 'º TRIMESTRE' )   AS TRIMESTRE, 
         [TB_ATRIBTURMA].ID_ATRIBTURMA             AS CODIGO, 
		 [TB_TURMA].ID_TURMA					   AS CODIGO_TURMA,
         [TB_TURMA].COMPLETO_TURMA                 AS TURMA, 
		 [TB_PROFESSOR].ID_PROF					   AS PROFESSOR,
		 [TB_DISCIPLINA].ID_DISC				   AS CODIGO_DISCIPLINA,
		 [TB_DISCIPLINA].NOME_DISC				   AS DISCIPLINA_NOME,
         ( [TB_PROFESSOR].NOME_PROF + ' - ' 
           + [TB_DISCIPLINA].NOME_DISC )           AS MATÉRIA, 
         [TB_ALUNO].RM_ALUNO                       AS RM, 
         [TB_ALUNO].NOMEALUNO + ' ' 
         + [TB_ALUNO].SOBRENALUNO                  AS ALUNO, 
		 [TB_AVALIACAO].ID_AVAL	                   AS id_AVALIACAO, 
         [TB_AVALIACAO].DESC_AVAL                  AS AVALIAÇÃO, 
         [TB_AVALIACAO].PESO_AVAL                  AS PESO, 
         [TB_NOTA].NOTA                            AS NOTA_NORMAL, 
         [TB_NOTA].NOTA * [TB_AVALIACAO].PESO_AVAL AS NOTA, 
         [TB_NOTA].NOTA + [TB_AVALIACAO].PESO_AVAL AS PESO_TOTAL	
  FROM   TB_NOTA [TB_NOTA] 
         INNER JOIN [TB_ATRIBALUNO] 
                 ON [TB_ATRIBALUNO].ID_ATRIBALUNO = [TB_NOTA].ID_ALUNO 
         --INNER JOIN [TB_ATRIBTURMA] ON [TB_ATRIBTURMA].ID_ATRIBTURMA = [TB_NOTA].ID_ATRIBT  
         INNER JOIN [TB_ATRIBTURMA] 
                 ON [TB_ATRIBTURMA].ID_ATRIBTURMA = [TB_NOTA].ID_ATRIBT 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
         INNER JOIN [TB_TURMA] 
                 ON [TB_TURMA].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_TURMA].ID_TURMA 
         INNER JOIN [TB_ALUNO] 
                 ON [TB_ALUNO].RM_ALUNO = [TB_ATRIBALUNO].RM_ALUNO 
         INNER JOIN [TB_AVALIACAO] 
                 ON [TB_AVALIACAO].ID_AVAL = [TB_NOTA].ID_AVAL 
         INNER JOIN [TB_ATRIBUICAO] 
                 ON [TB_ATRIBUICAO].[ID_ATRIB] = [TB_ATRIBTURMA].ID_ATRIB 
         INNER JOIN [TB_PROFESSOR] 
                 ON [TB_PROFESSOR].[ID_PROF] = [TB_ATRIBUICAO].[ID_PROF] 
         INNER JOIN [TB_DISCIPLINA] 
                 ON [TB_DISCIPLINA].[ID_DISC] = [TB_ATRIBUICAO].[ID_DISC] 



GO 

CREATE VIEW VW_GRIDNOTAS 
AS 
  SELECT YEAR([TB_NOTA].ANOLETIVO)             AS ANO, 
         STR([TB_NOTA].TRIM)                   AS TRIM, 
         STR([TB_NOTA].TRIM) + ' º TRIMESTRE'  AS TRIMESTRE, 
		 [TB_TURMA].ID_TURMA				   AS CODIGO_TURMA,
         [TB_TURMA].COMPLETO_TURMA             AS TURMA, 
		 [TB_DISCIPLINA].ID_DISC			   AS CODIGO_DISCIPLINA,
		 [TB_ATRIBTURMA].ID_ATRIBTURMA         AS ID_ATRIBTURMA,
         ( [TB_PROFESSOR].NOME_PROF + ' - ' 
           + [TB_DISCIPLINA].NOME_DISC )       AS MATÉRIA, 
         [TB_ALUNO].RM_ALUNO                   AS RM, 
         [TB_ALUNO].NOMEALUNO + ' ' 
         + [TB_ALUNO].SOBRENALUNO              AS ALUNO, 
         [TB_AVALIACAO].ID_AVAL                AS TPAVALIAÇÃO, 
         [TB_AVALIACAO].DESC_AVAL              AS AVALIAÇÃO, 
		 [TB_NOTA].id_NOTA                     AS CODIGO_NOTA,
         [TB_NOTA].NOTA 
  FROM   TB_NOTA [TB_NOTA] -- ANOLETIVO  
         INNER JOIN [TB_ATRIBALUNO] 
                 ON [TB_ATRIBALUNO].ID_ATRIBALUNO = [TB_NOTA].ID_ALUNO 
         INNER JOIN [TB_ATRIBTURMA] 
                 ON [TB_ATRIBTURMA].ID_ATRIBTURMA = [TB_NOTA].ID_ATRIBT 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
         INNER JOIN [TB_TURMA] 
                 ON [TB_TURMA].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_TURMA].ID_TURMA 
         INNER JOIN [TB_ALUNO] 
                 ON [TB_ATRIBALUNO].RM_ALUNO = [TB_ALUNO].RM_ALUNO 
         INNER JOIN [TB_AVALIACAO] 
                 ON [TB_AVALIACAO].ID_AVAL = [TB_NOTA].ID_AVAL 
         INNER JOIN [TB_ATRIBUICAO] 
                 ON [TB_ATRIBUICAO].[ID_ATRIB] = [TB_ATRIBTURMA].ID_ATRIB 
         INNER JOIN [TB_PROFESSOR] 
                 ON [TB_PROFESSOR].[ID_PROF] = [TB_ATRIBUICAO].[ID_PROF] 
         INNER JOIN [TB_DISCIPLINA] 
                 ON [TB_DISCIPLINA].[ID_DISC] = [TB_ATRIBUICAO].[ID_DISC] 
  WHERE  [TB_ATRIBALUNO].STATUS_RELACIONAMENTO = 1 

GO 

CREATE VIEW VW_GRIDNOTASGERAL 
AS 
  SELECT YEAR([TB_NOTA].ANOLETIVO)                 AS ANO, 
         STR([TB_NOTA].TRIM)                       AS TRIM, 
         ( STR([TB_NOTA].TRIM) + 'º TRIMESTRE' )  AS TRIMESTRE, 
         [TB_ATRIBTURMA].ID_ATRIBTURMA             AS CODIGO, 
		 [TB_TURMA].ID_TURMA					   AS CODIGO_TURMA,
         [TB_TURMA].COMPLETO_TURMA                 AS TURMA, 
		 [TB_PROFESSOR].ID_PROF					   AS PROFESSOR,
		 [TB_DISCIPLINA].ID_DISC				   AS CODIGO_DISCIPLINA,
		 [TB_DISCIPLINA].NOME_DISC				   AS DISCIPLINA_NOME,
         ( [TB_PROFESSOR].NOME_PROF + ' - ' 
           + [TB_DISCIPLINA].NOME_DISC )           AS MATÉRIA, 
         [TB_ALUNO].RM_ALUNO                       AS RM, 
         [TB_ALUNO].NOMEALUNO + ' ' 
         + [TB_ALUNO].SOBRENALUNO                  AS ALUNO, 
		 [TB_AVALIACAO].ID_AVAL                    AS ID_AVALIACAO, 
         [TB_AVALIACAO].DESC_AVAL                  AS AVALIAÇÃO, 
         [TB_AVALIACAO].PESO_AVAL                  AS PESO, 
         [TB_NOTA].NOTA                            AS NOTA_NORMAL, 
         [TB_NOTA].NOTA * [TB_AVALIACAO].PESO_AVAL AS NOTA, 
         [TB_NOTA].NOTA + [TB_AVALIACAO].PESO_AVAL AS PESO_TOTAL 
  FROM   TB_NOTA [TB_NOTA] 
         INNER JOIN [TB_ATRIBALUNO] 
                 ON [TB_ATRIBALUNO].ID_ATRIBALUNO = [TB_NOTA].ID_ALUNO 
         --INNER JOIN [TB_ATRIBTURMA] ON [TB_ATRIBTURMA].ID_ATRIBTURMA = [TB_NOTA].ID_ATRIBT  
         INNER JOIN [TB_ATRIBTURMA] 
                 ON [TB_ATRIBTURMA].ID_ATRIBTURMA = [TB_NOTA].ID_ATRIBT 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
         INNER JOIN [TB_TURMA] 
                 ON [TB_TURMA].ID_TURMA = [TB_ATRIBTURMA].ID_TURMA 
                    AND [TB_ATRIBALUNO].ID_TURMA = [TB_TURMA].ID_TURMA 
         INNER JOIN [TB_ALUNO] 
                 ON [TB_ALUNO].RM_ALUNO = [TB_ATRIBALUNO].RM_ALUNO 
         INNER JOIN [TB_AVALIACAO] 
                 ON [TB_AVALIACAO].ID_AVAL = [TB_NOTA].ID_AVAL 
         INNER JOIN [TB_ATRIBUICAO] 
                 ON [TB_ATRIBUICAO].[ID_ATRIB] = [TB_ATRIBTURMA].ID_ATRIB 
         INNER JOIN [TB_PROFESSOR] 
                 ON [TB_PROFESSOR].[ID_PROF] = [TB_ATRIBUICAO].[ID_PROF] 
         INNER JOIN [TB_DISCIPLINA] 
                 ON [TB_DISCIPLINA].[ID_DISC] = [TB_ATRIBUICAO].[ID_DISC] 
  WHERE  [TB_ATRIBALUNO].STATUS_RELACIONAMENTO = 1 



GO 

CREATE PROCEDURE RETORNAMEDIAPORMATERIA @RM        INT, 
                                        @TRIMESTRE INT 
AS 
  BEGIN 
      SELECT ANO, 
             TRIMESTRE, 
             MATÉRIA, 
             RM, 
             ALUNO, 
             CAST(SUM(NOTA * PESO / PESO) / SUM(PESO) AS NUMERIC(4, 2)) AS 
             MÉDIA 
      FROM   VW_GRIDNOTASGERAL 
      WHERE  RM = @RM 
             AND TRIM = @TRIMESTRE 
      GROUP  BY ANO, 
                TRIMESTRE, 
                MATÉRIA, 
                RM, 
                ALUNO; 
  END 

GO 

CREATE FUNCTION FTC_RETORNAMEDIA(@RM INT) 
RETURNS VARCHAR(100) 
WITH EXECUTE AS CALLER 
AS 
  BEGIN 
      DECLARE @SITUACAO CHAR(1) = '', 
              @NOTA     NUMERIC(4, 2) = (SELECT MÉDIA 
                 FROM   (SELECT ANO, 
                                RM, 
                                ALUNO, 
                                CAST(SUM(NOTA * PESO / PESO) / SUM(PESO) AS 
                                     NUMERIC(4, 2)) AS 
                                MÉDIA 
                         FROM   VW_GRIDNOTASGERAL 
                         WHERE  RM = CAST(@RM AS INT) 
                         GROUP  BY ANO, 
                                   RM, 
                                   ALUNO) A); 

      IF( @NOTA >= 6 ) 
        BEGIN 
            SET @SITUACAO = 'A'; 
        END; 
      ELSE 
        BEGIN 
            SET @SITUACAO = 'E'; 
        END; 

      RETURN @SITUACAO 
  END 

GO 

CREATE PROCEDURE RETORNAAVALIACOESMATERIA @RM        INT, 
                                          @TRIMESTRE INT 
AS 
  BEGIN 
      SELECT ANO, 
             TRIMESTRE, 
             MATÉRIA                         AS MATERIA, 
             AVALIAÇÃO                      AS AVALIACAO, 
             CAST(AVG(NOTA) AS NUMERIC(4, 2)) AS MÉDIA 
      FROM   VW_GRIDNOTAS 
      WHERE  RM = @RM 
             AND TRIM = @TRIMESTRE 
      GROUP  BY ANO, 
                TRIMESTRE, 
                MATÉRIA, 
                AVALIAÇÃO; 
  END 

GO 

CREATE VIEW VW_RANKING 
AS 
  WITH RANKING 
       AS (SELECT TRIMESTRE, 
                  TURMA, 
                  CAST(SUM(NOTA_NORMAL) / COUNT(NOTA_NORMAL) AS NUMERIC(4, 2)) 
                  AS 
                  NOTAS 
           FROM   VW_GRIDNOTASGERAL 
           GROUP  BY TURMA, 
                     TRIMESTRE) 
  SELECT TOP 3 * 
  FROM   RANKING; 

GO 

CREATE PROC PROC_CRUDNOTA @TP_OPERACAO CHAR(1), 
                          @ID_ALUNO    INT, 
                          @ID_ATRIBT   INT, 
                          @ID_AVAL     INT, 
                          @TRIM        INT, 
                          @NOTA        NUMERIC(4, 2) 
AS 
  BEGIN 
      IF( @TP_OPERACAO = 'I' ) 
        BEGIN 
            INSERT INTO TB_NOTA 
                        (ID_ALUNO, 
                         ID_ATRIBT, 
                         ID_AVAL, 
                         TRIM, 
                         NOTA) 
            VALUES      ( (SELECT ID_ATRIBALUNO 
                           FROM   TB_ATRIBALUNO 
                           WHERE  RM_ALUNO = @ID_ALUNO 
                                  AND STATUS_RELACIONAMENTO = 1), 
                          @ID_ATRIBT, 
                          @ID_AVAL, 
                          @TRIM, 
                          @NOTA); 
        END 

      IF( @TP_OPERACAO = 'U' ) 
        BEGIN 
            UPDATE TB_NOTA 
            SET    NOTA = @NOTA 
            WHERE  ID_ALUNO = (SELECT ID_ATRIBALUNO 
                               FROM   TB_ATRIBALUNO 
                               WHERE  RM_ALUNO = @ID_ALUNO 
                                      AND STATUS_RELACIONAMENTO = 1) 
                   AND ID_ATRIBT = @ID_ATRIBT 
                   AND ID_AVAL = @ID_AVAL 
                   AND TRIM = @TRIM 
        END 

      IF( @TP_OPERACAO = 'S' ) 
        BEGIN 
            SELECT * 
            FROM   VW_GRIDNOTASGERAL 
            WHERE  RM = @ID_ALUNO 
                   AND TRIM = COALESCE(@TRIM,TRIM) 
                   AND CODIGO = COALESCE(@ID_ATRIBT,CODIGO) 
				   AND AVALIAÇÃO = COALESCE((SELECT DISTINCT DESC_AVAL FROM TB_AVALIACAO WHERE ID_AVAL = @ID_AVAL),AVALIAÇÃO) 
        END 

      IF( @TP_OPERACAO = 'D' ) 
        BEGIN 
            UPDATE TB_NOTA 
            SET    STATUS_NOTA = 0 
            WHERE  ID_ALUNO = (SELECT ID_ATRIBALUNO 
                               FROM   TB_ATRIBALUNO 
                               WHERE  RM_ALUNO = @ID_ALUNO 
                                      AND STATUS_RELACIONAMENTO = 1) 
        END 

      IF( @TP_OPERACAO = 'R' ) 
        BEGIN 
            UPDATE TB_NOTA 
            SET    STATUS_NOTA = 1 
            WHERE  ID_ALUNO = (SELECT ID_ATRIBALUNO 
                               FROM   TB_ATRIBALUNO 
                               WHERE  RM_ALUNO = @ID_ALUNO 
                                      AND STATUS_RELACIONAMENTO = 1) 
        END 
  END 

GO 
--select * from fct_Grafico_Professor(1,null,null,null,null,null,null) 
create FUNCTION FCT_GRAFICO_PROFESSOR (
	 @TIPO_PESQUISA INT -- 1 PARA ANALISAR ALUNOS, 2 PARA TURMAS, NULL PARA PEGAR DE TODOS OS ANOS
	,@PROFESSOR		INT	-- CÓDIGO DO PROFESSOR, NULL IRÁ TRAZER DE TODOS OS PROFS
	,@TRIMESTRE		INT -- 1 - PRIMEIRO TR, 2 - SEGUNDO, 3 - TERCEIRO, NULL IRÁ TRAZER TODOS
	,@MATERIA		INT -- CASO PROFESSOR COM DUAS MATÉRIAS
	,@TURMA			INT -- CASO 1 DO TIPO_PESQUISA 
	,@aval			INT -- CASO 1 DO TIPO_PESQUISA 
	,@aluno			INT -- CASO 1 DO TIPO_PESQUISA  
) 
RETURNS @MEDIA TABLE (
		  ANO					INT
		,TRIMESTRE				VARCHAR(100) 
		,PROFESSOR				INT
		,CODIGO_DISCIPLINA		INT
		,DISCIPLINA_NOME		VARCHAR(100)
		,CODIGO_TURMA			INT
		,TURMA					VARCHAR(10)
		,MATERIA				VARCHAR(100)
		,ALUNO					VARCHAR(100)
		,AVALIACAO				VARCHAR(20)
		,MEDIA					NUMERIC(4,2)
)
AS
	BEGIN
		IF(@TIPO_PESQUISA = 1) 
			BEGIN
			  INSERT INTO @MEDIA (
					 ANO
					,TRIMESTRE
					,CODIGO_TURMA
					,TURMA
					,PROFESSOR
					,CODIGO_DISCIPLINA
					,DISCIPLINA_NOME 
					,MATERIA
					,ALUNO
					,AVALIACAO
					,MEDIA) 
			  SELECT ANO, 
					 TRIMESTRE,
					 CODIGO_TURMA,
					 TURMA, 
					 PROFESSOR, 
					 CODIGO_DISCIPLINA,
					 DISCIPLINA_NOME, 
					 MATÉRIA,  
					 ALUNO,  
					 AVALIAÇÃO,
					 CAST(SUM(NOTA * PESO / PESO) / SUM(PESO) AS NUMERIC(4, 2)) AS 
					 MÉDIA 
			-- SELECT * 
			  FROM   VW_GRIDNOTASGERAL 
			  WHERE	 PROFESSOR			= COALESCE(@PROFESSOR,PROFESSOR)
				 AND TRIM				= COALESCE(@TRIMESTRE,TRIM)
				 AND CODIGO_DISCIPLINA	= COALESCE(@MATERIA,CODIGO_DISCIPLINA)
				 AND CODIGO				= COALESCE(@TURMA,CODIGO) 
				 AND ID_AVALIACAO		= COALESCE(@aval,id_avaliacao) 
				 AND RM					= COALESCE(@aluno,rm) 
			  GROUP  BY ANO, 
						TRIMESTRE, 
						CODIGO_TURMA,
						TURMA, 
						PROFESSOR,
						CODIGO_DISCIPLINA,
						DISCIPLINA_NOME,
						MATÉRIA,  
						ALUNO, 
						AVALIAÇÃO; 
			END
		ELSE 
		IF(@TIPO_PESQUISA = 3) 
			BEGIN
			  INSERT INTO @MEDIA (
					 ANO
					,TRIMESTRE
					,CODIGO_TURMA
					,TURMA
					,PROFESSOR
					,CODIGO_DISCIPLINA
					,DISCIPLINA_NOME 
					,MATERIA 
					,MEDIA) 
			  SELECT ANO, 
					 TRIMESTRE,
					 CODIGO_TURMA,
					 TURMA, 
					 PROFESSOR,
					 CODIGO_DISCIPLINA,
					 DISCIPLINA_NOME, 
					 MATÉRIA,   
					 CAST(SUM(NOTA * PESO / PESO) / SUM(PESO) AS NUMERIC(4, 2)) AS 
					 MÉDIA 
			-- SELECT * 
			  FROM   VW_GRIDNOTASGERAL 
			  WHERE	 PROFESSOR			= COALESCE(@PROFESSOR,PROFESSOR)
				 AND TRIM				= COALESCE(@TRIMESTRE,TRIM)
				 AND CODIGO_DISCIPLINA	= COALESCE(@MATERIA,CODIGO_DISCIPLINA)
				 AND CODIGO				= COALESCE(@TURMA,CODIGO) 
				 AND id_avaliacao		= COALESCE(@aval,id_avaliacao) 
				 AND RM					= COALESCE(@aluno,rm) 
			  GROUP  BY ANO, 
						TRIMESTRE,
						CODIGO_TURMA, 
						TURMA, 
						PROFESSOR,
						CODIGO_DISCIPLINA,
						DISCIPLINA_NOME,
						MATÉRIA; 
			END
		ELSE IF(@TIPO_PESQUISA = 2)
			BEGIN
				INSERT INTO @MEDIA (
					  ANO
					  ,TRIMESTRE
					  ,CODIGO_TURMA
					  ,TURMA
					  ,PROFESSOR
					  ,CODIGO_DISCIPLINA
					  ,DISCIPLINA_NOME 
					  ,MATERIA
					  ,AVALIACAO
					  ,MEDIA) 	
				SELECT ANO
					  ,TRIMESTRE
					  ,CODIGO_TURMA
					  ,TURMA
					  ,PROFESSOR
					  ,CODIGO_DISCIPLINA
					  ,DISCIPLINA_NOME 
					  ,MATÉRIA
					  ,AVALIAÇÃO
					  ,CAST(AVG(MÉDIA) AS NUMERIC(4,2)) AS MÉDIA 
				FROM( SELECT ANO, 
							 TRIMESTRE,
							 CODIGO_TURMA,
							 TURMA, 
							 PROFESSOR,
							 CODIGO_DISCIPLINA,
							 DISCIPLINA_NOME ,
							 MATÉRIA,  
							 AVALIAÇÃO,
							 (SUM(NOTA * PESO / PESO) / SUM(PESO)) AS 
							 MÉDIA 
					-- SELECT * 
					  FROM   VW_GRIDNOTASGERAL 
					  WHERE	 PROFESSOR		= COALESCE(@PROFESSOR,PROFESSOR)
						 AND TRIM			= COALESCE(@TRIMESTRE,TRIM)
						 AND CODIGO_DISCIPLINA	= COALESCE(@MATERIA,CODIGO_DISCIPLINA)
						 AND id_avaliacao	= COALESCE(@aval,id_avaliacao) 
						 AND RM				= COALESCE(@aluno,rm) 
					  GROUP  BY ANO, 
								TRIMESTRE,
								CODIGO_TURMA, 
								TURMA, 
								PROFESSOR,
								CODIGO_DISCIPLINA,
								DISCIPLINA_NOME ,
								MATÉRIA, 
								AVALIAÇÃO
						) A
				  GROUP	 BY ANO,
							TRIMESTRE,
							CODIGO_TURMA,
							TURMA,
							PROFESSOR,
							CODIGO_DISCIPLINA,
							DISCIPLINA_NOME ,
							MATÉRIA,
							AVALIAÇÃO	; 		 
			END
		  ELSE
			BEGIN
				INSERT INTO @MEDIA (
					   ANO
					  ,TRIMESTRE
					  ,CODIGO_TURMA
					  ,TURMA
					  ,PROFESSOR
					  ,CODIGO_DISCIPLINA
					  ,DISCIPLINA_NOME 
					  ,MATERIA
					  ,AVALIACAO
					  ,MEDIA) 	
				SELECT ANO
					  ,TRIMESTRE
					  ,CODIGO_TURMA
					  ,TURMA
					  ,PROFESSOR
					  ,CODIGO_DISCIPLINA
					  ,DISCIPLINA_NOME 
					  ,MATÉRIA
					  ,AVALIAÇÃO
					  ,CAST(AVG(MÉDIA) AS NUMERIC(4,2)) AS MÉDIA 
				FROM( SELECT ANO, 
							 TRIMESTRE,
							 CODIGO_TURMA,
							 TURMA, 
							 PROFESSOR,
							 CODIGO_DISCIPLINA,
							 DISCIPLINA_NOME ,
							 MATÉRIA, 
							 RM, 
							 ALUNO, 
							 AVALIAÇÃO,
							 (SUM(NOTA * PESO / PESO) / SUM(PESO)) AS 
							 MÉDIA 
					-- SELECT * 
					  FROM   VW_GRIDNOTASGERAL_HISTORICO 
					  WHERE	 PROFESSOR		= COALESCE(@PROFESSOR,PROFESSOR)
						 AND TRIM			= COALESCE(@TRIMESTRE,TRIM)
						 AND CODIGO_DISCIPLINA	= COALESCE(@MATERIA,CODIGO_DISCIPLINA)
						 AND ID_AVALIACAO	= COALESCE(@aval,ID_AVALIACAO) 
						 AND RM				= COALESCE(@aluno,rm) 
					  GROUP  BY ANO, 
								TRIMESTRE,
								CODIGO_TURMA, 
								TURMA, 
								PROFESSOR,
								CODIGO_DISCIPLINA,
								DISCIPLINA_NOME ,
								MATÉRIA, 
								RM, 
								ALUNO,
								AVALIAÇÃO
						) A
				  GROUP	 BY ANO,
							TRIMESTRE,
							CODIGO_TURMA,
							TURMA,
							PROFESSOR,
							CODIGO_DISCIPLINA,
							DISCIPLINA_NOME ,
							MATÉRIA,
							AVALIAÇÃO; 		 
			END
		RETURN 
	END;
	GO

create proc Grafico 
	@TIPO_PESQUISA INT -- 1 PARA ANALISAR ALUNOS, 2 PARA TURMAS, NULL PARA PEGAR DE TODOS OS ANOS
	,@PROFESSOR		INT	-- CÓDIGO DO PROFESSOR, NULL IRÁ TRAZER DE TODOS OS PROFS
	,@TRIMESTRE		INT -- 1 - PRIMEIRO TR, 2 - SEGUNDO, 3 - TERCEIRO, NULL IRÁ TRAZER TODOS
	,@MATERIA		INT -- CASO PROFESSOR COM DUAS MATÉRIAS
	
as 
begin
	--declare @tipo_pesquisa int = 3
	create table #Table_teste (texto VARCHAR(max))
       DECLARE @teste varchar(100)
      DECLARE crTurmas CURSOR FOR 
        SELECT DISTINCT turma 
        FROM   Fct_grafico_professor(@tipo_pesquisa, @PROFESSOR, @TRIMESTRE, @MATERIA, null, null,null) 

      OPEN crTurmas
		FETCH next FROM crTurmas INTO @teste 

		WHILE @@FETCH_STATUS = 0 
		BEGIN 
			DECLARE @result VARCHAR(max) 
             
            SET @result = '{name: ' + @teste  + ', data:[' 

            DECLARE @media numeric(4,2) 
			declare @id_turma int = (select id_turma from tb_turma where completo_turma = @teste)
            DECLARE crteste2 CURSOR FOR 
				SELECT DISTINCT media 
              FROM   Fct_grafico_professor(@tipo_pesquisa, @PROFESSOR, @TRIMESTRE, @MATERIA, @id_turma, null, null) 

            OPEN crteste2 

            FETCH next FROM crteste2 INTO @media 

            WHILE @@fetch_status = 0 
              BEGIN 
                  SET @result += '''' + cast(@media as varchar(6)) + '''' + ',' 

                  FETCH next FROM crteste2 INTO @media 
              END 
     		CLOSE crteste2  

            DEALLOCATE crteste2  

            SET @result += ']},' 

            INSERT INTO #Table_teste 
			VALUES     (@result) 

            FETCH next FROM crTurmas  INTO @teste 
		END 
			
    CLOSE crTurmas 

    DEALLOCATE crTurmas 
		
		select * from #Table_teste
		drop table #Table_teste
			
  END

--execute Grafico 1,null,null,null,null
--execute Grafico 2,null,null,null



/*  
========================================================================================================
========================================================================================================
                VIEWS - CONSULTAS GERAIS  
========================================================================================================
========================================================================================================
*/ 
CREATE VIEW TABELATURMA 
AS 
  SELECT CODIGO, 
         CURSO, 
         ANO, 
         PERÍODO, 
         [ANO_LETIVO], 
         TURMA, 
         STATUS 
  FROM   (SELECT TURMA.ID_TURMA                         AS 'CODIGO', 
                 CURSO.NOMECURSO                        AS CURSO, 
                 CASE TURMA.ANO_TURMA 
                   WHEN 1 THEN '1º ANO' 
                   WHEN 2 THEN '2º ANO' 
                   WHEN 3 THEN '3º ANO' 
                 END                                    AS ANO, 
                 TURMA.NOME_TURMA                       AS NOME, 
                 CASE TURMA.PERIODO_TURMA 
                   WHEN 'M' THEN 'MANHÃ' 
                   WHEN 'T' THEN 'TARDE' 
                   WHEN 'N' THEN 'NOTURNO' 
                 END                                    AS PERÍODO, 
                 YEAR(TURMA.ANOLETIVO_TURMA)            AS 'ANO_LETIVO', 
                 TURMA.COMPLETO_TURMA                   AS TURMA, 
                 ROW_NUMBER() 
                   OVER( 
                     PARTITION BY TURMA.COMPLETO_TURMA 
                     ORDER BY TURMA.COMPLETO_TURMA ASC) RN, 
                 CASE 
                   WHEN TURMA.STATUS_TURMA IS NULL THEN 'INATIVO' 
                   WHEN TURMA.STATUS_TURMA = 1 THEN 'ATIVO' 
                   ELSE 'INATIVO' 
                 END                                    AS STATUS 
          FROM   TB_TURMA TURMA 
                 INNER JOIN TB_CURSO CURSO 
                         ON CURSO.ID_CURSO = TURMA.ID_CURSO) A; 

GO 

CREATE VIEW TABELADISCIPLINA 
AS 
  SELECT DISCIPLINA.ID_DISC     AS 'CODIGO', 
         DISCIPLINA.NOME_DISC   AS 'NOME', 
         CASE DISCIPLINA.SERIE 
           WHEN 1 THEN '1º ANO' 
           WHEN 2 THEN '2º ANO' 
           WHEN 3 THEN '3º ANO' 
         END                    AS 'SERIE', 
         CURSO.NOMECURSO        AS CURSO, 
         DISCIPLINA.NOME_DISC   AS DISCIPLINA, 
         YEAR(DISCIPLINA.ANO)   AS ANO, 
         DISCIPLINA.STATUS_DISC AS STATUS 
  FROM   TB_DISCIPLINA DISCIPLINA 
         INNER JOIN TB_CURSO CURSO 
                 ON CURSO.ID_CURSO = DISCIPLINA.ID_CURSO; 

GO 


CREATE VIEW TABELAPROFESSOR
AS 
  SELECT  DISTINCT
	  PROFESSOR.ID_PROF		AS CODIGO_PROFESSOR,
	  PROFESSOR.NOME_PROF	AS PROFESSOR,
	  TURMA.ID_TURMA		AS CODIGO_TURMA,
	  TURMA.COMPLETO_TURMA	AS TURMA
  FROM TB_ATRIBTURMA 

  INNER JOIN TB_TURMA TURMA
	ON TURMA.ID_TURMA	 = TB_ATRIBTURMA.ID_TURMA
  INNER JOIN TB_ATRIBUICAO PROFESSORMAT
	ON PROFESSORMAT.ID_ATRIB = TB_ATRIBTURMA.ID_ATRIB
  INNER JOIN TB_PROFESSOR PROFESSOR
	ON PROFESSOR.ID_PROF = PROFESSORMAT.ID_PROF
  INNER JOIN TB_ATRIBALUNO
	ON TB_ATRIBALUNO.ID_TURMA = TURMA.ID_TURMA
	WHERE TURMA.ID_TURMA IN( (SELECT DISTINCT AAA.ID_TURMA FROM TB_ATRIBALUNO AAA WHERE STATUS_RELACIONAMENTO = 1) )

GO

CREATE VIEW TABELAATRIBUICAO 
AS 
  SELECT ATRIBUICAO.ID_ATRIB  AS 'CODIGO', 
         PROFESSOR.NOME_PROF  AS 'PROFESSOR', 
         DISCIPLINA.NOME_DISC AS 'DISCIPLINA', 
         ATRIBUICAO.NOME, 
         YEAR(ATRIBUICAO.ANO) AS ANO, 
         CASE ATRIBUICAO.STATUSATRIB 
           WHEN 1 THEN 'ATIVO' 
           ELSE 'INATIVO' 
         END                  AS STATUS 
  FROM   TB_ATRIBUICAO ATRIBUICAO 
         INNER JOIN TB_PROFESSOR PROFESSOR 
                 ON ATRIBUICAO.ID_PROF = PROFESSOR.ID_PROF 
         INNER JOIN TB_DISCIPLINA DISCIPLINA 
                 ON ATRIBUICAO.ID_DISC = DISCIPLINA.ID_DISC; 

GO 

CREATE VIEW TABELAMENSAGEM 
AS 
  SELECT DISTINCT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
		CASE 
			   WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF THEN 
			   'PROFESSOR'
			   WHEN MENSAGEM.RECEBEMSG_ALUNO = AALUNO.ID_ATRIBALUNO AND MENSAGEM.RECEBEMSG_TURMA IS NULL THEN 'ALUNO'
			   WHEN MENSAGEM.RECEBEMSG_TURMA = AALUNO2.ID_TURMA THEN   'TURMA'
        END                  
		AS RECEPTOR, 
		CASE 
				WHEN MENSAGEM.RECEBEMSG_PROFESSOR IS NOT NULL THEN MENSAGEM.RECEBEMSG_PROFESSOR            
				WHEN MENSAGEM.RECEBEMSG_ALUNO IS NOT NULL THEN MENSAGEM.RECEBEMSG_ALUNO  
         END                  
		 AS CODIGO_RECEPTOR, 

         CASE 
			   WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF THEN 
			   PROFESSOR.NOME_PROF 
			   WHEN MENSAGEM.RECEBEMSG_ALUNO = AALUNO.ID_ATRIBALUNO THEN ALUNO.NOMEALUNO 
			   WHEN MENSAGEM.RECEBEMSG_ALUNO = AALUNO.ID_ATRIBALUNO AND MENSAGEM.RECEBEMSG_TURMA = AALUNO2.ID_TURMA THEN 
			   TURMA.COMPLETO_TURMA + ' ' + ALUNO.NOMEALUNO + ' ' + ALUNO.SOBRENALUNO
         END        
		 AS RECEBE,
		 CASE 
			   WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF THEN 
			   'PROFESSOR'
			   WHEN MENSAGEM.ENVMSG_ALUNO = AALUNO.ID_ATRIBALUNO THEN 'ALUNO'
         END                  
		 AS REMETENTE,  
         CASE 
           WHEN MENSAGEM.ENVMSG_PROFESSOR IS NOT NULL THEN MENSAGEM.ENVMSG_PROFESSOR 
           WHEN MENSAGEM.ENVMSG_ALUNO IS NOT NULL	  THEN MENSAGEM.ENVMSG_ALUNO 
         END                  
		 AS CODIGO_REMETENTE,
		 CASE 
           WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF THEN 
           PROFESSOR.NOME_PROF 
           WHEN MENSAGEM.ENVMSG_ALUNO = AALUNO.ID_ATRIBALUNO THEN ALUNO.NOMEALUNO + ' ' + SOBRENALUNO 
         END                  
		 AS ENVIA,
		  
         MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
         MENSAGEM.ASSMSG      AS 'ASSUNTO', 
         MENSAGEM.DESCMSG     AS 'DESCRICAO' 
		--SELECT * 
  FROM   TB_MENSAGEM MENSAGEM 
         LEFT JOIN TB_PROFESSOR PROFESSOR 
                ON MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                    OR MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
         LEFT JOIN TB_ATRIBALUNO AALUNO
				ON MENSAGEM.RECEBEMSG_ALUNO = AALUNO.ID_ATRIBALUNO OR MENSAGEM.ENVMSG_ALUNO = AALUNO.ID_ATRIBALUNO
		LEFT JOIN TB_ATRIBALUNO AALUNO2
				ON MENSAGEM.RECEBEMSG_TURMA = AALUNO2.ID_TURMA
		LEFT JOIN TB_ALUNO ALUNO
				ON AALUNO.RM_ALUNO = ALUNO.RM_ALUNO
		LEFT JOIN TB_TURMA TURMA
				ON AALUNO2.ID_TURMA = TURMA.ID_TURMA




GO 

CREATE VIEW TABELASUGESTAO 
AS 
  SELECT SUGESTAO.ID_SUGESTAO  AS CODIGO, 
         ( CAST(ALUNO.RM_ALUNO AS VARCHAR) + ' - ' 
           + ALUNO.NOMEALUNO ) AS ALUNO, 
         SUGESTAO.DTENVSGT     AS 'DATA_ENVIO', 
         SUGESTAO.MSGSGT       AS 'SUGESTAO' 
  FROM   TB_SUGESTAO SUGESTAO 
         INNER JOIN TB_ALUNO ALUNO 
                 ON SUGESTAO.FKSGT = ALUNO.RM_ALUNO 

GO 

CREATE VIEW TABELAATRIBTURMA 
AS 
  SELECT CODIGO, 
         NOME, 
         TURMA 
  FROM   (SELECT ATRIBTURMA.ID_ATRIBTURMA     AS CODIGO, 
                 ATRIB.NOME, 
                 TURMA.COMPLETO_TURMA         AS TURMA, 
                 ROW_NUMBER() 
                   OVER( 
                     PARTITION BY ATRIB.NOME 
                     ORDER BY ATRIB.NOME ASC) RN 
          FROM   TB_ATRIBTURMA ATRIBTURMA 
                 INNER JOIN TB_ATRIBUICAO ATRIB 
                         ON ATRIB.ID_ATRIB = ATRIBTURMA.ID_ATRIB 
                 INNER JOIN TB_TURMA TURMA 
                         ON TURMA.ID_TURMA = ATRIBTURMA.ID_TURMA) A 

GO 

CREATE VIEW TABELAALUNO 
AS 
  SELECT ATALUNO.ANO_ATRIB    ANO, 
         ALUNO.RM_ALUNO       AS RM, 
         ALUNO.NOMEALUNO      AS NOME, 
         ALUNO.SOBRENALUNO    AS SOBRENOME, 
         ALUNO.CPFALUNO       AS CPF, 
         ALUNO.NASCALUNO      AS NASCIMENTO, 
         ALUNO.EMAILALUNO     AS EMAIL, 
		 TURMA.ID_TURMA		  AS CODIGO_TURMA,
         TURMA.COMPLETO_TURMA AS TURMA, 
         CASE ATALUNO.STATUS_RELACIONAMENTO 
           WHEN 1 THEN 'ATIVO' 
           WHEN 2 THEN 'APROVADO' 
           WHEN 3 THEN 'FINALIZOU' 
           WHEN 4 THEN 'REPROVADO' 
           ELSE 'INATIVO' 
         END       
           AS STATUS 
  FROM   TB_ALUNO ALUNO 
         INNER JOIN TB_ATRIBALUNO ATALUNO 
                 ON ATALUNO.RM_ALUNO = ALUNO.RM_ALUNO 
         INNER JOIN TB_TURMA TURMA 
                 ON TURMA.ID_TURMA = ATALUNO.ID_TURMA 

GO 

/*  
========================================================================================================
========================================================================================================
          STORED PROCEDURES - CRUD'S E COMANDOS PARA NOTAS E MENSAGENS  
========================================================================================================
========================================================================================================
*/ 


CREATE PROC PROC_CRUDMENSAGEM @TP_COMANDO          CHAR(1), 
                              @ENVMSG_ALUNO        INT, 
                              @ENVMSG_PROFESSOR    INT, 
                              @RECEBEMSG_ALUNO     INT, 
                              @RECEBEMSG_TURMA     INT, 
                              @RECEBEMSG_PROFESSOR INT, 
                              @ASSMSG              VARCHAR(100), 
                              @DESCMSG             VARCHAR(MAX) 
AS 
  BEGIN 
      IF( @TP_COMANDO = 'I' ) 
        BEGIN 
            INSERT INTO TB_MENSAGEM 
                        (ENVMSG_ALUNO, 
                         ENVMSG_PROFESSOR, 
                         RECEBEMSG_ALUNO, 
                         RECEBEMSG_TURMA, 
                         RECEBEMSG_PROFESSOR, 
                         ASSMSG, 
                         DESCMSG) 
            VALUES     ( (SELECT CASE 
                                   WHEN @ENVMSG_ALUNO IS NULL THEN @ENVMSG_ALUNO 
                                   ELSE (SELECT TOP 1 ID_ATRIBALUNO 
                                         FROM   TB_ATRIBALUNO 
                                         WHERE  RM_ALUNO = @ENVMSG_ALUNO 
                                                AND STATUS_RELACIONAMENTO = 1) 
                                 END), 
                         @ENVMSG_PROFESSOR, 
                         (SELECT CASE 
                                   WHEN @RECEBEMSG_ALUNO IS NULL THEN 
                                   @RECEBEMSG_ALUNO 
                                   ELSE (SELECT TOP 1 ID_ATRIBALUNO 
                                         FROM   TB_ATRIBALUNO 
                                         WHERE  RM_ALUNO = @RECEBEMSG_ALUNO 
                                                AND STATUS_RELACIONAMENTO = 1) 
                                 END), 
                         @RECEBEMSG_TURMA, 
                         @RECEBEMSG_PROFESSOR, 
                         @ASSMSG, 
                         @DESCMSG ); 
        END 

      IF( @TP_COMANDO = 'S' ) 
        BEGIN 
			--CODIGO,	RECEPTOR, CODIGO_RECEPTOR,RECEBE,
            SELECT DISTINCT *  
            FROM   TABELAMENSAGEM 
            WHERE  ( ( CODIGO_RECEPTOR = (SELECT CASE 
                                                   WHEN @RECEBEMSG_ALUNO IS NULL 
                                                 THEN 
                                                   @RECEBEMSG_ALUNO 
                                                   ELSE 
                                         (SELECT TOP 1 ID_ATRIBALUNO 
                                          FROM   TB_ATRIBALUNO 
                                          WHERE 
                                                 RM_ALUNO = @RECEBEMSG_ALUNO 
                                                 AND STATUS_RELACIONAMENTO = 
                                                     1) 
                                                 END) 
                       AND RECEPTOR = 'ALUNO' ) 
                      OR ( CODIGO_RECEPTOR = @RECEBEMSG_PROFESSOR 
                           AND RECEPTOR = 'PROFESSOR' ) ) 
                    OR ( ( CODIGO_REMETENTE = (SELECT ID_ATRIBALUNO 
                                               FROM   TB_ATRIBALUNO 
                                               WHERE  RM_ALUNO = @ENVMSG_ALUNO 
                                                      AND STATUS_RELACIONAMENTO 
                                                          = 
                                                          1) 
                           AND REMETENTE = 'ALUNO' ) 
                          OR ( CODIGO_REMETENTE = @ENVMSG_PROFESSOR 
                               AND REMETENTE = 'PROFESSOR' ) ) 
        END 
  END 

GO
CREATE PROCEDURE PROC_BUSCAMENSAGEM @TIPO         INT, 
                                    @CODIGO_BUSCA VARCHAR(100) 
AS 
  BEGIN 
      IF ( @TIPO = 1 ) 
        WITH ENVIADAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.RM_ALUNO 
                        END                  AS ENVIO, 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                        END                  AS ENVIA, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF) 
        , 
             RECEBIDAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ATTALUNO.RM_ALUNO 
                        END                  AS RECEBIDO, 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.COMPLETO_TURMA 
                        END                  AS RECEBE, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.RECEBEMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.RECEBEMSG_PROFESSOR = 
                                  PROFESSOR.ID_PROF 
                        LEFT JOIN TB_ATRIBALUNO ATTALUNO 
                               ON ATTALUNO.RM_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_TURMA TURMA 
                               ON MENSAGEM.RECEBEMSG_TURMA = ATTALUNO.ID_TURMA 
                                  AND ATTALUNO.ID_TURMA = TURMA.ID_TURMA 
                 WHERE  ATTALUNO.STATUS_RELACIONAMENTO = 1) 
        SELECT RECEBIDAS.CODIGO, 
               RECEBIDAS.RECEBIDO, 
               RECEBIDAS.RECEBE, 
               ENVIADAS.ENVIO, 
               ENVIADAS.ENVIA, 
               RECEBIDAS.DATA_ENVIO, 
               ENVIADAS.ASSUNTO, 
               ENVIADAS.DESCRICAO 
        FROM   RECEBIDAS 
               INNER JOIN ENVIADAS 
                       ON RECEBIDAS.CODIGO = ENVIADAS.CODIGO 
        WHERE  RECEBIDO = @CODIGO_BUSCA 
      ELSE IF ( @TIPO = 2 ) 
        WITH ENVIADAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.RM_ALUNO 
                        END                  AS ENVIO, 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                        END                  AS ENVIA, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF) 
        , 
             RECEBIDAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ATTALUNO.RM_ALUNO 
                        END                  AS RECEBIDO, 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.COMPLETO_TURMA 
                        END                  AS RECEBE, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE MENSAGEM.ASSMSG 
                          WHEN NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE MENSAGEM.DESCMSG 
                          WHEN NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.RECEBEMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.RECEBEMSG_PROFESSOR = 
                                  PROFESSOR.ID_PROF 
                        LEFT JOIN TB_ATRIBALUNO ATTALUNO 
                               ON ALUNO.RM_ALUNO = ATTALUNO.RM_ALUNO 
                        LEFT JOIN TB_TURMA TURMA 
                               ON MENSAGEM.RECEBEMSG_TURMA = ATTALUNO.ID_TURMA 
                                  AND ATTALUNO.ID_TURMA = TURMA.ID_TURMA 
                 WHERE  ATTALUNO.STATUS_RELACIONAMENTO = 1) 
        SELECT RECEBIDAS.CODIGO, 
               RECEBIDAS.RECEBIDO, 
               RECEBIDAS.RECEBE, 
               ENVIADAS.ENVIO, 
               ENVIADAS.ENVIA, 
               RECEBIDAS.DATA_ENVIO, 
               ENVIADAS.ASSUNTO, 
               ENVIADAS.DESCRICAO 
        FROM   RECEBIDAS 
               INNER JOIN ENVIADAS 
                       ON RECEBIDAS.CODIGO = ENVIADAS.CODIGO 
        WHERE  ENVIO = @CODIGO_BUSCA 
      ELSE IF( @TIPO = 3 ) 
        WITH ENVIADAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.RM_ALUNO 
                        END                  AS ENVIO, 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                        END                  AS ENVIA, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF) 
        , 
             RECEBIDAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ATTALUNO.RM_ALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.ID_TURMA 
                        END                  AS RECEBIDO, 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.COMPLETO_TURMA 
                        END                  AS RECEBE, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.RECEBEMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.RECEBEMSG_PROFESSOR = 
                                  PROFESSOR.ID_PROF 
                        LEFT JOIN TB_ATRIBALUNO ATTALUNO 
                               ON ATTALUNO.RM_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_TURMA TURMA 
                               ON MENSAGEM.RECEBEMSG_TURMA = ATTALUNO.ID_TURMA 
                                  AND ATTALUNO.ID_TURMA = TURMA.ID_TURMA 
                 WHERE  ATTALUNO.STATUS_RELACIONAMENTO = 1) 
        SELECT RECEBIDAS.CODIGO, 
               RECEBIDAS.RECEBIDO, 
               RECEBIDAS.RECEBE, 
               ENVIADAS.ENVIO, 
               ENVIADAS.ENVIA, 
               RECEBIDAS.DATA_ENVIO, 
               ENVIADAS.ASSUNTO, 
               ENVIADAS.DESCRICAO 
        FROM   RECEBIDAS 
               INNER JOIN ENVIADAS 
                       ON RECEBIDAS.CODIGO = ENVIADAS.CODIGO 
        WHERE  ENVIADAS.ENVIO = @CODIGO_BUSCA 
                OR RECEBIDAS.RECEBIDO = @CODIGO_BUSCA 
      ELSE IF( @TIPO = 5 ) 
        WITH ENVIADAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.ID_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.RM_ALUNO 
                        END                  AS ENVIO, 
                        CASE 
                          WHEN MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF 
                        THEN 
                          PROFESSOR.NOME_PROF 
                          WHEN MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                        END                  AS ENVIA, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.ENVMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_PROFESSOR PROFESSOR 
                               ON MENSAGEM.ENVMSG_PROFESSOR = PROFESSOR.ID_PROF) 
        , 
             RECEBIDAS 
             AS (SELECT MENSAGEM.ID_MENSAGEM AS 'CODIGO', 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ATTALUNO.RM_ALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.ID_TURMA 
                        END                  AS RECEBIDO, 
                        CASE 
                          WHEN MENSAGEM.RECEBEMSG_ALUNO = ATTALUNO.RM_ALUNO THEN 
                          ALUNO.NOMEALUNO 
                          WHEN MENSAGEM.RECEBEMSG_TURMA = TURMA.ID_TURMA THEN 
                          TURMA.COMPLETO_TURMA 
                        END                  AS RECEBE, 
                        MENSAGEM.DTMSG       AS 'DATA_ENVIO', 
                        CASE 
                          WHEN MENSAGEM.ASSMSG IS NULL THEN 'SEM ASSUNTO' 
                          ELSE MENSAGEM.ASSMSG 
                        END                  AS 'ASSUNTO', 
                        CASE 
                          WHEN MENSAGEM.DESCMSG IS NULL THEN 'SEM DESCRIÇÃO' 
                          ELSE MENSAGEM.DESCMSG 
                        END                  AS 'DESCRICAO' 
                 FROM   TB_MENSAGEM MENSAGEM 
                        LEFT JOIN TB_ALUNO ALUNO 
                               ON MENSAGEM.RECEBEMSG_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_ATRIBALUNO ATTALUNO 
                               ON ATTALUNO.RM_ALUNO = ALUNO.RM_ALUNO 
                        LEFT JOIN TB_TURMA TURMA 
                               ON MENSAGEM.RECEBEMSG_TURMA = ATTALUNO.ID_TURMA 
                                  AND ATTALUNO.ID_TURMA = TURMA.ID_TURMA 
                 WHERE  ATTALUNO.STATUS_RELACIONAMENTO = 1) 
        SELECT RECEBIDAS.CODIGO, 
               RECEBIDAS.RECEBIDO, 
               RECEBIDAS.RECEBE, 
               ENVIADAS.ENVIO, 
               ENVIADAS.ENVIA, 
               RECEBIDAS.DATA_ENVIO, 
               ENVIADAS.ASSUNTO, 
               ENVIADAS.DESCRICAO 
        FROM   RECEBIDAS 
               INNER JOIN ENVIADAS 
                       ON RECEBIDAS.CODIGO = ENVIADAS.CODIGO 
        WHERE  RECEBIDO IN( @CODIGO_BUSCA, (SELECT ID_TURMA 
                                            FROM   TB_ATRIBALUNO 
                                            WHERE  RM_ALUNO = @CODIGO_BUSCA 
                                                   AND STATUS_RELACIONAMENTO = 1 
                                           ) 
                          ) 
  END; 

GO 

CREATE TRIGGER ENVIA_MSG_V2 
ON TB_MENSAGEM 
AFTER INSERT 
AS 
    DECLARE @ID                  INT, 
            @ENVMSG_ALUNO        INT, 
            @ENVMSG_PROFESSOR    INT, 
            @RECEBEMSG_ALUNO     INT, 
            @RECEBEMSG_TURMA     INT, 
            @RECEBEMSG_PROFESSOR INT, 
            @ASSMSG              VARCHAR(100), 
            @DESCMSG             VARCHAR(MAX)

    SELECT @ID = ID_MENSAGEM, 
           @ENVMSG_ALUNO = ENVMSG_ALUNO, 
           @ENVMSG_PROFESSOR = ENVMSG_PROFESSOR, 
           @RECEBEMSG_ALUNO = RECEBEMSG_ALUNO, 
           @RECEBEMSG_TURMA = RECEBEMSG_TURMA, 
           @RECEBEMSG_PROFESSOR = RECEBEMSG_PROFESSOR, 
           @ASSMSG = ASSMSG, 
           @DESCMSG = DESCMSG 
    FROM   INSERTED; 

    IF( @RECEBEMSG_TURMA <> NULL 
         OR @RECEBEMSG_TURMA <> '' ) 
      BEGIN 
          INSERT INTO TB_MENSAGEM 
                      (ENVMSG_ALUNO, 
                       ENVMSG_PROFESSOR, 
                       RECEBEMSG_ALUNO, 
                       RECEBEMSG_TURMA, 
                       ASSMSG, 
                       DESCMSG) 
          SELECT @ENVMSG_ALUNO, 
                 @ENVMSG_PROFESSOR, 
                 *, 
                 @RECEBEMSG_TURMA, 
                 @ASSMSG, 
                 @DESCMSG 
          FROM   (SELECT ID_ATRIBALUNO 
                  FROM   TB_ATRIBALUNO 
                  WHERE  ID_TURMA = @RECEBEMSG_TURMA 
                         AND STATUS_RELACIONAMENTO = 1) A 

          DELETE FROM TB_MENSAGEM 
          WHERE  ID_MENSAGEM = @ID; 
      END 

GO 

/*  
========================================================================================================
========================================================================================================
          STORED PROCEDURES - CRUD'S E FUNÇÕES PARA PASSAGEM DE ANO 
========================================================================================================
========================================================================================================
*/ 
CREATE FUNCTION [DBO].[SDF_SPLITSTRING] (@SSTRING    NVARCHAR(2048), 
                                         @CDELIMITER NCHAR(1)) 
RETURNS @TPARTS TABLE ( 
  PART INT) 
AS 
  BEGIN 
      IF @SSTRING IS NULL 
        RETURN 

      DECLARE @ISTART INT, 
              @IPOS   INT 

      IF SUBSTRING(@SSTRING, 1, 1) = @CDELIMITER 
        BEGIN 
            SET @ISTART = 2 

            INSERT INTO @TPARTS 
            VALUES      ( NULL ) 
        END 
      ELSE 
        SET @ISTART = 1 

      WHILE 1 = 1 
        BEGIN 
            SET @IPOS = CHARINDEX(@CDELIMITER, @SSTRING, @ISTART) 

            IF @IPOS = 0 
              SET @IPOS = LEN( @SSTRING ) + 1 

            IF @IPOS - @ISTART > 0 
              INSERT INTO @TPARTS 
              VALUES      ( SUBSTRING(@SSTRING, @ISTART, @IPOS - @ISTART)) 
            ELSE 
              INSERT INTO @TPARTS 
              VALUES      ( NULL ) 

            SET @ISTART = @IPOS + 1 

            IF @ISTART > LEN(@SSTRING) 
              BREAK 
        END 

      RETURN 
  END 

GO 

CREATE PROC PROC_MANTEM_RELACIONAMENTOS @TURMA        INT, 
                                        @TIRA_ANTIGOS CHAR(1) 
AS 
  BEGIN 
      DECLARE @TURMANOVA INT = (SELECT MAX(ID_TURMA) 
         FROM   TB_TURMA); 

      INSERT INTO TB_ATRIBTURMA 
      SELECT ID_ATRIB, 
             @TURMANOVA, 
             1 
      FROM   TB_ATRIBTURMA 
      WHERE  ID_TURMA = @TURMA; 

      IF( @TIRA_ANTIGOS != '' 
          AND @TIRA_ANTIGOS IS NOT NULL ) 
        BEGIN 
            UPDATE TB_ATRIBTURMA 
            SET    STATUS_ATRTURMA = 0 
            WHERE  ID_TURMA = @TURMA; 
        END 
  END 

GO 

CREATE PROC PROC_APROVA_ALUNOS @ID_TURMA             INT, 
                               @REPROVADOS           VARCHAR(MAX), 
                               @TIRA_RELACIONAMENTOS CHAR(1) 
AS 
  BEGIN 
      -- DECLARAR VARIAVEIS PARA MANIPULAR INFORMAÇÕES DA TURMA E DOS ALUNOS  
      DECLARE @NOME               CHAR(1), 
              @ANO                CHAR(1), 
              @PERIODO            CHAR(1), 
              @CURSO              CHAR(1), 
              @ALUNOSAPROVADOS    VARCHAR(MAX), 
              @IDALUNOSAPROVADOS  VARCHAR(MAX), 
              @IDALUNOSREPROVADOS VARCHAR(MAX); 

      CREATE TABLE #TURMA 
        ( 
           NOME    CHAR(1), 
           ANO     CHAR(1), 
           PERIODO CHAR(1), 
           CURSO   CHAR(1) 
        ); 

      INSERT INTO #TURMA 
      SELECT NOME_TURMA, 
             ANO_TURMA, 
             PERIODO_TURMA, 
             ID_CURSO 
      FROM   TB_TURMA 
      WHERE  ID_TURMA = @ID_TURMA; 

      SET @CURSO = (SELECT CURSO 
                    FROM   #TURMA); 
      SET @ANO = (SELECT ANO 
                  FROM   #TURMA); 
      SET @NOME = (SELECT NOME 
                   FROM   #TURMA); 

      IF( @REPROVADOS <> '' 
          AND @REPROVADOS IS NOT NULL ) 
        BEGIN 
            UPDATE TB_ATRIBALUNO 
            SET    STATUS_RELACIONAMENTO = 4 
            WHERE  ID_ATRIBALUNO IN(SELECT ID_ATRIBALUNO 
                                    FROM   TB_ATRIBALUNO 
                                    WHERE  STATUS_RELACIONAMENTO = 1 
                                           AND ID_TURMA = @ID_TURMA 
                                           AND RM_ALUNO IN(SELECT 
                                               CAST(PART AS INT) 
                                                           FROM 
                                               SDF_SPLITSTRING(REPLACE( 
                                               @REPROVADOS, '''', ''), 
                                               ','))); 
        END 

      IF( @ANO = 3 ) 
        BEGIN 
            UPDATE TB_ATRIBALUNO 
            SET    STATUS_RELACIONAMENTO = 3 
            WHERE  ID_ATRIBALUNO IN(SELECT ID_ATRIBALUNO 
                                    FROM   TB_ATRIBALUNO 
                                    WHERE  STATUS_RELACIONAMENTO = 1 
                                           AND ID_TURMA = @ID_TURMA 
                                           AND RM_ALUNO IN((SELECT RM_ALUNO 
                                                            FROM   TB_ATRIBALUNO 
                                                            WHERE 
                                               STATUS_RELACIONAMENTO 
                                               = 1 
                                               AND 
                                                          ID_TURMA = @ID_TURMA 
                                                                   AND 
                                               RM_ALUNO NOT IN ( 
                                               ( 
                                               SELECT 
                                               CAST(PART AS INT) 
                                               FROM 
                                               SDF_SPLITSTRING( 
                                               REPLACE( 
                                               @REPROVADOS, '''' 
                                               , 
                                               ''), 
                                               ',' 
                                               )))))); 

            INSERT INTO TB_ATRIBALUNO 
                        (RM_ALUNO, 
                         ID_TURMA, 
                         ANO_ATRIB) 
            SELECT CAST(PART AS INT), 
                   @ID_TURMA, 
                   ( YEAR(GETDATE()) + 1 ) 
            FROM   SDF_SPLITSTRING(REPLACE(@REPROVADOS, '''', ''), ','); 
        END 
      ELSE 
        BEGIN 
            UPDATE TB_ATRIBALUNO 
            SET    STATUS_RELACIONAMENTO = 2 
            WHERE  ID_ATRIBALUNO IN(SELECT ID_ATRIBALUNO 
                                    FROM   TB_ATRIBALUNO 
                                    WHERE  STATUS_RELACIONAMENTO = 1 
                                           AND ID_TURMA = @ID_TURMA 
                                           AND RM_ALUNO IN(SELECT RM_ALUNO 
                                                           FROM   TB_ATRIBALUNO 
                                                           WHERE 
                                               STATUS_RELACIONAMENTO 
                                               = 1 
                                               AND 
                                                          ID_TURMA = @ID_TURMA 
                                                                  AND 
                                               RM_ALUNO NOT IN 
                                               (SELECT 
                                                                  CAST(PART AS 
                                                                  INT) 
                                                                       FROM 
                                               SDF_SPLITSTRING( 
                                               REPLACE( 
                                               @REPROVADOS, '''', 
                                               '' 
                                                               ), 
                                               ',' 
                                               )))); 

            -- SE EXISTIR TURMA PARA OS ALUNOS  
            DECLARE @PERIODONOVO CHAR(1) = (/*CURSO/ANO/PERIODO */ 
              SELECT CASE 
                       WHEN CURSO IN(SELECT ID_CURSO 
                                     FROM   TB_CURSO 
                                     WHERE  ABREVCURSO = 'EDF' 
                                             OR ABREVCURSO = 'INF') THEN 'M' 
                       WHEN ANO = 1 
                            AND CURSO IN(SELECT ID_CURSO 
                                         FROM   TB_CURSO 
                                         WHERE  ABREVCURSO = 'EEL' 
                                                 OR ABREVCURSO = 'TEL') THEN 'T' 
                       ELSE 'M' 
                     END AS PERIODO 
               FROM   #TURMA) 

            SET @ANO = CAST(@ANO AS NUMERIC) + 1; 

            INSERT INTO TB_TURMA 
                        (ID_CURSO, 
                         ANO_TURMA, 
                         PERIODO_TURMA, 
                         NOME_TURMA) 
            VALUES      (@CURSO, 
                         @ANO, 
                         @PERIODONOVO, 
                         @NOME); 

            DECLARE @TURMA_NOVA2 INT = (SELECT MAX(ID_TURMA) 
               FROM   TB_TURMA); 

            BEGIN TRY 
                EXECUTE PROC_MANTEM_RELACIONAMENTOS 
                  @ID_TURMA, 
                  @TIRA_RELACIONAMENTOS 
            END TRY 

            BEGIN CATCH 
                PRINT 'ERROOOOU' 
            END CATCH 

            /*PENDENTE*/ 
            INSERT INTO TB_ATRIBALUNO 
                        (RM_ALUNO, 
                         ID_TURMA, 
                         ANO_ATRIB) 
            SELECT RM_ALUNO, 
                   @TURMA_NOVA2, 
                   ( YEAR(GETDATE()) + 1 ) 
            FROM   TB_ATRIBALUNO 
            WHERE  STATUS_RELACIONAMENTO = 2 
                   AND ID_TURMA = @ID_TURMA 
                   AND RM_ALUNO NOT IN(SELECT CAST(PART AS INT) 
                                       FROM   SDF_SPLITSTRING(REPLACE( 
                                              @REPROVADOS, 
                                                              '''', 
                                                              '') 
                                              , ',')); 

            IF( @REPROVADOS <> '' 
                AND @REPROVADOS IS NOT NULL ) 
              BEGIN 
                  /*PENDENTE*/ 
                  INSERT INTO TB_ATRIBALUNO 
                              (RM_ALUNO, 
                               ID_TURMA, 
                               ANO_ATRIB) 
                  SELECT CAST(PART AS INT), 
                         @ID_TURMA, 
                         ( YEAR(GETDATE()) + 1 ) 
                  FROM   SDF_SPLITSTRING(REPLACE(@REPROVADOS, '''', ''), ','); 
              END 
        END 
  END 

GO 

CREATE PROCEDURE APROVA_TODOS @TIRA_RELACIONAMENTOS CHAR(1) 
AS 
  BEGIN 
      DECLARE @TURMA  INT, 
              @RM     INT, 
              @RESULT VARCHAR(MAX); 
      DECLARE CRTURMAS CURSOR FOR 
        (SELECT distinct ID_TURMA 
         FROM   TB_atribaluno 
         WHERE  YEAR(ANO_ATRIB) = YEAR(GETDATE())); 

      OPEN CRTURMAS; 

      FETCH NEXT FROM CRTURMAS INTO @TURMA; 

      WHILE @@FETCH_STATUS = 0 
        BEGIN 
            SET @RESULT = ''; 
            SET @RM = 0; 

            DECLARE CRALUNOS CURSOR FOR 
              (SELECT RM_ALUNO 
               FROM   TB_ALUNO 
               WHERE  RM_ALUNO IN((SELECT RM_ALUNO 
                                   FROM   TB_ATRIBALUNO 
                                   WHERE  ID_TURMA = @TURMA 
                                          AND STATUS_RELACIONAMENTO = 1 
                                          AND YEAR(ANO_ATRIB) = YEAR(GETDATE())) 
                                 )) 

            OPEN CRALUNOS; 

            FETCH NEXT FROM CRALUNOS INTO @RM; 

            DECLARE @CONTROLE_ALU INT = 0; 

            WHILE @@FETCH_STATUS = 0 
              BEGIN 
                  DECLARE @STATUS_ALU CHAR(1) = (SELECT 
                  DBO.FTC_RETORNAMEDIA(@RM)); 

                  IF( @STATUS_ALU = 'E' ) 
                    BEGIN 
                        SET @RESULT = COALESCE(@RESULT + ',', '') 
                                      + CAST(@RM AS VARCHAR(5)); 
                    END; 

                  FETCH NEXT FROM CRALUNOS INTO @RM; 

                  PRINT 'teste '+ @TURMA + cast(@RM as varchar(100)) + cast(@STATUS_ALU as varchar(100))
              END 
			  
            CLOSE CRALUNOS; 

            DEALLOCATE CRALUNOS; 

            IF( @RESULT != '' )   
              BEGIN 
                  SET @RESULT = (SELECT RIGHT(@RESULT, LEN(@RESULT) - 1));  
              END 

            PRINT 'TOP ' + @RESULT  
            EXECUTE PROC_APROVA_ALUNOS 
              @TURMA, 
              @RESULT, 
              @TIRA_RELACIONAMENTOS  


            FETCH NEXT FROM CRTURMAS INTO @TURMA; 
        END 

      CLOSE CRTURMAS; 

      DEALLOCATE CRTURMAS; 
  END 

GO 
/*  
========================================================================================================
========================================================================================================
                INSERT DE TESTE  
========================================================================================================
========================================================================================================
*/ 
/* 
SELECT * FROM TB_ALUNO
SELECT * FROM TB_NOTA
INSERT INTO TB_CURSO (NOMECURSO,ABREVCURSO) VALUES('INFORMATICA','INF'); 
INSERT INTO TB_DISCIPLINA (NOME_DISC,SERIE,ID_CURSO) VALUES ('BADA',1,(SELECT MAX(ID_CURSO) FROM TB_CURSO)) 
INSERT INTO TB_DISCIPLINA (NOME_DISC,SERIE,ID_CURSO) VALUES ('JAVA',1,(SELECT MAX(ID_CURSO) FROM TB_CURSO)) 
INSERT INTO TB_TURMA (ID_CURSO,ANO_TURMA,NOME_TURMA,PERIODO_TURMA) VALUES(1,1,'A','M'); 
INSERT INTO TB_TURMA (ID_CURSO,ANO_TURMA,NOME_TURMA,PERIODO_TURMA) VALUES(1,1,'B','M'); 
INSERT INTO TB_PROFESSOR (ID_PROF,NOME_PROF) VALUES(1,'LEANDRO');  
INSERT INTO TB_PROFESSOR (ID_PROF,NOME_PROF) VALUES(2,'LALLO');  
INSERT INTO TB_ATRIBUICAO (ID_PROF,ID_DISC) VALUES(1,2) 
INSERT INTO TB_ATRIBUICAO (ID_PROF,ID_DISC) VALUES(2,1) 
INSERT INTO TB_ATRIBTURMA (ID_ATRIB,ID_TURMA) VALUES (1,1),(2,2),(2,1),(1,2) 
--INSERT DE ALUNO 
EXECUTE PROC_CRUDALUNO 'I',26897,'FELIPE','AUGUSTO',NULL ,NULL,NULL,NULL,NULL,2 
EXECUTE PROC_CRUDALUNO 'I',32457,'ELLEN','CHRISTINA',NULL,NULL,NULL,NULL,NULL,2 
EXECUTE PROC_CRUDALUNO 'I',27656,'EMILY','APARECIDA',NULL,NULL,NULL,NULL,NULL,2 

EXECUTE PROC_CRUDALUNO 'I',25895,'LEONARDO','MENEZES',NULL,NULL,NULL,NULL,NULL,1 
EXECUTE PROC_CRUDALUNO 'I',25896,'BRUNA','MAGRINI',NULL,NULL,NULL,NULL,NULL,1 
EXECUTE PROC_CRUDALUNO 'I',25909,'JULIA','GONÇALVES',NULL,NULL,NULL,NULL,NULL,1  
--INSERT DE AVALIACAO 
INSERT INTO TB_AVALIACAO (DESC_AVAL,PESO_AVAL) VALUES ('PAT',3),('PEM',2),('TABALHO',2); 

--INSERT DE NOTA DOS 3 ALUNOS 
	 --SP_HELPTEXT PROC_CRUDNOTA 
     
	 EXECUTE PROC_CRUDNOTA 'I',26897 ,2,3,1,10 
     EXECUTE PROC_CRUDNOTA 'I',26897 ,2,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',26897 ,2,1,1,10     
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,3,1,8.5   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,1,1,6     
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,3,1,5   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,1,1,9     
     
	 EXECUTE PROC_CRUDNOTA 'I',25895 ,1,3,1,10   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,1,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,1,1,1,10 
     
	 EXECUTE PROC_CRUDNOTA 'I',25896 ,1,3,1,8.5 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,1,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',25896 ,1,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,3,1,8   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,1,1,9 
     -------------------------------------------
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,3,1,10 
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,1,1,10 	     
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,3,1,8.5   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,1,1,6  
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,3,1,5   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,1,1,9  
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,3,1,10   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,3,1,8.5 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25909 ,3,3,1,8   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,3,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,3,1,1,9 
    
	--------------------------------------------
	--------------------------------------------
	---		DEPOIS DA PASSAGEM DE ANO		----

	--------------------------------------------
	--------------------------------------------
	 
	 EXECUTE PROC_CRUDNOTA 'I',26897 ,2,3,1,10 
     EXECUTE PROC_CRUDNOTA 'I',26897 ,2,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',26897 ,2,1,1,10     
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,3,1,8.5   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,2,1,1,6     
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,3,1,5   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,2,1,1,9     
     EXECUTE PROC_CRUDNOTA 'I',25895 ,1,3,1,10   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,1,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,1,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,1,3,1,8.5 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,1,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',25896 ,1,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,3,1,8   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,1,1,1,9 
     -------------------------------------------
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,3,1,10 
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',26897 ,4,1,1,10 	     
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,3,1,8.5   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',32457 ,4,1,1,6  
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,3,1,5   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,2,1,7   
     EXECUTE PROC_CRUDNOTA 'I',27656 ,4,1,1,9  
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,3,1,10   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,2,1,08   
     EXECUTE PROC_CRUDNOTA 'I',25895 ,3,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,3,1,8.5 
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,2,1,9   
     EXECUTE PROC_CRUDNOTA 'I',25896 ,3,1,1,10 
     EXECUTE PROC_CRUDNOTA 'I',25909 ,3,3,1,8   
     EXECUTE PROC_CRUDNOTA 'I',25909 ,3,2,1,7   
     EXECUTE PROC_CRUDNOTA 'S',25909 ,3,NULL,1,9 
    
	INSERT INTO TB_EVENTO (TITULO_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO) VALUES('EVENTO Nº1','EVENTO DE ABERTURA DA ESCOLA','01/01/2019');
	 --SELECT * FROM tb_atribaluno 
    --SELECT * FROM tb_atribturma 
    --SELECT * FROM tb_turma 
    --select * from tabelaAluno  
	--select * from tb_nota
	 --EXECUTE APROVA_TODOS NULL 
    --EXECUTE PROC_APROVA_ALUNOS 2,NULL,null

	-- SELECT POR ALUNOS, AVALIAÇÃO
	--select * from fct_Grafico_Professor(1,null,null,null,null,null,null) 

	-- SELECT POR TURMAS, AVALIAÇÃO
	--select * from fct_Grafico_Professor(2,null,null,null,null,null,null) 
	
	-- SELECT POR TURMAS
	
	
	-- SELECT POR Alunos
	--select ano,trimestre,disciplina_nome,turma, materia, aluno, cast(avg(media) as numeric(4,2)) as media
	  from fct_Grafico_Professor(1,null,null,null,null,null,null) group by  ano,trimestre,disciplina_nome,turma, materia, aluno
	  


	-- ENVIO DE MENSAGEM DO ALUNO 25895 PARA PROFESSOR 1
	-- SELECT DE MENSAGEM DO ALUNO 25895 PARA PROFESSOR 1
	EXECUTE PROC_CRUDMENSAGEM 'I',25895,NULL,NULL,NULL,1,'TESTE PARA PROFESSOR','MENSAGEM PARA PROFESSOR' 
	EXECUTE PROC_CRUDMENSAGEM 'S',25895,NULL,NULL,NULL,NULL,NULL,NULL

	-- ENVIO DE MENSAGEM DO PROFESSOR 1 PARA TURMA 1
	-- SELECT DE MENSAGEM DO PROFESSOR 1 PARA TURMA 1
	EXECUTE PROC_CRUDMENSAGEM 'I',NULL,1,NULL,1,NULL,'TESTE PARA TURMA','MENSAGEM PARA TURMA 1' 
	EXECUTE PROC_CRUDMENSAGEM 'S',NULL,1,NULL,NULL,NULL,NULL,NULL

	 

*/ 

--select * from tb_login;

--select distinct Disciplina, atribuicao from vw_Professores where codigo = 100

-- retonar média anual 
select disciplina_nome,turma,materia, cast(avg(media) as numeric(4,2)) from fct_Grafico_Professor(3,null,null,null,null,null,null) 
group by disciplina_nome,turma,materia



execute grafico 1,null,null,null



 