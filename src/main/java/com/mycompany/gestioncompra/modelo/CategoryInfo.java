/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.modelo;

import com.mycompany.gestioncompra.dto.Categoria;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author josera
 */
public class CategoryInfo {
    private int code;
    private String name;

    // Upload file.
    private CommonsMultipartFile fileData;
    
    public CategoryInfo() {
        
    }
    
    public CategoryInfo(Categoria categoria) {
        this.code = categoria.getCodigo();
        this.name = categoria.getNombre();
    }
    
    public CategoryInfo(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /**
     * @return the codigo
     */
    public int getCode() {
        return code;
    }

    /**
     * @param Code the Code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fileData
     */
    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
    
}
