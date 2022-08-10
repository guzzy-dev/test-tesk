package DAO.Impl;

import DAO.ExpressionDAO;
import Model.Expression;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class ExpressionDAOImpl implements ExpressionDAO<Expression> {

    SessionFactory factory;

    public ExpressionDAOImpl() {
        factory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Optional<Expression> getById(Long id) {
        Transaction transaction = factory.getCurrentSession().beginTransaction();
        Expression expression = factory.getCurrentSession().get(Expression.class, id);
        transaction.commit();
        return Optional.of(expression);
    }

    @Override
    public List<Expression> getByResult(double result) {
        Transaction transaction = factory.getCurrentSession().beginTransaction();
        List<Expression> expressionList =  factory.getCurrentSession().createQuery("from Expression e where e.result = " + result).list();
        transaction.commit();
        return expressionList;

    }


    @Override
    public List<Expression> getWithLowerResults(double result) {
        Transaction transaction = factory.getCurrentSession().beginTransaction();
        List<Expression>  expressionList =  factory.getCurrentSession().createQuery("from Expression e where e.result < " + result).list();
        transaction.commit();
        return expressionList;
    }

    @Override
    public List<Expression> getWithHigherResults(double result) {
        Transaction transaction = factory.getCurrentSession().beginTransaction();
        List<Expression>  expressionList = factory.getCurrentSession().createQuery("from Expression e where e.result > " + result).list();
        transaction.commit();
        return expressionList;
    }

    @Override
    public void save(Expression expression) {
        Transaction transaction = factory.getCurrentSession().beginTransaction();
        factory.getCurrentSession().save(expression);
        transaction.commit();

    }
}
