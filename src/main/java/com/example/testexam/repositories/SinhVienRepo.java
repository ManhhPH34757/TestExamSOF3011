package com.example.testexam.repositories;

import com.example.testexam.entities.LopHoc;
import com.example.testexam.entities.SinhVien;
import com.example.testexam.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SinhVienRepo {
    EntityManager entityManager = JpaUtils.getEntityManager();

    public List<SinhVien> getList(){
        String select = "select sv from SinhVien sv";
        TypedQuery<SinhVien> query = entityManager.createQuery(select, SinhVien.class);
        List<SinhVien> list = query.getResultList();
        return list;
    }

    public List<SinhVien> find(String name){
        String select = "select sv from SinhVien sv where sv.hoTen like :name";
        TypedQuery<SinhVien> query = entityManager.createQuery(select, SinhVien.class);
        if (name == null || name.trim().isEmpty()){
            query.setParameter("name", "%%");
        }else {
            query.setParameter("name", "%"+name+"%");
        }
        List<SinhVien> list = query.getResultList();
        return list;
    }

    public void insert(SinhVien sinhVien){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sinhVien);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(SinhVien sinhVien){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(sinhVien);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try {
            entityManager.getTransaction().begin();
            SinhVien sinhVien = findById(id);
            entityManager.remove(sinhVien);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public SinhVien findById(int id){
        return entityManager.find(SinhVien.class, id);
    }
}
