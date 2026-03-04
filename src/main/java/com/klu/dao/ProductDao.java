package com.klu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.klu.entity.Product;
import com.klu.util.HibernateUtil;

public class ProductDao {

    // SAVE PRODUCT
    public void saveProduct(Product p) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();
        session.close();
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery("FROM Product",Product.class);

        return q.list();
    }

    // SORT PRICE ASC
    public List<Product> sortPriceAsc(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product ORDER BY price ASC",Product.class);

        return q.list();
    }

    // SORT PRICE DESC
    public List<Product> sortPriceDesc(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product ORDER BY price DESC",Product.class);

        return q.list();
    }

    // SORT BY QUANTITY
    public List<Product> sortQuantityDesc(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product ORDER BY quantity DESC",Product.class);

        return q.list();
    }

    // PAGINATION FIRST 3
    public List<Product> first3Products(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery("FROM Product",Product.class);

        q.setFirstResult(0);
        q.setMaxResults(3);

        return q.list();
    }

    // PAGINATION NEXT 3
    public List<Product> next3Products(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery("FROM Product",Product.class);

        q.setFirstResult(3);
        q.setMaxResults(3);

        return q.list();
    }

    // COUNT TOTAL PRODUCTS
    public Long countProducts(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Long> q = session.createQuery(
                "SELECT COUNT(*) FROM Product",Long.class);

        return q.uniqueResult();
    }

    // COUNT WHERE QUANTITY > 0
    public Long countAvailable(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Long> q = session.createQuery(
                "SELECT COUNT(*) FROM Product WHERE quantity > 0",Long.class);

        return q.uniqueResult();
    }

    // GROUP BY DESCRIPTION
    public List<Object[]> groupByDescription(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Object[]> q = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        return q.list();
    }

    // MIN MAX PRICE
    public Object[] minMaxPrice(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Object[]> q = session.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product");

        return q.uniqueResult();
    }

    // PRICE RANGE
    public List<Product> priceRange(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE price BETWEEN 5000 AND 50000",Product.class);

        return q.list();
    }

    // LIKE START
    public List<Product> nameStart(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE productName LIKE 'L%'",Product.class);

        return q.list();
    }

    // LIKE END
    public List<Product> nameEnd(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE productName LIKE '%e'",Product.class);

        return q.list();
    }

    // LIKE CONTAIN
    public List<Product> nameContain(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE productName LIKE '%top%'",Product.class);

        return q.list();
    }

    // LENGTH
    public List<Product> nameLength(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE LENGTH(productName)=5",Product.class);

        return q.list();
    }
}