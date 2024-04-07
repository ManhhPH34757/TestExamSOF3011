package com.example.testexam.repositories;

import com.example.testexam.entities.LopHoc;
import com.example.testexam.entities.SinhVien;
import com.example.testexam.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LopHocRepo {
    EntityManager entityManager = JpaUtils.getEntityManager();

    public List<LopHoc> getList(){
        String select = "select sv from LopHoc sv";
        TypedQuery<LopHoc> query = entityManager.createQuery(select, LopHoc.class);
        List<LopHoc> list = query.getResultList();
        return list;
    }

    public LopHoc findById(int id){
        return entityManager.find(LopHoc.class, id);
    }
}
