package lab1.soa.dao;

import lab1.soa.config.HibernateSessionFactoryUtil;
import lab1.soa.entities.HumanBeing;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HumanBeingDao {
    public HumanBeing findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(HumanBeing.class, id);
    }

    public void save(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(humanBeing);
        tx1.commit();
        session.close();
    }

    public void update(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(humanBeing);
        tx1.commit();
        session.close();
    }

    public void delete(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(humanBeing);
        tx1.commit();
        session.close();
    }

    public List<HumanBeing> findAll() {
        return (List<HumanBeing>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HumanBeing").list();
    }

}
