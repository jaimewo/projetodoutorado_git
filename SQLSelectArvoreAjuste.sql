SELECT vaa.id as id, vaa.valor as valor, v.id as idvariavel, v.sigla as sigla,v.nome as nome
FROM arvoreajuste aa 
INNER JOIN variavelarvoreajuste vaa ON aa.id = vaa.idarvoreajuste
INNER JOIN variavel v ON vaa.idvariavel = v.id
WHERE aa.id = 988;