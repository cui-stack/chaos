package com.cui.tech.chaos.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2019/11/7 17:30
 */
@Data
public class MU extends ID {
    private String mu;

    public MU() {
    }

    public MU(String mu) {
        this.mu = mu;
    }

    public MU(Integer id) {
        super(id);
    }

    public MU(String mu, Integer id) {
        super(id);
        this.mu = mu;
    }
}
