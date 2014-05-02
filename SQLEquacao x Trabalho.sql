SELECT e.expressaoequacao FROM trabalhocientifico tc INNER JOIN equacaotrabalhocientifico etc ON tc.id = etc.idtrabalhocientifico
                                    INNER JOIN equacao e ON etc.idequacao = e.id
                                    WHERE tc.id = 1;