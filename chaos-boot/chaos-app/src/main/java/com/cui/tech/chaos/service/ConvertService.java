package com.cui.tech.chaos.service;

import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.db.Model;
import com.cui.tech.chaos.model.db.MuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/5/15 18:42
 */
@Component
public class ConvertService<T extends MuModel> {
    public List<DTO> convertToDTO(List<T> mm, Class c) {
        return mm.stream().map(m -> convertToDTO(m, c)).collect(Collectors.toList());
    }

    /**
     * @param mm
     * @param c
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public DTO convertToDTO(MuModel mm, Class c) {
        DTO t = null;
        try {
            t = (DTO) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        convertToDTO(mm, t);
        return t;
    }

    /**
     * 转化一个MuModel为DTO
     *
     * @param mm
     * @param t
     */
    public DTO convertToDTO(MuModel mm, DTO t) {
        BeanUtils.copyProperties(mm, t);
        return t;
    }

    public DTO convertToDTO(Model mm, DTO t) {
        BeanUtils.copyProperties(mm, t);
        return t;
    }

    /**
     * @param t
     * @param c
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public MuModel convertToMuModel(DTO t, Class c) {
        MuModel mm = null;
        try {
            mm = (MuModel) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        convertToMuModel(t, mm);
        return mm;
    }

    /**
     * 转化一个DTO为MuModel
     *
     * @param t
     * @param mm
     */
    public MuModel convertToMuModel(DTO t, MuModel mm) {
        BeanUtils.copyProperties(t, mm);
        return mm;
    }

    public Model convertToModel(DTO t, Model mm) {
        BeanUtils.copyProperties(t, mm);
        return mm;
    }


    /**
     * 通过自定义方式转化一个MuModel为DTO
     *
     * @param mm
     * @param f
     * @return
     */
    public DTO convertToDTO(MuModel mm, Function<MuModel, DTO> f) {
        return f.apply(mm);
    }

    public DTO convertToDTO(Model mm, Function<Model, DTO> f) {
        return f.apply(mm);
    }

    /**
     * 通过自定义方式转化一个DTO为MuModel
     *
     * @param t
     * @param f
     * @return
     */
    public MuModel convertToMuModel(DTO t, Function<DTO, MuModel> f) {
        return f.apply(t);
    }

    /**
     * 修改DTO属性
     *
     * @param t
     * @param c
     */
    public void changeFromDTO(DTO t, Consumer<DTO> c) {
        c.accept(t);
    }

    /**
     * 修改MuModel属性
     *
     * @param mm
     * @param c
     */
    public void changeFromMuModel(MuModel mm, Consumer<DTO> c) {
        c.accept(mm);
    }
}
