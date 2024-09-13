CREATE TABLE document (
                          id BIGSERIAL PRIMARY KEY,
                          reg_number VARCHAR(255),
                          reg_date DATE,
                          out_number VARCHAR(255),
                          out_date DATE,
                          delivery_method VARCHAR(255),
                          correspondent VARCHAR(255),
                          subject VARCHAR(255),
                          description TEXT,
                          execution_date DATE,
                          access VARCHAR(255),
                          control VARCHAR(255)
);
