INSERT INTO USUARIO(nome, email, senha) VALUES('Cliente', 'cliente@email.com', '$2a$10$Z023MkDCM3SW8Ny8.I8D8eWNmV1LFzsSu3wuY5YlQ7Gcctfv1cJD6');

INSERT INTO SABOR(id,descricao,tempo_adicional) values(1,'morango',0);
INSERT INTO SABOR(id,descricao,tempo_adicional) values(2,'banana',0);
INSERT INTO SABOR(id,descricao,tempo_adicional) values(3,'kiwi',5);


INSERT INTO COMPLEMENTO(id,descricao,valor,tempo_adicional) VALUES(1,'Granola',0.00,0);
INSERT INTO COMPLEMENTO(id,descricao,valor,tempo_adicional) VALUES(2,'Paçoca',3.00,3);
INSERT INTO COMPLEMENTO(id,descricao,valor,tempo_adicional) VALUES(3,'Leite Ninho',3.00,0);

INSERT INTO PRODUTO(id,tamanho,valor,tempo) VALUES(1,'Pequeno(300ml)',10.00,5);
INSERT INTO PRODUTO(id,tamanho,valor,tempo) VALUES(2,'Médio(500ml)',13.00,7);
INSERT INTO PRODUTO(id,tamanho,valor,tempo) VALUES(3,'Grande(700ml)',15.00,10);