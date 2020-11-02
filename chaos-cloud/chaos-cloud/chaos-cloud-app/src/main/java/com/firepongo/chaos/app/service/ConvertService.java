package com.firepongo.chaos.app.service;

import com.firepongo.chaos.app.DTO;
import com.firepongo.chaos.app.db.DATA;
import com.firepongo.chaos.app.db.Model;
import com.firepongo.chaos.app.db.MuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/5/15 18:42
 */
@Component
public class ConvertService<T extends Model> {

    public List<DTO> convertToDTO(List<T> mm, Class c) {
        return mm.stream().map(m -> convertToDTO(m, c)).collect(Collectors.toList());
    }

    public List<DTO> convertToDTO(List<T> mm, Class c, Consumer<DTO> cc) {
        return mm.stream().map(m -> convertToDTO(m, c, cc)).collect(Collectors.toList());
    }

    public List<DTO> convertToDTO(List<T> mm, Class c, BiConsumer<Model, DTO> cc) {
        return mm.stream().map(m -> convertToDTO(m, c, cc)).collect(Collectors.toList());
    }

    public DTO convertToDTO(T mm, Class c) {
        DTO target = null;
        if (mm == null) {
            return null;
        }
        try {
            target = (DTO) c.newInstance();
            BeanUtils.copyProperties(mm, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public DTO convertToDTO(T mm, Class c, BiConsumer<Model, DTO> cc) {
        DTO t = convertToDTO(mm, c);
        cc.accept(mm, t);
        return t;
    }

    public DTO convertToDTO(T mm, Class c, Consumer<DTO> cc) {
        DTO t = convertToDTO(mm, c);
        cc.accept(t);
        return t;
    }


    public MuModel convertToMuModel(DTO tt, Class c) {
        MuModel target = null;
        if (tt == null) {
            return null;
        }
        try {
            target = (MuModel) c.newInstance();
            BeanUtils.copyProperties(tt, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;

    }

    public Model convertToModel(DTO tt, Class c) {
        Model target = null;
        if (tt == null) {
            return null;
        }
        try {
            target = (Model) c.newInstance();
            BeanUtils.copyProperties(tt, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public void change(DATA tt, Consumer<DATA> c) {
        c.accept(tt);
    }

}
