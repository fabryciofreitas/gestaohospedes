CREATE TABLE check_in (
      id BIGSERIAL PRIMARY KEY,
      hospede_id BIGINT NOT NULL REFERENCES hospede(id),
      data_entrada TIMESTAMP NOT NULL,
      data_saida TIMESTAMP,
      possui_veiculo BOOLEAN NOT NULL DEFAULT FALSE,
      valor_total NUMERIC(10,2)
);

CREATE INDEX idx_check_in_hospede ON check_in(hospede_id);
CREATE INDEX idx_check_in_data_saida ON check_in(data_saida);