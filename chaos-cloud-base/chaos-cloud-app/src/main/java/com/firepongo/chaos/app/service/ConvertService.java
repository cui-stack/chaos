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

    public List<DTO> convertToDTO(List<T> models, Class clazz) {
        return models.stream().map(model -> convertToDTO(model, clazz)).collect(Collectors.toList());
    }

    public List<DTO> convertToDTO(List<T> models, Class clazz, Consumer<DTO> consumer) {
        return models.stream().map(model -> convertToDTO(model, clazz, consumer)).collect(Collectors.toList());
    }

    public List<DTO> convertToDTO(List<T> models, Class clazz, BiConsumer<Model, DTO> consumer) {
        return models.stream().map(model -> convertToDTO(model, clazz, consumer)).collect(Collectors.toList());
    }

    public DTO convertToDTO(T model, Class clazz) {
        DTO target = null;
        if (model == null) {
            return null;
        }
        try {
            target = (DTO) clazz.newInstance();
            BeanUtils.copyProperties(model, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public DTO convertToDTO(T model, Class clazz, BiConsumer<Model, DTO> consumer) {
        DTO dto = convertToDTO(model, clazz);
        consumer.accept(model, dto);
        return dto;
    }

    public DTO convertToDTO(T model, Class clazz, Consumer<DTO> consumer) {
        DTO dto = convertToDTO(model, clazz);
        consumer.accept(dto);
        return dto;
    }


    public MuModel convertToMuModel(DTO dto, Class clazz) {
        MuModel model = null;
        if (dto == null) {
            return null;
        }
        try {
            model = (MuModel) clazz.newInstance();
            BeanUtils.copyProperties(dto, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;

    }

    public List<MuModel> convertToMuModel(List<DTO> dtos, Class clazz) {
        return dtos.stream().map((dto) -> convertToMuModel(dto, clazz)).collect(Collectors.toList());
    }

    public Model convertToModel(DTO dto, Class clazz) {
        Model model = null;
        if (dto == null) {
            return null;
        }
        try {
            model = (Model) clazz.newInstance();
            BeanUtils.copyProperties(dto, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


}
