package br.com.juanpina.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Esse método vai recuperar tudo que eu tenho de propriedade nula
    public static String[] getNullPropertyNames(Object source) {

        // Pegando o objeto
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Obtendo as propriedades desse meu objeto
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Criando um conjunto com as propriedades que eu tenho de valores nulos
        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);

    }

}
