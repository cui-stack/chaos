package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.db.MuModel;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author G.G
 * @date 2020/5/15 18:42
 */
@Component
public class ConvertService {
    public DTO convert(MuModel mm, Function<MuModel, DTO> f) {
        return f.apply(mm);
    }

    public MuModel convert(DTO t, Function<DTO, MuModel> f) {
        return f.apply(t);
    }

    public void convert(DTO t, Consumer<DTO> c) {
        c.accept(t);
    }

    public void convert(MuModel mm, Consumer<DTO> c) {
        c.accept(mm);
    }
}
